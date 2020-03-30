package ua.i.mail100.model;

public enum UserStatus {
    ACTIVE {
        @Override
        public String toString() {
            return "active";
        }
    },
    BLOCKED {
        @Override
        public String toString() {
            return "blocked";
        }
    }
}
