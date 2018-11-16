/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases.ruleSpecificationInterface;

import static com.bharath.activerulesforgraphdatabases.ruleSpecificationInterface.createRule.createRule;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author bharathvadlamannati
 */

//This class is the main source for creating rules
public class ruleSpecificationInterface {
    public static void main (String[] args) throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.print("Hello User. To Create a rule please select a type: 1. Time Based 2. Non-Time Based\n");
        int userruletypenumber = input.nextInt();
 
        createRule(userruletypenumber);
        input.close();
    }
}
