package com.audit_new.scanner.service.pojo;

public class SaveGivenData {

    public int location;
    public int sublocation;
    public int facility;
    public int cubicle;
    public String audit_name;
    public String initiator;
    public int group_id;
    public int subgroup_id;
    public String remarks;
    public int auditor_id;
    public String auditor_code;
    public int comp_id;
    public String bm_code;
    public String serialno;
    public String asset_id;
    public String date;

    public SaveGivenData(int location, int sublocation, int facility, int cubicle, String audit_name, String initiator, int group_id, int subgroup_id, String remarks, int auditor_id, String auditor_code, int comp_id, String bm_code, String serialno, String asset_id, String date) {
        this.location = location;
        this.sublocation = sublocation;
        this.facility = facility;
        this.cubicle = cubicle;
        this.audit_name = audit_name;
        this.initiator = initiator;
        this.group_id = group_id;
        this.subgroup_id = subgroup_id;
        this.remarks = remarks;
        this.auditor_id = auditor_id;
        this.auditor_code = auditor_code;
        this.comp_id = comp_id;
        this.bm_code = bm_code;
        this.serialno = serialno;
        this.asset_id=asset_id;
        this.date=date;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
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

    public String getAudit_name() {
        return audit_name;
    }

    public void setAudit_name(String audit_name) {
        this.audit_name = audit_name;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getSubgroup_id() {
        return subgroup_id;
    }

    public void setSubgroup_id(int subgroup_id) {
        this.subgroup_id = subgroup_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getAuditor_id() {
        return auditor_id;
    }

    public void setAuditor_id(int auditor_id) {
        this.auditor_id = auditor_id;
    }

    public String getAuditor_code() {
        return auditor_code;
    }

    public void setAuditor_code(String auditor_code) {
        this.auditor_code = auditor_code;
    }

    public int getComp_id() {
        return comp_id;
    }

    public void setComp_id(int comp_id) {
        this.comp_id = comp_id;
    }

    public String getBm_code() {
        return bm_code;
    }

    public void setBm_code(String bm_code) {
        this.bm_code = bm_code;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}
