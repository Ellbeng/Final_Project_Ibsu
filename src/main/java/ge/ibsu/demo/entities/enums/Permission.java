package ge.ibsu.demo.entities.enums;

public enum Permission {
    LOAN_READ("loan:read"), LOAN_ADD("loan:add"),USER_READ("user:read"), USER_ADD("user:add");

    Permission(String permission) {
        this.permission = permission;
    }

    private String permission;

    public String getPermission() {
        return permission;
    }
}
