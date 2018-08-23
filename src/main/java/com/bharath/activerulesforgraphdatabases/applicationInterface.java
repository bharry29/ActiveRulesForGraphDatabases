/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bharath.activerulesforgraphdatabases;

import java.util.Scanner;

/**
 *
 * @author bharathvadlamannati
 */
public class applicationInterface {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Hello User. To Create a rule please select 1 or To Validate Rules in the repository please select 2\n");
        int useroptionnumber = input.nextInt();
        if(useroptionnumber == 1){
            System.out.println("Hello");
        }
        else if (useroptionnumber == 2){
            System.out.println("Hello");
        }
        else{
            System.out.println("Wrong Input");
        }
    }
}
