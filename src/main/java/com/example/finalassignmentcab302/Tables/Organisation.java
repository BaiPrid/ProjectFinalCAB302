package com.example.finalassignmentcab302.Tables;

/**
 * A simple model class representing an Organisation with an id, name, description, imgpath, email, groupsupported, username and password.
 */
public class Organisation {

    private int id;
    private String name;
    private String description;
    private String imgPath;
    private String email;
    private String groupSupported;
    private String userName;
    private String passWord;

    /**
     * Constructs a new Organisation with the specified id, name, groupsupported, description, imgpath, email, username and password
     * @param id of the organisation
     * @param name of the organisation
     * @param groupSupported by the organisation
     * @param description of the organisation
     * @param imgPath of the organisations image
     * @param email of the organisation
     * @param userName of the organisation
     * @param passWord of the organisation
     */
    public Organisation(int id, String name, String groupSupported, String description, String imgPath, String email, String userName, String passWord){
        this.id = id;
        this.name = name;
        this.groupSupported = groupSupported;
        this.description = description;
        this.imgPath = imgPath;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * Constructs a new Organisation with the specified name, groupsupported, description, imgpath, email, username and password
     * @param name of the organisation
     * @param groupSupported by the organisation
     * @param description of the organisation
     * @param imgPath of the organisations image
     * @param email of the organisation
     * @param userName of the organisation
     * @param passWord of the organisation
     */

    public Organisation(String name, String groupSupported, String description, String imgPath, String email, String userName, String passWord){
        this.name = name;
        this.groupSupported = groupSupported;
        this.description = description;
        this.imgPath = imgPath;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getGroupSupported(){
        return groupSupported;
    }
    public String getDescription(){
        return description;
    }
    public String getImgPath(){
        return imgPath;
    }
    public String getEmail(){
        return email;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassWord(){
        return passWord;
    }



    public void setName(String name){
        this.name = name;
    }
    public void setGroupSupported(String groupSupported){
        this.groupSupported = groupSupported;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setImgPath(String imgPath){
        this.imgPath = imgPath;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }


}
