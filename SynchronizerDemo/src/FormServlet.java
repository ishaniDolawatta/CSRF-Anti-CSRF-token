

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet({ "/FormServlet", "/FormServlet/*" })
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("FormServlet.java: doGet: "+"we are here");
		//String text = "This is to see whether the ajax call is working fine";
		String text_val="";
		try{
			InputStream input = getServletContext().getResourceAsStream("data.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line = null;
			while((line=br.readLine())!=null){
				if(line.contains("csrf_token")){
					String key_value[] = line.split(",");
					text_val = key_value[1];
				}
			}
			System.out.println("FormServlet.java: doGet: text: "+text_val);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(text_val);
			
		}catch(Exception e){
			System.out.println("SomeServlet.java"+e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Cookie cookiename[]  = request.getCookies();
		boolean validUser = false;
		String formVal="";
		String sessionCookie="";
		String nextPage = "";
		try{
			InputStream input = getServletContext().getResourceAsStream("data.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line = null;
			while((line=br.readLine())!=null){
				if(line.contains("csrf_token")){
					String key_value[] = line.split(",");
					formVal = key_value[1];
				}
				if(line.contains("Session")){
					String key_value[] = line.split(",");
					sessionCookie = key_value[1];
					
				}
			}
			
			System.out.println("FormServlet.java: doPost: csrf: "+formVal);
			System.out.println("FormServlet.java: doPost: Session: "+sessionCookie);
			
			
		}catch(Exception e){
			System.out.println("FormServlet.java: exception: "+e.getMessage());
		}
		System.out.println("FormServlet.java : doPost: hiddenField "+request.getParameter("hiddenField"));
		for(Cookie ck:cookiename){
			System.out.println("FormServlet.java : doPost: "+ck.getName());
			
			if(ck.getValue().equals(sessionCookie) && request.getParameter("hiddenField").equals(formVal)){
				System.out.println("FormServlet: Check if : true");
				validUser = true;
			}
		}
		System.out.println("FormServlet: isValid : "+validUser);
		if(validUser){
			nextPage = "Success.jsp";
		}
		else{
			nextPage = "Failure.jsp";
		}
		response.sendRedirect(nextPage);
		
	}

}
