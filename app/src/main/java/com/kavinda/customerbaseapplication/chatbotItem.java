package com.kavinda.customerbaseapplication;

public class chatbotItem {
    String messageText;
    String reciveMode;
    public chatbotItem(String messageText, String reciveMode) {
        this.messageText = messageText;
        this.reciveMode = reciveMode;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getReciveMode() {
        return reciveMode;
    }

    public void setReciveMode(String reciveMode) {
        this.reciveMode = reciveMode;
    }


}
