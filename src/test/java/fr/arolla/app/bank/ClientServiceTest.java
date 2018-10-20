package fr.arolla.app.bank;

import fr.arolla.app.bank.client.Client;
import fr.arolla.app.bank.client.ClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    private String firstName = "Clifford";
    private String lastName = "Garrison";
    private String empty = "";

    @AfterEach
    void tearDown() {
        firstName = null;
        lastName = null;
        empty = null;
    }

    @Test
    @DisplayName("Create client with first name and last name goes successful")
    void create_SHOULD_success_WHEN_client_has_first_name_and_last_name() {
        Client client = ClientService.create(firstName, lastName);
        assertFalse(client.getId().isEmpty());
        assertEquals(firstName, client.getFirstName());
        assertEquals(lastName, client.getLastName());
    }

    @Test
    @DisplayName("Create client with empty first name and empty last name throws exception")
    void create_SHOULD_throw_exception_WHEN_client_has_empty_first_and_empty_last_name(){
        assertThrows(IllegalArgumentException.class, () -> ClientService.create(empty, empty));
    }

    @Test
    @DisplayName("Create client with empty first name and non empty last name throws exception")
    void create_SHOULD_throw_exception_WHEN_client_has_empty_first_and_non_empty_last_name(){
        assertThrows(IllegalArgumentException.class, () -> ClientService.create(empty, lastName));
    }

    @Test
    @DisplayName("Create client with non empty first name and empty last name throws exception")
    void create_SHOULD_throw_exception_WHEN_client_has_non_empty_first_and_empty_last_name(){
        assertThrows(IllegalArgumentException.class, () -> ClientService.create(firstName, empty));
    }

    @Test
    @DisplayName("Create client with null arguments goes unsuccessful")
    void create_SHOULD_throw_exception_WHEN_first_name_and_last_name_are_null(){
        assertThrows(IllegalArgumentException.class, () -> ClientService.create(null, null));
    }

    @Test
    @DisplayName("Create client test with null first name goes unsuccessful")
    void create_SHOULD_throw_exception_WHEN_first_name_is_null(){
        assertThrows(IllegalArgumentException.class, () -> ClientService.create(null, lastName));
    }

    @Test
    @DisplayName("Create client test with null last name goes unsuccessful")
    void create_SHOULD_throw_exception_WHEN_last_name_is_null(){
        assertThrows(IllegalArgumentException.class, () -> ClientService.create(firstName, null));
    }
}