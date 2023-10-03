package src;

import java.io.*;

public class MusicAlbumCollection {
	private int count;
	private MusicAlbum[] albums;

	// Constructor to initialize the collection
	public MusicAlbumCollection() {
		count = 0;
	}

	private void resize(int newCapacity) {
		MusicAlbum[] newAlbums = new MusicAlbum[newCapacity];

		if (count >= 0 && albums != null)
			System.arraycopy(albums, 0, newAlbums, 0, count);

		albums = newAlbums;
	}

	// Method to add a new MusicAlbum object to the collection
	public void add(MusicAlbum newAlbum) {
		if (albums == null)
			resize(1);
		else if (count >= albums.length)
			resize(albums.length * 2);
		albums[count] = newAlbum;
		count++;
	}

	// Method to remove a MusicAlbum object from the collection by its index
	public void remove(int index) {
		if (index >= 0 && index < count) {
			for (int i = index; i < count - 1; i++) {
				albums[i] = albums[i + 1];
			}
			count--;
			if (count <= albums.length / 2)
				resize(albums.length / 2);
		} else {
			System.out.println("Invalid index.");
		}
	}

	// Method to print detailed information about an item at the specified index
	public void printOne(int index) {
		if (index >= 0 && index < count) {
			System.out.println(albums[index].getAlbumDescription());
		} else {
			System.out.println("Invalid index.");
		}
	}

	// Method to print the entire list of albums in the collection
	public void print() {
		for (int i = 0; i < count; i++) {
			System.out.println("Album " + (i + 1) + ":");
			System.out.println(albums[i].getAlbumDescription());
			System.out.println();
		}
	}

	// Method to print title of each album in the collection
	public void printList() {
		for (int i = 0; i < count; i++) {
			System.out.println((i + 1) + ". " + albums[i].getTitle());
		}
	}

	// Method to search and print albums by title
	public void search(String phrase) {
		boolean found = false;

		for (int i = 0; i < count; i++) {
			if (albums[i].getTitle().toLowerCase().contains(phrase.toLowerCase()) ||
					albums[i].getArtist().toLowerCase().contains(phrase.toLowerCase())) {
				System.out.println("Matching album found:");
				System.out.println(albums[i].getAlbumDescription());
				found = true;
			}
		}
		if (!found) {
			System.out.println("Album with title '" + phrase + "' not found.");
		}
	}

	// Method to sort albums by release year using bubble sort
	public void sort() {
		for (int i = 0; i < count - 1; i++) {
			for (int j = 0; j < count - 1 - i; j++) {
				if (albums[j].getReleaseYear() > albums[j + 1].getReleaseYear()) {
					// Swap albums[j] and albums[j+1]
					MusicAlbum temp = albums[j];
					albums[j] = albums[j + 1];
					albums[j + 1] = temp;
				}
			}
		}
	}

	// Method to search and print albums by additional properties (e.g., release year)
	public void searchByProperty(int year) {
		boolean found = false;

		for (int i = 0; i < count; i++) {
			if (albums[i].getReleaseYear() == year) {
				System.out.println("Matching album found:");
				System.out.println(albums[i].getAlbumDescription());
				found = true;
			}
		}
		if (!found) {
			System.out.println("No albums released in the year " + year + " found.");
		}
	}

	private String getAll() {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < count; i++) {
			output.append(albums[i].getAlbumDescription()).append("\n");
		}

		return output.toString();
	}

	// Method to save the collection to a file
	public void saveToFile(String path) {
		try {
			// Create a FileWriter object with the specified file path
			FileWriter fileWriter = new FileWriter(path);

			// Create a BufferedWriter to write efficiently
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Write the content to the file
			bufferedWriter.write(getAll());

			// Close the BufferedWriter to flush and release resources
			bufferedWriter.close();

			System.out.println("Content has been written to the file successfully.");
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}

	// Method to read the collection from a file
	public void readFromFile(String path) {
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}
}
