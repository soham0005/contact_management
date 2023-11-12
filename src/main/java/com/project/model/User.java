package com.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(
            unique = true
    )
    private String email;
    private String password;
    @Column(
            length = 500
    )
    private String about;
    private boolean enabled;
    private String role;
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Contact> contactList = new ArrayList<>();

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public User(int id, String name, String email, String password, String about, boolean enanbled, String role, String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.enabled = enanbled;
        this.role = role;
        this.imageUrl = imageUrl;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", enanbled=" + enabled +
                ", role='" + role + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isEnanbled() {
        return enabled;
    }

    public void setEnanbled(boolean enanbled) {
        this.enabled = enanbled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
