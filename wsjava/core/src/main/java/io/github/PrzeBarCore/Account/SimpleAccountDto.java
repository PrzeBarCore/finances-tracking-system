package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.Transaction.SimpleTransactionDto;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.List;

public class SimpleAccountDto {
    private Integer id;
    private NameString name;

    SimpleAccountDto(){};

    SimpleAccountDto(Integer id, NameString name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    NameString getName() {
        return name;
    }

    void setId(Integer id) {
        this.id = id;
    }

    void setName(NameString name) {
        this.name = name;
    }
}

