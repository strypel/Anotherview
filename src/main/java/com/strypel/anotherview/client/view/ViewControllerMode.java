package com.strypel.anotherview.client.view;

public enum ViewControllerMode {
    OFF ("OFF"),
    RAY_CAST ("RAY_CAST");

    private String TITLE;
    ViewControllerMode(String title) {
        this.TITLE = title;
    }
    public String getTITLE() {
        return TITLE;
    }

    public static ViewControllerMode getNextBy(ViewControllerMode last){
        if(getIndex(last) != -1){
            int nextIndex = getIndex(last) + 1;
            if(nextIndex > values().length - 1){
                nextIndex = 0;
            }

            return values()[nextIndex];
        }
        return last;
    }
    public static ViewControllerMode getPreviousBy(ViewControllerMode last){
        if(getIndex(last) != -1){
            int previousIndex = getIndex(last) - 1;
            if(previousIndex < 0){
                previousIndex = values().length - 1;
            }

            return values()[previousIndex];
        }
        return last;
    }
    public static int getIndex(ViewControllerMode mode){
        for (int i = 0; i <= values().length - 1; i++) {
            if(values()[i].equals(mode)){
                return i;
            }
        }
        return -1;
    }

    public static ViewControllerMode getModeBy(String title){
        for (ViewControllerMode mode : values()) {
            if(mode.getTITLE().equals(title))
                return mode;
        }
        return null;
    }
}
