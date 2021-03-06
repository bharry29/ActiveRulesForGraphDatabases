/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases.neo4jQueryInterface;

import com.bharath.activerulesforgraphdatabases.rule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static com.bharath.activerulesforgraphdatabases.neo4jQueryInterface.neo4jDBInterface.testRule;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bharathvadlamannati
 */
public class validateRule {
    
    //This function finds the rules in the rules repository based on the event from the user
    public static void findRules(String eventFromUser, int ruleType) throws FileNotFoundException, IOException, Exception{
        
        System.out.print("Hello User. You are testing rules from the repository\n");
        
        File ruleFolder = null;
        if(ruleType == 1)
        {
            File folder = new File("Rules/TimeBased");
            ruleFolder = folder;
        }
        else{
            File folder = new File("Rules/NonTimebased");
            ruleFolder = folder;
        }
        
        File[] listOfFiles = ruleFolder.listFiles();
        
        int count = 0;
        List<String> resultFileNames = new ArrayList<>();
        List<String> resultFilePaths = new ArrayList<>();
        String eventInRuleFile = "";
        String partOfEventFromFile = "";
        String inputParamsFormatInFile="";
        String partOfInputParamsFormatInFile = "";
        
        String[] inputParamValues = null;
        List<String> paramsListInRule = new ArrayList<>();
        List<String> paramsvalues = new ArrayList<String>();
        
        List<rule> rulesList = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                partOfEventFromFile = "";
                inputParamsFormatInFile="";
                partOfInputParamsFormatInFile = "";
                eventInRuleFile = "";
                paramsListInRule = new ArrayList<>();
                paramsvalues = new ArrayList<String>();
                
                Scanner txtscan = new Scanner(file);
                rule newRule = new rule();
                
                if(txtscan.hasNextLine()){
                    String inputParamsString = txtscan.nextLine();
                    
                    if(inputParamsString.contains("Input Parameters:")){
                        int endIndex = inputParamsString.lastIndexOf("]");
                        int startIndex = inputParamsString.indexOf("[");
                        String inputParamsValueFromRule = inputParamsString.substring(startIndex+1,endIndex);
                        if(inputParamsValueFromRule != null && !inputParamsValueFromRule.isEmpty()){
                            paramsListInRule =
                                    Arrays.asList(inputParamsValueFromRule.split(","));
                            
                            newRule.setRuleParamsList(paramsListInRule);
                        }
                    }
                }
                
                while(txtscan.hasNextLine()){
                    String nextLine = txtscan.nextLine();
                    partOfEventFromFile += nextLine + " ";
                    
                    if(nextLine.contains("}")){
                        break;
                    }
                }
                
                if(!partOfEventFromFile.isEmpty()){
                    if(partOfEventFromFile.contains("Event:")){
                        
                        int startIndex = partOfEventFromFile.indexOf("{");
                        int endIndex = partOfEventFromFile.lastIndexOf("}");
                        eventInRuleFile = partOfEventFromFile.substring(startIndex+1,endIndex);
                    }
                }
                
                if(ruleType == 2)
                {
                    inputParamValues = matchInput(eventFromUser.trim(), eventInRuleFile.trim(), paramsListInRule);
                    if(inputParamValues!=null){
                        int paramArraySize  = paramsListInRule.size();
                        
                        for(int i = 0;i< paramArraySize;i++){
                            paramsvalues.add(inputParamValues[i]);
                        }
                        
                        count++;
                        
                        resultFileNames.add(file.getName());
                        resultFilePaths.add(file.getPath());
                        newRule.setEvent(eventInRuleFile);
                        
                        newRule.setRuleParamsValues(Arrays.asList(inputParamValues));
                        
                        String partOfCondFromFile = "";
                        while(txtscan.hasNextLine()){
                            String nextLine = txtscan.nextLine();
                            partOfCondFromFile += nextLine + "\n";
                            
                            if(nextLine.contains("}")){
                                break;
                            }
                        }
                        
                        if(!partOfCondFromFile.isEmpty()){
                            if(partOfCondFromFile.contains("Condition:")){
                                
                                int startIndex = partOfCondFromFile.indexOf("{");
                                int endIndex = partOfCondFromFile.lastIndexOf("}");
                                String conditionInRuleFile = partOfCondFromFile.substring(startIndex+1,endIndex);
                                newRule.setCondition(conditionInRuleFile);
                            }
                        }
                        
                        String partOfActionFromFile = "";
                        while(txtscan.hasNextLine()){
                            String nextLine = txtscan.nextLine();
                            partOfActionFromFile += nextLine + "\n";
                            
                            if(nextLine.contains("}")){
                                break;
                            }
                        }
                        
                        if(!partOfActionFromFile.isEmpty()){
                            if(partOfActionFromFile.contains("Action:")){
                                
                                int startIndex = partOfActionFromFile.indexOf("{");
                                int endIndex = partOfActionFromFile.lastIndexOf("}");
                                String actionInRuleFile = partOfActionFromFile.substring(startIndex+1,endIndex);
                                newRule.setAction(actionInRuleFile);
                            }
                        }
                        
                        rulesList.add(newRule);
                    }
                }
                else if(ruleType == 1){
                    if(eventFromUser.trim()
                            .equals(eventInRuleFile.trim())){
                        count++;
                        
                        resultFileNames.add(file.getName());
                        resultFilePaths.add(file.getPath());
                        newRule.setEvent(eventInRuleFile);
                        
                        String partOfCondFromFile = "";
                        while(txtscan.hasNextLine()){
                            String nextLine = txtscan.nextLine();
                            partOfCondFromFile += nextLine + "\n";
                            
                            if(nextLine.contains("}")){
                                break;
                            }
                        }
                        
                        if(!partOfCondFromFile.isEmpty()){
                            if(partOfCondFromFile.contains("Condition:")){
                                
                                int startIndex = partOfCondFromFile.indexOf("{");
                                int endIndex = partOfCondFromFile.lastIndexOf("}");
                                String conditionInRuleFile = partOfCondFromFile.substring(startIndex+1,endIndex);
                                newRule.setCondition(conditionInRuleFile);
                            }
                        }
                        
                        String partOfActionFromFile = "";
                        while(txtscan.hasNextLine()){
                            String nextLine = txtscan.nextLine();
                            partOfActionFromFile += nextLine + "\n";
                            
                            if(nextLine.contains("}")){
                                break;
                            }
                        }
                        
                        if(!partOfActionFromFile.isEmpty()){
                            if(partOfActionFromFile.contains("Action:")){
                                
                                int startIndex = partOfActionFromFile.indexOf("{");
                                int endIndex = partOfActionFromFile.lastIndexOf("}");
                                String actionInRuleFile = partOfActionFromFile.substring(startIndex+1,endIndex);
                                newRule.setAction(actionInRuleFile);
                            }
                        }
                        
                        rulesList.add(newRule);
                    }
                }
            }
        }
        
        if (count > 0){
            int filecount = 1;
            System.out.println("The Event EXISTS" + " in "+ resultFileNames) ;
            for (String filepath:resultFilePaths){
                Path p = Paths.get(filepath);
                String filename = p.getFileName().toString();
                System.out.println("Rule "+ filecount +  ":" + filename);
                printRuleFile(filepath);
                filecount++;
            }
            
            testRule(rulesList, ruleType);
        }
        
        else{
            System.out.println("The Event DOES NOT EXIST in existing rules repository");
        }
    }
    
    // This function is used to find match between user entered partOfEventFromFile and partOfEventFromFile in rule file
    public static String[] matchInput(String eventFromUser, String eventInFile, List<String> paramsList)
    {
        
        int numberOfParams = paramsList.size();
        String[] userInputArray = new String[numberOfParams];
        String patternTemplate = eventInFile;
        
        patternTemplate = escapeRE(patternTemplate);
        for(String param:paramsList)
        {
            param = param.replace("\"", "");
            if(!eventInFile.contains(param)){
                return null;
            }
            
            patternTemplate = patternTemplate.replace("\\"+param, "(?<"+param.substring(1)+">.*)");
        }
        
        
        
        eventFromUser = eventFromUser.replace("\"", "");
        Pattern pattern = Pattern.compile(patternTemplate) ;
        Matcher matcher = pattern.matcher(eventFromUser);
        
        if (matcher.matches()) {
            for(int i=0;i<numberOfParams;i++){
                String groupName = paramsList.get(i).substring(1);
                userInputArray[i] = matcher.group(groupName).trim();
            }
        }
        else{
            return null;
        }
        
        return userInputArray;
        
    }
    
    //This funciton escapes the rule based on the pattern. This is internal for the previous function.
    public static String escapeRE(String str) {
        final String regExSpecialChars = "<([{\\^-=$!|]})?*+.>";
        final String regExSpecialCharsRE = regExSpecialChars.replaceAll( ".", "\\\\$0");
        final Pattern reCharsREP = Pattern.compile( "[" + regExSpecialCharsRE + "]");
        
        Matcher m = reCharsREP.matcher(str);
        return m.replaceAll( "\\\\$0");
    }
    
    //This function reads the parameters
    public static void readRuleParams(List<String> paramsList)
    {
        System.out.println(paramsList);
    }
    
    public static void printRuleParams(List<String> paramsList){
        
        System.out.println(paramsList);
    }
    
    //This function prints the rule which matched with the event entered by the user in the rule repository
    public static void printRuleFile(String inputFilePath) throws FileNotFoundException, IOException{
        
        int character;
        StringBuffer buffer = new StringBuffer("");
        
        try (FileInputStream inputStream = new FileInputStream(new File(inputFilePath))) {
            while( (character = inputStream.read()) != -1)
                buffer.append((char) character);
        }
        System.out.println("The Rule which is found in the rule repository based on your event:\n" + buffer);
    }
}
