package com.audit_new.scanner.service.pojo;

public class EmployeeDetails {

    public String emp_code;
    public String emp_name;

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public EmployeeDetails(String emp_code, String emp_name) {
        this.emp_code = emp_code;
        this.emp_name=emp_name;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }
}
