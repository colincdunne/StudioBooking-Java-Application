/* Colin Dunne - R00115734 - Booking Project  */
import java.util.Scanner;

public class Project {

	public static void main(String[] args) {

		final int MAX_MEMBERS = 8, DAY_ONE = 260, DAY_TWO = 240, DAY_FIVE = 210, DAY_NINE = 200, STUDIO_MUSICIANS = 100;
		final double DISCOUNT = 0.95, LEVY = 1.05;
		int memberAmount = 0, days = 0, nameInstrument = 1, musicians = 0, paymentType = 0;
		double totalPayment = 0;
		String name = null, email = null, phoneNumber = null, date = null;
		String bandInfo = "", memberTemp;
		boolean loopCheck = false;

		Scanner keyboard = new Scanner(System.in);

		do
		{
			System.out.println("What is your name?"); 
			name = keyboard.nextLine(); //Asking Username and entering it into name variable
			if (name.length() == 0)
				System.out.print("That is not long enough. ");
		} while (name.length() == 0);
		do
		{
			System.out.println("What is your email address?");
			email = keyboard.nextLine(); //Asking email and entering it into email variable
			if (email.length() == 0)
				System.out.print("That is not long enough. ");
		} while (email.length() == 0);
		do
		{
			System.out.println("What is your phone number?");
			phoneNumber = keyboard.nextLine(); //Asking phone number and entering it into phoneNumber variable
			if (phoneNumber.length() == 0)
				System.out.print("That is not long enough. ");
		} while (phoneNumber.length() == 0);
		do
		{
			System.out.println("What date would you like to begin. (dd/mm/yyyy)");
			date = keyboard.nextLine(); //Asking date and entering it into date variable
			if (date.length() == 0)
				System.out.print("That is not long enough. ");
		} while (date.length() == 0);
		
		System.out.println("How many members is there in the band? Can support up to 8"); // Getting the amount of band members
		do // Creating the outer loop for entry verification
		{		
			do
			{
				if (!keyboard.hasNextInt())
				{
				System.out.print("That is not a valid entry. ");
				keyboard.next(); // Consume what is in the keyboard buffer
				System.out.print("Please re enter number of band members.");
				}
			} while (!keyboard.hasNextInt());
			memberAmount = keyboard.nextInt(); // Assign value in keyboard to memberAmount variable
			if (memberAmount < 1 || memberAmount > 8) // If the int is within the set range
			{				
				System.out.print("That is not a valid entry. ");
				System.out.print("Please re enter number of band members.");
			}
		} while (memberAmount < 1 || memberAmount > 8);
		
		System.out.println("How many days do you require the studio for?"); // Getting the amount of days
		do
		{		
			do // Inner loop for non int entries
			{
				if (!keyboard.hasNextInt())
				{
				System.out.print("That is not a valid entry. ");
				keyboard.next(); // Consume what is in the keyboard buffer
				System.out.print("Please re enter number of days.");	
				}
			}while (!keyboard.hasNextInt());
			days = keyboard.nextInt(); // Assign days to value in keyboard buffer
			if (days < 1) 
			{				
				System.out.print("That is not a valid entry. ");
				System.out.print("Please re enter number of days.");
			}
		} while (days < 1);
		loopCheck = false;
		keyboard.nextLine();
		while (nameInstrument <= memberAmount) // Outer loop for band member names and instruments
		{
			bandInfo += nameInstrument + ": ";
			System.out.println("What is band member #" + nameInstrument + "'s name?"); // Getting each band members name
			memberTemp = keyboard.nextLine();
			bandInfo += memberTemp + " - ";
			System.out.println("What is " + memberTemp + "'s instrument"); // Getting each members instrument
			bandInfo += keyboard.nextLine() + "\n";
			nameInstrument++;
		}
		loopCheck = false;
		musicians = MAX_MEMBERS - memberAmount; // Calculating space allowed for session musicians
		if (musicians != 0)
		{
			System.out.println("There is room for " + musicians + " session musicians. How many do you want?"); // Ask user how many musicians
			while (!loopCheck) // Create outer loop
			{
				while (!keyboard.hasNextInt()) // Inner loop for non int entries
				{
					System.out.print("That is not a valid entry. ");
					keyboard.next(); // Consume keyboard buffer 
					System.out.print("Please re enter number of musicians.");
				}
				musicians = keyboard.nextInt(); // Assign musicians as keyboard buffer
				if (musicians >= 0 && musicians <= MAX_MEMBERS - memberAmount)
					loopCheck = true; // Set flag to true if value is within range
				else // Else ask user to try again
					System.out.print("That entry will not work. Please enter another number.");
			}
		}
		else
			System.out.println("There is no room for studio musicians.");
		System.out.println("Will you be paying by:\n" // Ask user to enter payment method
				+ "1: Credit Card (5% levy)\n"
				+ "2: Cash (5% discount)\n"
				+ "3: Credit Card)");
		loopCheck = false;
		while (!loopCheck) // Creating Outer Loop
		{
			while (!keyboard.hasNextInt()) // Inner loop for non int
			{
				System.out.print("That is not a valid entry. ");
				keyboard.next();  // Consume Keyboard Buffer
				System.out.print("Please re enter a number between one and three.");
			}
			paymentType = keyboard.nextInt(); // Assign Payment as keyboard buffer
			if (paymentType > 0 && paymentType <=3)
				loopCheck = true; // If value is within range set flag to true to exit loop
			else // Else repeat loop for valid entry
				System.out.print("Please re enter a number between one and three.");
		}
		// Print out booking request
		System.out.println("Booking Application");
		System.out.println("-------------------");
		System.out.println("Requested by: " + name + " (Contact: " + email + " & " + phoneNumber + ")\n");
		System.out.println("Date requested: --> " + date + "\n");
		System.out.println("Band Members");
		System.out.println("------------");
		System.out.println(bandInfo);
		System.out.println();
		if (musicians > 0) // If session musicians required print out how many on book request
			System.out.println("Includes " + musicians + " session musicians per day.");
		if (days == 1) // Calculate cost at price for one day
			totalPayment = (DAY_ONE * days) + ((STUDIO_MUSICIANS * musicians) * days);
		else if (days >= 2  && days <= 4) // Calculate cost at price for 2-4 days
			totalPayment = (DAY_TWO * days) + ((STUDIO_MUSICIANS * musicians) * days);
		else if (days >= 5  && days <= 8) // Calculate cost at price for 5-8 days
			totalPayment = (DAY_FIVE * days) + ((STUDIO_MUSICIANS * musicians) * days);
		else if (days >= 9) // Calculate cost at price for over 9 days
			totalPayment = (DAY_NINE * days) + ((STUDIO_MUSICIANS * musicians) * days);
		switch (paymentType)
		{
		case 1:
			totalPayment = (double)totalPayment * LEVY; // Calculate total cost with 5% credit card levy
			System.out.println("Payment will be €" + totalPayment + " to be paid by Credit Card."); // Print out total cost
			break;
		case 2:
			totalPayment = (double)totalPayment * DISCOUNT; // Calculate total cost with 5% discount for cash
			System.out.println("Payment will be €" + totalPayment + " to be paid by Cash."); // Print out total cost
			break;
		case 3:
			System.out.println("Payment will be €" + totalPayment + " to be paid by Cheque."); // Print out cost with payment type 3
		}
		keyboard.close();
	}
}
