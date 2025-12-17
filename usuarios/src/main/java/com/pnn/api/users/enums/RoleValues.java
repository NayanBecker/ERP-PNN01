package com.pnn.api.users.enums;

public enum RoleValues {

    ADMIN(1L),
    FUNCIONARIO(2L);

    private final long roleId;

    RoleValues(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }
}

