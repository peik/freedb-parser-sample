package tdd.musicdb;

import java.text.DecimalFormat;

public class Song {

	private String title;
	private int durationSecs;

	public Song(String title, int durationSecs) {
		this.title = title;
		this.durationSecs = durationSecs;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDurationSecs() {
		return durationSecs;
	}

	public void setDurationSecs(int durationSecs) {
		this.durationSecs = durationSecs;
	}

	public String getDurationFormatted() {
		return formatDuration(durationSecs);
	}

	public static String formatDuration(int secs) {
		int mins = secs / 60;
		int remaining = secs % 60;
		DecimalFormat secFormat = new DecimalFormat();
		secFormat.setMinimumIntegerDigits(2);
		return mins + ":" + secFormat.format(remaining);
	}
}
