package tdd.musicdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import tdd.util.FileScanner;

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

	public AlbumParser parse(String filename) throws FileNotFoundException {
		File file = getFile(filename);
		return new AlbumParser(file);
	}

	public Album getAlbum(String filename) throws FileNotFoundException {
		return parse(filename).getAlbum();
	}

	public List<Album> getAllAlbums() throws FileNotFoundException {
		List<Album> albums = new ArrayList<Album>();
		FileScanner scanner = new FileScanner(location);
		List<String> files = scanner.listTxtFiles();
		for (String filename : files) {
			albums.add(getAlbum(filename));
		}
		return albums;
	}
}
