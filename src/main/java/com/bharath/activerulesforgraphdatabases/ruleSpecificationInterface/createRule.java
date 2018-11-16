/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases.ruleSpecificationInterface;

import static com.bharath.activerulesforgraphdatabases.applicationInterface.getCurrentTime;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author bharathvadlamannati
 */
public class createRule {
    
    //This function creates a rule based on the option entered by the user. 1 for time based and 2 for non time based.
    public static void createRule(int ruleType) throws IOException{
        
        String ruleFolder;
        if(ruleType ==1) {
            String ruleFolderTime = "Rules/TimeBased";
            ruleFolder = ruleFolderTime;
        }
        else
        {
            String ruleFolderNonTime = "Rules/NonTimebased";
            ruleFolder = ruleFolderNonTime;
        }
        List<String> ruleinputparameterslist = new ArrayList<String>();
        String ruleinputevent = "", ruleinputcondition = "", ruleinputaction = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your Rule Name: ");
        String ruleinputname = input.nextLine();
        String presenttime = getCurrentTime();
        
        if(ruleType ==1){
            ruleinputparameterslist.add("currenttime");
            String presenttimeeventparamsformat = "WITH " + "\"" + presenttime + "\" AS currenttime";
            ruleinputevent = presenttimeeventparamsformat;
        }
        
        if(ruleType ==2) {
            //Input Parameters
            System.out.print("How many Input parameters for this rule ? ");
            String ruleinputparameterscount = input.nextLine();
            
            for (int i = 0; i< parseInt(ruleinputparameterscount); i++){
                System.out.print("Enter your Rule's number " + (i+1) + " Input Parameter(s): ");
                String ruleinputparameters = input.nextLine();
                ruleinputparameterslist.add(ruleinputparameters);
            }
            
        //Event
        System.out.print("Enter your Rule's Event part: ");
        while(input.hasNextLine()){
            String newLine = input.nextLine();
            ruleinputevent += newLine+"\n";
            if(newLine.isEmpty()){
                break;
            }
        }
        ruleinputevent = ruleinputevent.trim();
        }
        
        //Condition
        System.out.print("Enter your Rule's Condition part: ");
        
        while(input.hasNextLine()){
            String newLine = input.nextLine();
            ruleinputcondition += newLine+"\n";
            if(newLine.isEmpty()){
                break;
            }
        }
        ruleinputcondition = ruleinputcondition.trim();
        
        //Action
        System.out.print("Enter your Rule's Action part: ");
        while(input.hasNextLine()){
            String newLine = input.nextLine();
            ruleinputaction += newLine+"\n";
            if(newLine.isEmpty()){
                break;
            }
        }
        ruleinputaction = ruleinputaction.trim();
        
        String ruleStart = "Input Parameters:[";
        for(int i=0;i<ruleinputparameterslist.size();i++){
            if(i==ruleinputparameterslist.size()-1){
                ruleStart+= ruleinputparameterslist.get(i)+"]";
            }
            else{
                ruleStart+= ruleinputparameterslist.get(i)+",";
            }
            
        }
        String fullrule = ruleStart + "\n" + "Event:" +"{" + ruleinputevent + "}" + "\n" + "Condition:" + "{" + ruleinputcondition + "}" +  "\n" + "Action:" + "{" + ruleinputaction + "}";
        
        System.out.println("Do you want to save it to Rules Repository (y/n)?");
        String saveruleoption = input.nextLine();
        
        if("y".equals(saveruleoption)){
            createRuleFile(ruleFolder,ruleinputname,fullrule);
            System.out.println("Rule Saved to Directory...!!! Exiting to Main Menu");
        }
        else{
            System.out.println("Rule Not Saved...!!! Exiting to Main Menu");
        }
        input.close();
    }
 
    //This function writes the data into a file based on the rule type. This is an internal method for the previous function.
    public static void createRuleFile (String directoryName,String ruleName, String ruleData) throws IOException
    {
        File dir = new File(directoryName);
        if (!dir.exists()) dir.mkdirs();
        File outFile = new File (directoryName + "/" + ruleName + ".txt");
        
        try{
            FileWriter fWriter = new FileWriter (outFile);
            fWriter.write(ruleData);
            fWriter.close();
        }
        catch( IOException e ){
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
