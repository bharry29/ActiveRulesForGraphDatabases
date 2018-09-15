/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases;

import com.bharath.activerulesforgraphdatabases.neo4jQueryInterface.neo4jQueryInterface;
import com.bharath.activerulesforgraphdatabases.ruleSpecificationInterface.ruleSpecificationInterface;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author bharathvadlamannati
 */
public class applicationInterface {
    public static void main (String[] args) throws IOException, Exception{
        neo4jQueryInterface nqi = new neo4jQueryInterface();
        ruleSpecificationInterface rsi = new ruleSpecificationInterface();
        // 1 - Rule spec Interface, 2 - Neo4j Query Interface
        int defaultApp = 2;
        String appname = "";
        String[] argmts = new String[] {""};
        if(defaultApp == 1){
            rsi.main(argmts);
            appname = "Rule Specification Interface";
        }
        else if (defaultApp == 2){
            nqi.main(argmts);
             appname = "Neo4j Query Interface";
        }
        else{
            System.out.println("Wrong Input");
        }
        
        System.out.printf("Hello User. The default application is: %s\n", appname);
    }
}
