/*
 * To change this license header, choose License Headers input Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template input the editor.
 */
package com.bharath.activerulesforgraphdatabases;

import java.io.*;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 *
 * @author bharathvadlamannati
 */

    
public class applicationInterface implements Runnable {
    static BufferedReader input ;  
    static int setquit=0;

    @Override
    public void run() {
        String msg = null;
        while(true){
            try{
            msg=input.readLine();
            }
            catch(Exception e){
                        System.out.print(e.toString());
            }
             
            if(msg.equals("Q")) {
                setquit=1;
                break;
            }
        }
    }
  
    public static void main(String[] args) throws InterruptedException {
        
        input = new BufferedReader(new InputStreamReader(System.in));
         
        Thread t1=new Thread(new applicationInterface());
        t1.start();
         
        System.out.println("Press Q THEN ENTER to terminate");
         
        while(true){
            t1.sleep(10);
            if(setquit == 1) break;
            
            System.out.println("Hello");
        }
        
    }
}
