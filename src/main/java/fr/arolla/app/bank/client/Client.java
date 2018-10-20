package fr.arolla.app.bank.client;

import fr.arolla.app.bank.account.Account;
import fr.arolla.app.bank.utils.Utils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class Client {
    @Builder.Default private String id = Utils.getToken();
    private String firstName;
    private String lastName;
    @Builder.Default List<Account> accounts = new LinkedList<>();
}
