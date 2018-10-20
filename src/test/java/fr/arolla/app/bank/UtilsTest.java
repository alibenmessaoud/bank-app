package fr.arolla.app.bank;

import fr.arolla.app.bank.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UtilsTest {

    @Test
    void generateBankNumber() {
        String bankNumber = Utils.generateBankNumber();
        assertNotNull(bankNumber);
    }
}