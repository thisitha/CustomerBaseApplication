package com.kavinda.customerbaseapplication;

public class payment {
     String Month;
     String  Amount;

     public payment(){

     }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public payment(String month, String amount) {
        Month = month;
        Amount = amount;
    }
}
