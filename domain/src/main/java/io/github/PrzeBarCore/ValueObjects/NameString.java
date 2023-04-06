package io.github.PrzeBarCore.ValueObjects;

public class NameString {
    private String text;

    protected NameString(){};
    private NameString(String text) {
        this.text = text;
    }


    public static NameString of(String text) {
        return new NameString(text);
    }

    public String getText() {
        return text;
    }
}
