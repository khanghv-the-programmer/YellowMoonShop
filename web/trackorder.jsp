<%-- 
    Document   : trackorder
    Created on : Oct 14, 2020, 10:30:41 AM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="style/navcss.css" %></style>
        <link href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />" rel="stylesheet">
        <title>Track Order</title>
    </head>
    <body>
        <div id="nav-bar">




            <div class="drop-down" >
                <button class="drop-btn">Category</button>
                <div class="drop-down-content">
                    <form action="SearchCake" method="POST" class="nav-form">
                        <c:forEach items="${sessionScope.CATELIST}" var="category">
                            <input type="hidden" name="how" value="category"/>
                            <button name="Category" class="btn-cate" value="${category.categoryID}">${category.categoryName}</button>
                        </c:forEach>
                    </form>
                </div>
            </div>


            <a href="LoadingCake"><img src="https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.15752-9/121335582_341830913577392_3409883093042061007_n.png?_nc_cat=110&_nc_sid=ae9488&_nc_ohc=dwNpknMfhQ8AX99TMIq&_nc_ht=scontent.fsgn5-3.fna&oh=20e52f0f787dc40eff286543be3be3b0&oe=5FACE1CA" style="height: 120px; width: 120px; margin-left: 460px; margin-right: 300px;"/></a>

            <c:if test="${sessionScope.DTO == null}" var="isNotLogin">
                <form action="index.jsp" method="POST" class="nav-form">

                    <button type="submit" class="btn-cate">Login</button>
                </form></c:if>
            <c:if test="${sessionScope.DTO.roleID.roleID ne '2'}">

                <form action="viewcart.jsp" method="POST" class="nav-form">
                    <button type="submit" class="btn-cate">View Cart</button>
                </form>

            </c:if>
            <c:if test="${!isNotLogin}">

                <form action="Logout" method="POST" class="nav-form">
                    <button type="submit" class="btn-cate">Log out</button>
                </form>
            </c:if>





        </div><br/><br/><br/><br/>

        <c:if test="${requestScope.CART != null}" var="isCartNotNull">
            <c:if test="${not empty requestScope.CART}" var="isCartNotEmpty">
                <div id="detail">
                    <div>
                        Cart Name: ${requestScope.CART.orderName}<br/>
                        Date Order: ${requestScope.CART.dateOrder}<br/>
                        Address: ${requestScope.CART.address}<br/>
                        Phone: ${requestScope.CART.phone}<br/>
                        Payment method:${requestScope.CART.payment}<br/>
                        Payment status:${requestScope.CART.paymentStatus}<br/>
                    </div><br/><br/>
                    <div>
                        <c:if test="${not empty requestScope.CART.orderDetailList}" var="isListNotEmpty">

                            <table border="1" style="background-color: white">
                                <thead>
                                    <tr>
                                        <th>Number</th>
                                        <th>Cake Name</th>
                                        <th>Image</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${requestScope.CART.orderDetailList}" var="product" varStatus="counter"> 
                                        <tr>
                                            <td style="width: 100px; text-align: center">${counter.count}</td>
                                            <td style="width: 350px; text-align: center">${product.IDCake.cakeName}</td>
                                            <td style="width: 100px; text-align: center"><img src="${product.IDCake.image}" height="100px" width="100px"/></td>
                                            <td style="width: 100px; text-align: center">${product.quantity}</td>
                                            <td style="width: 100px; text-align: center">${product.price}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table><br/>
                            Total: ${requestScope.CART.total} VND<br/><br/>
                        </c:if>
                        <c:if test="${!isListNotEmpty}">
                            Cart Empty!
                            <a href="LoadingCake">Browse some products</a>
                        </c:if>

                    </div>
                </div>
            </c:if>
            <c:if test="${!isCartNotEmpty}">
                Cart Empty!
                <a href="LoadingCake">Browse some products</a>
            </c:if>
        </c:if>
        <c:if test="${!isCartNotNull}">
            <p style="color: red">Cart not Found</p>
        </c:if>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script>
            <%@include file="script/catalogjs.js" %>
        </script>
        <script>
            <%@include file="script/cartjs.js" %>
        </script>
    </body>
</html>
