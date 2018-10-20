package fr.arolla.app.bank.account;

import fr.arolla.app.bank.operation.OperationType;
import fr.arolla.app.bank.utils.Amount;
import fr.arolla.app.bank.operation.Operation;
import fr.arolla.app.bank.utils.Utils;

public class AccountService {

    private AccountService(){
        // empty constructor
    }

    public static Account create(Amount initialAmount) {
        if (initialAmount == null) {
            throw new IllegalArgumentException("Initial amount id is null");
        }
        return Account.builder()
                .name("checking-account")
                .balance(initialAmount)
                .build();
    }

    public static Account create() {
        return create(Amount.of(0));
    }

    public static void accept(Account account, Operation operation){
        if (account == null)
            throw new IllegalArgumentException("The account is null!");

        if (operation == null)
            throw new IllegalArgumentException("The operation is null!");

        if (operation.getAmount().equals(Amount.ofZero))
            return;

        if (account.getBalance().isLesserThan(operation.getAmount()) && operation.getOperationType().equals(OperationType.WITHDRAWAL))
            throw new IllegalArgumentException("The account does not have sufficient funds!");

        Amount newAmount = Amount.ofZero;

        switch (operation.getOperationType()) {
            case DEPOSIT:
                newAmount = account.getBalance().addTo(operation.getAmount());
                break;
            case WITHDRAWAL:
                newAmount = account.getBalance().subBy(operation.getAmount());
                break;
                default:
        }

        account.setBalance(newAmount);
        account.getOperations().add(operation);
    }

    public static String print(Account account){
        StringBuilder result = new StringBuilder();
        result.append(Utils.RC).append("==========================================================");
        result.append(Utils.RC).append(String.format("Account: %s / %s : ", account.getName(), account.getId()));
        result.append(Utils.RC).append("==========================================================");
        result.append(Utils.RC).append(String.format("Balance : %s", account.getBalance().getValue()));
        result.append(Utils.RC).append("------------------- OPERATIONS HISTORY -------------------");
        if (!account.getOperations().isEmpty()){
            for (Operation operation : account.getOperations()) {
                result.append(Utils.RC).append(operation);
            }
        }
        return result.toString();
    }
}
