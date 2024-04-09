import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class BankAccount 
{
	private String accountType;
	private String accountHolderName;
	private String phoneNumber;
	private int accountNumber;
	private double balance;
	private int pin;

	public BankAccount(String accountType, double balance, int pin)
	{
		this.accountType=accountType;
		if (accountType.equals("Savings")) 
		{
			this.accountHolderName = "Rubesh";
			this.phoneNumber = "8838908340";
			this.accountNumber = 123456;
		}
		else if (accountType.equals("Current")) 
		{
			this.accountHolderName = "Kashvi";
			this.phoneNumber = "9344435918";
			this.accountNumber = 654321;
		}
		else 
		{
			this.accountHolderName = "Unknown";
			this.phoneNumber = "Unknown";
		}
		this.balance = balance;
		this.pin = pin;
	}

	public String getAccountType() 
	{
		return accountType;
	}

	public String getAccountHolderName() 
	{
		return accountHolderName;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public int getAccountNumber() 
	{
		return accountNumber;
	}

	public double getBalance() 
	{
		return balance;
	}

	public void deposit(double amount)
	{
		if (amount <= 0) 
		{
			System.out.println("Invalid amount for deposit.");
		}
		else 
		{
			balance += amount;
			System.out.println("Amount deposited: Rs." + amount + ". New balance: Rs." + balance);
		}
	}

	public void withdraw(double amount)
	{
		if (amount <= 0) 
		{
			System.out.println("Invalid amount for withdrawal.");
		}
		else if (amount > balance) 
		{
			System.out.println("Insufficient balance.");
		}
		else 
		{
			balance -= amount;
			System.out.println("Amount withdrawn: Rs." + amount + ". Remaining balance: Rs." + balance);
		}
	}

	 public void changePin(BankAccount account, Scanner scanner, String language) 
	 {
        System.out.print((language.equals("தமிழ்")) ? "தொலைபேசி எண் உள்ளிடவும்: " : "Enter your phone number: ");
        String phoneNumberInput = scanner.nextLine();
		
		if (!phoneNumberInput.equals(account.getPhoneNumber())) 
		{
           System.out.println((language.equals("தமிழ்")) ? "தவறான தொலைபேசி எண். PIN மாற்றம் தோல்வியடைந்தது." : "Invalid phone number. PIN change failed.");
           return; 
        }
		else
		{
		   System.out.println((language.equals("தமிழ்")) ? "தொலைபேசி எண் சரிபார்க்கப்பட்டது" : "Phone Number verified");
		}		

        int otp = ThreadLocalRandom.current().nextInt(1000, 10000);

        System.out.println((language.equals("தமிழ்")) ? "OTP அனுப்பப்பட்டது: " + otp : "OTP sent: " + otp);

        System.out.print((language.equals("தமிழ்")) ? "OTP உள்ளிடவும்: " : "Enter OTP: ");
        int enteringOtp = scanner.nextInt();

        if (enteringOtp == otp) 
		{
            System.out.print((language.equals("தமிழ்")) ? "பழைய PIN உள்ளிடவும்: " : "Enter old PIN: ");
            int oldPin = scanner.nextInt();
            System.out.print((language.equals("தமிழ்")) ? "புதிய PIN உள்ளிடவும்: " : "Enter new PIN: ");
            int newPin = scanner.nextInt();
            this.pin = newPin;
            ATM.savingspin = newPin;
            System.out.println((language.equals("தமிழ்")) ? "PIN விருத்திக்கப்பட்டது." : "PIN changed successfully.");
        }
		else 
		{
            System.out.println((language.equals("தமிழ்")) ? "தவறான OTP. PIN மாற்றம் தோல்வியடைந்தது." : "Incorrect OTP. PIN change failed.");
        }
    }


	public int getPin() 
	{
		return pin;
	}
}

class ATM 
{
	public static int savingspin = 2005;
	public static int currentpin = 2050;
	private static final String savingsaccounttype = "Savings";
	private static final String currentaccounttype = "Current";

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		int languageChoice = 1;
		while(true) 
		{
			System.out.println("Choose your language / உங்கள் மொழியை தேர்ந்தெடுக்கவும்");
			System.out.println("1. English");
			System.out.println("2. தமிழ்");
			System.out.println("3. Exit");

			languageChoice = scanner.nextInt();
			scanner.nextLine();
			String language = (languageChoice == 1) ? "English" : "தமிழ்";
			if(languageChoice != 1 && languageChoice != 2)
			{
				System.out.println("எங்கள் ஏடிஎம் பயன்படுத்தியதற்கு நன்றி! / Thank you for using our ATM!");
				break;
			}
			if (language.equals("தமிழ்")) 
			{
				System.out.println("Signore வங்கி ATM க்கு வரவேற்கிறோம்");
			} 
			else
			{
				System.out.println("Welcome to Signore Bank ATM");
			}

			List<BankAccount> accounts = new ArrayList<>();
			accounts.add(new BankAccount(savingsaccounttype, 10000, savingspin));
			accounts.add(new BankAccount(currentaccounttype, 20000, currentpin));

			System.out.print((language.equals("தமிழ்")) ? "உங்கள் கணக்கு எண் உள்ளிடவும்: " : "Enter your account number: ");
			int accountNumber = scanner.nextInt();
			System.out.print((language.equals("தமிழ்")) ? "உங்கள் தனிப்பட்ட அடையாள எண் உள்ளிடவும்: " : "Enter your pin number: ");
			int pin = scanner.nextInt();

			BankAccount account = null;
			for (BankAccount acc : accounts) 
			{
				if ((accountNumber == acc.getAccountNumber() && pin == acc.getPin())) 
				{
					account = acc;
					break;
				}
			}

			if (account != null)
				{
				System.out.println((language.equals("தமிழ்")) ? "பின் சரிபார்க்கப்பட்டது." : "PIN verified successfully.");

				System.out.println((language.equals("தமிழ்")) ? "கணக்கு வகை: " + account.getAccountType() : "Account type: " + account.getAccountType());
				System.out.println((language.equals("தமிழ்")) ? "கணக்கின் பெயர்: " + account.getAccountHolderName() : "Account Holder: " + account.getAccountHolderName());
				System.out.println((language.equals("தமிழ்")) ? "தொலைபேசி எண்: " + account.getPhoneNumber() : "Phone Number: " + account.getPhoneNumber());

				boolean stayWithInMenu = Boolean.TRUE;
				while (stayWithInMenu) 
				{
					System.out.println("\n" + ((language.equals("தமிழ்")) ? "பட்டி:" : "Menu:"));
					System.out.println("1. " + ((language.equals("தமிழ்")) ? "வைப்பு" : "Deposit"));
					System.out.println("2. " + ((language.equals("தமிழ்")) ? "திரும்பப் பெறுங்கள்" : "Withdraw"));
					System.out.println("3. " + ((language.equals("தமிழ்")) ? "இருப்பு கணக்கு" : "Check Balance"));
					System.out.println("4. " + ((language.equals("தமிழ்")) ? "ரசீது அச்சு" : "Print Receipt"));
					System.out.println("5. " + ((language.equals("தமிழ்")) ? "கடவுச்சொல் மாற்று" : "Change PIN"));
					System.out.println("6. " + ((language.equals("தமிழ்")) ? "வெளியேறு" : "Exit"));

					System.out.print((language.equals("தமிழ்")) ? "உங்கள் தேர்வுக்கு எண் உள்ளிடவும்: " : "Enter your choice: ");
					int choice;
					try 
					{
						choice = scanner.nextInt();
					} 
					catch (InputMismatchException e)
					{
						System.out.println((language.equals("தமிழ்")) ? "தவறான தேர்வு. தயவுசெய்து 1 முதல் 6 வரை எண்ணை உள்ளிடவும்." : "Invalid choice. Please enter a number from 1 to 6.");
						scanner.nextLine();
						continue;
					}
					scanner.nextLine();

					switch (choice) 
					{
					case 1:
						deposit(account, scanner, language);
						break;
					case 2:
						withdraw(account, scanner, language);
						break;
					case 3:
						checkBalance(account, language);
						break;
					case 4:
						printReceipt(account, language);
						break;
					case 5:
						changePin(account, scanner, language);
						stayWithInMenu = Boolean.FALSE;
						break;
					case 6:
						System.out.println((language.equals("தமிழ்")) ? "எங்கள் ஏடிஎம் பயன்படுத்தியதற்கு நன்றி!" : "Thank you for using our ATM!");
						return;
					default:
						System.out.println((language.equals("தமிழ்")) ? "தவறான தேர்வு. தயவுசெய்து 1 முதல் 6 வரை எண்ணை உள்ளிடவும்." : "Invalid choice. Please enter a number from 1 to 6.");
					}
				}
			} 
			else
			{
				System.out.println((language.equals("தமிழ்")) ? "தவறான கணக்கு எண் அல்லது பின்." : "Invalid account number or PIN.");
			}
		}
		scanner.close();
	}

	private static void deposit(BankAccount account, Scanner scanner, String language) 
	{
		while (true) 
		{
			try 
			{
				System.out.print((language.equals("தமிழ்")) ? "வைப்பு சேர்க்க விரும்பும் தொகை: Rs. " : "Enter amount to deposit: Rs. ");
				double depositAmount = scanner.nextDouble();
				account.deposit(depositAmount);
				break;
			}
			catch (InputMismatchException e) 
			{
				System.out.println((language.equals("தமிழ்")) ? "தவறான உள்ளீடு. தயவுசெய்து சரியான தொகையை உள்ளிடவும்." : "Invalid input. Please enter a valid amount.");
				scanner.nextLine();
			}
		}
	}

	private static void withdraw(BankAccount account, Scanner scanner, String language)
	{
		while (true) 
		{
			try 
			{
				System.out.print((language.equals("தமிழ்")) ? "பணம் திருப்ப விரும்பும் தொகை: Rs. " : "Enter amount to withdraw: Rs. ");
				double withdrawAmount = scanner.nextDouble();
				account.withdraw(withdrawAmount);
				break;
			} 
			catch (InputMismatchException e) 
			{
				System.out.println((language.equals("தமிழ்")) ? "தவறான உள்ளீடு. தயவுசெய்து சரியான தொகையை உள்ளிடவும்." : "Invalid input. Please enter a valid amount.");
				scanner.nextLine();
			}
		}
	}

	private static void checkBalance(BankAccount account, String language) 
	{
		System.out.println((language.equals("தமிழ்")) ? "இருப்பு தொகை: Rs." + account.getBalance() : "Available balance: Rs." + account.getBalance());
	}

	private static void printReceipt(BankAccount account, String language)
	{
		System.out.println((language.equals("தமிழ்")) ? "ரசீது:" : "Receipt:");
		System.out.println((language.equals("தமிழ்")) ? "கணக்கு வகை: " + account.getAccountType() : "Account Type: " + account.getAccountType());
		System.out.println((language.equals("தமிழ்")) ? "கணக்கின் பெயர்: " + account.getAccountHolderName() : "Account Holder: " + account.getAccountHolderName());
		System.out.println((language.equals("தமிழ்")) ? "தொலைபேசி எண்: " + account.getPhoneNumber() : "Phone Number: " + account.getPhoneNumber());
		System.out.println((language.equals("தமிழ்")) ? "இருப்பு தொகை: Rs." + account.getBalance() : "Available Balance: Rs." + account.getBalance());
	}

	private static void changePin(BankAccount account, Scanner scanner, String language)
	{
		account.changePin( account,scanner,language);
	}
}