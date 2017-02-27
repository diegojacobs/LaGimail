/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Jacobs
 */
public class GetEmail{
    private Communicator _communicator;
    private String email;
    private Timestamp date;
    private DateFormat dateFormat;
    
    public GetEmail(String email){
        _communicator = new Communicator();
        this.email = email;
        this.date = new Timestamp(System.currentTimeMillis());
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }
    
    public String Get(){
        String isValid = "";
        _communicator.initiateCommunication();
        String response = _communicator.readUTF("");
        
        String message = "HELO";
        System.out.println(message);      
        response = _communicator.readUTF(message);
        System.out.println(response);
        
        if(response.startsWith("200")){
            message = "USER: <" + this.email +">";
            System.out.println(message);    
            response = _communicator.readUTF(message);
            System.out.println(response);
            
            if(response.startsWith("200")){
                message = "DATE: <" + this.date.toString() +">";
                System.out.println(message);    
                response = _communicator.readUTF(message);
                System.out.println(response);
                
                if(response.startsWith("200")){
                    message = "GET";
                    System.out.println(message);     
                    response = _communicator.readUTF(message);
                    System.out.println(response);
                    
                    message = "QUIT";
                    String temp = _communicator.readUTF(message);
                    
                    isValid = response;
                }
            }
        }
        message = "QUIT";       
        response = _communicator.readUTF(message);
        
        _communicator.closeCommunication();
        
        return isValid;
    }
}
