package com.example.projectphase1;

public class ClassMyTasksFB {
    private String job_img_url;
    private int job_id;
    private  String job_name;
    private double job_ammount;
    private String mytask_id;
    private String mytask_description;
    private String myTask_username;
    private String myTask_location;
    private String myTask_date;

    public String getJob_img_url() {
        return job_img_url;
    }

    public void setJob_img_url(String job_img_url) {
        this.job_img_url = job_img_url;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public double getJob_ammount() {
        return job_ammount;
    }

    public void setJob_ammount(double job_ammount) {
        this.job_ammount = job_ammount;
    }

    public ClassMyTasksFB(String img, int i, String n, double a, String mytask_id, String mytask_description, String myTask_username, String myTask_location, String myTask_date) {

        this.job_img_url=img;
        job_id=i;
        job_name=n;
        job_ammount=a;
        this.mytask_id = mytask_id;
        this.mytask_description = mytask_description;
        this.myTask_username = myTask_username;
        this.myTask_location = myTask_location;
        this.myTask_date = myTask_date;
    }

    public String getMytask_id() {
        return mytask_id;
    }

    public void setMytask_id(String mytask_id) {
        this.mytask_id = mytask_id;
    }

    public String getMytask_description() {
        return mytask_description;
    }

    public void setMytask_description(String mytask_description) {
        this.mytask_description = mytask_description;
    }


    public String getMyTask_username() {
        return myTask_username;
    }

    public void setMyTask_username(String myTask_username) {
        this.myTask_username = myTask_username;
    }

    public String getMyTask_location() {
        return myTask_location;
    }

    public void setMyTask_location(String myTask_location) {
        this.myTask_location = myTask_location;
    }

    public String getMyTask_date() {
        return myTask_date;
    }

    public void setMyTask_date(String myTask_date) {
        this.myTask_date = myTask_date;
    }
}
