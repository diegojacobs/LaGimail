/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import Models.Email;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;

/**
 *
 * @author Diego Jacobs
 */
public class GetEmail{
    private Communicator _communicator;
    private String email;
    private Timestamp date;
    private DateFormat dateFormat;
    private Gson gson;
    
    public GetEmail(String email){
        _communicator = new Communicator("WEB");
        this.email = email;
        this.date = new Timestamp(System.currentTimeMillis());
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        gson = new Gson();
    }
    
    public String Get(){
        String isValid = "";
        _communicator.initiateCommunication();
        
        try{
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
                    message = "DATE: <" + this.date.toString() +">";
                    System.out.println(message);    
                    _communicator.sendMessage(message);
                    response = _communicator.readResponse();
                    System.out.println(response);

                    if(response.startsWith("200")){
                        message = "GET";
                        System.out.println(message);     
                        _communicator.sendMessage(message);
                        response = _communicator.readResponse();
                        System.out.println(response);

                        if (response.startsWith("200")){
                            String json = response.substring(4, response.indexOf("}")+1);
                            Email email = gson.fromJson(json, Email.class);
                            System.out.println(email.toString());
                        }

                        message = "QUIT";                    
                        System.out.println(message);
                        
                        _communicator.sendMessage(message);
                        String temp = _communicator.readResponse();    
                        System.out.println(temp);
                    
                        isValid = response;
                    }
                }
            }
        }
        catch(JsonSyntaxException e){
            System.err.println(e.toString());
            String message = "QUIT";       
            _communicator.sendMessage(message);
            String response = _communicator.readResponse();
        }

        String message = "QUIT";                    
        System.out.println(message);

        _communicator.sendMessage(message);
        String temp = _communicator.readResponse();
        System.out.println(temp);
        
        return isValid;
    }
}
