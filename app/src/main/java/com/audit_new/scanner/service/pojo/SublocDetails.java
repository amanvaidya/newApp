package com.audit_new.scanner.service.pojo;

public class SublocDetails {
    private int subloc_id;
    private String subloc_name;

    public SublocDetails(int subloc_id, String subloc_name) {
        this.subloc_id = subloc_id;
        this.subloc_name = subloc_name;
    }

    public int getSubloc_id() {
        return subloc_id;
    }

    public void setSubloc_id(int subloc_id) {
        this.subloc_id = subloc_id;
    }

    public String getSubloc_name() {
        return subloc_name;
    }

    public void setSubloc_name(String subloc_name) {
        this.subloc_name = subloc_name;
    }
}
