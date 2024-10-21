package com.example.finalassignmentcab302.Tables;

/**
 * A simple model class representing an Organisations answers to the registration quiz with an organisationId, category, size, donationOptions, taxableCategory and donordpecifies.
 */
public class OrganisationAnswers {

    private int organisationId;
    private String category;
    private String size;
    private String donationOptions;
    private String taxableCategory;
    private boolean donorSpecifies;

    /**
     * Constructs a new OrganisationAnswers with the specified organisationId, category, size, donationOptions, taxableCategory and donordpecifies.
     * @param organisationId of the organisation
     * @param category of the organisation
     * @param size of the organisation
     * @param donationOptions of the organisation
     * @param taxableCategory of the organisation
     * @param donorSpecifies option of the organisation
     */
    public OrganisationAnswers(int organisationId, String category, String size, String donationOptions, String taxableCategory, Boolean donorSpecifies){
        this.organisationId = organisationId;
        this.category = category;
        this.size = size;
        this.donationOptions = donationOptions;
        this.taxableCategory = taxableCategory;
        this.donorSpecifies = donorSpecifies;

    }

    /**
     * Constructs a new OrganisationAnswers with the specified category, size, donationOptions, taxableCategory and donordpecifies.
     * @param category of the organisation
     * @param size of the organisation
     * @param donationOptions of the organisation
     * @param taxableCategory of the organisation
     * @param donorSpecifies option of the organisation
     */
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
