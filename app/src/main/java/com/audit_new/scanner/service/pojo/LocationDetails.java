package com.audit_new.scanner.service.pojo;

public class LocationDetails {
    private int loc_id;
    private String loc_name;

    public LocationDetails(int loc_id, String loc_name) {
        this.loc_id = loc_id;
        this.loc_name = loc_name;
    }

    public int getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(int loc_id) {
        this.loc_id = loc_id;
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }
}
