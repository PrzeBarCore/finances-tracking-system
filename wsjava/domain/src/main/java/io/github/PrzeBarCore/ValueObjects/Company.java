package io.github.PrzeBarCore.ValueObjects;

public class Company {
    private NameString name;

    private Company(NameString name) {
        this.name = name;
    }

    public static Company of(NameString name) {
        return new Company(name);
    }

    public NameString getname() {
        return name;
    }
}
