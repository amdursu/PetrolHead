<%-- 
    Document   : search_results
    Created on : 12-May-2019, 15:22:42
    Author     : andrei
--%>

<%@page import="domain.Manufacturer"%>
<%@page import="domain.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            ArrayList<Car> cars = (ArrayList<Car>) request.getAttribute("searchC");
            ArrayList<Manufacturer> manufacturers = (ArrayList<Manufacturer>) request.getAttribute("searchM");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid">
            <h1>Search results</h1>
            <% if(cars.size() > 0) { %>
            <div class="cars">
                <h2 style="margin-left: 30px;">Car models</h2><br>
                <form action="ModelController" method="post">
                    <div class="row">
                    <% 
                        int i = 0;
                        for(Car c : cars){ 
                            String car = c.getManufacturer() + " " + c.getModel();
                            if(i % 3 == 0 && i != 0){
                    %>
                    </div> <div class="row"> <% } %>
                    <div class='col-md-4 car'>
                        <button type="submit" name="<%= c.getModel() %>">
                            <img src='<%= c.getImg() %>' alt="<%= c.getModel() %>">
                            <h3>See car model</h3>
                        </button>
                        <h2><%= car %></h2>
                    </div>
                    <% i++; } %>
                    </div>
                </form>
            </div> <% } %>
            <br>
            <% if(manufacturers.size() > 0) { %>
            <div class="item">
                <h2 style="margin-left: 30px;">Manufacturers</h2><br>
                <form action="ManufacturerController" method="post">
                    <div class="row">
                    <% 
                        int j = 0;
                        for(Manufacturer m : manufacturers){ 
                            if(j % 4 == 0 && j != 0){
                    %>
                    </div> <div class="row"> <% } %>
                    <div class='col-md-3 manufacturer'>
                        <button type="submit" name="<%= m.getName()%>">
                            <img src='<%= m.getImgPath()%>' alt="<%= m.getName()%>">
                            <h3>See car models</h3>
                        </button>
                    </div>
                    <% j++; } %>
                    </div>
                </form>
            </div> <% } %>
        </div>
    </body>
</html>
