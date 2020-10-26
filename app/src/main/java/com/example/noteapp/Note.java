package com.example.noteapp;

import java.io.Serializable;

public class Note implements Serializable {
    private String title,subTitle;

    public Note() {
    }

    public Note(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
