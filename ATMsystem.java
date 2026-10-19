package com.mycompany.atmsystem;
import java.util.Scanner;

// Custom Exceptions
class InvalidPinException extends Exception {
    public InvalidPinException(String msg) { super(msg); }
}
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) { super(msg); }
}
class InvalidAmountException extends Exception {
    public InvalidAmountException(String msg) { super(msg); }
}

public class ATMsystem {
    private static final int CORRECT_PIN = 1234;
    private static double balance = 10000.0;

    public static void verifyPin(int pin) throws InvalidPinException {
        if (pin != CORRECT_PIN) {
            throw new InvalidPinException("Invalid PIN! Please try again.");
        }
    }

    public static void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive!");
        if (amount > balance) throw new InsufficientBalanceException("Insufficient Balance! Balance: " + balance);
        balance -= amount;
        System.out.println("Withdrawal successful! Remaining: " + balance);
    }

    public static void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit must be positive!");
        balance += amount;
        System.out.println("Deposit successful! New balance: " + balance);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter PIN (1234): ");
            int pin = sc.nextInt();
            verifyPin(pin);
            System.out.println("Login Successful!");

            while (true) {
                System.out.println("\n1.Balance Enquiry 2.Withdraw 3.Deposit 4.Exit");
                System.out.print("Choose: ");
                int choice = sc.nextInt();
                try {
                    switch (choice) {
                        case 1: System.out.println("Balance: " + balance); break;
                        case 2: System.out.print("Enter amount: "); withdraw(sc.nextDouble()); break;
                        case 3: System.out.print("Enter amount: "); deposit(sc.nextDouble()); break;
                        case 4: System.out.println("Thank you! Visit Again!"); return;
                        default: System.out.println("Invalid choice!");
                    }
                } catch (InsufficientBalanceException | InvalidAmountException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (InvalidPinException e) {
            System.out.println("ATM Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}