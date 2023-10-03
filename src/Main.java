package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static int getUserInt(Scanner scanner) {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("\u001B[31mIt is not a number. Try again: \u001B[0m");
			}
		}
	}

	public static double getUserDouble(Scanner scanner) {
		while (true) {
			try {
				return scanner.nextDouble();
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("\u001B[31mIt is not a number. Try again: \u001B[0m");
			}
		}
	}

	public static void search(Scanner scanner, MusicAlbumCollection albumCollection) {
		System.out.print("Enter a phrase to search for: ");
		String title = scanner.nextLine();
		albumCollection.search(title);
	}

	public static void inputItems(Scanner scanner, MusicAlbumCollection albumCollection) {
		System.out.println("Adding new albums to the collection. Enter 'stop' to finish.");
		while (true) {
			System.out.print("Enter album title: ");
			String title = scanner.nextLine();
			if (title.equalsIgnoreCase("stop")) {
				break;
			}

			System.out.print("Enter artist name: ");
			String artist = scanner.nextLine();

			System.out.print("Enter release year: ");
			int releaseYear = getUserInt(scanner);
			scanner.nextLine(); // Consume newline character

			System.out.print("Enter album price: ");
			double price = getUserDouble(scanner);
			scanner.nextLine(); // Consume newline character

			MusicAlbum newAlbum = new MusicAlbum(title, artist, releaseYear, price);
			albumCollection.add(newAlbum);
		}
	}

	private static void searchByYear(Scanner scanner, MusicAlbumCollection albumCollection) {
		System.out.print("Enter release year to search for albums: ");
		int year = getUserInt(scanner);
		scanner.nextLine(); // Consume newline character
		albumCollection.searchByProperty(year);
	}

	private static void removeItem(Scanner scanner, MusicAlbumCollection albumCollection) {
		System.out.print("Enter index of album to remove: ");
		int index = getUserInt(scanner);
		scanner.nextLine(); // Consume newline character
		albumCollection.remove(index);
	}

	private static void readFromFile(Scanner scanner, MusicAlbumCollection albumCollection) {
		System.out.print("Enter file name to read from: ");
		String fileName = scanner.nextLine();
		albumCollection.readFromFile(fileName);
	}

	private static void saveToFile(Scanner scanner, MusicAlbumCollection albumCollection) {
		System.out.print("Enter file name to save to: ");
		String fileName = scanner.nextLine();
		albumCollection.saveToFile(fileName);
	}

	private static void printMenu() {
		System.out.println("Choose an option to proceed:");
		System.out.println("1 - Add albums to collection");
		System.out.println("2 - Print albums list");
		System.out.println("3 - Sort albums by release year");
		System.out.println("4 - Search albums by a phrase");
		System.out.println("5 - Search albums by release year");
		System.out.println("6 - Remove an album from the collection");
		System.out.println("7 - Print detailed albums list");
		System.out.println("8 - Read albums from a file");
		System.out.println("9 - Save albums to a file");
		System.out.println("0 - Exit the program\n");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MusicAlbumCollection albumCollection = new MusicAlbumCollection();

		System.out.println("Welcome to the music album database!");
		int choice;

		do {
			printMenu();

			choice = getUserInt(scanner);
			scanner.nextLine(); // Consume newline character

			switch (choice) {
				case 1 -> inputItems(scanner, albumCollection);
				case 2 -> albumCollection.printList();
				case 3 -> {
					albumCollection.sort();
					System.out.println("Albums sorted by release year.");
				}
				case 4 -> search(scanner, albumCollection);
				case 5 -> searchByYear(scanner, albumCollection);
				case 6 -> removeItem(scanner, albumCollection);
				case 7 -> albumCollection.print();
				case 8 -> readFromFile(scanner, albumCollection);
				case 9 -> saveToFile(scanner, albumCollection);
				case 0 -> System.out.println("Exiting the program.");
				default -> System.out.println("Invalid choice. Please choose a valid option.");
			}

			System.out.println();
		} while (choice != 0);

		scanner.close();
	}
}
