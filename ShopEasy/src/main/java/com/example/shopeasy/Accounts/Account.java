package com.example.shopeasy.Accounts;

import jakarta.persistence.*;
@Entity
@Table(name = "accounts")
public class Account { //Assigns entity and connects to table name.

    @Id//Identifies the ID in the database table
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Notes that ID values are generated

    //Column identifiers for the table fields
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
    }//Empty account.


    public String getUsername() {
        return username;
    }
    //Username get and setters
    public void setUsername(String username) {
        this.username = username;
    }

    //Account constructor with the needed parameters
    public Account(String email, String firstName, String lastName, String username, String password) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

        this.employeeStatus = false;//Employee status is false by default
        this.notificationPreference = true;//Notification preference is true by default.
    }


    public void setPassword(String password) {
        this.password = password;
    } //Password setters and getters
    public String getPassword() {
        return password;
    }

    public String getEmail() {//Email set and getters
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {//First and last name setter and getters
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentInfo() {//Payment info set and getters
        return paymentInfo;
    }
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    //Setter and getters for Shipping Address, Account ID, and Employee Status
    public String getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer id) {
        this.accountId = id;
    }
    public Boolean getEmployeeStatus() {
        return employeeStatus;
    }
    public void setEmployeeStatus(Boolean employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}