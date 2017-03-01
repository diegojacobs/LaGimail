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

/**
 *
 * @author Diego Jacobs
 */
public class SendEmail {
    private Communicator _communicator;
    private String email;
    private Timestamp date;
    private DateFormat dateFormat;
    private Gson gson;
    
    public SendEmail(String email){
        _communicator = new Communicator();
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
            String response = _communicator.readUTF(message);
            System.out.println(response);

            if(response.startsWith("200")){
                message = "MAIL FROM: <" + this.email +">";
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

                        if (response.startsWith("200")){
                            String json = response.substring(4, response.indexOf("}")+1);
                            //Email emails = gson.fromJson(json, Email.class);
                            //System.out.println(emails.toString());
                        }

                        message = "QUIT";
                        String temp = _communicator.readUTF(message);

                        isValid = response;
                    }
                }
            }
        }
        catch(JsonSyntaxException e){
            System.err.println(e.toString());
            String message = "QUIT";       
            String response = _communicator.readUTF(message);
        }
        
        _communicator.closeCommunication();
        
        return isValid;
    }
}
