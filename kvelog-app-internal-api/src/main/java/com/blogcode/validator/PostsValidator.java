package com.blogcode.validator;

import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.domain.Posts;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PostsValidator {
    public void validate(Posts post, Errors errors) {
        boolean isValidDType = false;

        for(PostType type : PostType.values()){
            if(post.getDType().equals(type)){
                isValidDType = true;
            }
        }

        if(!isValidDType){
            errors.reject("wrongDType","dType is wrong");
        }

    }
}
