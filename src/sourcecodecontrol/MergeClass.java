package sourcecodecontrol;

import difflib.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MergeClass {
	// Helper method for get the file content
	private List<String> fileToLines(String filename) {
		List<String> lines = new LinkedList<String>();
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public void diff() {
		List<String> original = fileToLines("originalFile.txt");
		List<String> revised = fileToLines("revisedFile.xt");

		// Compute diff. Get the Patch object. Patch is the container for
		// computed deltas.
		
		System.out.println("Testing!");
		Patch patch = DiffUtils.diff(original, revised);

		for (Delta delta : patch.getDeltas()) {
			System.out.println(delta);
		}
	}
}
