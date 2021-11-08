package com.kavinda.customerbaseapplication;

public class customer {
    String Name ;
    String Address ;
    String Contact ;
    String ID_Card ;
    String Date ;
    String Occupation ;
    String DOB ;
    String SpouseDOB ;

    String Income ;
    String Remarks ;
    String Gender ;
    String Civil_Status ;
    String Child1 ;
    String Child2 ;
    String Child3 ;
    String Child4 ;
    String Policy_name ;
    String Policy_number ;
    String Premium ;
    String Mode ;
    String Term ;

    public customer(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getID_Card() {
        return ID_Card;
    }

    public void setID_Card(String ID_Card) {
        this.ID_Card = ID_Card;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getSpouseDOB() {
        return SpouseDOB;
    }

    public void setSpouseDOB(String spouseDOB) {
        SpouseDOB = spouseDOB;
    }

    public String getIncome() {
        return Income;
    }

    public void setIncome(String income) {
        Income = income;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCivil_Status() {
        return Civil_Status;
    }

    public void setCivil_Status(String civil_Status) {
        Civil_Status = civil_Status;
    }

    public String getChild1() {
        return Child1;
    }

    public void setChild1(String child1) {
        Child1 = child1;
    }

    public String getChild2() {
        return Child2;
    }

    public void setChild2(String child2) {
        Child2 = child2;
    }

    public String getChild3() {
        return Child3;
    }

    public void setChild3(String child3) {
        Child3 = child3;
    }

    public String getChild4() {
        return Child4;
    }

    public void setChild4(String child4) {
        Child4 = child4;
    }

    public String getPolicy_name() {
        return Policy_name;
    }

    public void setPolicy_name(String policy_name) {
        Policy_name = policy_name;
    }

    public String getPolicy_number() {
        return Policy_number;
    }

    public void setPolicy_number(String policy_number) {
        Policy_number = policy_number;
    }

    public String getPremium() {
        return Premium;
    }

    public void setPremium(String premium) {
        Premium = premium;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getExpiary() {
        return Expiary;
    }

    public void setExpiary(String expiary) {
        Expiary = expiary;
    }

    String Expiary ;

    public customer(String name, String address, String contact, String ID_Card, String date, String occupation, String DOB, String spouseDOB, String income, String remarks, String gender, String civil_Status, String child1, String child2, String child3, String child4, String policy_name, String policy_number, String premium, String mode, String term, String expiary) {
        Name = name;
        Address = address;
        Contact = contact;
        this.ID_Card = ID_Card;
        Date = date;
        Occupation = occupation;
        this.DOB = DOB;
        SpouseDOB = spouseDOB;
        Income = income;
        Remarks = remarks;
        Gender = gender;
        Civil_Status = civil_Status;
        Child1 = child1;
        Child2 = child2;
        Child3 = child3;
        Child4 = child4;
        Policy_name = policy_name;
        Policy_number = policy_number;
        Premium = premium;
        Mode = mode;
        Term = term;
        Expiary = expiary;
    }
}
