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
    public void executeRules(List<rule> rules) throws Exception
    {
        try ( Session session = driver.session() )
        {
            for (rule rule : rules) {
                
                String output = session.writeTransaction( new TransactionWork<String>()
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
                            parameters.put(params.get(count), paramsvalues.get(count));
                        }
                        
                        String tranparam = "";
                        
                        String key ="";
                        for(count = 0; count<parameters.size();count++){
                            key = parameters.keySet().toArray()[count].toString();
                            tranparam = tranparam.concat(String.format("%s as %s,", key, parameters.get(key)));
                        }
                        
                        tranparam = tranparam.substring(0, tranparam.lastIndexOf(","));
                        
          
                        String event = rule.getEvent();
                        
                        String condition = rule.getCondition() ;
                        
                        String action = rule.getAction();
                        
                        String fullTran = "WITH " + tranparam +"\n" + event + " \n" + condition + "\n" + action;
                        System.out.println("Full Tran:" + fullTran);
                        
                        StatementResult result = tx.run(fullTran,parameters);
                        
                        Record records = result.single();
                        
                        List<String> resultarray = new ArrayList<>();
                        List<Record> sr = result.list();
                        for(Record r: sr){
                            resultarray.add(r.values().toString());
                        }
                        
                        tx.success();
                        tx.close();
                        
                        return "The Output from DB is : " + records.fields().toString();
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
    
    public static void testRule(List<rule> rules) throws Exception
    {
        try ( neo4jDBInterface db = new neo4jDBInterface( "bolt://localhost:11002", "neo4j", "1234" ) )
        {
            db.executeRules(rules);
        }
    }
}