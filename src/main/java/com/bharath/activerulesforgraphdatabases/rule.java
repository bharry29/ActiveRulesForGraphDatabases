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
    

    public String getRuleParams() {
        return params;
    }
    

    public void setRuleParams(String params) {
        this.params = params;
    }

	 public List<String> getRuleParamsList() {
        return paramsList;
    }
    

    public void setRuleParamsList(List<String> paramsList) {
        this.paramsList = paramsList;
    }
    
     public List<String> getRuleParamsValues() {
        return paramsValues;
    }
    

    public void setRuleParamsValues(List<String> paramsValues) {
        this.paramsValues = paramsValues;
    }
    
    public String getParamsFormat() {
        return paramsformat;
    }
    

    public void setParamsFormat(String paramsformat) {
        this.paramsformat = paramsformat;
    }
    

    public String getEvent() {
        return event;
    }
    

    public void setEvent(String event) {
        this.event = event;
    }
    
    

    public String getCondition() {
        return condition;
    }
    
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    
    public int getParamsCount() {
        return paramscount;
    }
    
    public void setParamsCount(int paramscount) {
        this.paramscount = paramscount;
    }
    
    public String toString(){
        return (this.params + " " + this.paramsformat + " " + this.event + " " + this.condition + " " + this.action);
    }
}