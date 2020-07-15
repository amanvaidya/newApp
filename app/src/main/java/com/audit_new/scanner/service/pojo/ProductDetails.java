package com.audit_new.scanner.service.pojo;

public class ProductDetails {
    private int prod_id;
    private String product_name;
    private String product_desc;
    private String asset_type;
    private String model;
    private String manufacturer;
    private String UOM;
    private String group_Id;
    private String groupName;
    private int mCount;



    public ProductDetails(int prod_id, String product_name, String product_desc, String asset_type, String model, String manufacturer, String UOM, String group_Id, String groupName, int count) {
        this.prod_id = prod_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.asset_type = asset_type;
        this.model = model;
        this.manufacturer = manufacturer;
        this.UOM = UOM;
        this.group_Id = group_Id;
        this.groupName = groupName;
        this.mCount = count;
    }

    public void changeCount(int count){
        mCount = count;
    }
    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getGroup_Id() {
        return group_Id;
    }

    public void setGroup_Id(String group_Id) {
        this.group_Id = group_Id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
