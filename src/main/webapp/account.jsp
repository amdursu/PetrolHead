<%-- 
    Document   : account
    Created on : 07-Apr-2019, 17:25:36
    Author     : andrei
--%>

<%@page import="domain.PopularityItem"%>
<%@page import="domain.FavoritesDAO"%>
<%@page import="domain.Favorite"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.DBHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% 
            String userEmail = (String)request.getSession().getAttribute("USER");
            String name = DBHandler.getName(userEmail); 
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Account</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
      <div class="container-fluid">
        <h2>Hello, <%= name%>!</h2>
        <a href="LogoutController" class="logout">Logout</a><br>
        
        <% if(request.getSession().getAttribute("ADMIN") == null && request.getSession().getAttribute("OWNER") == null){ 
                ArrayList<Favorite> favorites = (ArrayList<Favorite>) FavoritesDAO.getUserFavorites(userEmail);
        %>
        <% if(request.getAttribute("err") != null){ %>
        <div class="ef">
            <p style="color: #CA2B2B"><%= request.getAttribute("err")%></p>
        </div>
        <% } %>
        <h2 class="fav" style="margin-left: 50px; font-size: 22px;">Favorite cars list:</h2>
        <div class="favorites">
            <form action="ModelController" method="post">
                <div class="row rand">
                <% 
                    if(favorites.size() == 0){ %>
                    <div class="nofav">
                        <h3>You have not added any car to favorites</h3>
                    </div>
                    <% }
                    int i = 0;
                    for(Favorite f : favorites){
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
        <% } else if((Boolean)request.getSession().getAttribute("ADMIN") == true){ %>
        <br><br><br>
        <div class="admin">
            <form action="AddController" method="post">
                <div class="row">
                    <div class="col-md-4 newMan">
                        <button type="submit" name="newManufacturer" class="btn btn-danger btn-lg">Add new manufacturer</button>
                    </div>
                    <div class="col-md-4 newCar">
                        <button type="submit" name="newCar" class="btn btn-danger btn-lg">Add new car model</button>
                    </div>
                    <div class="col-md-4 newDrivetrain">
                        <button type="submit" name="newDrivetrain" class="btn btn-danger btn-lg">Add new drivetrain</button>
                    </div>
                </div>
            </form>
        </div>
        <% } else if((Boolean)request.getSession().getAttribute("OWNER") == true){ 
                ArrayList<PopularityItem> p = (ArrayList<PopularityItem>)request.getServletContext().getAttribute("top");
                ArrayList<Favorite> topFavorites = (ArrayList<Favorite>) domain.FavoritesDAO.getTopFavorites(p); %>
                <br><br><br><h1>Statistics</h1>
        <div class="noUsers"><h3>Number of registered users: <%= domain.DBHandler.noUsers() %></h3></div><br>
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
        <% } %>
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
