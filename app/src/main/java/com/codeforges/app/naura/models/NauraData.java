package com.codeforges.app.naura.models;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NauraData)) return false;

        NauraData nauraData = (NauraData) o;

        if (id != nauraData.id) return false;
        if (!itemTitle.equals(nauraData.itemTitle)) return false;
        if (!objectForm.equals(nauraData.objectForm)) return false;
        if (!objectStructureForm.equals(nauraData.objectStructureForm)) return false;
        if (!otherContacts.equals(nauraData.otherContacts)) return false;
        if (!ownerName.equals(nauraData.ownerName)) return false;
        if (!ownerPhone.equals(nauraData.ownerPhone)) return false;
        if (!photoUrls.equals(nauraData.photoUrls)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + itemTitle.hashCode();
        result = 31 * result + ownerName.hashCode();
        result = 31 * result + ownerPhone.hashCode();
        result = 31 * result + otherContacts.hashCode();
        result = 31 * result + objectForm.hashCode();
        result = 31 * result + objectStructureForm.hashCode();
        result = 31 * result + photoUrls.hashCode();
        return result;
    }
}
