package com.haris.notes.oneNote.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class Notes {

    private int noteid;
    @Lob
    private String content;
    private  String ownerName ;

    public Notes(int noteid, String content, String ownerName) {
        this.noteid = noteid;
        this.content = content;
        this.ownerName = ownerName;
    }

    public int getNoteid() {
        return noteid;
    }



    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    @Override
    public String toString() {
        return "Notes{" +
                "noteid=" + noteid +
                ", content='" + content + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

}
