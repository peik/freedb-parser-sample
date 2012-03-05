package tdd.musicdb;

import java.util.List;

import org.approvaltests.Approvals;
import org.junit.Test;

public class AlbumParserTest {

	@Test
	public void testCalculateSongLengths() throws Exception {
		AlbumParser parser = new AlbumParser("12025d03.txt");
		parser.offsets.add(150);
		parser.offsets.add(12143);
		parser.offsets.add(31682);
		parser.discLengthSecs = 607;
		List<Integer> lengthsSecs = parser.calculateSongLengths();
		Approvals.approve(lengthsSecs.toString());
		// 1. I'm Your Puppet 2:39 = 159s
		// 2. Me And Jacky 4:20 = 260s
		// 3. If You Don't Like Country 3:05 = 185s
		// Total time: 10:04
	}

	@Test
	public void testParse() throws Exception {
		AlbumParser parser = new AlbumParser("c00ccd0d.txt");
		Album album = parser.getAlbum();
		Approvals.approve(album.getListing());
	}

}
