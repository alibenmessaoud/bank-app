package fr.arolla.app.bank;

import fr.arolla.app.bank.operation.Operation;
import fr.arolla.app.bank.operation.OperationService;
import fr.arolla.app.bank.operation.OperationType;
import fr.arolla.app.bank.utils.Amount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationServiceTest {

    private Amount amountOfTen;

    @BeforeEach
    void setUp() {
        amountOfTen = Amount.of(10);
    }

    @AfterEach
    void tearDown() {
        amountOfTen = null;
    }

    @Test
    @DisplayName("Create DEPOSIT Operation test goes successful")
    void deposit_SHOULD_success_WHEN_params_are_valid() {
        OperationType depositType = OperationType.DEPOSIT;
        Operation depositOperation = OperationService.deposit(amountOfTen);
        assertEquals(depositType.name(), depositOperation.getOperationType().name());
        assertEquals(amountOfTen.getValue(), depositOperation.getAmount().getValue());
    }

    @Test
    @DisplayName("Create WITHDRAW Operation test goes successful")
    void withdraw_SHOULD_success_WHEN_params_are_valid() {
        OperationType withdrawType = OperationType.WITHDRAWAL;
        Operation withdrawOperation = OperationService.withdraw(amountOfTen);
        assertEquals(withdrawType.name(), withdrawOperation.getOperationType().name());
        assertEquals(amountOfTen.getValue(), withdrawOperation.getAmount().getValue());
    }

    @Test
    @DisplayName("Create DEPOSIT Operation test with null values goes unsuccessful")
    void deposit_SHOULD_throw_exception_WHEN_params_are_null() {
        assertThrows(IllegalArgumentException.class, () -> OperationService.deposit( null));
    }

    @Test
    @DisplayName("Create DEPOSIT Operation test with null values goes unsuccessful")
    void withdraw_SHOULD_throw_exception_WHEN_params_are_null() {
        assertThrows(IllegalArgumentException.class, () -> OperationService.withdraw( null));
    }

    @Test
    @DisplayName("Print DEPOSIT Operation test contains DEPOSIT after print")
    void toString_SHOULD_contain_DEPOSIT_WHEN_operation_is_deposit() {
        Operation depositOperation = OperationService.deposit(amountOfTen);
        String printedOperation = depositOperation.toString();
        assertTrue(printedOperation.contains(OperationType.DEPOSIT.name()));
    }

    @Test
    @DisplayName("Print WITHDRAWAL Operation test contains WITHDRAWAL after print")
    void toString_SHOULD_contain_WITHDRAWAL_WHEN_operation_is_withdraw() {
        Operation depositOperation = OperationService.deposit(amountOfTen);
        String printedOperation = depositOperation.toString();
        assertTrue(printedOperation.contains(OperationType.WITHDRAWAL.name()));
    }


}