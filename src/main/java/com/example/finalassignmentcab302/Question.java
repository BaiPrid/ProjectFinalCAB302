package com.example.finalassignmentcab302;

public class Question {
    private String Question;
    private String Answer1;
    private String Answer2;

    public Question(String question, String answer1, String answer2){
        this.Question = question;
        this.Answer1 = answer1;
        this.Answer2 = answer2;
    }

    public String getQuestion(){
        return Question;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }
}