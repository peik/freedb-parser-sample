package tdd.musicdb;

import java.io.File;

public class AlbumFileFinder {

	private String location;

	public static AlbumFileFinder getInstance() {
		return new AlbumFileFinder("src/tdd/musicdb/data/");
	}

	static AlbumFileFinder getTestDataInstance() {
		return new AlbumFileFinder("src/tdd/musicdb/testdata/");
	}

	private AlbumFileFinder(String location) {
		this.location = location;
	}

	public File getFile(String filename) {
		String path = location + filename;
		return new File(path);
	}

}
