package tdd.musicdb;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import tdd.util.TestResult;

public class AlbumFileFinderTest {

	@Test
	public void testFindFile() {
		AlbumFileFinder r = AlbumFileFinder.getInstance();
		File file = r.getFile("12025d03.txt");
		System.out.println(file);
		System.out.println(file.getAbsolutePath());
		assertTrue(file.exists());
	}

	@Test
	public void testLoadAllAlbums() throws Exception {
		AlbumFileFinder finder = AlbumFileFinder.getInstance();
		List<Album> albums = finder.getAllAlbums();

		TestResult result = new TestResult();
		for (Album album : albums) {
			result.log("------------------------------------------------------------------");
			result.log(album.getListing());
		}
		result.approve();
	}

}
