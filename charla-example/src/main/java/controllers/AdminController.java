package controllers;

import errors.BusinessError;
import services.AdminService;

public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public String createAdmin(String cod, String codRequester) {
        return adminService.createAdmin(cod, codRequester)
                .map(__ -> "User added!")
                .getOrElseGet(BusinessError::getMessage);
    }

    public String deleteAdmin(String cod, String codRequester) {
        return adminService.deleteAdmin(cod, codRequester)
                .map(__ -> "User deleted!")
                .getOrElseGet(BusinessError::getMessage);
    }
}
