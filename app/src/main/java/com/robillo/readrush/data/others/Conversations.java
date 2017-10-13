package com.robillo.readrush.data.others;

/**
 * Created by robinkamboj on 13/10/17.
 */

public class Conversations {

    private String kenText;
    private String editTextHint;
    private String chatPrimary;
    private String chatSecondary;

    public Conversations(String kenText, String editTextHint, String chatPrimary, String chatSecondary) {
        this.kenText = kenText;
        this.editTextHint = editTextHint.equals("NULL")?null:editTextHint;
        this.chatPrimary = chatPrimary.equals("NULL")?null:chatPrimary;
        this.chatSecondary = chatSecondary.equals("NULL")?null:chatSecondary;
    }

    public String getKenText() {
        return kenText;
    }

    public void setKenText(String kenText) {
        this.kenText = kenText;
    }

    public String getEditTextHint() {
        return editTextHint;
    }

    public void setEditTextHint(String editTextHint) {
        this.editTextHint = editTextHint;
    }

    public String getChatPrimary() {
        return chatPrimary;
    }

    public void setChatPrimary(String chatPrimary) {
        this.chatPrimary = chatPrimary;
    }

    public String getChatSecondary() {
        return chatSecondary;
    }

    public void setChatSecondary(String chatSecondary) {
        this.chatSecondary = chatSecondary;
    }
}
