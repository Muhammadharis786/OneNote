    package com.haris.notes.oneNote.Model;


    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;

    @Entity

    public class Notes {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int noteid;
        @Lob
        private String content;
        @Column(name = "ownerName")
        private  String ownerName ;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid", nullable = false)
        @JsonIgnore

        private User user; // <-- Yeh field User entity mein 'mappedBy = "user"' se jura h




        public Notes() {

        }



        public int getNoteid() {
            return noteid;
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
        public User getUser() {return user;}

        public void setUser(User user) {this.user = user;}

    }
