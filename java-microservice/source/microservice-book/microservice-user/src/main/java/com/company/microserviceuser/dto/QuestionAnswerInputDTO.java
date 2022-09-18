package com.company.microserviceuser.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Question and answer. 
 * @author xindaqi 
 * @since 2020-10-20
 */

public class QuestionAnswerInputDTO implements Serializable{

    @NotNull 
    @NotBlank(message = "参数为空")
    private String questions;

    @NotNull
    @NotBlank(message = "参数为空answer")
    private String answers;

    public void setQuestions(String questions) {
        this.questions = questions;
    }
    public String getQuestions() {
        return questions;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
    public String getAnswers() {
        return answers;
    }

    
}