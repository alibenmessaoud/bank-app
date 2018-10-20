package fr.arolla.app.bank.utils;

public class IllegalAmountException extends RuntimeException {
    private static final String MESSAGE = "Amount have to be greater than 0";
    public IllegalAmountException() {
        super(MESSAGE);
    }
}
