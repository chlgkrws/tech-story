package com.blogcode.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

@Component
@Aspect
@Log4j2
public class RequestLoggingAspect {

    @Around("within(com.blogcode..*))") // ex. within(me.shinsunyoung.demo..*)) 1
    public Object logging(ProceedingJoinPoint pjp) throws Throwable { // 2

        String params = getRequestParams(); // request 값 가져오기

        Class cls = pjp.getTarget().getClass();
        String reqUrl = getRequestUrl(pjp, cls);

        log.info("START {}",reqUrl);
        //log.info("-----------> REQUEST URI : {}",reqUrl);
        long startAt = System.currentTimeMillis();

        log.info("-----------> REQUEST : {}({}) = {}", pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(), params);

        Object result = pjp.proceed(); // 4

        long endAt = System.currentTimeMillis();

        log.info("-----------> RESPONSE : {}({}) = {} ({}ms)", pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(), result, endAt - startAt);
        log.info("End {}",reqUrl);
        return result;
    }

    private String paramMapToString(Map<String, String[]> paramMap) {
//		return paramMap.entrySet().stream()
//				.map(entry -> String.format("%s -> (%s)", entry.getKey(), Joiner.on(",").join(entry.getValue())))
//				.collect(Collectors.joining(", "));
        String result = "";
        for(Entry<String, String[]> et : paramMap.entrySet()) {
            result += " " + et.getKey() +" -> [" + Arrays.toString(et.getValue())+"] || ";
        }
        return result;
    }

    // Get request values
    private String getRequestParams() {

        String params = "없음";

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes(); // 3

        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();

            Map<String, String[]> paramMap = request.getParameterMap();
            if (!paramMap.isEmpty()) {
                params = " [" + paramMapToString(paramMap) + "]";
            }
        }

        return params;
    }

    private String getRequestUrl(JoinPoint joinPoint, Class cls) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestMapping requestMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
        String baseUrl = requestMapping.value()[0];

        String url = Stream.of( GetMapping.class, PutMapping.class, PostMapping.class,
                PatchMapping.class, DeleteMapping.class, RequestMapping.class)
                .filter(mappingClass -> method.isAnnotationPresent(mappingClass))
                .map(mappingClass -> getUrl(method, mappingClass, baseUrl))
                .findFirst().orElse(null);
        return url;
    }

    /* httpMETHOD + requestURI 를 반환 */
    private String getUrl(Method method, Class<? extends Annotation> annotationClass, String baseUrl){
        Annotation annotation = method.getAnnotation(annotationClass);
        String[] value;
        String httpMethod = null;
        try {
            value = (String[])annotationClass.getMethod("value").invoke(annotation);
            httpMethod = (annotationClass.getSimpleName().replace("Mapping", "")).toUpperCase();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return null;
        }
        return String.format("%s %s%s", httpMethod, baseUrl, value.length > 0 ? value[0] : "") ;
    }
}
