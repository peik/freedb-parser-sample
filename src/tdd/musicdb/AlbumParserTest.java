package tdd.musicdb;

import java.util.List;

import org.approvaltests.Approvals;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class AlbumParserTest {

	@Test
	public void testCalculateSongLengths() throws Exception {
		// Data copied from 12025d03.txt
		AlbumParser parser = new AlbumParser();
		parser.offsets.add(150);
		parser.offsets.add(12143);
		parser.offsets.add(31682);
		parser.discLengthSecs = 607;
		List<Integer> lengthsSecs = parser.calculateSongLengths();
		Approvals.approve(lengthsSecs.toString());
		// Expected result:
		// 1. I'm Your Puppet 2:39 = 159s
		// 2. Me And Jacky 4:20 = 260s
		// 3. If You Don't Like Country 3:05 = 185s
		// Total time: 10:04
	}

	@Test
	public void testParse() throws Exception {
		AlbumParser parser = AlbumFileFinder.getInstance()
				.parse("c00ccd0d.txt");
		Approvals.approve(new XStream().toXML(parser));
	}

	@Test
	public void testGetAlbum() throws Exception {
		AlbumParser parser = AlbumFileFinder.getInstance()
				.parse("c00ccd0d.txt");
		Album album = parser.getAlbum();
		Approvals.approve(new XStream().toXML(album));
	}

	@Test
	public void testGetAlbumListing() throws Exception {
		AlbumParser parser = AlbumFileFinder.getInstance()
				.parse("c00ccd0d.txt");
		Album album = parser.getAlbum();
		Approvals.approve(album.getListing());
	}

	@Test
	public void testCorruptDiscLength() throws Exception {
		AlbumParser parser = AlbumFileFinder.getTestDataInstance().parse(
				"corrupt_disc_length.txt");
		Album album = parser.getAlbum();
		Approvals.approve(album.getListing());
	}

	@Test
	public void testCorruptOffsetInteger() throws Exception {
		AlbumParser parser = AlbumFileFinder.getTestDataInstance().parse(
				"corrupt_offset_integer.txt");
		Album album = parser.getAlbum();
		Approvals.approve(album.getListing());
	}

	@Test
	public void testCorruptOffsetLastLineMissing() throws Exception {
		AlbumParser parser = AlbumFileFinder.getTestDataInstance().parse(
				"corrupt_offset_last_line_missing.txt");
		Album album = parser.getAlbum();
		Approvals.approve(album.getListing());
	}

	@Test
	public void testCorruptSong7Missing() throws Exception {
		AlbumParser parser = AlbumFileFinder.getTestDataInstance().parse(
				"corrupt_song_7_missing.txt");
		Album album = parser.getAlbum();
		Approvals.approve(album.getListing());
	}

}
