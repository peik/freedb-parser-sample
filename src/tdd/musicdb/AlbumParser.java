package tdd.musicdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlbumParser {

	private static final int TRACK_FRAMES_PER_SEC = 75;

	private Album album = new Album();
	private String filename;
	List<Integer> offsets = new ArrayList<Integer>();;
	int discLengthSecs;

	public AlbumParser(String filename) throws FileNotFoundException {
		this.filename = filename;
		Map<String, String> data = parseDataFile();
	}

	public Album getAlbum() {
		return album;
	}

	private Map<String, String> parseDataFile() throws FileNotFoundException {
		Map<String, String> data = new HashMap<String, String>();
		File file = AlbumFileFinder.getInstance().getFile(filename);
		FileInputStream in = new FileInputStream(file);
		Scanner scanner = new Scanner(in);
		try {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("#")) {

				} else {
					int idx = line.indexOf('=');
					if (idx >= 0) {
						String key = line.substring(0, idx);
						String value = line.substring(idx);
						data.put(key, value);
					}
				}
			}
		} finally {
			scanner.close();
		}
		return data;
	}

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
