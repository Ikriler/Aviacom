package com.iproject.aviacom.enums;

public enum RolesEnum {
    ADMIN {
        public String realAuthName() {
            return "ROLE_ADMIN";
        }
    },
    CASHIER {
        public String realAuthName() {
            return "ROLE_CASHIER";
        }
    },
    AIRDROME {
        public String realAuthName() {
            return "ROLE_AIRDROME";
        }
    },
    BOOKING {
        public String realAuthName() {
            return "ROLE_BOOKING";
        }
    },
    PERSONNEL {
        public String realAuthName() {
            return "ROLE_PERSONNEL";
        }
    },
    NONE {
        public String realAuthName() {
            return "ROLE_ANONYMOUS";
        }
    },
    CLIENT {
        public String realAuthName() {
            return "ROLE_USER";
        }
    };
    public abstract String realAuthName();
}