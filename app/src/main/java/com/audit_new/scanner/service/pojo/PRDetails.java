package com.audit_new.scanner.service.pojo;

public class PRDetails {
    public String req_no;
    public String req_date;
    public String req_by;
    public String costcenter_id;
    public String remarks;
    public String entity_id;
    public String loc_id;
    public String subloc_id;
    public String req_type;
    public String asset_type;
    public String req_level;
    public String budget_status;
    public String fin_year;

    private String req_id;

    public PRDetails(String req_id) {
        this.req_id = req_id;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public PRDetails(String req_no, String req_date, String req_by, String costcenter_id, String remarks, String entity_id, String loc_id, String subloc_id, String req_type, String asset_type, String req_level, String budget_status, String fin_year) {
        this.req_no = req_no;
        this.req_date = req_date;
        this.req_by = req_by;
        this.costcenter_id = costcenter_id;
        this.remarks = remarks;
        this.entity_id = entity_id;
        this.loc_id = loc_id;
        this.subloc_id = subloc_id;
        this.req_type = req_type;
        this.asset_type = asset_type;
        this.req_level = req_level;
        this.budget_status = budget_status;
        this.fin_year = fin_year;
    }

    public String getReq_no() {
        return req_no;
    }

    public void setReq_no(String req_no) {
        this.req_no = req_no;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public String getReq_by() {
        return req_by;
    }

    public void setReq_by(String req_by) {
        this.req_by = req_by;
    }

    public String getCostcenter_id() {
        return costcenter_id;
    }

    public void setCostcenter_id(String costcenter_id) {
        this.costcenter_id = costcenter_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getSubloc_id() {
        return subloc_id;
    }

    public void setSubloc_id(String subloc_id) {
        this.subloc_id = subloc_id;
    }

    public String getReq_type() {
        return req_type;
    }

    public void setReq_type(String req_type) {
        this.req_type = req_type;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getReq_level() {
        return req_level;
    }

    public void setReq_level(String req_level) {
        this.req_level = req_level;
    }

    public String getBudget_status() {
        return budget_status;
    }

    public void setBudget_status(String budget_status) {
        this.budget_status = budget_status;
    }

    public String getFin_year() {
        return fin_year;
    }

    public void setFin_year(String fin_year) {
        this.fin_year = fin_year;
    }
}
