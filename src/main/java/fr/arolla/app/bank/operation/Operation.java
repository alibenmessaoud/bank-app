package fr.arolla.app.bank.operation;

import fr.arolla.app.bank.utils.Amount;
import fr.arolla.app.bank.utils.Utils;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Currency;
import java.util.Locale;

@Data
@Builder
public class Operation {
    @Builder.Default private String id = Utils.getToken();
    private Amount amount;
    private OperationType operationType;
    @Builder.Default private Currency currency = Currency.getInstance(Locale.getDefault());
    @Builder.Default private Instant date = Instant.now();

    public String toString(){
        return String.format("%8s: %10s | %12s %3s | %16s",
                id, operationType.name(), amount.getValue() * operationType.getValue(),
                currency, Utils.shortDate(date));
    }
}
