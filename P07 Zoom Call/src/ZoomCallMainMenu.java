//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Zoom Call - ZoomCallMainMenu
// Course:   CS 300 Summer 2024
//
// Author:   Winston Chan
// Email:    wchan35@wisc.edu
// Lecturer: (Andy Kuemmel)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         NONE
// Online Sources:  ZyBooks Ch.9
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
/**
 * Runs menu to maintain a Zoom Call.
 * One option of the main menu is to run breakout rooms.
 * In this case, a submenu runs until the breakout rooms close.
 * This code will not compile and run until the ZoomCall class is working correctly.
 * @author Andy Kuemmel
 * @author Winston Chan
 */
public class ZoomCallMainMenu {
    // static class variables .. available to all static methods
    private static final int MAX_ROOMS = 9; // Maximum number of breakout rooms
    private static ZoomCall call = null;    // ZoomCall instance
    private static Scanner kb = null;       // Scanner for user input

    /**
     * The main method that calls the menu and the functions to be printed to the screen.
     * @param args
     */
    public static void main(String[] args) {
        call = new ZoomCall("CS 300");
        boolean done = false;
        kb = new Scanner(System.in);
        int userInput = 0;
        while (!done) {
            printMainRoomMenu();
            try {
                userInput = Integer.parseInt(kb.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Add a participant.");
                        System.out.println("Enter a name for this participant: ");
                        String name = kb.nextLine();
                        call.addToCall(name);
                        break;
                    case 2:
                        System.out.println("Remove a participant.");
                        System.out.println("Enter the participant's index: ");
                        ZoomParticipant p = call.dropFromCall(Integer.parseInt(kb.nextLine()));
                        break;
                    case 3:
                        System.out.println("Mute a participant.");
                        System.out.println("Enter the participant's index: ");
                        call.mute(Integer.parseInt(kb.nextLine()));
                        break;
                    case 4:
                        call.muteAll();
                        break;
                    case 5:
                        call.unMuteAll();
                        break;
                    case 6:
                        int numRooms = 0;
                        do {
                            System.out.println("Enter the number of breakout rooms (1-" + MAX_ROOMS + ") : ");
                            numRooms = Integer.parseInt(kb.nextLine());
                        } while (numRooms < 1 || numRooms > MAX_ROOMS); // change the boolean expression
                        call.startBreakoutRooms(numRooms);
                        runBreakoutRooms();
                        break;
                    case 7:
                        System.out.println("Change a participant's name.");
                        System.out.println("Enter the participant's index: ");
                        int index = Integer.parseInt(kb.nextLine());
                        System.out.println("Enter the participant's new name: ");
                        String newName = kb.nextLine();
                        call.changeName(index, newName);
                        break;
                    case 8:
                        done = true;
                        break;
                    default:
                        System.out.println("The number " + userInput + " is not an option.");
                } // switch
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer to state your choice.\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } // while
        System.out.println("Thank you for using the Zoom Call app!");
    }// method

    /**
     * Prints the main room menu to the console.
     */
    private static void printMainRoomMenu() {
        call.printMainRoom();
        System.out.println("\nYou are in the Zoom Main Room. Here are your choices:");
        System.out.println("1. Add a participant.");
        System.out.println("2. Remove a participant.");
        System.out.println("3. Mute a participant.");
        System.out.println("4. Mute all participants.");
        System.out.println("5. Unmute all participants.");
        System.out.println("6. Begin Breakout Rooms.");
        System.out.println("7. Change a participant's name.");
        System.out.println("8. End the call.");
        System.out.println("---------------------------------");
        System.out.println("What is your choice? ");
    }

    /**
     * Prints the breakout room menu to the console.
     */
    private static void printBreakoutRoomMenu() {
        call.printBreakoutRooms();
        System.out.println("\nYou are in the Zoom Breakout Rooms. Here are your choices:");
        System.out.println("1. Remove a participant.");
        System.out.println("2. Change a participant's room.");
        System.out.println("3. End breakout rooms.");
        System.out.println("---------------------------------");
        System.out.println("What is your choice? ");
    }

    /**
     * Runs the breakout room submenu, allowing the user to manage breakout rooms.
     */
    private static void runBreakoutRooms() {
        boolean done = false; // done is a local variable so we can resuse it
        int userInput = 0;
        while (!done) {
            printBreakoutRoomMenu();
            userInput = Integer.parseInt(kb.nextLine());
            switch (userInput) {
                case 1: // remove
                    System.out.println("Remove a participant.");
                    System.out.println("Enter a room number.");
                    try {
                        int roomNumber = Integer.parseInt(kb.nextLine());
                        System.out.println("Enter a participant's index number.");
                        int indexNumber = Integer.parseInt(kb.nextLine());
                        call.dropFromCall(roomNumber, indexNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter an integer.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2: // change
                    System.out.println("Change a participant's room.");
                    System.out.println("Enter a room number.");
                    try {
                        int roomNumber = Integer.parseInt(kb.nextLine());
                        System.out.println("Enter a participant's index number.");
                        int indexNumber = Integer.parseInt(kb.nextLine());
                        System.out.println("Enter the new room number.");
                        int newRoomNumber = Integer.parseInt(kb.nextLine());
                        call.changeRooms(roomNumber, indexNumber, newRoomNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter an integer.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3: // end
                    System.out.println();
                    call.endBreakoutRooms();
                    done = true;
                    break;
                default:
                    System.out.println("The number " + userInput + " is not an option.");
            } // switch
        } // while
    }// method
}
