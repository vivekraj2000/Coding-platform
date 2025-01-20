package com.letsCode.codingPlatform.enums;

public enum UserRoleEnum {
    ADMIN("ADMIN"), USER("USER");

    private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRoleString() {
        return role;
    }

    @Override
    public String toString() {
        return String.valueOf(role);
    }
}
