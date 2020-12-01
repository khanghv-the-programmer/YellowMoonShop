<%-- 
    Document   : createcake
    Created on : Oct 11, 2020, 4:41:20 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8" />
        <script>
            addEventListener("load", function () {
                setTimeout(hideURLbar, 0);
            }, false);

            function hideURLbar() {
                window.scrollTo(0, 1);
            }
        </script>
        <title>Create Cake</title>
        <style><%@include file="style/style.css" %></style>
    </head>
    <body>







    </form>

    <section class="w3l-login-6">
        <div class="login-hny">
            <div class="form-content">
                <div class="form-right">
                    <div class="overlay">
                        <div class="grid-info-form">
                            <h5>Java Web - Lab 2</h5>
                            <h3> 
                                Create Cake</h3>							
                            <a href="LoadingCake" class="read-more-1 btn">Go Back</a>

                        </div>

                    </div>
                </div>
                <div class="form-left">
                    <div class="middle">
                        <h4>Create Cake</h4>

                    </div>
                    <form action="CreateCake" method="POST" style="position: static"  class="signin-form">


                        <div class="form-input">
                            <label>Cake Name</label>
                            <input type="text" name="txtName" value="${requestScope.CAKE.cakeName}${requestScope.NAME}" style="height: 50px" />
                            <br/><font color='red'>${requestScope.NERR}</font><br/>
                        </div>

                        <div class="form-input">
                            <label>Cake Price (VND)</label>
                            <input type="text" name="txtPrice" value="${requestScope.CAKE.price}${requestScope.PRICE}" style="height: 50px" />
                            <br/><font color='red'>${requestScope.PERR}</font><br/>
                        </div>
                        <div class="form-input">
                            <label>Description</label>
                            <input type="text" name="txtDesc" value="${requestScope.CAKE.description}${requestScope.DESC}" style="height: 50px" />
                            <br/><font color='red'>${requestScope.DERR}</font><br/>
                        </div>

                        <div class="form-input">
                            <label>Image(Optional)</label>
                            <input type="text" name="txtImg" value="${requestScope.CAKE.image}${requestScope.IMG}" style="height: 50px" />

                        </div>
                        <div class="form-input">
                            <label>Category</label>
                            <select style="background: #fff;
                                    outline: none;
                                    width: 100%;
                                    font-size: 17px;
                                    padding: 10px 15px;
                                    color:#090e0d;
                                    border: 2px solid #BECED0;" name="txtCategory">
                                <c:forEach items="${sessionScope.CATELIST}" var="category">
                                    <option value="${category.categoryID}">${category.categoryName}</option>
                                </c:forEach>
                            </select>


                        </div>

                        <div class="form-input">
                            <label>Quantity</label>
                            <input type="text" name="txtQuantity" value="${requestScope.CAKE.quantity}${requestScope.QUAN}" style="height: 50px" />
                            <br/><font color='red'>${requestScope.QERR}</font><br/>
                        </div>

                        <div class="form-input">
                            <label>Created Date</label>
                            <input type="date" name="txtCreate" value="${requestScope.CAKE.createDate}${requestScope.CREATE}"style="height: 50px" />
                            <br/><font color='red'>${requestScope.DTERR}</font><br/>
                        </div>

                        <div class="form-input">
                            <label>Expired Date</label>
                            <input type="date" name="txtExpired" value="${requestScope.CAKE.expireDate}${requestScope.EXPIRED}" style="height: 50px" />
                            <br/><font color='red'>${requestScope.DTERR}</font><br/>
                        </div>



                        <button name="action" value="Update" class="btn">Add Cake</button>
                    </form>


                </div>




            </div>

        </div>
    </section>
    <script>
        <%@include file="script/catalogjs.js" %>
    </script>
</body>
</html>
