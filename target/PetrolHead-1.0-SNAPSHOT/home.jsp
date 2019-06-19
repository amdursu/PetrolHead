<%-- 
    Document   : home
    Created on : 07-Apr-2019, 20:06:40
    Author     : andrei
--%>

<%@page import="domain.PopularityItem"%>
<%@page import="domain.Favorite"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%  ArrayList<PopularityItem> p = (ArrayList<PopularityItem>)request.getServletContext().getAttribute("top");
            ArrayList<Favorite> topFavorites = (ArrayList<Favorite>) domain.FavoritesDAO.getTopFavorites(p); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Home</title>
    </head>
    <body>
      <%@include file="header.jsp" %>
      <div class="container-fluid">
          <div class='cont'>
              <p class="welcome">Welcome to our website. Here you can discover car manufacturers, car models, user reviews, our users' top favorites. 
              By joining our community you can add your own car reviews and configure car specifications that you can add to your personal favorite list. </p>
          </div>
          <h3 class='fav'>Our users' top favorite cars:</h3>
          <form action="ModelController" method="post">
                <div class="row rand">
                <% 
                    int i = 0;
                    for(Favorite f : topFavorites){
                        String car = f.getCar().getManufacturer() + " " + f.getCar().getModel();
                        if(i % 3 == 0 && i != 0){
                %>
                </div> <div class="row rand">
                <% } %>
                <div class="col-md-3 favorite">
                    <div class="mod">
                        <img src="<%= f.getCar().getImg()%>" alt="<%= f.getCar().getModel()%>"><br>
                        <h2><%= car%></h2><br><br><br>
                    </div>
                    <label>Engine: </label> <%= f.getDrivetrain().getEngine() %> <br>
                    <label>Transmission: </label> <%= f.getDrivetrain().getTransmission() %> <br>
                    <label>Power: </label> <%= f.getDrivetrain().getPower() %> HP <br>
                    <label>Torque: </label> <%= f.getDrivetrain().getTorque() %> Nm <br><br>
                    <div class="det"><button type="submit" name="<%= f.getCar().getModel()%>" class="btn btn-info btn-lg">Details</button></div>
                </div>
                <% i++; } %>
                </div>
            </form>
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
