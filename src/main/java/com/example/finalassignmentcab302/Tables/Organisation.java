package com.example.finalassignmentcab302.Tables;


public class Organisation {

    private int id;
    private String name;
    private String description;
    private String imgPath;
    private String email;
    private String groupSupported;
    private String userName;
    private String passWord;

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
