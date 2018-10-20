package fr.arolla.app.bank.client;

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
}
