package com.iproject.aviacom.services;

import java.util.ArrayList;
import java.util.List;
import com.iproject.aviacom.enums.*;

public class CurrentUserService {

    private List<String> needRoles = new ArrayList<>();

    private String role = "";

    public CurrentUserService(String role) {
        this.role = role;
    }

    public CurrentUserService isAdmin() {
        this.needRoles.add(RolesEnum.ADMIN.realAuthName());
        return this;
    }

    public CurrentUserService isCashier() {
        this.needRoles.add(RolesEnum.CASHIER.realAuthName());
        return this;
    }

    public CurrentUserService isAirdrome() {
        this.needRoles.add(RolesEnum.AIRDROME.realAuthName());
        return this;
    }

    public CurrentUserService isBooking() {
        this.needRoles.add(RolesEnum.BOOKING.realAuthName());
        return this;
    }

    public CurrentUserService isPersonnel() {
        this.needRoles.add(RolesEnum.PERSONNEL.realAuthName());
        return this;
    }

    public CurrentUserService isClient() {
        this.needRoles.add(RolesEnum.CLIENT.realAuthName());
        return this;
    }

    public CurrentUserService isNone() {
        this.needRoles.add(RolesEnum.NONE.realAuthName());
        return this;
    }

    public boolean CheckContains() {
        Boolean check = this.needRoles.contains(this.role);
        this.needRoles = new ArrayList<>();
        return check;
    }

}
