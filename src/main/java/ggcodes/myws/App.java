package ggcodes.myws;


import ggcodes.myws.services.UserSIB;
import jakarta.xml.ws.Endpoint;

public class App 
{
    public static void main( String[] args )
    {
    	Endpoint.publish("http://localhost:8000/user", new UserSIB());
    	System.out.println("Service working");
    }
}
