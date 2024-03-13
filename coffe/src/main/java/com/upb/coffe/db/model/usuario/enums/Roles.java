package com.upb.coffe.db.model.usuario.enums;

public enum Roles {
    ROL_ADMIN("ADMIN"), ROL_CLIENTE("CLIENTE"), ROL_TRABAJADOR("TRABAJADOR");

    private final String rol;

    Roles(String rol) {
        this.rol = rol;
    }

}
