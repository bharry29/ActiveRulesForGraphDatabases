/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bharath.activerulesforgraphdatabases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bharathvadlamannati
 */
public class rule{
    
    private List<String> paramsList;
    private int paramscount;
    private String params;
    private String paramsformat;
    private String event;
    private String condition;
    private String action;
     private List<String> paramsValues;
    
    public rule(String params, String paramsformat, String event, String condition, String action){
        this.params = params;
        this.paramsformat = paramsformat;
        this.event = event;
        this.condition = condition;
        this.action = action;
    }

	 public rule(List<String> paramsList,String paramsformat, String event, String condition, String action){
        this.paramsList = paramsList;
        this.paramsformat = paramsformat;
        this.event = event;
        this.condition = condition;
        this.action = action;
    }

    public rule() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //This property is used to fetch the parameters of the rule
    public String getRuleParams() {
        return params;
    }
    
    //This property sets the parameters of the rule
    public void setRuleParams(String params) {
        this.params = params;
    }
    
    //This property is used to fetch the parameters as a list
	 public List<String> getRuleParamsList() {
        return paramsList;
    }
    
    //This property sets the parameters as a list     
    public void setRuleParamsList(List<String> paramsList) {
        this.paramsList = paramsList;
    }
    
    //This property fethes the paramters values of the rule during rule excecution
     public List<String> getRuleParamsValues() {
        return paramsValues;
    }
    
    //This property sets the paramters values of the rule during rule excecution
    public void setRuleParamsValues(List<String> paramsValues) {
        this.paramsValues = paramsValues;
    }
    
    //This property fetches the parameters as a format like "With xxx as xxx"
    public String getParamsFormat() {
        return paramsformat;
    }
    
    //This property sets the parameters as a format like "With xxx as xxx"
    public void setParamsFormat(String paramsformat) {
        this.paramsformat = paramsformat;
    }
    
     //This property fetches the event
    public String getEvent() {
        return event;
    }
    
    //This property sets the event
    public void setEvent(String event) {
        this.event = event;
    }
    
    //This property gets the condition
    public String getCondition() {
        return condition;
    }
    
    //This property sets the condition
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    //This property fetches the action
    public String getAction() {
        return action;
    }
    
    //This property sets the condition
    public void setAction(String action) {
        this.action = action;
    }
    
    ////This property fetches the parameters count
    public int getParamsCount() {
        return paramscount;
    }
    
    //This property sets the parameters count
    public void setParamsCount(int paramscount) {
        this.paramscount = paramscount;
    }
    
    //This property creates the complete rule as a string
    public String toString(){
        return (this.params + " " + this.paramsformat + " " + this.event + " " + this.condition + " " + this.action);
    }
}