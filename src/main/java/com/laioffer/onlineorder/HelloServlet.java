package com.laioffer.onlineorder;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.laioffer.onlineorder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Customer customer= new Customer();
        customer.setEmail("sun@laioffer.com");
        customer.setPassword("123456");
        customer.setFirstName("rick");
        customer.setLastName("sun");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));

        /*
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        JSONObject customer = new JSONObject();
        customer.put("email", "sun@laioffer.com");
        customer.put("first_name", "rick");
        customer.put("last_name", "sun");
        customer.put("age", 50);

        out.print(customer);
        */

        /*response.setContentType("text/html"); //data format
        String username = request.getParameter("username");

        // Hello
        PrintWriter out = response.getWriter(); //handler object that can print data into response body
        out.println("<html><body>");
        //out.println("<h1>hello" + message + "</h1>");
        out.println("<h1>" + username + "</h1>");
        out.println("</body></html>");*/
    }

    public void destroy() {
    }
}