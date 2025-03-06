<%-- 
    Document   : ManageForAdmin_Search
    Created on : Feb 20, 2025, 3:26:46 PM
    Author     : thaiv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page</title>
        <style>
            .button-search{
                margin-left: 10px;
                border: solid;
                border-radius: 10px ;
                background-color: #EA83AA;
                color: white;
                padding: 7px;
            }
        </style>
    </head>
    <body>
        <div class="navbar">
            <div class="logo"><i class="fas fa-store"></i> Toy Store</div>
            <form action="AdminManager_Search" method="GET">
                <div class="search-bar">
                    <i class="fas fa-search"></i>
                    <input type="hidden" name="page" value="<%= request.getParameter("page")%>">
                    <input type="text" name="query" placeholder="Search here">
                    <button class="button-search" type="submit">Search</button> 
                </div>
            </form>


        </div>
    </body>
</html>
