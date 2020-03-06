package com.audit_new.scanner.service.remote;


import com.audit_new.scanner.service.DAO.AuditDetailsDao;
import com.audit_new.scanner.service.DAO.EmployeeDao;
import com.audit_new.scanner.service.DAO.FacilityDao;
import com.audit_new.scanner.service.DAO.LoginDAO;
import com.audit_new.scanner.service.DAO.MobileScanningDAO;
import com.audit_new.scanner.service.DAO.MobileScanningDetailsDao;
import com.audit_new.scanner.service.DAO.SaveAuditDetails;

public class ApiUtils {
    //public static final String BASE_URL = "http://supportweb.in:8080/iciciMobile-A/";

    public static String BASE_URL = "";

    public static LoginDAO getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(LoginDAO.class);
    }

    public static FacilityDao getFacilityService() {
        return RetrofitClient.getClient(BASE_URL).create(FacilityDao.class);
    }

    public static AuditDetailsDao getAuditDetails() {
        return RetrofitClient.getClient(BASE_URL).create(AuditDetailsDao.class);
    }

    public static MobileScanningDAO getMobileDetails() {
        return RetrofitClient.getClient(BASE_URL).create(MobileScanningDAO.class);
    }

    public static MobileScanningDetailsDao saveMobileDetails() {
        return RetrofitClient.getClient(BASE_URL).create(MobileScanningDetailsDao.class);
    }

    public static EmployeeDao getEmpCode() {
        return RetrofitClient.getClient(BASE_URL).create(EmployeeDao.class);
    }

    public static SaveAuditDetails saveAudit() {
        return RetrofitClient.getClient(BASE_URL).create(SaveAuditDetails.class);
    }
}
