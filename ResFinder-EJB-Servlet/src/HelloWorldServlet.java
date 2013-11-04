

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.enterprise.inject.New;
import javax.inject.*;

import ejb.Entity.*;
import ejb.Service.UserService;

@SuppressWarnings("serial")
@WebServlet("/HelloWorldEJB")
public class HelloWorldServlet extends HttpServlet {

   static String PAGE_HEADER = "<html><head /><body>";

   static String PAGE_FOOTER = "</body></html>";

  
   @EJB
   public UserService userService;
  
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   /*
	   Context context = null;
	   try {
	     context = new InitialContext();
	     helloService = (HelloService) context.lookup( 
  	       "java:global/ResFinder-EJB-Session-Entity/HelloService");
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
	   */
	
	   PrintWriter writer = resp.getWriter();
	   
       writer.println(PAGE_HEADER);
      
       User user = new User();
       
       /*user.setId("ak");
       user.setName("ak");
       
       user.setEmailId("ak");
       user.setPassword("ak");*/
	      // writer.println("<h1>" + helloService.createHelloMessage("Rajiv") + "</h1>");
       try
       {
    	   userService.delete("tk");
      writer.println("<h1>" + user.getName() + "</h1>");
       }
       catch(Exception e)
       {

			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			String [] s=errors.toString().split("at");
			for(String s1 : s)
			{
				writer.println(s1);
			}
			
       }
       writer.println("grsfgrsfrs");
       writer.println(PAGE_FOOTER);
       writer.close();
   }

}
