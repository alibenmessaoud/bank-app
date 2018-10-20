package fr.arolla.app.bank;

import fr.arolla.app.bank.account.Account;
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

    @Test
    @DisplayName("Add account goes successful")
    void addAccount_SHOULD_return_non_empty_accounts_list_WHEN_add_account() {
        Client client = ClientService.create(firstName, lastName);
        Account account = Account.builder().build();
        ClientService.addAccount(client, account);
        assertEquals(1, client.getAccounts().size());
    }

    @Test
    @DisplayName("Add account goes successful even the account is duplicate")
    void addAccount_SHOULD_return_1_accounts_list_WHEN_add_same_account_to_client_twice_or_more() {
        Client client = ClientService.create(firstName, lastName);
        Account account = Account.builder().build();
        ClientService.addAccount(client, account);
        ClientService.addAccount(client, account);
        ClientService.addAccount(client, account);
        ClientService.addAccount(client, account);
        assertEquals(1, client.getAccounts().size());
    }

    @Test
    @DisplayName("Add account throws exception when client is null")
    void addAccount_SHOULD_throw_exception_WHEN_params_are_null_1() {
        Client client = ClientService.create(firstName, lastName);
        Account account = Account.builder().build();
        assertThrows(IllegalArgumentException.class, () -> ClientService.addAccount(null, account));
    }

    @Test
    @DisplayName("Add account throws exception when params are null")
    void addAccount_SHOULD_throw_exception_WHEN_params_are_null_2() {
        Client client = ClientService.create(firstName, lastName);
        Account account = Account.builder().build();
        assertThrows(IllegalArgumentException.class, () -> ClientService.addAccount(null, null));
    }

    @Test
    @DisplayName("Add account throws exception when account is null")
    void addAccount_SHOULD_throw_exception_WHEN_params_are_null_3() {
        Client client = ClientService.create(firstName, lastName);
        Account account = Account.builder().build();
        assertThrows(IllegalArgumentException.class, () -> ClientService.addAccount(client, null));
    }
}