/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases.neo4jQueryInterface;

/**
 *
 * @author bharathvadlamannati
 */
import com.bharath.activerulesforgraphdatabases.rule;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import static org.neo4j.driver.v1.Values.parameters;

import static org.neo4j.driver.v1.Values.parameters;

/**
 *
 * @author bharathvadlamannati
 */

public class neo4jDBInterface implements AutoCloseable
{
    private final Driver driver;
    
    public neo4jDBInterface( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }
    
    @Override
    public void close() throws Exception
    {
        driver.close();
    }
    public void executeNonTimebasedRules(List<rule> rules) throws Exception
    {
        try ( Session session = driver.session() )
        {
            for (rule rule : rules) {
                
                String output;
                output = session.writeTransaction( new TransactionWork<String>()
                {
                    @Override
                    public String execute( Transaction tx )
                    {
                        Map<String, Object> parameters;
                        parameters = new HashMap<String, Object>();
                        
                        List<String> params = rule.getRuleParamsList();
                        
                        int paramsCount = params.size();
                        int count;
                        
                        List<String> paramsvalues = rule.getRuleParamsValues();
                        
                        for (count = 0; count< paramsCount; count++){
                            parameters.put(params.get(count).substring(1).trim(), StringEscapeUtils.unescapeJava(paramsvalues.get(count)));
                        }
                        
                        String event = rule.getEvent();
                        
                        String condition = rule.getCondition() ;
                        
                        String action = rule.getAction();
                        
                        String fullTran = event + " \n" + condition + "\n" + action;
                        System.out.println("Full Tran:" + fullTran);
                        
                        StatementResult result = tx.run(fullTran,parameters);
                        
                        List<String> resultarray = new ArrayList<>();
                        List<Record> sr = result.list();
                        
                        for(Record r: sr){
                            resultarray.add(r.values().toString());
                        }
                        
                        tx.success();
                        tx.close();
                        
                        return "The Output from DB is : " + resultarray;
                    }
                } );
                
                System.out.println(output);
                
            }
        }
        
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        close();
    }
    
    public void executeTimebasedRules(List<rule> rules) throws Exception
    {
        try ( Session session = driver.session() )
        {
            for (rule rule : rules) {
                
                String output;
                output = session.writeTransaction( new TransactionWork<String>()
                {
                    @Override
                    public String execute( Transaction tx )
                    {
                       
                        String event = rule.getEvent();
                        
                        String condition = rule.getCondition() ;
                        
                        String action = rule.getAction();
                        
                        String fullTran = event + " \n" + condition + "\n" + action;
                        System.out.println("Full Tran:" + fullTran);
                        
                        StatementResult result = tx.run(fullTran);
                        
                        List<String> resultarray = new ArrayList<>();
                        List<Record> sr = result.list();
                        
                        for(Record r: sr){
                            resultarray.add(r.values().toString());
                        }
                        
                        tx.success();
                        tx.close();
                        
                        return "The Output from DB is : " + resultarray;
                    }
                } );
                
                System.out.println(output);
                
            }
        }
        
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        close();
    }
    
    public static void testRule(List<rule> rules, int ruleType) throws Exception
    {
        try ( neo4jDBInterface db = new neo4jDBInterface( "bolt://localhost:7687", "neo4j", "1234" ) )
        {
            if(ruleType == 2)
                db.executeNonTimebasedRules(rules);
            else if(ruleType == 1)
                db.executeTimebasedRules(rules);   
        }
    }
    
    
}