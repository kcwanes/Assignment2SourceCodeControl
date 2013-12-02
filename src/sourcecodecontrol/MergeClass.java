package sourcecodecontrol;

import difflib.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MergeClass {
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

		String[] path = new String[2];

		System.out.println("Enter the name of the file (with exntension):");
		Scanner uIS = new Scanner(System.in);
		String filename = uIS.next();
		System.out.println("The filename is:");
		System.out.println(filename);

		System.out
				.println("Enter the name of the branch you want to use as your 'base branch' (referred to as branch X in the assignment instructions): ");
		String branchX = uIS.next();

		System.out
				.println("Enter the name of the branch you want to use as the branch that suggestions will be drawn from (referred to as branch Y in the assignment instructions): ");
		String branchY = uIS.next();
		
		List<String> original = fileToLines(filename);
		
		path = Helper.getPathToFileVersion(filename + ".txt", v);
		Helper.printFileToTerminal(path[0]);
		Helper.printFileToTerminal(path[1]);
		
		List<String> revised = fileToLines(filename);

		Patch patch = DiffUtils.diff(original, revised);

		for (Delta delta : patch.getDeltas()) {
			System.out.println(delta);
		}
	}
}
