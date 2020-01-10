package com.example.projectphase1;

public class User {
    private String user_Email;
    private String user_Password;

    private  String user_Name;
    private String user_UserName;


    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public void setUser_UserName(String user_UserName) {
        this.user_UserName = user_UserName;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public String getUser_UserName() {
        return user_UserName;
    }

    public User(String user_Email, String user_Password, String user_Name, String user_UserName) {
        this.user_Email = user_Email;
        this.user_Password = user_Password;
        this.user_Name = user_Name;
        this.user_UserName = user_UserName;
    }

    public User()
    {

    }

}
