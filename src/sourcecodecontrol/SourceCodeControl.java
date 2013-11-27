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
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        Scanner uIS = new Scanner(System.in);
        String B = "";
        System.out.println("[Commit | Checkout | GetHistory | Merge | Branch | Delete | Exit]");
        System.out.print("What Is Thy Bidding? ");
        do{
            B = uIS.next();
            Helper.EchoString(B);       
        }while(ProcessCommand(B) == 0);
    }
    
 
    public static int ProcessCommand(String cmd) throws Exception{
        if (cmd.equalsIgnoreCase("Checkout"))
        {
            Helper.EchoString("Starting Checkout...");
            CheckoutClass checkout = new CheckoutClass();
            checkout.checkoutMethod();
            Helper.EchoString("You Have Checked Out Your Files.");
            return Helper.EchoString("Please input another command.");
           
        }
        else if (cmd.equalsIgnoreCase("Commit"))
        {
        	Helper.EchoString("Starting Commit...");
            CommitClass commit = new CommitClass();
            commit.commitMethod();
            Helper.EchoString("You Have Committed Your Files.");
            return Helper.EchoString("Please input another command.");
        }
        else if (cmd.equalsIgnoreCase("GetHistory"))
        {
        	Helper.EchoString("You have requested a history.");
        	return Helper.EchoString("Please input another command.");
        }
        else if (cmd.equalsIgnoreCase("Merge"))
        {
        	Helper.EchoString("Starting Merge...");
            Helper.EchoString("You Have Merged Your Files.");
            return Helper.EchoString("Please input another command.");
        }
        else if (cmd.equalsIgnoreCase("Branch"))
        {
        	Helper.EchoString("Starting Branch...");
            Helper.EchoString("You Have Branched Your Source Code.");
            return Helper.EchoString("Please input another command.");
        }
        else if (cmd.equalsIgnoreCase("Delete"))
        {
            Helper.EchoString("You Have Deleted Your Files.");
            return Helper.EchoString("Please input another command.");
        }
        else if (cmd.equalsIgnoreCase("Exit")) 
        {
        	Helper.EchoString("You Wish To Leave Me; Goodbye!");
            return 1;
        }
        
        else{
        	return Helper.EchoString("Please Input A Valid Command!");
        }
    }
}
