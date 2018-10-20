package fr.arolla.app.bank.operation;

import fr.arolla.app.bank.utils.Amount;

public class OperationService {

    private OperationService(){
        // empty
    }

    public static Operation deposit(Amount amount) {
        return operation(amount, OperationType.DEPOSIT);
    }

    public static Operation withdraw(Amount amount) {
        return operation(amount, OperationType.WITHDRAWAL);
    }

    private static Operation operation(Amount amount, OperationType operationType) {
        if (amount == null || operationType == null) {
            throw new IllegalArgumentException("Amount is null or operationType is null");
        }
        return Operation
                .builder()
                .operationType(operationType)
                .amount(amount)
                .build();
    }
}
