package ua.i.mail100.model;

public enum DistanceType {
    ESTAFETA {
        @Override
        public String toString() {
            return "estafeta";
        }
    },
    SINGLE {
        @Override
        public String toString() {
            return "single";
        }
    }
}
