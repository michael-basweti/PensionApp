package com.example.user.pensionapp;

/**
 * Created by user on 7/27/2017.
 */

public class Blog {
    private String account_no,amount,bank,county,dependant1_phone,dependant1name,
    dependant2_name,dependant2phone,first_Name,gender,id,image,monthly,phone,relationship1,
    relationship2,second_Name,select,surname;
    public Blog(){

    }

    public Blog(String account_no, String amount, String bank, String county, String dependant1_phone, String dependant1name, String dependant2_name, String dependant2phone, String first_Name, String gender, String id, String image, String monthly, String phone,
                String relationship1, String relationship2, String second_Name, String select, String surname) {
        this.account_no = account_no;
        this.amount = amount;
        this.bank = bank;
        this.county = county;
        this.dependant1_phone = dependant1_phone;
        this.dependant1name = dependant1name;
        this.dependant2_name = dependant2_name;
        this.dependant2phone = dependant2phone;
        this.first_Name = first_Name;
        this.gender = gender;
        this.id = id;
        this.image = image;
        this.monthly = monthly;
        this.phone = phone;
        this.relationship1 = relationship1;
        this.relationship2 = relationship2;
        this.second_Name = second_Name;
        this.select = select;
        this.surname = surname;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDependant1_phone() {
        return dependant1_phone;
    }

    public void setDependant1_phone(String dependant1_phone) {
        this.dependant1_phone = dependant1_phone;
    }

    public String getDependant1name() {
        return dependant1name;
    }

    public void setDependant1name(String dependant1name) {
        this.dependant1name = dependant1name;
    }

    public String getDependant2_name() {
        return dependant2_name;
    }

    public void setDependant2_name(String dependant2_name) {
        this.dependant2_name = dependant2_name;
    }

    public String getDependant2phone() {
        return dependant2phone;
    }

    public void setDependant2phone(String dependant2phone) {
        this.dependant2phone = dependant2phone;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationship1() {
        return relationship1;
    }

    public void setRelationship1(String relationship1) {
        this.relationship1 = relationship1;
    }

    public String getRelationship2() {
        return relationship2;
    }

    public void setRelationship2(String relationship2) {
        this.relationship2 = relationship2;
    }

    public String getSecond_Name() {
        return second_Name;
    }

    public void setSecond_Name(String second_Name) {
        this.second_Name = second_Name;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
