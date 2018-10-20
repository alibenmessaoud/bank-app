package fr.arolla.app.bank;

import fr.arolla.app.bank.account.Account;
import fr.arolla.app.bank.account.AccountService;
import fr.arolla.app.bank.client.Client;
import fr.arolla.app.bank.client.ClientService;
import fr.arolla.app.bank.operation.Operation;
import fr.arolla.app.bank.operation.OperationService;
import fr.arolla.app.bank.utils.Amount;

public class App {

    public static void main(String[] params){
        Client client = ClientService.create("Ali", "Ben Messaoud");

        Account account1 = AccountService.create();
        Account account2 = AccountService.create();

        ClientService.addAccount(client, account1);
        ClientService.addAccount(client, account1);
        ClientService.addAccount(client, account2);

        Operation operation0 = OperationService.deposit(Amount.of(1000));
        Operation operation1 = OperationService.withdraw(Amount.of(1000));
        Operation operation2 = OperationService.deposit(Amount.of(100));

        AccountService.accept(account1, operation0);
        AccountService.accept(account1, operation1);
        AccountService.accept(account1, operation2);


        System.out.println(ClientService.print(client));
    }
}
