package com.kavinda.customerbaseapplication;

public class prospector {
    String Address;
    String Contact;
    String DOB;
    String Date;
    String Dependents;
    String Income;
    String Name;
    String Remarks;
    String appointmentAddress;
    String appointmentTime;

    public String getAppointmentAddress() {
        return appointmentAddress;
    }

    public void setAppointmentAddress(String appointmentAddress) {
        this.appointmentAddress = appointmentAddress;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    String appointmentDate;
String Occupation;

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public prospector(){

    }




    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDependents() {
        return Dependents;
    }

    public void setDenendents(String Dependents) {
        Dependents = Dependents;
    }

    public String getIncome() {
        return Income;
    }

    public void setIncome(String income) {
        Income = income;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public prospector(String address, String contact, String DOB, String date, String dependents, String income, String name, String remarks,String appointmentAddress,String appointmentTime,String appointmentDate,String occupation) {
        this.Address = address;
        this.Contact = contact;
        this.DOB = DOB;
        this.Date = date;
        this.Dependents = Dependents;
        this.Income = income;
        this.Name = name;
        this.Remarks = remarks;
        this.appointmentAddress = appointmentAddress;
        this.Occupation = occupation;
        this.appointmentTime = appointmentTime;
        this.appointmentTime = appointmentDate;
    }
}
