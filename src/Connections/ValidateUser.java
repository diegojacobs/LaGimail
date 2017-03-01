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
public class ValidateUser{
    Communicator _communicator;
    private String email;
    private String password;
    
    public ValidateUser(String email, String password){
        _communicator = new Communicator("WEB");
        this.email = email;
        this.password = password;
    }
    
    public String validate(){
        String isValid = "500";
        _communicator.initiateCommunication();
        
        String message = "HELO";
        System.out.println(message);      
        
        _communicator.sendMessage(message);
        String response = _communicator.readResponse();
        System.out.println(response);
        
        if(response.startsWith("200")){
            message = "USER: <" + this.email +">";
            System.out.println(message);        
            
            _communicator.sendMessage(message);
            response = _communicator.readResponse();
            System.out.println(response);
            
            if(response.startsWith("200")){
                message = "PASSWORD: <" + this.password +">"; 
                System.out.println(message);
                
                _communicator.sendMessage(message);
                response = _communicator.readResponse();
                System.out.println(response);
                
                if(response.startsWith("200")){
                    message = "VALIDATE";
                    System.out.println(message);
                    
                    _communicator.sendMessage(message);
                    response = _communicator.readResponse();
                    System.out.println(response);
                    
                    message = "QUIT";
                    System.out.println(message);
                    
                    _communicator.sendMessage(message);
                    String temp = _communicator.readResponse();
        
                    return response;
                }
            }
        }
        
        isValid = "400 Missing Connection";
        
        message = "QUIT";
        _communicator.sendMessage(message);
        response = _communicator.readResponse();
        System.out.println(response);

        return isValid;
    }
}
