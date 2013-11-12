package sourcecodecontrol;

import java.util.Scanner;
import java.lang.String;


/**
 *
 * @author Khatchadour Wanes
 */
public class SourceCodeControl {
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner uIS = new Scanner(System.in);
        String B = "";
        System.out.println("[Commit | Checkout | Revert | Merge | Branch | Delete | Exit]");
        System.out.print("What Is Thy Bidding? ");
        do{
            B = uIS.next();
            EchoString(B);       
        }while(ProcessCommand(B) == 0);
    }
    
    public static int EchoString(String string){
        System.out.print(string + '\n');
        return 0;
    }
    
    public static int ProcessCommand(String cmd){
        if (cmd.equalsIgnoreCase("Checkout"))
        {
            EchoString("Starting Checkout...");
            return EchoString("You Have Checked Out Your Files.");
        }
        else if (cmd.equalsIgnoreCase("Commit"))
        {
            EchoString("Starting Commit...");
            return EchoString("You Have Committed Your Files.");
        }
        else if (cmd.equalsIgnoreCase("Revert"))
        {
            EchoString("Starting Revert...");
            return EchoString("You Have Reverted Your Files.");
        }
        else if (cmd.equalsIgnoreCase("Merge"))
        {
            EchoString("Starting Merge...");
            return EchoString("You Have Merged Your Files.");
        }
        else if (cmd.equalsIgnoreCase("Branch"))
        {
            EchoString("Starting Branch...");
            return EchoString("You Have Branched Your Source Code.");
        }
        else if (cmd.equalsIgnoreCase("Delete"))
        {
            return EchoString("You Have Deleted Your Files.");
        }
        else if (cmd.equalsIgnoreCase("Exit")) 
        {
            EchoString("You Wish To Leave Me; Goodbye!");
            return 1;
        }
        
        else{
        	return EchoString("Please Input A Valid Command!");
        }
    }
}
