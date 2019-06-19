<%-- 
    Document   : add_drivetrain
    Created on : 18-May-2019, 16:13:20
    Author     : andrei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Add New Drivetrain</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid">
            <div class="formcont">
                <form action="AddDrivetrain" method="post">
                    <div class="addtext">
                        <label for="model">Car model: </label> 
                        <input type="text" name="model" required>
                    </div>
                    <div class="addtext">
                        <label for="engine">Engine: </label> 
                        <input type="text" name="engine" required>
                    </div>
                    <div class="addtext">
                        <label for="transmission">Transmission: </label> 
                        <input type="text" name="transmission" required>
                    </div>
                    <div class="addtext">
                        <label for="power">Power (HP): </label> 
                        <input type="text" name="power" required>
                    </div>
                    <div class="addtext">
                        <label for="torque">Torque (Nm): </label> 
                        <input type="text" name="torque" required>
                    </div>
                    <div class="submitdrive">
                        <input type="submit" class="btn btn-info btn-lg addMan" name="addMan" value="Add Car Model">
                    </div>
                </form>
            </div>
        </div>
        <div class="bar"></div>
        <%@include file="footer.jsp" %>
    </body>
</html>
