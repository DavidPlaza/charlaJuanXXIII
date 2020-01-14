package model;

import java.time.OffsetDateTime;

public class AdminUser {

    private final String code;
    private final boolean admin;
    private final String codeAdminUpdate;
    private final OffsetDateTime dateUpdate;

    public AdminUser(String code, boolean admin, String codeAdminUpdate, OffsetDateTime dateUpdate) {
        this.code = code;
        this.admin = admin;
        this.codeAdminUpdate = codeAdminUpdate;
        this.dateUpdate = dateUpdate;
    }

    public String getCode() {
        return code;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getCodeAdminUpdate() {
        return codeAdminUpdate;
    }

    public OffsetDateTime getDateUpdate() {
        return dateUpdate;
    }
}
