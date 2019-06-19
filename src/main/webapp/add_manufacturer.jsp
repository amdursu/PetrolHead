<%-- 
    Document   : add_manufacturer
    Created on : 18-May-2019, 12:56:46
    Author     : andrei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Add New Manufacturer</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid">
            <div class="formcont">
                <form action="AddManufacturer" method="post" enctype="multipart/form-data">
                    <div class="addtext">
                        <label for="manufacturer">Manufacturer Name: </label> 
                        <input type="text" name="manufacturer" required> <br><br>
                    </div>
                    <div class="addimg">
                        <label for="image">Select image: </label> 
                        <input type="file" name="image"> <br><br>
                    </div>
                    <input type="submit" class="btn btn-info btn-lg addMan" name="addMan" value="Add Manufacturer">
                </form>
            </div>
        </div>
        <div class="bar"></div>
      <%@include file="footer.jsp" %> 
    </body>
</html>
