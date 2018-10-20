package fr.arolla.app.bank;

import fr.arolla.app.bank.account.Account;
import fr.arolla.app.bank.account.AccountService;
import fr.arolla.app.bank.operation.Operation;
import fr.arolla.app.bank.operation.OperationType;
import fr.arolla.app.bank.utils.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    @DisplayName("Create account returns a non null account object when no initial amount is provided")
    void create_SHOULD_success_WHEN_initial_amount_is_not_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create());
        assertTrue(optionalAccount.isPresent());
    }

    @Test
    @DisplayName("Create account will return an account with an id when no initial amount is provided")
    void create_SHOULD_return_account_with_bank_number_WHEN_initial_amount_is_not_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create());

        String id = "";
        if (optionalAccount.isPresent())
            id = optionalAccount.get().getId();

        assertTrue(!id.isEmpty());
    }

    @Test
    @DisplayName("Create account will return an account with a name containing checking-account when no initial amount is provided")
    void create_SHOULD_create_checking_account_WHEN_initial_amount_is_not_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create());

        String name = "";
        if (optionalAccount.isPresent())
            name = optionalAccount.get().getName();

        assertEquals("checking-account", name);
    }

    @Test
    @DisplayName("Create account has currency set when no initial amount is provided")
    void create_SHOULD_has_currency_WHEN_initial_amount_is_not_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create());

        Currency currency = null;
        if (optionalAccount.isPresent())
            currency = optionalAccount.get().getCurrency();

        assertEquals(Currency.getInstance(Locale.getDefault()), currency);
    }

    @Test
    @DisplayName("Create account has an empty operations list when no initial amount is provided")
    void create_SHOULD_has_empty_operations_WHEN_initial_amount_is_not_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create());

        int size = -1;
        if (optionalAccount.isPresent()){
            size = optionalAccount.get().getOperations().size();
        }

        assertEquals(0, size);
    }

    @Test
    @DisplayName("Create account has a 0 balance ")
    void create_SHOULD_has_zeroed_balance_WHEN_initial_amount_is_not_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create());

        Amount amount = null;
        if (optionalAccount.isPresent()){
            amount = optionalAccount.get().getBalance();
        }

        assertEquals(Amount.ofZero, amount);
    }

    @Test
    @DisplayName("Create account returns a non null account object when initial amount is provided")
    void create_SHOULD_success_WHEN_initial_amount_is_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create(Amount.of(100)));
        assertTrue(optionalAccount.isPresent());
    }

    @Test
    @DisplayName("Create account will return an account with an id when initial amount is provided")
    void create_SHOULD_return_account_with_bank_number_WHEN_initial_amount_is_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create(Amount.of(100)));

        String id = "";
        if (optionalAccount.isPresent())
            id = optionalAccount.get().getId();

        assertTrue(!id.isEmpty());
    }

    @Test
    @DisplayName("Create account will return an account with a name containing checking-account when initial amount is provided")
    void create_SHOULD_create_checking_account_WHEN_initial_amount_is_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create(Amount.of(100)));

        String name = "";
        if (optionalAccount.isPresent())
            name = optionalAccount.get().getName();

        assertEquals("checking-account", name);
    }

    @Test
    @DisplayName("Create account has currency set when initial amount is provided")
    void create_SHOULD_has_currency_WHEN_initial_is_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create(Amount.of(100)));

        Currency currency = null;
        if (optionalAccount.isPresent())
            currency = optionalAccount.get().getCurrency();

        assertEquals(Currency.getInstance(Locale.getDefault()), currency);
    }

    @Test
    @DisplayName("Create account has an empty operations list when initial amount is provided")
    void create_SHOULD_has_empty_operations_WHEN_initial_is_provided() {
        Optional<Account> optionalAccount = Optional.ofNullable(AccountService.create(Amount.of(100)));

        int size = -1;
        if (optionalAccount.isPresent()){
            size = optionalAccount.get().getOperations().size();
        }

        assertEquals(0, size);
    }

    @Test
    @DisplayName("Accept operation throw exception when parameters are null")
    void accept_SHOULD_throw_exception_WHEN_params_are_null() {
        Account account = AccountService.create(Amount.of(0));

        Operation operation = Operation
                .builder()
                .operationType(OperationType.DEPOSIT)
                .amount(Amount.of(0))
                .build();

        assertThrows(IllegalArgumentException.class, () -> AccountService.accept(account, null));
        assertThrows(IllegalArgumentException.class, () -> AccountService.accept(null, null));
        assertThrows(IllegalArgumentException.class, () -> AccountService.accept(null, operation));
    }

    @Test
    @DisplayName("Accept operation return empty balance when a deposit a zero amount on an empty balance")
    void accept_SHOULD_has_empty_balance_and_empty_operations_WHEN_apply_deposit_of_empty_amount() {
        Account account = AccountService.create(Amount.of(0));

        Operation operation = Operation
                .builder()
                .operationType(OperationType.DEPOSIT)
                .amount(Amount.of(0))
                .build();

        AccountService.accept(account, operation);
        assertEquals(Amount.of(0), account.getBalance());
        assertEquals(0, account.getOperations().size());
    }

    @Test
    @DisplayName("Accept operation return empty balance when a withdraw a zero amount on an empty balance")
    void accept_SHOULD_has_empty_balance_and_empty_operations_WHEN_apply_withdraw_of_empty_amount() {
        Account account = AccountService.create(Amount.of(0));

        Operation operation = Operation
                .builder()
                .operationType(OperationType.WITHDRAWAL)
                .amount(Amount.of(0))
                .build();

        AccountService.accept(account, operation);
        assertEquals(Amount.of(0), account.getBalance());
        assertEquals(0, account.getOperations().size());
    }

    @Test
    @DisplayName("Accept operation return balance of 10 when a deposit a 10 amount on an new created account of 0")
    void accept_SHOULD_has_10_balance_and_1_operation_WHEN_apply_deposit_of_10_amount_on_created_account_of_0() {
        Account account = AccountService.create(Amount.of(0));

        Operation operation = Operation
                .builder()
                .operationType(OperationType.DEPOSIT)
                .amount(Amount.of(10))
                .build();

        AccountService.accept(account, operation);
        assertEquals(Amount.of(10), account.getBalance());
        assertEquals(1, account.getOperations().size());
    }

    @Test
    @DisplayName("Accept operation throws exception when the account does not have sufficient funds!")
    void accept_SHOULD_throw_exception_when_funds_are_insufficient1() {
        Account account = AccountService.create(Amount.of(0));

        Operation operation = Operation
                .builder()
                .operationType(OperationType.WITHDRAWAL)
                .amount(Amount.of(10))
                .build();

        assertThrows(IllegalArgumentException.class, () -> AccountService.accept(account, operation));
    }

    @Test
    @DisplayName("Accept operation throws exception when the account does not have sufficient funds!")
    void accept_SHOULD_throw_exception_when_funds_are_insufficient2() {
        Account account = AccountService.create(Amount.of(50));

        Operation operation = Operation
                .builder()
                .operationType(OperationType.WITHDRAWAL)
                .amount(Amount.of(60))
                .build();

        assertThrows(IllegalArgumentException.class, () -> AccountService.accept(account, operation));
    }

    @Test
    @DisplayName("Accept operation return balance of 10 when a deposit a 10 amount on an new created account of 0")
    void accept_SHOULD_success_WHEN_apply_deposit_withdraw_amounts() {
        Account account = AccountService.create(Amount.of(100));

        Operation operation1 = Operation
                .builder()
                .operationType(OperationType.DEPOSIT)
                .amount(Amount.of(10))
                .build();

        Operation operation2 = Operation
                .builder()
                .operationType(OperationType.WITHDRAWAL)
                .amount(Amount.of(10))
                .build();

        AccountService.accept(account, operation1);
        AccountService.accept(account, operation2);

        assertEquals(Amount.of(100), account.getBalance());
        assertEquals(2, account.getOperations().size());
    }

    @Test
    @DisplayName("Print operations contains operation names in the printed message")
    void print_SHOULD_print_WHEN_operation_list_is_full(){
        Account account = AccountService.create(Amount.of(100));

        Operation operation1 = Operation
                .builder()
                .operationType(OperationType.DEPOSIT)
                .amount(Amount.of(10))
                .build();

        Operation operation2 = Operation
                .builder()
                .operationType(OperationType.WITHDRAWAL)
                .amount(Amount.of(10))
                .build();

        Operation operation3 = Operation
                .builder()
                .operationType(OperationType.WITHDRAWAL)
                .amount(Amount.of(50))
                .build();

        AccountService.accept(account, operation1);
        AccountService.accept(account, operation2);
        AccountService.accept(account, operation3);

        String print = AccountService.print(account);
        long count = Arrays
                .stream(print.split(" "))
                .filter(word -> word.contains(OperationType.WITHDRAWAL.name())
                        || word.contains(OperationType.DEPOSIT.name()))
                .count();
        assertEquals(3, count);
    }
}