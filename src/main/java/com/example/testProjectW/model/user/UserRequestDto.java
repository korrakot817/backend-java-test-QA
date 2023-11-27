package com.example.testProjectW.model.user;

import com.example.testProjectW.model.RelateParty;

public class UserRequestDto {

    private String fistName;

    private String lastName;

    private String idCardType;

    private String IdCardNo;

    private String phoneNumber;

    private String email;

    private String role;

    private RelateParty relateParty;


    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
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
        return IdCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        IdCardNo = idCardNo;
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

    public RelateParty getRelateParty() {
        return relateParty;
    }

    public void setRelateParty(RelateParty relateParty) {
        this.relateParty = relateParty;
    }
}
