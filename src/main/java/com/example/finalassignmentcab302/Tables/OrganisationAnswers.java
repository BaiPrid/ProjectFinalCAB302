package com.example.finalassignmentcab302.Tables;


public class OrganisationAnswers {

    private int organisationId;
    private String category;
    private String size;
    private String donationOptions;
    private String taxableCategory;
    private boolean donorSpecifies;

    public OrganisationAnswers(int organisationId, String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies){
        this.organisationId = organisationId;
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;

    }

    public OrganisationAnswers(String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies){
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;
    }


    public int getOrganisationId(){
        return organisationId;
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
