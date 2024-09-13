package com.example.finalassignmentcab302.Tables;

public class UserAnswers {

    private int userId;
    private String category;
    private String size;
    private String donationOptions;
    private String taxableCategory;
    private boolean donorSpecifies;
    private String userAns1;
    private String userAns2;
    private String userAns3;

    public UserAnswers(int userId, String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies, String userAns1, String userAns2, String userAns3){
        this.userId = userId;
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;
        this.userAns1 = userAns1;
        this.userAns2 = userAns2;
        this.userAns3 = userAns3;
    }

    public UserAnswers(String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies, String userAns1, String userAns2, String userAns3){
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;
        this.userAns1 = userAns1;
        this.userAns2 = userAns2;
        this.userAns3 = userAns3;
    }

    public int getUserId(){
        return userId;
    }
    public String getCategory(){
        return category;
    }
    public String getSize(){
        return size;
    }
    public String getDonationOptions(){
        return donationOptions;
    }
    public String getTaxableCategory(){
        return taxableCategory;
    }
    public Boolean getDonorSpecifies(){
        return donorSpecifies;
    }
    public String getUserAns1(){return userAns1;}
    public String getUserAns2(){return userAns2;}
    public String getUserAns3(){return userAns3;}

    public void setCategory(String category){
        this.category = category;
    }
    public void setSize(String size){
        this.size = size;
    }
    public void setDonationOptions(String donationOptions){
        this.donationOptions = donationOptions;
    }
    public void setTaxableCategory(String taxableCategory){
        this.taxableCategory = taxableCategory;
    }
    public void setDonorSpecifies(Boolean donorSpecifies){
        this.donorSpecifies = donorSpecifies;
    }
    public void setUserAns1(String userAns1) {this.userAns1 = userAns1;}
    public void setUserAns2(String userAns2) {this.userAns2 = userAns2;}
    public void setUserAns3(String userAns3) {this.userAns3 = userAns3;}
}

