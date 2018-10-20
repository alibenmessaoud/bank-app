package fr.arolla.app.bank.operation;

public enum OperationType {
    DEPOSIT (+1),
    WITHDRAWAL(-1);

    private final int value;
    OperationType(final int value) {
        this.value = value;
    }
    public int getValue() { return value; }
}
