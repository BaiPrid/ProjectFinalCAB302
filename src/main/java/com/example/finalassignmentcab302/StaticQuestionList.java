package com.example.finalassignmentcab302;

import java.util.ArrayList;
import java.util.List;

public class StaticQuestionList {

    private static final List<Question> questions = new ArrayList<>();

    static {
        questions.add(new Question("What type of charity would you like support?", "Animals", "Orphans", "Environmental","Cancer Patients", "Local Schools", "Poor Countries", "Homeless"));
        questions.add(new Question("Would you rather donate to a for-profit or not-for-profit organisation?", "For Profit", "Not For Profit"));
        questions.add(new Question("What size charity would you like to donate to?", "Local", "Small", "Medium", "Large", "National", "Global"));
        questions.add(new Question("How would you best like the charity to be run?", "Monetary Donation", "Volunteer Work", "Payed Employees", "Hired 3rd Party Corporations"));
        questions.add(new Question("Would you like your donations to be tax deductible?", "Tax Deductible", "Not Tax Deductible"));
        questions.add(new Question("How much control would you like over your donation?", "You Choose", "Organisation Chooses"));
    }

    public static List<Question> getQuestions() {
        return questions;
    }
}