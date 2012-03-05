package tdd.musicdb;

public enum Keys {

	DISCID, DTITLE, DYEAR, DGENRE, TTITLE, Track_frame_offsets(
			"# Track frame offsets:"), Disc_length("# Disc length:");

	String key;

	private Keys() {
		this.key = name();
	}

	private Keys(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return this.key;
	}
}
