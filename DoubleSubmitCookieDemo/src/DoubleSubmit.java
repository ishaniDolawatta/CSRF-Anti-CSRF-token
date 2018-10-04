

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoubleSubmit
 */
@WebServlet("/DoubleSubmit")
public class DoubleSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoubleSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		boolean isValidUser = false;
		Cookie cookies[] = request.getCookies();
		String nextPage = "Unsuccessful.jsp";
		int redirectCode = 404;
		String formElementValue = request.getParameter("hidden");
		System.out.println("DoubleSubmit: doPost: hidden field value: "+formElementValue);
		for(Cookie ck:cookies){
			String sentCookieName = ck.getName();
			System.out.println("DoubleSubmit: doPost: cookie name: "+sentCookieName);
			if(sentCookieName.equalsIgnoreCase("CSRFToken")){
				System.out.println("DoubleSubmit: doPost: inside if ");
				String cookieValue = ck.getValue();
				System.out.println("DoubleSubmit: doPost: cookie value: "+cookieValue);
				if(cookieValue.equals(formElementValue)){
					isValidUser = true;
				}
			}
		}
		if(isValidUser){
			nextPage ="Successful.jsp";
		}
		response.sendRedirect(nextPage);
	}

}
