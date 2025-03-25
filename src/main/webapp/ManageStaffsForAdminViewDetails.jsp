<%-- 
    Document   : ManageStaffsForAdminViewDetails
    Created on : Mar 1, 2025, 1:14:05 PM
    Author     : NHATHCE181222
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Details - Admin</title>

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600&display=swap" rel="stylesheet">



    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff" class="active"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManageCategory" ><i class="fas fa-box"></i> Manage Category</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=managerStock" ><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp"></jsp:include>

            <div class="main-content container">
                <h2 class="text-center"><i class="fas fa-user"></i> Staff Details</h2>
                <div class="row">
                    <div class="col-md-12">
                        <h4>Personal Information</h4>
                        <table class="table table-bordered">
                        <tr><th>Staff ID</th><td>${staff.staffID}</td></tr>
                        <tr><th>Username</th><td>${staff.username}</td></tr>
                        <tr><th>Email</th><td>${staff.email}</td></tr>
                        <tr><th>Full Name</th><td>${staff.fullName}</td></tr>
                        <tr><th>Role</th><td>${staff.role}</td></tr>
                        <tr><th>Active</th><td>${staff.isActive}</td></tr>
                    </table>
                </div>
                <div class="" style="margin-top: 300px;">
                    <div class="action-buttons-add">
                        <c:choose>
                            <c:when test="${user.role eq 'Admin'}">
                                <button class="btn btn-secondary shadow" disabled>
                                    <i class="fas fa-ban"></i> Disabled
                                </button>
                            </c:when>
                            <c:when test="${isActive eq true}">
                                <a style="padding: 5px 16px;" href="AdminManageStaff?action=banStaff&id=${staff.staffID}" 
                                   onclick="return confirm('Are you sure you want to ban this user?')" 
                                   class="btn btn-custom shadow">
                                    <i class="fas fa-ban"></i> Ban Staff
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a style="padding: 5px 16px;" href="AdminManageStaff?action=unBanStaff&id=${staff.staffID}" 
                                   onclick="return confirm('Are you sure you want to unban this user?')" 
                                   class="btn btn-custom shadow">
                                    <i class="fas fa-check"></i> Unban Staff
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <a href="AdminManageStaff?action=staff" class="btn btn-custom shadow">
                            <i class="fas fa-arrow-left"></i> Back to Staff List
                        </a>
                    </div>

                </div>
            </div>
        </div>

    </body>
</html>
