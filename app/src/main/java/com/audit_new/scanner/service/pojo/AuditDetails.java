package com.audit_new.scanner.service.pojo;

public class AuditDetails {

    public int location;
    public int sublocation;
    public int facility;
    public int cubicle;
    public String initiator;
    public String audit_name;

    public AuditDetails(int location, int sublocation, int facility, int cubicle, String initiator, String audit_name) {
        this.location = location;
        this.sublocation = sublocation;
        this.facility = facility;
        this.cubicle = cubicle;
        this.initiator = initiator;
        this.audit_name = audit_name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getSublocation() {
        return sublocation;
    }

    public void setSublocation(int sublocation) {
        this.sublocation = sublocation;
    }

    public int getFacility() {
        return facility;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    public int getCubicle() {
        return cubicle;
    }

    public void setCubicle(int cubicle) {
        this.cubicle = cubicle;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getAudit_name() {
        return audit_name;
    }

    public void setAudit_name(String audit_name) {
        this.audit_name = audit_name;
    }
}
