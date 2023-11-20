package ShwetaGaikwadProjectJava.ShwetaGaikwadProjectJava;

import java.util.Scanner;

public class EmailApplication {
    private String firstName;
    private String lastName;
    private String password;
    private int defaultMailboxCapacity = 500;
    private String alternateEmail;
    private String email;
    private String companySuffix = "gmail.com";

    public EmailApplication(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + companySuffix;
        this.password = generateRandomPassword(8);
    }

    private String generateRandomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * passwordSet.length());
            password.append(passwordSet.charAt(index));
        }
        return password.toString();
    }

    public void setMailboxCapacity(int capacity) {
        this.defaultMailboxCapacity = capacity;
    }

    public void setAlternateEmail(String altEmail) {
        this.alternateEmail = altEmail.toLowerCase() + "@" + companySuffix;
    }

    public void changePassword(String newPassword) {
        if (isPasswordStrong(newPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully.\n");
        } else {
            System.out.println("Password should contain uppercase, lowercase, number, and special character.\n");
            
        }
    }

    private boolean isPasswordStrong(String password) {
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*\\d.*)";
        String specialChars = "(.*[!@#$%^&*].*)";
        return password.matches(upperCaseChars) && password.matches(lowerCaseChars) &&
               password.matches(numbers) && password.matches(specialChars);
    }

    
	// Method for sending emails
    public void sendEmail(String recipient, String message) {
        // Implement email sending functionality here
        System.out.println("Email sent to:\n" + recipient.toLowerCase() +"@gmail.com :"  + message+"\n");
    }

    // Method for user-friendly interface
    public static void displayMenu() {
        System.out.println("========= Email Application Menu =========");
        System.out.println("1. Set Alternate Email");
        System.out.println("2. Set Mailbox Capacity");
        System.out.println("3. Change Password");
        System.out.println("4. Send Email");
        System.out.println("5. Exit");
        System.out.println("==========================================");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();

            EmailApplication emailApp = new EmailApplication(firstName, lastName);

            System.out.println("Email created successfully: " + emailApp.email+"\n");
            System.out.println("Auto Generated Password: " + emailApp.password +"\n");

            boolean exit = false;
            while (!exit) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Set alternate email: \n");
                        String altEmail = scanner.nextLine();
                        emailApp.setAlternateEmail(altEmail);
                        System.out.println("Alternate Email set to: " + emailApp.alternateEmail+"\n");
                        break;
                    case 2:
                        System.out.print("Set mailbox capacity: ");
                        int capacity = scanner.nextInt();
                        emailApp.setMailboxCapacity(capacity);
                        System.out.println("Mailbox Capacity set to: " + emailApp.defaultMailboxCapacity+"mb\n");
                        break;
                    case 3:
                        System.out.print("Enter new password: \n");
                        String newPassword = scanner.nextLine();
                        emailApp.changePassword(newPassword);
                        break;
                    case 4:
                        System.out.print("Enter recipient's email address: ");
                        String recipient = scanner.nextLine();
                        System.out.print("Enter your message: ");
                        String message = scanner.nextLine();
                        emailApp.sendEmail(recipient, message);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}

 
