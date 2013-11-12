/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        do{
            System.out.println("[Commit | Checkout | Revert | Merge | Branch | Delete | Exit]");
            System.out.print("What Is Thy Bidding? ");
          
            B = uIS.next();
            EchoString(B);       
        }while(ProcessCommand(B) != 1);
    }
    
    public static int EchoString(String string){
        System.out.print(string + '\n');
        return 0;
    }
    
    public static int ProcessCommand(String cmd){
        cmd = cmd.toLowerCase();
        switch(cmd){
            case "checkout":
            {
                EchoString("Starting Checkout...");
                return EchoString("You Have Checked Out Your Files.");
            }
            case "commit": 
            {
                EchoString("Starting Commit...");
                return EchoString("You Have Committed Your Files.");
            }
            case "revert":
            {
                EchoString("Starting Revert...");
                return EchoString("You Have Reverted Your Files.");
            }
            case "merge":
            {
                EchoString("Starting Merge...");
                return EchoString("You Have Merged Your Files.");
            }
            case "branch":
            {
                EchoString("Starting Branch...");
                return EchoString("You Have Branched Your Source Code.");
            }
            case "delete":
            {
                return EchoString("You Have Deleted Your Files.");
            }
            case "exit": 
            {
                EchoString("You Wish To Leave Me; Goodbye!");
                return 1;
            }
            default:
            {
                return EchoString("Please Input A Legitimate Command");
            }
        }
    }
}
