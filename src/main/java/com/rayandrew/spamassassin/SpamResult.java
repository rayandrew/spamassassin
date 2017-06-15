package com.rayandrew.spamassassin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ironlota on 6/15/17.
 */
public class SpamResult {
    private final SimpleIntegerProperty no;
    private final SimpleStringProperty text;
    private final SimpleStringProperty result;

    public SpamResult(int no, String text, String result) {
        this.no = new SimpleIntegerProperty(no);
        this.text = new SimpleStringProperty(text);
        this.result = new SimpleStringProperty(result);
    }

    public int getNo() {
        return no.get();
    }

    public void setNo(int no) {
        this.no.set(no);
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String result) {
        this.result.set(result);
    }
}
