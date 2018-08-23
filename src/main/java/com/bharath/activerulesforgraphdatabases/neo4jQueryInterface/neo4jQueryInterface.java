/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases.neo4jQueryInterface;

import static com.bharath.activerulesforgraphdatabases.neo4jQueryInterface.validateRule.findRules;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author bharathvadlamannati
 */
public class neo4jQueryInterface {
    public static void main (String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        
            System.out.print("Hello User. To Test please select an option: 1. Time Based 2. Non-Time Based\n");
            int usertestingnumber = input.nextInt();
            System.out.println("You entered " + usertestingnumber);
            
            try (Scanner eventinput = new Scanner(System.in)) {
                System.out.print("Please Enter a CQL Event to Test if there are any rules are applicable: \n");
                
                String eventFromUser = null;
                ArrayList<String> completeEventFromUser = new ArrayList<>();
                for(eventFromUser = eventinput.nextLine();!eventFromUser.isEmpty();eventFromUser = eventinput.nextLine()){
                    completeEventFromUser.add(eventFromUser);
                }
                
                for(String s: completeEventFromUser)
                {
                    eventFromUser += s+" ";
                }
                
                System.out.println("Your event is : " + "\"" + eventFromUser + "\" \n");
                
                String inputParamsValuesFromUser = "";
                try(Scanner userinputparamsformat = new Scanner(System.in)){
                System.out.print("Please Enter the input params format for this event to Test:\n");
                inputParamsValuesFromUser = userinputparamsformat.nextLine();
                }
                
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat timeformat = new SimpleDateFormat("K:mm a");
                String presenttime = timeformat.format(cal.getTime());
                System.out.print("The time is : " + "\"" + presenttime + "\" \n");
                
                if(usertestingnumber ==1)
                {
                    String presenttimeevent = "WITH " + "\"" + presenttime + "\" AS currenttime";
                    System.out.println(presenttimeevent + eventFromUser);
                    findRules(presenttimeevent + eventFromUser,inputParamsValuesFromUser, 1);
                }
                
                if(usertestingnumber ==2)
                {

                    findRules(eventFromUser,inputParamsValuesFromUser,2);
                }
            }
            input.close();
    }
}
