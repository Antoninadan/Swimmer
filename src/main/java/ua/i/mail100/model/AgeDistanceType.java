package ua.i.mail100.model;

public enum AgeDistanceType {
    ADULTS {
        @Override
        public String toString() {
            return "adults";
        }
    },
    CHILDREN {
        @Override
        public String toString() {
            return "children";
        }
    }
}
