package com.example.testProjectW.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity(name = "m_user")
public class User {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String rowId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 36)
    private Date created;

    @Column(length = 50)
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 36)
    private Date lastUpd;

    @Column(length = 50)
    private String lastUpdBy;

    @Column(length = 50)
    private String accntNo;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50)
    private String idCardType;

    @Column(length = 13)
    private String idCardNo;

    @Column(length = 50, nullable = false)
    private String phoneNumber;

    @Column(length = 50)
    private String email;

    @Column(length = 50, nullable = false)
    private String role;


    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getLastUpd() {
        return lastUpd;
    }

    public void setLastUpd(Date lastUpd) {
        this.lastUpd = lastUpd;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAccntNo() {
        return accntNo;
    }

    public void setAccntNo(String accntNo) {
        this.accntNo = accntNo;
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

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
