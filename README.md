# bank-app

Bank account kata

Think of your personal bank account experience When in doubt, go for the simplest solution

Requirements

Deposit and Withdrawal
Account statement (date, amount, balance)
Statement printing

User Stories

US 1:
In order to save money
As a bank client
I want to make a deposit in my account

US 2:
In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account

US 3:
In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance)  of my operations


## Main

	Client client = ClientService.create("Ali", "Ben Messaoud");

	Account account1 = AccountService.create();
	Account account2 = AccountService.create();

	ClientService.addAccount(client, account1);
	ClientService.addAccount(client, account1);
	ClientService.addAccount(client, account2);

	Operation operation0 = OperationService.deposit(Amount.of(1000));
	Operation operation1 = OperationService.withdraw(Amount.of(1000));
	Operation operation2 = OperationService.deposit(Amount.of(100));

	AccountService.accept(account1, operation0);
	AccountService.accept(account1, operation1);
	AccountService.accept(account1, operation2);


	System.out.println(ClientService.print(client));


## Output:

	##########################################################
	Client: Ali Ben Messaoud
	##########################################################

	==========================================================
	Account: checking-account / US26685854897443 :
	==========================================================
	Balance : 100.0 USD
	------------------- OPERATIONS HISTORY -------------------
	9dqbqs5s:    DEPOSIT |       1000.0 USD | 10/20/18 6:17 PM
	ms5cyue2: WITHDRAWAL |      -1000.0 USD | 10/20/18 6:17 PM
	gbf8qza0:    DEPOSIT |        100.0 USD | 10/20/18 6:17 PM

	==========================================================
	Account: checking-account / US47826380481692 :
	==========================================================
	Balance : 0.0 USD
	------------------- OPERATIONS HISTORY -------------------
	No operations
