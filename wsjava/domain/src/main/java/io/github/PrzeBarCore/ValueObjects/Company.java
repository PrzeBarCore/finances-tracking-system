package io.github.PrzeBarCore.ValueObjects;

public class Company {
    private String name;
    protected Company(){}
    private Company(String name) {
        this.name = name;
    }
    public static Company of(String name) {
        return new Company(name);
    }
    public String getText() {
        return name;
    }
}
