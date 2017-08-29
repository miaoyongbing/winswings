package dtss;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dcone.dtss.model.dc_user;


/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter("/LogFilter")
public class LogFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String s = request.getRemoteAddr();
		//System.out.println(s);
		
		HttpServletRequest request1 = (HttpServletRequest)request;
		HttpServletResponse response1 = (HttpServletResponse)response;
		request1.setCharacterEncoding("UTF-8");
		response1.setCharacterEncoding("UTF-8");
		response1.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		HttpSession  session = request1.getSession();
		
		if(session.getAttribute("itcode") == null||session.getAttribute("itcode") ==""){
			String temp = request1.getRequestURI();
			System.out.println(temp);
			String str=(String) session.getAttribute("itcode");
			System.out.println("itcode is "+str);
			if (temp.equals("http://www.w3.org/TR/html4/loose.dtd")||temp.equals("/dtss/") || temp.equals("/dtss/validuser")
					|| temp.equals("/dtss/Code") || temp.equals("/dtss/Checkitcode")
					|| temp.equals("/dtss/AddUser") || temp.equals("/dtss/img/33.png")||temp.equals("/dtss/img/error.png")
					|| temp.equals("/dtss/Valicode")||temp.equals("/dtss/js/jquery.min.js") ) {
				System.out.println("通过");
				chain.doFilter(request, response);//
			} else		
				response1.sendRedirect("/dtss/");
			
		} else {
			ServletContext application = request1.getServletContext();
			if(application.getAttribute("OnlineUsers")==null) {
				ArrayList<dc_user> users = new ArrayList<dc_user>();
			
				dc_user temp = new dc_user();
				String face=(String) session.getAttribute("face");
				String itcode=(String) session.getAttribute("itcode");
				String username=(String) session.getAttribute("username");
				temp.setPortrait(face);
				temp.setUsername(username);
				temp.setItcode(itcode);
				users.add(temp);
				application.setAttribute("OnlineUsers", users);
				System.out.println("application test1");
			} else {
				ArrayList<dc_user> users = (ArrayList<dc_user>)application.getAttribute("OnlineUsers");
				boolean isexist=false;
				String itcode=(String) session.getAttribute("itcode");
				System.out.println("now the itcode:"+itcode);
				if(users!=null) {
					
					for(dc_user t:users) {
						if(t!=null)
						{
							System.out.println("t is "+t.toString()+",t's itcode:"+t.getItcode());
							if(t.getItcode()!=null) {
								if(t.getItcode().equals(itcode))
								{
									isexist=true;
									break;
								}
							}
						}
					}
				}
				if(!isexist) {
					String username=(String) session.getAttribute("username");
					String face=(String) session.getAttribute("face");
					System.out.println("face is"+face);
					dc_user temp = new dc_user();
					temp.setUsername(username);
					temp.setPortrait(face);
					temp.setItcode(itcode);
					users.add(temp);
					application.setAttribute("OnlineUsers", users);
					System.out.println("application test2");
				}
			}
			System.out.println("now itcode is:"+session.getAttribute("itcode"));
			chain.doFilter(request, response);
			
		}
		
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
