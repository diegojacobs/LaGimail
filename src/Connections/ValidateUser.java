/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Jacobs
 */
public class ValidateUser {
    private ServerConnection serverConnection;
    private String email;
    private String password;
    private String message;
    private String response;
    private DataOutputStream output;
    private DataInputStream in;
    BufferedReader _in;
    PrintWriter _out;
    
    public ValidateUser(String email, String password){
        this.email = email;
        this.password = password;
        this.message = new String();
        serverConnection = new ServerConnection("127.0.0.1", 8000);
    }
    
    public String validate(){
        String isValid = "500";
        serverConnection.setClient();
        
        _out = new java.io.PrintWriter(serverConnection.getOutputStream());
        _in = new java.io.BufferedReader(new java.io.InputStreamReader(serverConnection.getInputStream()));
        
        message = "HELO";
        System.out.println(message);
        
        //this.writeUTF(message); //Send Handshake        
        
        response = this.readUTF();
        System.out.println(response);
        
        if(response.startsWith("200")){
            message = "USER: <" + this.email +">";
            System.out.println(message);
            
            //this.writeUTF(message); //Send User        
            
            response = this.readUTF();
            System.out.println(response);
            
            if(response.startsWith("200")){
                message = "PASSWORD: <" + this.password +">"; 
                System.out.println(message);
                
                //this.writeUTF(message); //Send Password
                
                response = readUTF();
                System.out.println(response);
                if(response.startsWith("200")){
                    message = "VALIDATE";
                    System.out.println(message);
                    
                    //this.writeUTF(message); //Send Action      
                    
                    response = readUTF();
                    System.out.println(response);
                    
                    return response;
                }
            }
        }
        
        isValid = "400 Missing Connection";
        
        message = "QUIT";
        this.writeUTF(message);        
        response = readUTF();
        System.out.println(response);
        
        serverConnection.closeClient();

        return isValid;
    }
    
    private void writeUTF(String message){
        _out.println(message);
        _out.flush();
    }
    
    private String readUTF(){
        try {
            _out.println(message);
            _out.flush();
            String response = _in.readLine();
            return response;
        } catch (IOException ex) {
            Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
