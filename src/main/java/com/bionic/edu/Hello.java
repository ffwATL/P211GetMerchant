package com.bionic.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.MerchantService;

/**
 * Servlet implementation class Hello
 */
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String chargeParam = (String)request.getParameter("charge");
		if (chargeParam != null){
			PrintWriter out = response.getWriter();
			ApplicationContext context = new ClassPathXmlApplicationContext ("spring/application-config.xml");
			        MerchantService merchantService = (MerchantService)context.getBean("merchantServiceImpl");
			        List<Merchant> list = merchantService.findAll();
			        response.setContentType("text/html");

		     out.print("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML  4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		     out.print("<html><head><meta http-equiv=\"Content-Type\"content=\"text/html; charset=UTF-8\">");
		     out.print("<title>Merchants</title><link rel=\"stylesheet\"type=\"text/css\" href=\"style.css\" />");
		     out.print("</head><body>");
		     out.print("<h1>Merchant List</h1>");
		     out.print("<table>");
		     out.print("<tr><td>Name<td>Bank<td>Charge<td>Minimum Sum</tr>");
		     double charge = Double.valueOf(chargeParam);
		     for(Merchant m : list){
		    	 if (m.getCharge() > charge) out.println(m.getDataForWeb());	    	 
		     }
		     out.print("</table>");
		     out.print("</body></html>");
		}


		


		/*
		response.getWriter().append("Served at: ").append(request.getContextPath());*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
