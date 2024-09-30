package com.example.finalassignmentcab302.Tables;


public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private Integer phoneNumber;
    private String economicClass;


    public User(int id, String firstName, String lastName, String userName, String password, String email, Integer phoneNumber, String economicClass){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.economicClass = economicClass;
    }

    public User(String firstName, String lastName, String userName, String password, String email, Integer phoneNumber, String economicClass){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.economicClass = economicClass;
    }

    ////////////////////NEW SECTION////////////////////
    //User constructor for just username and password
    public User(String username, String password){
        this.userName = username;
        this.password = password;
    }
    ///////////////////////////////////////////////////

    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public Integer getPhoneNumber(){
        return phoneNumber;
    }

    public String getEconomicClass(){
        return economicClass;
    }


    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhoneNumber(Integer phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setEconomicClass(String economicClass){
        this.economicClass = economicClass;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", economicClass='" + economicClass + '\'' +
                ", password=" + password +
                '}';
    }
}
