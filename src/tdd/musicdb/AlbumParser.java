package tdd.musicdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AlbumParser {

	private static final int TRACK_FRAMES_PER_SEC = 75;

	// Parsed data
	// Visible for testing
	Map<String, String> data = new TreeMap<String, String>();
	List<Integer> offsets = new ArrayList<Integer>();;
	int discLengthSecs;

	public AlbumParser(String filename) throws FileNotFoundException {
		parseDataFile(filename);
	}

	// Visible for testing
	AlbumParser() {
	}

	public Album getAlbum() {
		// Album basic data
		Album album = new Album();
		album.setTitle(data.get(Keys.DTITLE.toString()));
		album.setGenre(data.get(Keys.DGENRE.toString()));
		album.setDiscId(data.get(Keys.DISCID.toString()));
		String year = data.get(Keys.DYEAR.toString());
		try {
			album.setYear(Integer.parseInt(year));
		} catch (NumberFormatException e) {
			System.err.print("Unable to parse year: " + year);
		}
		// Songs
		List<Integer> songLengths = calculateSongLengths();
		for (int i = 0; i < songLengths.size(); i++) {
			String title = data.get(Keys.TTITLE.toString() + i);
			Song song = new Song(title, songLengths.get(i));
			album.addSong(song);
		}
		return album;
	}

	private void parseDataFile(String filename) throws FileNotFoundException {
		File file = AlbumFileFinder.getInstance().getFile(filename);
		FileInputStream in = new FileInputStream(file);
		Scanner scanner = new Scanner(in);
		try {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("#")) {
					if (line.startsWith(Keys.Track_frame_offsets.toString())) {
						parseOffsets(scanner);
					} else if (line.startsWith(Keys.Disc_length.toString())) {
						parseDiscLength(line);
					}
				} else {
					int idx = line.indexOf('=');
					if (idx >= 0) {
						String key = line.substring(0, idx);
						String value = line.substring(idx + 1);
						data.put(key, value);
					}
				}
			}
		} finally {
			scanner.close();
		}
	}

	/*
	Parse data example:
	# Track frame offsets: 
	#        150
	#        17347
	*/
	private void parseOffsets(Scanner scanner) {
		try {
			while (scanner.hasNextLine()) {
				String offsetStr = scanner.nextLine().substring(1).trim();
				Integer offset = Integer.valueOf(offsetStr);
				offsets.add(offset);
			}
		} catch (NumberFormatException endOfOffsets) {
			// Stop when we find no more integer offsets
		}
	}

	/*
	Parse data example:
	# Disc length: 3279 seconds
	*/
	private void parseDiscLength(String line) {
		line = line.substring(Keys.Disc_length.toString().length()).trim();
		StringBuilder secStr = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (!Character.isDigit(c)) {
				// Stop at the first non-digit character
				break;
			}
			secStr.append(c);
		}
		if (secStr.length() > 0) {
			this.discLengthSecs = Integer.parseInt(secStr.toString());
		}
	}

	// Visible for testing
	List<Integer> calculateSongLengths() {
		List<Integer> lengths = new ArrayList<Integer>();
		int totalFrames = discLengthSecs * TRACK_FRAMES_PER_SEC
				+ TRACK_FRAMES_PER_SEC;
		offsets.add(totalFrames);
		Iterator<Integer> it = offsets.iterator();
		int firstOffset = it.next();
		int startFrame = firstOffset;
		while (it.hasNext()) {
			int offset = it.next();
			int frames = offset - startFrame;
			int secs = frames / TRACK_FRAMES_PER_SEC;
			lengths.add(secs);
			startFrame = offset;
		}
		return lengths;
	}

}
