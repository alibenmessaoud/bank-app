package fr.arolla.app.bank.client;

import fr.arolla.app.bank.account.Account;
import fr.arolla.app.bank.account.AccountService;
import fr.arolla.app.bank.utils.Utils;

import java.util.List;
import java.util.Optional;

public class ClientService {

    private ClientService(){
        // empty
    }

    public static Client create(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("firstName is null or lastName is null");
        }
        return Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static void addAccount(Client client, Account account){
        if (client == null || account == null) {
            throw new IllegalArgumentException("client is null or account is null");
        }

        Optional.ofNullable(client.getAccounts()).ifPresent(accounts -> {
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        });

    }

    public static StringBuilder print(Client client){
        StringBuilder result = new StringBuilder();
        result.append(Utils.RC).append("##########################################################");
        result.append(Utils.RC).append(String.format("Client: %s %s", client.getFirstName(), client.getLastName()));
        result.append(Utils.RC).append("##########################################################");
        Optional<List<Account>> optionalAccounts = Optional.ofNullable(client.getAccounts());
        optionalAccounts.ifPresent(accounts1 -> {
            List<Account> accounts = accounts1;
            if (accounts.isEmpty()) {
                return;
            }
            accounts.forEach(account -> {
                result.append(AccountService.print(account));
            });
        });
        return result;
    }
}
