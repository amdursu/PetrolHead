<%-- 
    Document   : manufacturers
    Created on : 07-Apr-2019, 21:04:44
    Author     : andrei
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="domain.Manufacturer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/script.js"></script>
  </head>
    <body>
        <%@include file="header.jsp" %>
      <div class="container-fluid">
          <div class='cont'>
              <p class='welcome'>Here is a list of our selected car manufacturers. Each of them contain some of the most famous models produced.</p>
          </div>
          <div class='item'>
              <form action="ManufacturerController" method="post">
                <div class="row">
                <%
                    ArrayList<Manufacturer> manufacturers = (ArrayList<Manufacturer>) getServletContext().getAttribute("manufacturers");
                    int i = 0;
                    for(Manufacturer m : manufacturers){
                        if(i % 4 == 0 && i != 0){
                %>
                </div>
                <div class='row'> <% } %>

                <div class='col-md-3 manufacturer'>
                    <button type="submit" name="<%= m.getName()%>">
                        <img src='<%= m.getImgPath()%>' alt="<%= m.getName()%>">
                        <h3>See car models</h3>
                    </button>
                    
                </div>
                <% i++; } %>
                </div>
              </form>
          </div>
      </div>
      
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
