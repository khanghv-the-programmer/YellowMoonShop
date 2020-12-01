<%-- 
    Document   : index
    Created on : Oct 6, 2020, 5:10:47 PM
    Author     : USER
--%>

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
        <title>JSP Page</title>
        <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">



        <style><%@include file="style/style.css" %></style>
    </head>

    
    <body>
    <section class="w3l-login-6">
		<div class="login-hny">
			<div class="form-content">
				<div class="form-right">
					<div class="overlay">
						<div class="grid-info-form">
							<h5>Java Web - Lab 2</h5>
							<h3>WELCOME TO<br/> 
                                                            Yellow Moon Cake Shop!</h3>							
							<a href="LoadingCake" class="read-more-1 btn">Login As Guest</a>
							
						</div>
					
					</div>
				</div>
				<div class="form-left">
					<div class="middle">
						<h4>Login</h4>
						
					</div>
					<form action="Login" method="POST" style="position: static"  class="signin-form">
							<div class="form-input">
								<label>Username</label>
                                                                <input type="text"  name="txtUsername" value="${requestScope.USERNAME}" placeholder="Username" style="height: 50px" />
                                                                <br/><font color='red'>${requestScope.AERR}</font><br/>
							</div>
							
							<div class="form-input">
								<label>Password</label>
								<input type="password" placeholder="Password" value="" name="txtPassword" style="height: 50px" />
                                                                <br/><font color='red'>${requestScope.PERR}</font><br/>
							</div>
						
							
							<button name="action" value="Login" class="btn">Login</button>
					</form>
					
					<h2 style="color: red">${requestScope.EXPIRED}</h2>
                                        <h2 style="color: green">${requestScope.OK}</h2>
                                        
				</div>
                                        
				
                                    
					 
			</div>
			
		</div>
	</section>
    </body>
    
</html>
