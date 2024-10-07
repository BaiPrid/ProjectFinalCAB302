package com.example.finalassignmentcab302;

public class Question {
    private String Question;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;
    private String Answer5;
    private String Answer6;
    private String Answer7;

    public Question(String question, String answer1, String answer2){
        this.Question = question;
        this.Answer1 = answer1;
        this.Answer2 = answer2;
    }

    public Question(String question, String answer1, String answer2, String answer3, String answer4){
        this.Question = question;
        this.Answer1 = answer1;
        this.Answer2 = answer2;
        this.Answer3 = answer3;
        this.Answer4 = answer4;

    }

    public Question(String question, String answer1, String answer2, String answer3, String answer4, String answer5, String answer6){
        this.Question = question;
        this.Answer1 = answer1;
        this.Answer2 = answer2;
        this.Answer3 = answer3;
        this.Answer4 = answer4;
        this.Answer5 = answer5;
        this.Answer6 = answer6;

    }

    public Question(String question, String answer1, String answer2, String answer3, String answer4, String answer5, String answer6, String answer7){
        this.Question = question;
        this.Answer1 = answer1;
        this.Answer2 = answer2;
        this.Answer3 = answer3;
        this.Answer4 = answer1;
        this.Answer5 = answer5;
        this.Answer6 = answer6;
        this.Answer7 = answer7;

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

    public String getAnswer3() {
        return Answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public String getAnswer5() {
        return Answer5;
    }

    public String getAnswer6() {
        return Answer6;
    }

    public String getAnswer7() {
        return Answer7;
    }
}