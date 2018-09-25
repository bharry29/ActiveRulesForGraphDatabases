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
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat timeformat = new SimpleDateFormat("K:mm a");
            String presenttime = timeformat.format(cal.getTime());
            System.out.print("The time is : " + "\"" + presenttime + "\" \n");
            
            findRules(eventFromUser,2);
        }
        input.close();
    }
}
