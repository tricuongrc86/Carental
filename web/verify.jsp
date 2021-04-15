<%-- 
    Document   : orderSuccess
    Created on : Jan 23, 2021, 4:29:53 PM
    Author     : TriCuong
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
        <title>Verify Account</title>
    </head>
    <<style>
      body {
        text-align: center;
        padding: 40px 0;
        background: #EBF0F5;
      }
        h1 {
          color: #88B04B;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-weight: 900;
          font-size: 40px;
          margin-bottom: 10px;
        }
        p {
          color: #404F5E;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-size:20px;
          margin: 0;
        }
      i {
        color: #9ABC66;
        font-size: 100px;
        line-height: 200px;
        margin-left:-15px;
      }
      .card {
        background: white;
        padding: 60px;
        border-radius: 4px;
        box-shadow: 0 2px 3px #C8D0D8;
        display: inline-block;
        margin: 0 auto;
      }
    </style>
    <body>
      <div class="card">
      <div style="border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;">
        <i class="checkmark">✓</i>
      </div>
      <c:if test="${not empty requestScope.signup}"> <h1>Sign up is successful</h1></c:if>
         <c:if test="${ empty requestScope.signup}"> <h1>Please verify account</h1></c:if>
        <h3>Check your email to take verify code</h3>
        <h4>Code</h4>
        <form action="verify" method="POST">
        <button type="submit" >Verify</button>
        <input name="txtCode" value=""/>
        </form>
           <a href="reSend">ReSend verify code</a>
      </div>
    </body>
</html>
