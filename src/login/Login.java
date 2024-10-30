/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package login;

/**
 *
 * @author Timmyngove
 */
import java.util.Scanner;
import javax.swing.*;


public class Login {

    public static void main(String[] args) {
        UserLogin userlogin=new UserLogin();
        Task info = new Task();
        Scanner input = new Scanner(System.in);

        //taking user info for Registration 
        System.out.println("Register..........");
        System.out.print("Enter First Name: ");
        userlogin.firstName = input.next();
        System.out.print("Enter Last Name: ");
        userlogin.surname = input.next();
        System.out.print("Enter Username: ");
        userlogin.userName = input.next();
        System.out.print("Enter Password: ");
        userlogin.password = input.next();

        System.out.println(userlogin.registerUser());

        // validating username and password complexity
        while (!userlogin.checkUsername() || !userlogin.checkPasswordComplexity()) {
            System.out.println("Try to register again!!!!!");
            System.out.print("Enter Username: ");
            userlogin.userName = input.next();
            System.out.print("Enter Password: ");
            userlogin.password = input.next();
            System.out.println(userlogin.registerUser());
        }

        // Logging in
        System.out.println("Login..........");
        System.out.print("Enter Username: ");
        userlogin.enteredUserName = input.next();
        System.out.print("Enter Password: ");
        userlogin.enteredPassword = input.next();
        System.out.println(userlogin.returnLoginStatus());
        
        //loop that will keep running until correct info is entered
        while (!userlogin.loginUser()) {
            System.out.println("Try to Login again ..........");
            System.out.print("Enter Username: ");
            userlogin.enteredUserName = input.next();
            System.out.print("Enter Password: ");
            userlogin.enteredPassword = input.next();
            System.out.println(userlogin.returnLoginStatus());
        }

        if (userlogin.loginUser()) {
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, "Welcome To EasyKanban");

            int choice;
            do {
                info.input = JOptionPane.showInputDialog(dialog, "Choose an option:\n1. Add tasks\n2. Show report\n3. Quit");
                choice = Integer.parseInt(info.input);

                switch (choice) {
                    case 1:
                        int numTasks = Integer.parseInt(JOptionPane.showInputDialog(dialog, "Enter the number of tasks:"));
                        Task task = new Task();
                        int totalHours = 0;

                        for (int i = 0; i < numTasks; i++) {
                            String taskName = JOptionPane.showInputDialog(dialog, "Enter task name:");
                            String taskDescription = JOptionPane.showInputDialog(dialog, "Enter task description:");
                            String developerFirstName = JOptionPane.showInputDialog(dialog, "Enter developer’s first name:");
                            String developerLastName = JOptionPane.showInputDialog(dialog, "Enter developer’s last name:");
                            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog(dialog, "Enter task duration:"));

                            String taskID = task.createTaskID(taskName, i, developerLastName);
                            String taskStatus = "";

                            int option = Integer.parseInt(JOptionPane.showInputDialog(dialog, "Please choose the Status of this task:\n1. To Do\n2. Doing\n3. Done"));

                            switch (option) {
                                case 1:
                                    taskStatus = "To Do";
                                    break;
                                case 2:
                                    taskStatus = "Doing";
                                    break;
                                case 3:
                                    taskStatus = "Done";
                                    break;
                            }

                            String taskDetails = task.printTaskDetails(taskStatus, developerFirstName, developerLastName, i, taskName, taskDescription, taskID, taskDuration);
                            JOptionPane.showMessageDialog(dialog, taskDetails);

                            totalHours += taskDuration;
                        }

                        JOptionPane.showMessageDialog(dialog, "Total hours: " + totalHours);
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(dialog, "Coming Soon");
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(dialog, "Exiting the application.");
                        break;

                    default:
                        JOptionPane.showMessageDialog(dialog, "Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 3);

            dialog.dispose();  // Disposing the dialog 
        }
    }
}