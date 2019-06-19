<%-- 
    Document   : header
    Created on : 08-Apr-2019, 19:29:06
    Author     : andrei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="head">
        <header>
          <div class="row myhead">
            <div class="col-md-2">
              <img src="img/logo.png" alt="LOGO">
            </div>
            <div class="col-md-2 headelem">
              <a class="nav-item nav-link" href="home.jsp">HOME</a>
            </div>
            <div class="col-md-2 headelem">
              <a class="nav-item nav-link" href="manufacturers.jsp">CARS</a>
            </div>
            <div class="col-md-2 headelem">
              <% 
                  if(request.getSession(false) != null && request.getSession().getAttribute("USER") != null){
              %>
              <a class="nav-item nav-link" href="account.jsp">ACCOUNT</a>
              <% 
                }
                else{
              %>
              <a class="nav-item nav-link" href="login_register.jsp">ACCOUNT</a>
              <% } %>
            </div>
            <div class="col-md-4">
              <form action="SearchController" method="post">
                <span>
                  <input type="search" class="search" name="search" placeholder="Search">
                  <input type="submit" class="searchbutton" name="src" value=" ">
                </span>
              </form>
            </div>

          </div>
        </header>
            <div class="bar"></div>
      </div>
