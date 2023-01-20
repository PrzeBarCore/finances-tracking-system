package io.github.PrzeBarCore.Account.Dto;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;

public class AccountDto {
    private int id;
    private NameString name;
    private MonetaryAmount balance;
    private SimpleCurrency currency;
    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return this.id;
    }

    public NameString getName() {
        return name;
    }

    public MonetaryAmount getBalance() {
        return balance;
    }

    public SimpleCurrency getCurrency() {
        return currency;
    }

    public static class Builder{
        private int id;
        private NameString name;
        private MonetaryAmount balance;
        private SimpleCurrency currency;

        private Builder(){};

        public Builder withAccountName(NameString name) {
            this.name=name;
            return this;
        }

        public Builder withBalance(MonetaryAmount monetaryAmount) {
            this.balance=monetaryAmount;
            return this;
        }

        public Builder withCurrency(SimpleCurrency simpleCurrency) {
            this.currency=simpleCurrency;
            return this;
        }

        public AccountDto build() {
            var builtDto=new AccountDto();
            builtDto.id=this.id;
            builtDto.name=this.name;
            builtDto.balance=this.balance;
            builtDto.currency=this.currency;
            return builtDto;
        }
    }
}
