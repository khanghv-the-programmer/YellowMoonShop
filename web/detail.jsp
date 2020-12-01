<%-- 
    Document   : detail
    Created on : Oct 9, 2020, 5:17:07 PM
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
        <title>Detail</title>
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

        <div id="detail">
            <c:if test="${requestScope.CAKE != null}">
                <h2>${requestScope.CAKE.cakeName}</h2>
                <img style="width: 200px; height: 200px" src="${requestScope.CAKE.image}"/>
                <h4>${requestScope.CAKE.price} VND</h4>
                <p>
                    ${requestScope.CAKE.description}
                </p>
                <p>${requestScope.CAKE.createDate} - ${requestScope.CAKE.expireDate}</p>
                <p>Status: ${requestScope.CAKE.status}</p>
                <p>Quantity: ${requestScope.CAKE.quantity}</p>
                <p>Last Update: ${requestScope.CAKE.lastUpdate}</p>
                <p>Updated By: ${requestScope.CAKE.userUpdate}</p>


            </c:if>
            <h2><div id="Updated" style="color: green"> ${requestScope.OK}</div></h2>

        </div>


        <c:if test="${sessionScope.DTO.roleID.roleID eq '2'}" var="isAdmin">
            <form action="LoadDetail" method="POST">

                <button type="submit" value="edit" class="btn-cate" name="action">Edit
                    <input type="hidden" value="${requestScope.CAKE.IDCake}" name="cakeID"/>
                </button>

            </form>
        </c:if>
        <c:if test="${!isAdmin}">
            <c:if test="${sessionScope.CART != null}" var="isCartExist">
                <form action="AddToCart" method="POST">
                    <input type="hidden" value="${requestScope.CAKE.IDCake}" name="cakeID"/>
                    <input type="hidden" value="${requestScope.CAKE.price}" name="txtPrice"/>
                    <button type="submit" value="AddToCart" class="btn-cate" name="action">
                        Add To Cart
                        <input type="hidden" value="${requestScope.CAKE.IDCake}" name="cakeID"/>
                    </button>

                </form>
            </c:if>
            <c:if test="${!isCartExist}">
                <c:if test="${sessionScope.DTO.roleID.roleID eq '1'}" var="isMember">
                    <form action="AddToCart" method="POST">
                        <c:if test="${sessionScope.CART == null}">
                            Address: <input type="text" name="txtAddress" value="${requestScope.ADDRESS}" /><p style="color: red">${requestScope.AERR}</p><br/>
                            Phone: <input type="text" name="txtPhone" value="${sessionScope.DTO.phone}" /><p style="color: red">${requestScope.PERR}</p><br/>
                        </c:if>
                        <input type="hidden" value="${requestScope.CAKE.IDCake}" name="cakeID"/>
                        <input type="hidden" value="${requestScope.CAKE.price}" name="txtPrice"/>
                        <button type="submit" class="btn-cate"  value="AddToCart" name="action">
                            Add To Cart

                        </button>

                    </form>
                </c:if>
                <c:if test="${!isMember}">
                    <form action="AddToCart" method="POST">
                        <c:if test="${sessionScope.CART == null}">
                            Name: <input type="text" name="txtFullName" value="${requestScope.NAME}" /><p style="color: red">${requestScope.NERR}</p><br/>
                            Address: <input type="text" name="txtAddress" value="${requestScope.ADDRESS}" /><p style="color: red">${requestScope.AERR}</p><br/>
                            Phone: <input type="text" name="txtPhone" value="${requestScope.PHONE}${sessionScope.DTO.phone}" /><p style="color: red">${requestScope.PERR}</p><br/>
                            <input type="hidden" value="${requestScope.CAKE.IDCake}" name="cakeID"/>
                            <input type="hidden" value="${requestScope.CAKE.price}" name="txtPrice"/>
                        </c:if>
                        <c:if test="${requestScope.CAKE.quantity le 0}" var="isAvailable">   

                            Sorry This Cake is out of stock! Please choose another cake
                        </c:if> 

                        <c:if test="${!isAvailable}">
                            <button type="submit" value="AddToCart" class="btn-cate" name="action">
                                Add To Cart

                            </button>
                        </c:if>
                    </form>
                </c:if>

            </c:if>

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
