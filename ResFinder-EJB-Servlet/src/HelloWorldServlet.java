

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;

import ejb.Entity.User;
import ejb.Service.HelloService;
import ejb.Service.UserService;

/**
 * <p>
 * A simple servlet taking advantage of features added in 3.0.
 * </p>
 * 
 * <p>
 * The servlet is registered and mapped to /HelloServlet using the {@linkplain WebServlet
 * @HttpServlet}. The {@link HelloService} is injected by CDI.
 * </p>
 * 
 * @author Pete Muir
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/HelloWorldEJB")
public class HelloWorldServlet extends HttpServlet {

   static String PAGE_HEADER = "<html><head /><body>";

   static String PAGE_FOOTER = "</body></html>";

   @EJB 
   private HelloService helloService;
   @EJB
   private UserService userService;
   
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
//       User user = new User();
//       user.setId("tk");
//       user.setName("tk");
//       user.setEmailId("tk");
//       user.setPassword("tk");
	       writer.println("<h1>" + helloService.createHelloMessage("Rajiv") + "</h1>");
//       writer.println("<h1>" + userService.createUser(user) + "</h1>");
       writer.println(PAGE_FOOTER);
       writer.close();
   }

}
