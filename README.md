Simple Source Code Control (SCCC)
============================

Source Code Control Project for CSC444
Authors: Vuk Skulic & Chris Wanes

Assignment Requirements:

- Design and develop a simple command-line source code control system. 
  The system should be capable of satisfying the following requirements.
- Handle ASCII text files only.
- Put a file under source code control.
- Check in a modified version of a file after the user edits it.
- Allow the user to associate a comment with each check-in.
- Assign version numbers to all check-ins.
- Check out the most current version of a file.
- Check out any older version of a file by version number.
- List all versions of a file with their associated check-in comment.
- Create a new source control branch for a file associating a unique user-chosen
  identifier with the new branch (the original branch should be called "main").
- All of the above requirements should be satisfyable equally for all branches.
- Generate a suggested file as the next version for branch X based on the last
  change applied to branch Y (where X and Y can be any non-equal branch names).

