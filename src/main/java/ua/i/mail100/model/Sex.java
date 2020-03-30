package ua.i.mail100.model;

public enum Sex {
    MALE {
        @Override
        public String toString() {
            return "male";
        }
    },
    FEMALE {
        @Override
        public String toString() {
            return "female";
        }
    }
}


//    OPEN("open"),
//    TO_BE_CLOSED("tobeclosed"),
//    CLOSED("closed");
//
//    private final String name;
//
//    Status(String name) {
//        this.name = name;
//    }
//
//    public Status getByValue(String value){
//        return Status.valueOf(value);
//    }