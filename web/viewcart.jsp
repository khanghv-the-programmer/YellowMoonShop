<%-- 
    Document   : viewcart
    Created on : Oct 13, 2020, 4:39:07 AM
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
        <title>View Cart</title>
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

        <c:if test="${sessionScope.CART != null}" var="isCartNotNull">
            <c:if test="${not empty sessionScope.CART}" var="isCartNotEmpty">
                <div id="detail">
                <div>
                    Cart Name: ${sessionScope.CART.orderName}<br/>
                    Date Order: ${sessionScope.CART.dateOrder}<br/>
                    Address: ${sessionScope.CART.address}<br/>
                    Phone: ${sessionScope.CART.phone}<br/>
                </div><br/><br/>
                <div>
                    <c:if test="${not empty sessionScope.CART.orderDetailList}" var="isListNotEmpty">

                        <table border="1" style="background-color: #fff">
                            <thead>
                                <tr>
                                    <th>Number</th>
                                    <th>Cake Name</th>
                                    <th>Image</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Delete</th>
                                    <th>Update Quantity</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${sessionScope.CART.orderDetailList}" var="product" varStatus="counter"> 
                                    <tr>
                                        <td style="width: 100px; text-align: center">${counter.count}</td>
                                        <td style="width: 350px; text-align: center">${product.IDCake.cakeName}</td>
                                        <td style="width: 100px; text-align: center"><img src="${product.IDCake.image}" height="100px" width="100px"/></td>
                                        <td style="width: 100px; text-align: center">${product.quantity}</td>
                                        <td style="width: 100px; text-align: center">${product.price}</td>
                                        <td style="width: 100px; text-align: center">
                                            <form action="DeleteProductInCart" class="delete-form" method="POST">
                                                <input type="hidden" name="IDProduct" value="${product.IDCake.IDCake}" />
                                                <input type="submit" value="Delete" name="action" class="delete-button" />
                                            </form>

                                        </td>
                                        <td style="width: 150px; text-align: center">
                                            <form action="UpdateProductInCart" method="POST">
                                                <input type="number" name ="txtQuantity" value="${product.quantity}" max="${product.IDCake.quantity}" min="${1}"/>
                                                <br/>Remaining: ${product.IDCake.quantity}
                                                <input type="hidden" name="IDProduct" value="${product.IDCake.IDCake}" /><br/><br/>
                                                <input type="submit" value="Update Quantity" name="action" />
                                            </form>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table><br/>
                        Total: ${sessionScope.CART.total} VND<br/><br/>

                        <form action="Checkout">
                            <input type="submit" class="btn-cate" value="Checkout" />
                        </form>
                    </div>

                    </c:if>
                    <c:if test="${requestScope.INSUFFICIENT != null}">
                        <div id="insufficient" style="display: none"> ${requestScope.INSUFFICIENT}</div>
                    </c:if>
                    <c:if test="${!isListNotEmpty}">
                        Cart Empty!
                        <a href="LoadingCake">Browse some products</a>
                    </c:if>

                </div>
            </c:if>
            <c:if test="${!isCartNotEmpty}">
                Cart Empty!
                <a href="LoadingCake">Browse some products</a>
            </c:if>
        </c:if>
        <c:if test="${!isCartNotNull}">
            Cart Empty!
            <a href="LoadingCake">Browse some products</a>
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
