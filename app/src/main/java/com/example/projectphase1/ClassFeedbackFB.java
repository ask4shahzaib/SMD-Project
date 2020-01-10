package com.example.projectphase1;

public class ClassFeedbackFB {
    private String usernamee;
    String feedbacke;

    public ClassFeedbackFB(String username, String feedback) {
        this.usernamee = username;
        this.feedbacke = feedback;
    }

    public String getUsername() {
        return usernamee;
    }

    public void setUsername(String username) {
        this.usernamee = username;
    }

    public String getFeedback() {
        return feedbacke;
    }

    public void setFeedback(String feedback) {
        this.feedbacke = feedback;
    }
}
