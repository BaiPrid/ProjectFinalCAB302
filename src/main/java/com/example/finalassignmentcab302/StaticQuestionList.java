package com.example.finalassignmentcab302;

import java.util.ArrayList;
import java.util.List;

public class StaticQuestionList {

    private static final List<Question> questions = new ArrayList<>();

    static {
        questions.add(new Question("Would you rather donate to a charity that helps people or animals", "People", "Animals"));
        questions.add(new Question("Would you rather donate a lot of money or a little bit of money", "A lot", "A little"));
        questions.add(new Question("Would you rather donate to a large charity or a small charity", "Large", "Small"));
    }

    public static List<Question> getQuestions() {
        return questions;
    }
}