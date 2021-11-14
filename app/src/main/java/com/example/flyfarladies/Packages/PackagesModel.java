package com.example.flyfarladies.Packages;

public class PackagesModel {
    String pkId;
    String pkCost;
    String pkDetails;
    String pkDuration;
    String pkImage;
    String pkName;


    public PackagesModel() {
    }

    public PackagesModel(String pkId, String pkCost, String pkDetails, String pkDuration, String pkImage, String pkName) {
        this.pkCost = pkCost;
        this.pkDetails = pkDetails;
        this.pkDuration = pkDuration;
        this.pkImage = pkImage;
        this.pkName = pkName;
        this.pkId = pkId;
    }

    public String getPkCost() {
        return pkCost;
    }

    public void setPkCost(String pkCost) {
        this.pkCost = pkCost;
    }

    public String getPkDetails() {
        return pkDetails;
    }

    public void setPkDetails(String pkDetails) {
        this.pkDetails = pkDetails;
    }

    public String getPkDuration() {
        return pkDuration;
    }

    public void setPkDuration(String pkDuration) {
        this.pkDuration = pkDuration;
    }

    public String getPkImage() {
        return pkImage;
    }

    public void setPkImage(String pkImage) {
        this.pkImage = pkImage;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }
}
