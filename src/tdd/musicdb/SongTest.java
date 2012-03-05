package tdd.musicdb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SongTest {

	@Test
	public void testFormatDuration() {
		assertEquals("0:30", Song.formatDuration(30));
		assertEquals("1:00", Song.formatDuration(60));
		assertEquals("60:11", Song.formatDuration(3611));
	}
}
