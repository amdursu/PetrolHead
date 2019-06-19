<%-- 
    Document   : car
    Created on : 30-Apr-2019, 17:37:17
    Author     : andrei
--%>


<%@page import="domain.DBHandler"%>
<%@page import="domain.ReviewsDAO"%>
<%@page import="domain.Review"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="domain.Drivetrain"%>
<%@page import="domain.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% 
            Car c = (Car) request.getAttribute("car"); 
            ArrayList<Drivetrain> drivetrains = (ArrayList<Drivetrain>) request.getAttribute("drivetrains");
            String title = c.getManufacturer() + " " + c.getModel();
            int drivetrainsSize = drivetrains.size();
        %>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= title %></title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid ">
            
            <h1><%= title %></h1>
            <div class="intro">
                <div class="photo">
                    <img src="<%= c.getImg() %>" />
                </div>
                <div class="description">
                    <p><%= c.getDescription() %></p>
                </div>
            </div>
            <div class="dts">    
            <button type="button" class="btn btn-danger btn-lg dtsbtn" data-toggle="modal" data-target="#drivetrains">Engine Choices and Specifications</button>
                <div id="drivetrains" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-xl">
                        <div class="modal-content myModal">
                            <div class="modal-header">
                                <div class="modalTitle">
                                    <h1><%= title %> Drivetrain Options and Specifications</h1>
                                    <p>Here you can select your preffered drivetrain and add this car to your favorites.</p>
                                </div>
                                <div class="dismiss">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">&times;</button>
                                </div>
                            </div>
                            <div class="modal-body">
                                <form action="FavoriteController" method="post">
                                    <div class="row">
                                        <% 
                                            int i = 0;
                                            for(Drivetrain d : drivetrains){
                                                String name = d.getCarModel() + " " + d.getEngine() + " " + d.getTransmission() + " " + d.getPower();
                                                if(i % 2 == 0 && i != 0){

                                        %>
                                    </div> <div class="row"><% } %>

                                        <div class="col-md-4 <% if(i%2==0){%> left<% } else { %> right<% } %>" >
                                            <label>Engine: </label> <%= d.getEngine() %> <br>
                                            <label>Transmission: </label> <%= d.getTransmission() %> <br>
                                            <label>Power: </label> <%= d.getPower() %> HP <br>
                                            <label>Torque: </label> <%= d.getTorque() %> Nm <br>
                                            <% if(request.getSession().getAttribute("USER") != null){ %>
                                            <br>
                                            <button type="submit" name="<%= name%>" class="btn btn-info btn-lg">Add to favorites</button>
                                            <% } %>
                                        </div>   
                                            <% i++; } %>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
            <div class="reviews">
                <h2>Reviews</h2><br><br><br>
                <% if(request.getSession().getAttribute("USER") == null){ %>
                <div class="noreview">
                    <h3>You must be logged in to add a review!</h3>
                </div> <% } %>
                <% if(request.getSession().getAttribute("USER") != null){ %>
                <div class="bar"></div>
                <form action="AddReview" method="post">
                    <div class="rTitle">
                        <label for="rtitle">Review Title: </label>
                        <input type="text" name="rtitle">
                    </div>
                    <div class="rBody">
                        <label for="rbody">Review Body: </label>
                        <textarea name="rbody" rows="5" columns="30"></textarea>
                    </div>
                    <div class="rSubmit">
                        <button type="submit" class="btn btn-info btn-lg" name="<%= c.getModel() %>">Submit Review</button>
                    </div>
                </form> 
                <div class="bar"></div> <br><% } %>
                <% 
                    ArrayList<Review> reviews = ReviewsDAO.getReviews(c.getModel());
                    for(Review r : reviews){ %>
                    <div class="review">
                        <label>User <%= DBHandler.getUserName(Integer.parseInt(r.getUserID())) %> wrote the review: </label> <h3><%= r.getTitle() %></h3><br><br><br>
                        <p><%= r.getBody() %></p> 
                    </div> 
                    <% } %>
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
