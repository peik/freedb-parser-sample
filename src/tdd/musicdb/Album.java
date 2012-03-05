package tdd.musicdb;

import java.util.ArrayList;
import java.util.List;

public class Album {

	private String title;
	private int year;
	private String genre;
	private String discId;
	private List<Song> songs = new ArrayList<Song>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDiscId() {
		return discId;
	}

	public void setDiscId(String discId) {
		this.discId = discId;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void addSong(Song song) {
		this.songs.add(song);
	}

	public int getTotalDurationSecs() {
		int secs = 0;
		for (Song song : songs) {
			secs += song.getDurationSecs();
		}
		return secs;
	}

	/*
	Adele / Adele 21	Details
	Tracks: 13
	Total time: 54:33
	Year: 2011
	Disc-ID: rock / c00ccd0d

	1.	Rolling in the Deep	3:49
	2.	Rumour Has It	3:43
	3.	Turning Tables	4:10
	4.	Don't You Remember	4:03
	5.	Set Fire to the Rain	4:01
	6.	He Won't Go	4:37
	7.	Take It All	3:48
	8.	I'll Be Waiting	4:01
	9.	One and Only	5:48
	10.	Lovesong	5:16
	11.	Someone Like You	4:43
	12.	If It Hadn't Been for Love	3:08
	13.	Hiding My Heart	3:26
	*/
	public String getListing() {
		String newline = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append(title).append("\t").append("Details").append(newline);
		sb.append("Tracks: ").append(songs.size()).append(newline);
		sb.append("Total time: ")
				.append(Song.formatDuration(getTotalDurationSecs()))
				.append(newline);
		sb.append("Year: ").append(year).append(newline);
		sb.append("Disc-ID: ").append(genre).append(" / ").append(discId)
				.append(newline);
		sb.append(newline);
		sb.append(" ").append(newline);
		int number = 1;
		for (Song song : songs) {
			sb.append(number++).append(".\t").append(song.getTitle());
			sb.append("\t").append(song.getDurationFormatted()).append(newline);
		}
		return sb.toString();
	}
}
