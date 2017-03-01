/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Diego Jacobs
 */
public class SendEmail {
    private Communicator _communicator;
    private String from;
    private Timestamp date;
    private DateFormat dateFormat;
    private Gson gson;
    
    public SendEmail(String from){
        _communicator = new Communicator("SMTP");
        this.from = from;
        this.date = new Timestamp(System.currentTimeMillis());
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        gson = new Gson();
    }
    
    public String Send(ArrayList<String> listTo, String data){
        String isValid = "";
        
        _communicator.initiateCommunication();
        
        String response = _communicator.readResponse();
        System.out.println(response);
        
        String message = "HELO " + "localhost";
        _communicator.sendMessage(message);
        System.out.println(message);
        
        response = _communicator.readResponse();
        System.out.println(response);
        
        message = "MAIL FROM: <" + from +">";
        _communicator.sendMessage(message);
        System.out.println(message);
        
        response = _communicator.readResponse();
        System.out.println(response);
        
        for(String to : listTo){
            message = "RCPT TO: <" + to +">";
            _communicator.sendMessage(message);
            System.out.println(message);
            
            response = _communicator.readResponse();
            System.out.println(response);
        }
            
        message = "DATA";
        _communicator.sendMessage(message);
        System.out.println(message);
        
        response = _communicator.readResponse();
        System.out.println(response);
        
        message = data;
        _communicator.sendMessage(message);
        System.out.println(message);
        
        response = _communicator.readResponse();
        System.out.println(response);
        
        message = ".";
        _communicator.sendMessage(message);
        System.out.println(message);
        
        response = _communicator.readResponse();
        System.out.println(response);
        
        message = "QUIT";
        _communicator.sendMessage(message);
        System.out.println(message);
        
        _communicator.readResponse();
        System.out.println(response);
        
        return response;
    }
}
