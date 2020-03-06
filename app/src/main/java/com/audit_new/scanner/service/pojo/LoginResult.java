package com.audit_new.scanner.service.pojo;

public class LoginResult {

    public String emp_id;
    public String emp_name;
    public String auth_token;


    public LoginResult(String emp_id, String emp_name, String auth_token) {

        this.emp_name = emp_name;
        this.emp_id = emp_id;
        this.auth_token=auth_token;


    }

    public String getAuth_code() {
        return auth_token;
    }

    public void setAuth_code(String auth_code) {
        this.auth_token = auth_code;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }


}
