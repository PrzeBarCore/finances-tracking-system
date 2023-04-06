package io.github.PrzeBarCore.ValueObjects;

import java.math.BigDecimal;

public class MonetaryAmount {
    private BigDecimal value;
    protected MonetaryAmount(){};
    public static MonetaryAmount of(BigDecimal value){
        return new MonetaryAmount(value);}
    public static MonetaryAmount zero(){
        return new MonetaryAmount(BigDecimal.ZERO);
    }

    public void add(MonetaryAmount value){
        this.value.add(value.toBigDecimal());
    }

    public void subtract(MonetaryAmount value){
        this.value.subtract(value.toBigDecimal());
    }

    public void multiply(MonetaryAmount multiplier){
        this.value.multiply(multiplier.toBigDecimal());
    }

    public int compareTo(MonetaryAmount valueToBeCompared){
        return this.value.compareTo(new BigDecimal(valueToBeCompared.toString()));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    private BigDecimal toBigDecimal(){
        return new BigDecimal(value.toString());
    }
    private MonetaryAmount(BigDecimal value){
        this.value=value;
    }
}
