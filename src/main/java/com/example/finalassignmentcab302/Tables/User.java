package com.example.finalassignmentcab302.Tables;

/**
 * A simple model class representing a User with an id, first name, last name, username, password, email, phonenumber and economomic class.
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private Integer phoneNumber;
    private String economicClass;


    /**
     * Constructs a new User with the specified id, first name, last name, username, password, email, phonenumber and economomic class.
     * @param id of the User
     * @param firstName of the User
     * @param lastName of the User
     * @param userName of the User
     * @param password of the User
     * @param email of the User
     * @param phoneNumber of the User
     * @param economicClass of the User
     */

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

    /**
     * Constructs a new User with the specified first name, last name, username, password, email, phonenumber and economomic class.
     * @param firstName of the User
     * @param lastName of the User
     * @param userName of the User
     * @param password of the User
     * @param email of the User
     * @param phoneNumber of the User
     * @param economicClass of the User
     */

    public User(String firstName, String lastName, String userName, String password, String email, Integer phoneNumber, String economicClass){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.economicClass = economicClass;
    }


    /**
     * Constructs a new User with the specified username and password for login only purposes
     * @param username of the User
     * @param password of the User

     */

    public User(String username, String password){
        this.userName = username;
        this.password = password;
    }


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
