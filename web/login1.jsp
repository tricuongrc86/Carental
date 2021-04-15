<%-- 
    Document   : login1
    Created on : Mar 7, 2021, 11:12:28 PM
    Author     : TriCuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script
      src="https://kit.fontawesome.com/64d58efce2.js"
      crossorigin="anonymous"
    ></script>
       <script src="https://www.google.com/recaptcha/api.js"></script>
    <link rel="stylesheet" href="style.css" />
    <title>Sign in & Sign up Form</title>
  </head>
    <c:if test="${ empty requestScope.NOTIFICATION}"><body></c:if>
        <c:if test="${ not empty requestScope.NOTIFICATION}">  
        <body  onload="return alert('${requestScope.NOTIFICATION}')" >
        </c:if>
        <c:set var="error" value="${requestScope.ERR}"/>
    <div class="container">
      <div class="forms-container">
        <div class="signin-signup">
            <form action="login" class="sign-in-form" method="POST">
            <h2 class="title">Sign in</h2>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input type="text" name="txtUserID" value="${param.txtUserID}" placeholder="Username" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" name="txtPassword" value="" placeholder="Password" />
            </div>
                  <c:if test="${not empty requestScope.VERYFY}"><span style="color: red">${requestScope.VERYFY}</span></c:if>
                                    <div class="g-recaptcha"
                                         data-sitekey="6LfZ_G8aAAAAAJ8SOtCANmVOG6k38jZBrf4weVqI"></div>
            <input type="submit" value="Login" class="btn solid" />
            <p class="social-text">Or Sign in with social platforms</p>
            <div class="social-media">
              <a href="#" class="social-icon">
                <i class="fab fa-facebook-f"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-twitter"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-google"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-linkedin-in"></i>
              </a>
            </div>
          </form>
          <form action="signUp" method="POST" class="sign-up-form">
            <h2 class="title">Sign up</h2>
            <div class="input-field">
            <i class="fas fa-file-signature"></i>
  
              <input name="txtName" value="${param.txtName}" type="text" placeholder="Name ${error.nameEmpty}" />
       
            </div>
            <div class="input-field">
              <i class="fas fa-envelope"></i>
              <input type="email" name="txtEmail" value="${param.txtEmail}" placeholder="Email ${error.emailEmpty} ${error.emailExisted}" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" name="txtPassword" value="${param.txtPassword}" placeholder="Password" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password"  name="txtRePassword" value="${param.txtRePassword}" placeholder="RePassword" />
            </div>
            <div class="input-field">
             <i class="fas fa-phone"></i>
              <input type="text"   name="txtPhone" value="${param.txtPhone}" placeholder="Phone ${error.phoneEmpty}" />
            </div>
            <div class="input-field">
           <i class="fas fa-map-marked"></i>
              <input type="text" name="txtAddress" value="${param.txtAddress}" placeholder="Address ${error.addressEmpty}" />
            </div>
            <input type="submit" class="btn" value="Sign up" />
            <p class="social-text">Or Sign up with social platforms</p>
            <div class="social-media">
              <a href="#" class="social-icon">
                <i class="fab fa-facebook-f"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-twitter"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-google"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-linkedin-in"></i>
              </a>
            </div>
          </form>
        </div>
      </div>

      <div class="panels-container">
        <div class="panel left-panel">
          <div class="content">
            <h3>New here ?</h3>
            <p>
              Lorem ipsum, dolor sit amet consectetur adipisicing elit. Debitis,
              ex ratione. Aliquid!
            </p>
            <button class="btn transparent" id="sign-up-btn">
              Sign up
            </button>
          </div>
           <!-- <img src="img/log.svg" class="image" alt="" /> -->
          <img src="https://svgsilh.com/svg/305463.svg" class="image" alt="" />
        </div>
        <div class="panel right-panel">
          <div class="content">
            <h3>One of us ?</h3>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum
              laboriosam ad deleniti.
            </p>
            <button class="btn transparent" id="sign-in-btn">
              Sign in
            </button>
          </div>
          <!-- <img src="img/register.svg" class="image" alt="" /> -->
           <!--<img src="img/1296834-795548.svg" class="image" alt="" />-->
           <img src="https://svgsilh.com/svg/587548.svg" class="image" alt="" />
        </div>
      </div>
    </div>

    <script src="app.js"></script>
  </body>
</html>
