package fr.arolla.app.bank.account;

import fr.arolla.app.bank.utils.Amount;
import fr.arolla.app.bank.operation.Operation;
import fr.arolla.app.bank.utils.Utils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class Account {
    @Builder.Default private String id = Utils.generateBankNumber();
    private String name;
    @Builder.Default private Amount balance = Amount.of(0);
    @Builder.Default private Currency currency = Currency.getInstance(Locale.getDefault());
    @Builder.Default private List<Operation> operations = new LinkedList<>();
}
