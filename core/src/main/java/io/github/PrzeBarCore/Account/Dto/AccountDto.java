package io.github.PrzeBarCore.Account.Dto;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;

public class AccountDto {
    private Integer id;
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

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object dto) {
        if(dto instanceof AccountDto){
            return ((AccountDto) dto).id==this.id
                    && ((AccountDto) dto).currency == this.currency
                    && ((AccountDto) dto).balance == this.balance
                    && ((AccountDto) dto).name.equals(this.name);
        } else
            return false;
    }

    public static class Builder{
        private int id;
        private NameString name;
        private MonetaryAmount balance;
        private SimpleCurrency currency;

        private Builder(){};

        public Builder withId(int id) {
            this.id=id;
            return this;
        }
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
