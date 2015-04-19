package com.example.ruben.webapp.Models;

/**
 * Created by Codeforges on 18.04.15.
 */
public class NauraData {
    private long id;
    private String itemTitle;
    private String ownerName;
    private String ownerPhone;
    private String otherContacts;
    private String objectForm;
    private String objectStructureForm;
    private String photoUrls;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOtherContacts() {
        return otherContacts;
    }

    public void setOtherContacts(String otherContacts) {
        this.otherContacts = otherContacts;
    }

    public String getObjectForm() {
        return objectForm;
    }

    public void setObjectForm(String objectForm) {
        this.objectForm = objectForm;
    }

    public String getObjectStructureForm() {
        return objectStructureForm;
    }

    public void setObjectStructureForm(String objectStructureForm) {
        this.objectStructureForm = objectStructureForm;
    }

    public String getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String photoUrls) {
        this.photoUrls = photoUrls;
    }

    @Override
    public String toString() {
        return itemTitle;
    }

}
