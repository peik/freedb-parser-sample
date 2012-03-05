package tdd.musicdb;

import org.approvaltests.Approvals;
import org.junit.Test;

public class AlbumTest {

	@Test
	public void testListing() throws Exception {
		Album a = new Album();
		a.setArtist(new Artist("Adele"));
		a.setTitle("21");
		a.setYear(2011);
		a.setGenre("blues");
		a.setDiscId("a1234567");
		a.addSong(new Song("Rolling in the Deep", 229));
		a.addSong(new Song("Rumour Has It", 223));
		Approvals.approve(a.getListing());
	}
}
