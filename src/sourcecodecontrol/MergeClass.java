package sourcecodecontrol;

import difflib.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MergeClass {

	public void diff() {

		String[] pathX = new String[5];
		String[] pathY = new String[5];

		System.out.println("Enter the name of the file (with extension):");
		Scanner uIS = new Scanner(System.in);
		String filename = uIS.next();

		System.out
				.println("Enter the name of the branch you want to use as your 'base branch' (referred to as branch X in the assignment instructions): ");
		String branchX = uIS.next();

		System.out
				.println("Enter the name of the branch you want to use as the branch that suggestions will be drawn from (referred to as branch Y in the assignment instructions): ");
		String branchY = uIS.next();
		
		int x = Helper.countFileVersions(Helper.RepoPath + File.separator + Helper.stripExtension(filename) + File.separator + branchX);
		int y = Helper.countFileVersions(Helper.RepoPath + File.separator + Helper.stripExtension(filename) + File.separator + branchY);
		try {
			pathX = Helper.getPathToFileVersion(filename, branchX, x);
			pathY = Helper.getPathToFileVersion(filename, branchY, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> original = Helper.ConvertFileToLines(pathX[0]);		
		List<String> revised = Helper.ConvertFileToLines(pathY[0]);

		Patch patch = DiffUtils.diff(original, revised);

		for (Delta delta : patch.getDeltas()) {
			System.out.println(delta);
		}
	}
}
