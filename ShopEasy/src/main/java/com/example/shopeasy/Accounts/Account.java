package com.example.shopeasy.Accounts;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
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

    public void setAccountId(Integer id) {
        this.accountId = id;
    }
    public Integer getAccountId() {
        return accountId;
    }
}