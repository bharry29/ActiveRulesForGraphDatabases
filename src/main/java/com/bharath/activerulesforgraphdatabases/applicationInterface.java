/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases;

import com.bharath.activerulesforgraphdatabases.neo4jQueryInterface.neo4jQueryInterface;
import com.bharath.activerulesforgraphdatabases.ruleSpecificationInterface.ruleSpecificationInterface;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author bharathvadlamannati
 */
public class applicationInterface {
    
    //This function gets the default app and initiates the code for that module
    public static String getDefApp(int appId){
        String appName = "";
        if(appId == 1){
            appName = "Rule Specification Interface";
        }
        else if(appId == 2){
            appName = "Neo4j Query Interface";
        }
        else {
            appName = "Wrong App ID. Please Check the code";
        }
        
        return appName;
    }
    
    //This function gets the current time of the system
    public static String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
        String presenttime = timeformat.format(cal.getTime());
        return presenttime;
    }
    
    //This method is the first method of the code. Please set this method as the maoin method for this project.
    public static void main (String[] args) throws IOException, Exception{
        neo4jQueryInterface nqi = new neo4jQueryInterface();
        ruleSpecificationInterface rsi = new ruleSpecificationInterface();
        // 1 - Rule spec Interface, 2 - Neo4j Query Interface
        int defaultApp = 2;

        String[] argmts = new String[] {""};
        if(defaultApp == 1){
            System.out.printf("Hello User. The default application is: %s\n", getDefApp(defaultApp));
            rsi.main(argmts);
        }
        else if (defaultApp == 2){
            System.out.printf("Hello User. The default application is: %s\n", getDefApp(defaultApp));
            nqi.main(argmts);
        }
        else{
            System.out.printf("Hello User. The default application is: %s\n", getDefApp(defaultApp));
        }
    }
}
