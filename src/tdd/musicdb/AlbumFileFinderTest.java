package tdd.musicdb;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class AlbumFileFinderTest {

	@Test
	public void testFindFile() {
		AlbumFileFinder r = AlbumFileFinder.getInstance();
		File file = r.getFile("12025d03.txt");
		System.out.println(file);
		System.out.println(file.getAbsolutePath());
		assertTrue(file.exists());
	}

}
