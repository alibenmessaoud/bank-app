package fr.arolla.app.bank.utils;

import lombok.EqualsAndHashCode;

import java.text.NumberFormat;

@EqualsAndHashCode
public class Amount {

    public static final Amount ofZero = new Amount(0);
    private final double value;

    private Amount(double value){
        this.value = value;
    }

    public static Amount of(double value){
        if (value < 0) {
            throw new IllegalAmountException();
        }
        return new Amount(value);
    }

    public double getValue() {
        return value;
    }

    public Amount addTo(Amount amount) {
        return new Amount(this.value + amount.value);
    }

    public Amount subBy(Amount amount) {
        double newAmount = this.value - amount.value;
        if (newAmount < 0) {
            throw new IllegalAmountException();
        }
        return new Amount(newAmount);
    }

    public boolean isLesserThan(Amount anOtherAmount) {
        return this.value < anOtherAmount.getValue();
    }

    @Override
    public String toString() { return NumberFormat.getCurrencyInstance().format(value); }

}
