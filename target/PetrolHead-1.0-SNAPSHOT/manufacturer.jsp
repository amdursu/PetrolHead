<%-- 
    Document   : manufacturer
    Created on : 22-Apr-2019, 17:29:29
    Author     : andrei
--%>

<%@page import="domain.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Manufacturer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script type="text/javascript" src="js/script.js"></script>
        <% Manufacturer m = (Manufacturer) request.getAttribute("manufacturer"); %>
        
        <title><%= m.getName()%> Cars</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid">
           <h1><%= m.getName()%></h1>
          <div class='cont'>
              <p class='welcome'>Here is a list of our selected <%= m.getName() %> cars. Click on any of them to see more details, to configure or even add a review.</p>
          </div>
        <div class='cars'>
              <form action="ModelController" method="post">
                <div class="row">
                <%
                    ArrayList<Car> cars = (ArrayList<Car>) getServletContext().getAttribute("cars");
                    int i = 0;
                    for(Car c : cars){
                        if(c.getManufacturer().equals(m.getName())){
                            String s = c.getManufacturer() + " " + c.getModel();
                            if(i % 3 == 0 && i != 0){
                %>
                </div>
                <div class='row'> <% } %>

                <div class='col-md-4 car'>
                    <button type="submit" name="<%= c.getModel() %>">
                        <img src='<%= c.getImg() %>' alt="<%= c.getModel() %>">
                        <h3>See car model</h3>
                    </button>
                    <h2><%= s %></h2>
                </div>
                <% i++; }} %>
                </div>
             </form>
          </div>
        </div>
        <div class="bar"></div>
        <div class="navbar-fixed-bottom">
          <div class="bar"></div>
          <footer>
            <div class="row">
                <div class="col-md-2">
                    <img src="img/logo.png" alt="LOGO">
                </div>
                <div class="col-md-4 afoot">
                    <a href="home.jsp">HOME</a><br>
                    <a href="manufacturers.jsp">CARS</a><br>
                    <a href="login_register.jsp">ACCOUNT</a>
                </div>
                <div class="col-md-1">
                    <img src="img/fb.png" alt="Facebook">
                </div>
                <div class="col-md-1">
                    <img src="img/insta.png" alt="Instagram">
                </div>
                <div class="col-md-1">
                    <img src="img/twitter.png" alt="Twitter">
                </div>
                <div class="col-md-3 copy">
                    <p>COPYRIGHT © ANDREI URSU</p>
                </div>
            </div>
        </footer>
      </div>
    </body>
</html>
