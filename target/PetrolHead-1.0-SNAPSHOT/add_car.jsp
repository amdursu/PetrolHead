<%-- 
    Document   : add_car
    Created on : 18-May-2019, 12:56:54
    Author     : andrei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Add New Car Model</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid">
            <div class="formcont">
                <form action="AddCar" method="post" enctype="multipart/form-data">
                    <div class="addtext">
                        <label for="manufacturer">Manufacturer Name: </label> 
                        <input type="text" name="manufacturer" required>
                    </div>
                    <div class="addtext">
                        <label for="model">Model Name: </label> 
                        <input type="text" name="model" required>
                    </div>
                    <div class="addtext">
                        <label for="description">Description: </label> 
                        <textarea name="description" rows="5" cols="30"></textarea>
                    </div>
                    <div class="addimg">
                        <label for="image">Select image: </label> 
                        <input type="file" name="image"> <br><br>
                    </div>
                    <input type="submit" class="btn btn-info btn-lg addMan" name="addMan" value="Add Car Model">
                </form>
            </div>
        </div>
        <div class="bar"></div>
        <%@include file="footer.jsp" %>
    </body>
</html>
