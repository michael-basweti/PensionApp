package com.example.user.pensionapp;

/**
 * Created by user on 7/31/2017.
 */

public class Blog1 {
    private String Date,selection,first_Name,surname,phone,monthly,bank,account_no;
    public Blog1(){

    }

    public Blog1(String date, String selection, String first_Name,
                 String surname, String phone, String monthly, String bank, String account_no) {
        Date = date;
        this.selection = selection;
        this.first_Name = first_Name;
        this.surname = surname;
        this.phone = phone;
        this.monthly = monthly;
        this.bank = bank;
        this.account_no = account_no;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }
}
