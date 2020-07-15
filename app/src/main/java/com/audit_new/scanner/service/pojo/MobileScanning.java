package com.audit_new.scanner.service.pojo;

public class MobileScanning {
  private int id;
  private int loc_id;
  private int subloc_id;
  private int facility_id;
  private int cubicle_id;


  public MobileScanning(int id, int loc_id, int subloc_id, int facility_id, int cubicle_id) {
    this.id = id;
    this.loc_id = loc_id;
    this.subloc_id = subloc_id;
    this.facility_id = facility_id;
    this.cubicle_id = cubicle_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLoc_id() {
    return loc_id;
  }

  public void setLoc_id(int loc_id) {
    this.loc_id = loc_id;
  }

  public int getSubloc_id() {
    return subloc_id;
  }

  public void setSubloc_id(int subloc_id) {
    this.subloc_id = subloc_id;
  }

  public int getFacility_id() {
    return facility_id;
  }

  public void setFacility_id(int facility_id) {
    this.facility_id = facility_id;
  }

  public int getCubicle_id() {
    return cubicle_id;
  }

  public void setCubicle_id(int cubicle_id) {
    this.cubicle_id = cubicle_id;
  }
}