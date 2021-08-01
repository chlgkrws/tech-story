package com.blogcode.validator;

import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.QnaDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class QnaValidator {
    public void validate(QnaDTO qnaDTO, Errors errors) {
        boolean isValidDType = false;
        boolean isValidMemberId = false;

        for(PostType type : PostType.values()){
            if(qnaDTO.getDType().equals(type)){
                isValidDType = true;
            }
        }

        if(!isValidDType){
            errors.reject("wrongDType","dType is wrong");
        }

    }
}
