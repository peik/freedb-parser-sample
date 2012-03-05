package tdd.musicdb;

public class Artist {

	private String name;

	public Artist(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
