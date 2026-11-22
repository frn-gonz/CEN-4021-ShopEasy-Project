package com.example.shopeasy.Accounts;

import jakarta.persistence.*;
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountID")
    private Integer accountId;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "firstname", length = 50)
    private String firstName;

    @Column(name = "lastname", length = 50)
    private String lastName;

    @Column(name = "password", length = 25, nullable = false)
    private String password;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "paymentinfo", length = 16)
    private String paymentInfo;

    @Column(name = "shippingaddress", length = 255)
    private String shippingAddress;

    @Column(name = "employeestatus")
    private Boolean employeeStatus;

    @Column(name = "employeeID")
    private Integer employeeId;

    @Column(name = "notification_preference")
    private Boolean notificationPreference;

    public Account() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Account(String email,
                   String firstName,
                   String lastName,
                   String username,
                   String password) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

        this.employeeStatus = false;
        this.notificationPreference = true;
    }

    public void setAccountId(Integer id) {
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // Getters and setters...
}