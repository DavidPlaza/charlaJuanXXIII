package model;

import java.time.OffsetDateTime;

public class User {

    private final String code;
    private final String name;

    public User(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
}
