package io.github.PrzeBarCore.ValueObjects;

public class Description {
    private String text;

    protected Description() {
    }

    ;

    private Description(String text) {
        this.text = text;
    }


    public static Description of(String text) {
        return new Description(text);
    }

    public String getText() {
        return text;
    }
}
