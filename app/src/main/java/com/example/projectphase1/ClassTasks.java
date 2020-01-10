package com.example.projectphase1;

public class ClassTasks {
    private int jobId;
    private String jobName;
    private String jobPhoto;
    private double jobammount;


    public ClassTasks(int jobId, String jobName, String jobPhoto,double jobammount) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobPhoto = jobPhoto;
        this.jobammount=jobammount;
    }

    public double getJobammount() {
        return jobammount;
    }

    public void setJobammount(double jobammount) {
        this.jobammount = jobammount;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobPhoto(String jobPhoto) {
        this.jobPhoto = jobPhoto;
    }

    public int getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobPhoto() {
        return jobPhoto;
    }
}
