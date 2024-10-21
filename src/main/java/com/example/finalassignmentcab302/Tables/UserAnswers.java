package com.example.finalassignmentcab302.Tables;
/**
 * A simple model class representing a Users answers with a userId, category, size, donationoptions, taxablecategory and donorspecifies.
 */

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

    /**
     * Constructs a new UserAnswers with the specified category, size, donationoptions, taxablecategory and donorspecifies.
     * @param category of the UserAnswer
     * @param size of the UserAnswer
     * @param donationOptions of the UserAnswer
     * @param taxableCategory of the UserAnswer
     * @param donorSpecifies of the UserAnswer
     */
    public UserAnswers(String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies){
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;

    }

    public UserAnswers(String userAns1, String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies){
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;
        this.userAns1 = userAns1;
    }


    /**
     * Constructs a new UserAnswers with the specified userId, category, size, donationoptions, taxablecategory and donorspecifies.
     * @param userId of the UserAnswer
     * @param category of the UserAnswer
     * @param size of the UserAnswer
     * @param donationOptions of the UserAnswer
     * @param taxableCategory of the UserAnswer
     * @param donorSpecifies of the UserAnswer
     */
    public UserAnswers(int userId, String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies){
        this.userId = userId;
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;
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

}

