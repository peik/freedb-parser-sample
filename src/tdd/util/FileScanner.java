package tdd.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * FileScanner lists all files in a given directory.
 */
public class FileScanner {

	/** Main directory containing the files to list */
	private final String dir;

	public FileScanner(String dir) {
		this.dir = dir;
	}

	public List<String> listTxtFiles() throws FileNotFoundException {
		File dir = new File(this.dir);

		String[] txtFiles = dir.list(this.txtFilter);
		if (txtFiles == null) {
			throw new FileNotFoundException("Directory " + dir + " not found");
		}

		return Arrays.asList(txtFiles);
	}

	// Anonymous FilenameFilter class to find only .txt files
	private final FilenameFilter txtFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			return name.endsWith(".txt");
		}
	};

}
