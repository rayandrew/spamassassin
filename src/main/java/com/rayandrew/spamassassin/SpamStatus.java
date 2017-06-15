package com.rayandrew.spamassassin;

/**
 * Created by ironlota on 6/15/17.
 */
public enum SpamStatus {

    SPAM("S", "spam"), CLEAN("C", "clean");

    private String code;
    private String text;

    private SpamStatus(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static SpamStatus getByCode(String genderCode) {
        for (SpamStatus g : SpamStatus.values()) {
            if (g.code.equals(genderCode)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
