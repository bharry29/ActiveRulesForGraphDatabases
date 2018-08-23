/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases;

/**
 *
 * @author bharathvadlamannati
 */
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
    public void executeRules(List<Rule> rules) throws Exception
    {
        try ( Session session = driver.session() )
        {
            for (Rule rule : rules) {
                
                String output = session.writeTransaction( new TransactionWork<String>()
                {
                    @Override
                    public String execute( Transaction tx )
                    {
                        Map<String, Object> parameters;
                        parameters = new HashMap<String, Object>();
                        
                        List<String> params = rule.getRuleParamsList();
                        
                        int paramsCount = params.size();
                        
                        List<String> paramsvalues = rule.getRuleParamsValues();
                        
                        for (String p: params){
                            for(String pv:paramsvalues){
                                parameters.put(p, pv);
                            }
                        }
                        
                        parameters.forEach((String k,Object v)->{
                            System.out.format("WITH %s as %s", v,k);
                            String.format("WITH %s as %s", v,k);
                        });
                        
                        String event = rule.getEvent();
                        
                        String condition = rule.getCondition() ;
                        
                        String action = rule.getAction();
                        
                        String fullTran = parameters +"\n" + event + " \n" + condition + "\n" + action;
                        System.out.println("Full Tran:" + fullTran);
                        
                        StatementResult result = tx.run(fullTran);
                        
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
    
    public static void testRule(List<Rule> rules) throws Exception
    {
        try ( neo4jDBInterface db = new neo4jDBInterface( "bolt://localhost:11002", "neo4j", "1234" ) )
        {
            db.executeRules(rules);
        }
    }
}