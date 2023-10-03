package src;

public class MusicAlbum {
	private String title;
	private String artist;
	private int releaseYear;
	private double price;

	// Constructor with initial values
	public MusicAlbum(String title, String artist, int releaseYear, double price) {
		this.title = title;
		this.artist = artist;
		this.releaseYear = releaseYear;
		this.price = price;
	}

	// Getter and Setter methods
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Method to calculate the age of the album
	public int calculateAge() {
		int currentYear = java.time.Year.now().getValue();
		return currentYear - releaseYear;
	}

	// Method to get the full description of the album
	public String getAlbumDescription() {
		return "Title: " + title + "\nArtist: " + artist + "\nRelease Year: " + releaseYear + "\nPrice: $" + price;
	}
}

