package com.example.projectphase1;

public class ClassJobFB {
    private String job_img_url;
    private int job_id;
    private  String job_name;
    private double job_ammount;

    public ClassJobFB()
    {


    }
    public ClassJobFB(String job_img_url,int job_id, String job_name, double job_ammount) {
        this.job_id = job_id;
        this.job_name = job_name;

        this.job_ammount = job_ammount;
        this.job_img_url=job_img_url;
    }

    public void setJob_img_url(String job_img_url) {
        this.job_img_url = job_img_url;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public void setJob_ammount(double job_ammount) {
        this.job_ammount = job_ammount;
    }

    public String getJob_img_url() {
        return job_img_url;
    }

    public int getJob_id() {
        return job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public double getJob_ammount() {
        return job_ammount;
    }
}
