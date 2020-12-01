<%-- 
    Document   : catalog
    Created on : Oct 7, 2020, 5:47:43 PM
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
        <title>Catalog</title>
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
        <h2><div id="Updated" style="color: green"> ${requestScope.OK}</div></h2>
        <form action="SearchCake" method="POST" class="nav-form">
            <input type="text" name="txtSearch" style="height: 45px; width: 288px; margin-right: 5px "value="" placeholder="Input the name of cake you want to search"/>
            <input type="hidden" name="how" value="name" />
            <button type="submit" class="btn-cate">Search</button>
        </form><br/>
        <c:if test="${sessionScope.DTO != null}">
            <form action="TrackOrder" method="POST" class="nav-form">
                <input type="text" name="txtOrder" style="height: 45px; width: 288px; margin-right: 5px " value="" placeholder="Input the ID of order you want to track"/>
                <input type="submit" value="Track Order" class="btn-cate"/>
            </form>
        </c:if><br/>
        <form action="SearchCake" method="POST" class="nav-form">


            <div id="price-box">
                Find by Price:<br/>
                Min Price: <input type="range" name="txtMin" id="rangeMin" min="0" max="300000" step="10000"/>
                <span id="outputMin"></span><br/>
                Max Price: <input type="range" name="txtMax" id="rangeMax" min="0" max="300000" step="10000"/>
                <span id="outputMax"></span>
            </div>
            <input type="hidden" name="how" value="price" />
            <button type="submit" class="btn-cate">Search By Price</button>

        </form><br/>
        <h2 style="margin-right: 100px">Hello ${sessionScope.DTO.fullname}</h2>
        <c:if test="${sessionScope.DTO.roleID.roleID eq '2'}">
            <form action="createcake.jsp" method="POST" class="nav-form">
                <button type="submit" class="btn-cate">Create new MoonCake</button>
            </form>
        </c:if>
        <p style="color: red">${requestScope.EMPTY}</p>
        <p style="color: red">${requestScope.NOLOGIC}</p>
        <p style="color: red">${requestScope.NOORDER}</p>
        <h2>${requestScope.RANGE}</h2>

        <c:if test="${requestScope.CAKELIST != null}">
            <c:if test="${not empty requestScope.CAKELIST}" var="isNotEmpty">
                <div id="newfeed-area" >
                    <c:forEach items="${requestScope.CAKELIST}" var="cake">
                        <form action="LoadDetail" method="POST" style="display: inline-block">
                            <button type="submit" style="width: 400px; height: 400px" value="detail" name="action">
                                <input type="hidden" value="${cake.IDCake}" name="cakeID"/>
                                <h2>${cake.cakeName}</h2>
                                <img style="width: 200px; height: 200px" src="${cake.image}"/>
                                <h4>${cake.price} VND</h4>
                            </button>
                        </form>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${!isNotEmpty}">
                Empty List!
            </c:if>
        </c:if>

    <center>
        <c:forEach begin="1" end="${requestScope.TOTALPAGE}" var="page">
            <a id="page-num" style="font-size: 2em" href="LoadingCake?pageNum=${page}">${page}</a>&nbsp;&nbsp;
        </c:forEach>
    </center>
    <script>
        <%@include file="script/catalogjs.js" %>
    </script>
</body>
</html>

