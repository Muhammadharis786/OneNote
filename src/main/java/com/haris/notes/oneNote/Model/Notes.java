package com.haris.notes.oneNote.Model;


import jakarta.persistence.*;

@Entity

public class Notes {
    @Id
    private int noteid;
    @Lob
    private String content;
    @Column(name = "ownerName")
    private  String ownerName ;

    public Notes() {

    }

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
}
