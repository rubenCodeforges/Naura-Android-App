package com.codeforges.app.naura.models;


public class NauraData implements EntityInterface {
    private long id;
    private String itemTitle;
    private String itemData;
    private String itemImages;
    private int uploaded;

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

    public String getItemData() {
        return itemData;
    }

    public void setItemData(String itemData) {
        this.itemData = itemData;
    }

    public String getItemImages() {
        return itemImages;
    }

    public void setItemImages(String itemImages) {
        this.itemImages = itemImages;
    }

    public int getUploaded() {
        return uploaded;
    }

    public void setUploaded(int uploaded) {
        this.uploaded = uploaded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NauraData)) return false;

        NauraData nauraData = (NauraData) o;

        if (id != nauraData.id) return false;
        if (uploaded != nauraData.uploaded) return false;
        if (itemData != null ? !itemData.equals(nauraData.itemData) : nauraData.itemData != null)
            return false;
        if (itemImages != null ? !itemImages.equals(nauraData.itemImages) : nauraData.itemImages != null)
            return false;
        if (itemTitle != null ? !itemTitle.equals(nauraData.itemTitle) : nauraData.itemTitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (itemTitle != null ? itemTitle.hashCode() : 0);
        result = 31 * result + (itemData != null ? itemData.hashCode() : 0);
        result = 31 * result + (itemImages != null ? itemImages.hashCode() : 0);
        result = 31 * result + uploaded;
        return result;
    }

    @Override
    public String toString() {
        return "NauraData{" +
                "id=" + id +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemData='" + itemData + '\'' +
                ", itemImages='" + itemImages + '\'' +
                ", uploaded=" + uploaded +
                '}';
    }
}
