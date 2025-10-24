import java.io.*;
import java.util.Scanner;

public class NotesApp {

    // File name to store notes
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        System.out.println("** Welcome to Java Notes App **");

        // Main menu loop
        while (running) {
            System.out.println("\n-----------------------------");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear Notes");
            System.out.println("4. Exit");
            System.out.println("-----------------------------");
            System.out.print("Enter your choice: ");

            // Check if user entered valid number
            if (!input.hasNextInt()) {
                System.out.println("** Please enter a valid number! **");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine(); // newline

            switch (choice) {
                case 1:
                    addNote(input);
                    break;

                case 2:
                    viewNotes();
                    break;

                case 3:
                    clearNotes();
                    break;

                case 4:
                    running = false;
                    System.out.println("** Thank you for using Notes App! **");
                    break;

                default:
                    System.out.println("** Invalid choice! Try again. **");
            }
        }

        input.close();
    }

    // Method to add a new note
    private static void addNote(Scanner input) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            System.out.print("Enter your note: ");
            String note = input.nextLine();

            writer.write(note + System.lineSeparator());
            System.out.println("** Note saved successfully! **");
        } 
        catch (IOException e) {
            System.out.println(" Error saving note: " + e.getMessage());
        }
    }

    // Method to view all notes
    private static void viewNotes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("** No notes found. Add a note first! **");
            return;
        }

        System.out.println("\n ** Your Notes: **");
        System.out.println("------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean empty = true;

            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }

            if (empty) {
                System.out.println("(No notes available)");
            }
        } 
        catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    // Method to clear all notes
    private static void clearNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("");
            System.out.println("** All notes cleared successfully! **");
        } 
        catch (IOException e) {
            System.out.println(" Error clearing notes: " + e.getMessage());
        }
    }
}
