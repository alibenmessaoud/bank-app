package fr.arolla.app.bank;

import fr.arolla.app.bank.utils.Amount;
import fr.arolla.app.bank.utils.IllegalAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {

    @Test
    @DisplayName("Compare equal values in amounts goes successful")
    void of_SHOULD_success_WHEN_amounts_equal() {
        Assertions.assertEquals(Amount.of(0), Amount.ofZero);
        assertEquals(Amount.of(200), Amount.of(200));
    }

    @Test
    @DisplayName("Creating amount with negative argument throws an exception")
    void of_SHOULD_throw_exception_WHEN_amount_is_negative(){
        assertThrows(IllegalAmountException.class, () -> Amount.of(-100));
    }

    @Test
    @DisplayName("Adding two positive amounts return an amount equal to the sum")
    void addTo_SHOULD_return_total_WHEN_add_tow_positive_amounts() {
        assertEquals(Amount.of(100), Amount.of(50).addTo(Amount.of(50)));
    }

    @Test
    @DisplayName("Adding two amounts which one of them is negative throws an exception")
    void addTo_SHOULD_throw_exception_WHEN_add_negative_amount_to_positive_amount() {
        assertThrows(IllegalAmountException.class, () ->  Amount.of(-50).addTo(Amount.of(50)));
    }

    @Test
    @DisplayName("Subtracting two amount when the first one is great than the second goes successful")
    void subTo_SHOULD_return_total_WHEN_subtracting_great_amount_by_low_amount() {
        assertEquals(Amount.of(50), Amount.of(100).subBy(Amount.of(50)));
    }

    @Test
    @DisplayName("Subtracting two amount which the first one is positive and the second is negative throws an exception")
    void subTo_SHOULD_throw_exception_WHEN_subtracting_positive_amount_by_negative_amount() {
        assertThrows(IllegalAmountException.class, () -> Amount.of(150).subBy(Amount.of(-50)));
    }

    @Test
    @DisplayName("Subtracting two amount which the first one is low than the second throws an exception")
    void subTo_SHOULD_throw_exception_WHEN_subtracting_low_amount_by_great_amount() {
        assertThrows(IllegalAmountException.class, () -> Amount.of(20).subBy(Amount.of(50)));
    }

    @Test
    @DisplayName("Formatting amount based on Locale")
    void toString_SHOULD_format_amount_WHEN_locale_is_provided() {
        Locale.setDefault(Locale.US);
        assertEquals("$20.00", Amount.of(20).toString());
        Locale.setDefault(Locale.FRANCE);
        assertEquals("20,00 €", Amount.of(20).toString());
    }
}