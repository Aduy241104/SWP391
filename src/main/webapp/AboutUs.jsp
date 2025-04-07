<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, DAO.CategoryDAO, Model.Category" %>
<html>
<head>
    <title>About Us - Toy Store</title>
    <link rel="stylesheet" href="css/About.css"/>
</head>
<body>

    <div class="back-container">
        <a href="ViewProductListController" class="back-btn">← Back</a>
    </div>

    <h1>Welcome to Our Toy Store</h1>

    <div class="intro">
        <h2>About Us</h2>
        <p>
            At <strong>Toy Store</strong>, we believe that childhood should be full of fun, creativity, and wonder.
            Our mission is to bring colorful and safe toys that help children grow emotionally, socially, and intellectually.
        </p>
        <p>
            Since our founding in 2024, we've been a magical place for families to explore new toys that spark joy and imagination.
            Thank you for trusting us to be a part of your child's learning journey.
        </p>
    </div>

    <h2>Our Toy Categories</h2>

    <div class="category-list">
        <%
            CategoryDAO dao = new CategoryDAO();
            List<Category> categories = dao.getAllCategory();
            for (Category cat : categories) {
        %>
            <div class="category">
                <h3><%= cat.getCategoryName() %></h3>
                <p><%= cat.getDescription() %></p>
            </div>
        <%
            }
        %>
    </div>

</body>
</html>
