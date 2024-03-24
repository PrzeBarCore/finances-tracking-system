package io.github.PrzeBarCore.ValueObjects;

import java.math.BigDecimal;

public class MonetaryAmount {
    private BigDecimal value;
    protected MonetaryAmount(){};
    public static MonetaryAmount of(BigDecimal value){
        return new MonetaryAmount(value);}
    public static MonetaryAmount of(Double value){
        return MonetaryAmount.of(BigDecimal.valueOf((value)));}
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
    public BigDecimal getValue() {
        return value;
    }
    @Override
    public String toString() {
        return value.toString();
    }
    public BigDecimal toBigDecimal(){
        return new BigDecimal(value.toString());
    }
    public Double toDouble(){
        return value.doubleValue();
    }
    private MonetaryAmount(BigDecimal value){
        this.value=value;
    }
}
