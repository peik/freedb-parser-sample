package tdd.musicdb;

import java.io.File;

public class AlbumFileFinder {

	public static AlbumFileFinder getInstance() {
		return new AlbumFileFinder();
	}

	private AlbumFileFinder() {
	}

	public File getFile(String filename) {
		String path = "src/tdd/musicdb/data/" + filename;
		return new File(path);
	}

}
