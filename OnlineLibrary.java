/*
* Developed by - Ryan Dcunha
* Worked on this from 15-05-2023 to 19-05-2023
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Library {
    private String[] books = {
            "In Search of Lost Time by Marcel Proust",
            "Ulysses by James Joyce",
            "Don Quixote by Miguel de Cervantes",
            "One Hundred Years of Solitude by Gabriel Garcia Marquez",
            "The Great Gatsby by F. Scott Fitzgerald",
            "Moby Dick by Herman Melville",
            "War and Peace by Leo Tolstoy",
            "Hamlet by William Shakespeare",
            "The Odyssey by Homer",
            "Madame Bovary by Gustave Flaubert",
            "The Divine Comedy by Dante Alighieri",
            "Lolita by Vladimir Nabokov",
            "The Brothers Karamazov by Fyodor Dostoyevsky",
            "Crime and Punishment by Fyodor Dostoyevsky",
            "The Catcher in the Rye by J. D. Salinger",
    };
    String[] issued_books = new String[10];
    int[] issued_books_id = new int[10];

    public void addBook(String newBook) throws InterruptedException {
        System.out.println("\nADDING A BOOK");
        List<String> b = new ArrayList<>(Arrays.asList(books)); //converting array of books to array list
        b.add(newBook); // adding the book
        books = b.toArray(books);// converting the list back to array
        Thread.sleep(3000);
        System.out.println("Book added Successfully!");
    }
    public void issueBook(int book_id) throws InterruptedException {
        System.out.println("\nISSUING A BOOK");
        if (books == null || (book_id - 1) < 0 || (book_id - 1) >= books.length) {
            System.out.println("Sorry! Book not found");
            for (int id : issued_books_id) {
                if (book_id == id) {
                    System.out.println("Sorry! This book has already been issued");
                    break;
                }
            }
        } else {
            for (int id = 0; id < books.length; id++) { // checking for the books in the book list
                if (id == (book_id - 1)) { // if issued book id matches to the user input
                    for (int i = 0; i < issued_books.length; i++) { //going through the array of issued books
                        if (issued_books[i] == null) { // finding the location to store the issued book
                            issued_books[i] = books[id]; // storing the book in the issued books array
                            issued_books_id[i] = id + 1; // storing the idn the issued books id array
                            books[id] = "(issued)" + books[id];
                            Thread.sleep(3000);
                            System.out.println("Issued book: " + issued_books[i] + "\nBook ID = " + (id + 1));
                            break;
                        }
                    }
                }
            }
        }
    }
    public void returnBook(int book_id) throws InterruptedException {
        System.out.println("\nRETURNING THE BOOK");
        boolean returned = false;
        for (int id = 0; id < issued_books_id.length; id++) { // going through the issued book id list
            if (book_id == issued_books_id[id]) { // if the book id is present in the issued book id list
                issued_books_id[id] = 0; // reset the id slot to 0
                issued_books[id] = null; // reset the book slot to null
                returned = true;
                break;
            }
        }
        for (int id = 0; id < books.length; id++){
            if (books[id].startsWith("(issued)"))
                books[id] = books[id].substring(8); // removing the (issued) keyword
        }
        Thread.sleep(3000);
        if (returned)
            System.out.println("Book returned successfully");
        else
            System.out.println("Book not issued");
    }
    public void showAvailableBooks() throws InterruptedException {
        System.out.println("\nBooks Currently Available are -");
        Thread.sleep(3000);
        for (int srNo = 0; srNo < books.length; srNo++)
            System.out.println(srNo + 1 + ". " + books[srNo]);
//        System.out.println("\nIssued Books are"); // printing the issued books (not necessary so commented out)
//        for (int srNo = 0; srNo < books.length; srNo++)
//            System.out.println(issued_books_id[srNo] + ". " + issued_books[srNo]);
    }
}

public class ONLINE_LIBRARY {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("WELCOME TO THE ONLINE LIBRARY");
        Library onlineLib= new Library();
        Scanner sc = new Scanner(System.in);
        int choice;
        Thread.sleep(1000);
        do{
            System.out.print("\nWhat you wanna do??\n1.Add a book\n2.Issue a book\n3.Return a book\n4.Show Available Books\nPress 0 to exit\n  Answer here --> ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: // adding a book
                    System.out.print("Enter the name of the book you wanna add - ");
                    String noUse = sc.nextLine(); // this line was forcefully added to avoid error occurs cuz it takes int before on line 80
                    String newBook = sc.nextLine(); // details->https://www.geeksforgeeks.org/why-is-scanner-skipping-nextline-after-use-of-other-next-functions/
                    onlineLib.addBook(newBook);
                    continue;
                case 2 :// issuing a book
                    System.out.print("Choose the book you wanna issue by its serial number - ");
                    int issueBookId = sc.nextInt();
                    onlineLib.issueBook(issueBookId);
                    continue;
                case 3: // returning a book
                    System.out.print("Enter the book you wanna return by its serial number - ");
                    int returnBookId = sc.nextInt();
                    onlineLib.returnBook(returnBookId);
                    continue;
                case 4: // showing the available books
                    onlineLib.showAvailableBooks();
                    continue;
                case 0: // to terminate the program
                    System.out.println("Quitting the program...");
                    break;
                default:
                    System.out.println("Invalid. Chose again");
            }
        }while (choice!=0);
    }
}
