<%-- 
    Document   : userPage1
    Created on : Mar 8, 2021, 9:01:27 AM
    Author     : TriCuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ADMIN PAGE</title>
        <meta charset="utf-8">
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style_1.css">
        <link rel="stylesheet" type="text/css" href="modal.css">
        <script>

            function setMinReturnDay() {

                document.getElementById("returnDate").style.display = "inline";
                var today = document.getElementById("rentDate").value;
                var dd = parseInt(today.split("-")[2]) + 1;
                var yyyy = today.split("-")[0];
                var mm = today.split("-")[1];
                var Minn;
                if (dd < 10)
                    Minn = yyyy + "-" + mm + "-" + "0" + dd;
                else
                    Minn = yyyy + "-" + mm + "-" + dd;
                document.getElementById("returnDate").min = Minn;
                document.getElementById("returnDate").value = Minn;
                document.getElementById("strRentDate").value = document.getElementById("rentDate").value;

            }
            function setDayRentIsToday() {
                var c = new Date();
                var mm1 = c.getMonth() + 1;
                var dd1 = c.getDate();
                var yy = c.getFullYear();
                var Minn1;
                if (dd1 < 10)
                    // Minn1=yy+"-"+mm1+"-"+"0"+dd1;
                    dd1 = "0" + dd1;
                if (mm1 < 10)
                    mm1 = "0" + mm1;
                Minn1 = yy + "-" + mm1 + "-" + dd1;
                document.getElementById("rentDate").min = Minn1;
                setMinReturnDay();
            }
            function editTimeButton() {
                    document.getElementById("btnSetTime").disabled = false;
                    document.getElementById("btnEditTime").disabled = true;
            }
            function clickOpenModal() {
                document.getElementById("modal").style.display = "block";
            }
            function onloadBd() {

            }
        </script>
        <style> 
            td{
                text-align: center;
            } 
            #pagination a{
                color: black;
            }
            #navb a{
                color: black;
            }
            #navb a{
                color:  black;
            }
        </style>
    </head>

    <c:if test="${ empty requestScope.DIS}"><body></c:if>
        <c:if test="${ not empty requestScope.DIS}">  
        <body  onload="setDayRentIsToday()" >
        </c:if>
        <c:set  var="cart" value="${sessionScope.CART}"></c:set>

            <!-- TOP NAV -->
            <div class="navbar">
                <div href="#" class="logo">
                    <h3 style="color: brown">RICHARD CAR RENTAL</h3>
                    <form action="setTime" method="POST">
                        <div style="text-align: left;">
                            Rent Date<input  id="rentDate" type="date" name="txtRentDate" value="${sessionScope.dateRent}" oninput="setMinReturnDay()"  style=" font-size: 1rem;border-: 2px">
                        Return Date<input id="returnDate" type="date" name="txtReturnDate" value="${sessionScope.dateReturn}">
                        <button  type="submit" disabled id="btnSetTime">Set Time</button>
                        <button type="button" id="btnEditTime" onclick="editTimeButton()">Edit Time</button>
                    </div> 
                </form>
                <form action="search">
                    Search<input type="input" name="txtSearch" value="${param.txtSearch}">
                    Amount<input type="number" name="txtAmount" value="${param.txtAmount}">
                    <c:set var="listCata" value="${sessionScope.listType}"/>
                    <select name="txtTypeCar" value="${param.txtTypeCar}">
                        <c:forEach var="cata" items="${listCata}">
                            <c:if test="${cate eq param.txtTypeCar}">
                                <option selected="true">${cata}</option>
                            </c:if>
                            <c:if test="${cate ne param.txtTypeCar}">
                                <option>${cata}</option>
                            </c:if>

                        </c:forEach>
                    </select>

                    <div >
                        <button>Search</button>

                    </div> 
                </form>
                <div id="pagination" class="pagination" style="text-align: center; padding: 20px">
                    <c:forEach begin="1" end="${endPage}" var="index">
                        <a href="${requestScope.urlReWriting}${index}">${index}</a>
                    </c:forEach>
                </div>
            </div>



            <div class="navbar-right menu" id="navb">
                <a href="#"><h4>Welcome ${sessionScope.NAME}</h4></a>


                <a href="#">Home</a>
                <a href="#">Kids</a>
                <a href="#">About</a>
                <a href="#">Contacts</a>
            </div>
            <div class="navbar-right" id="navb">
                <a  class="cart" onclick="clickOpenModal()">
                    <i class="bx bx-cart-alt" ></i>
                    <span class="badge">${cart.size}</span>
                </a>
                <a href="logout" class="active"><button type="submit" style="padding: 2px">Logout</button></a>
            </div>

        </div>
        <!-- END TOP NAV -->
        <c:set var="listDTO" value="${requestScope.LISTCAR}"></c:set>
            <!-- MAIN -->

            <div id="slider" class="slider">
            <c:forEach var="dto" items="${listDTO}">
             

                    <input type="hidden" name="index" value="${index}">
                    <input type="hidden" name="txtId" value="${dto.carID}">
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}">
                    <input type="hidden" name="txtAmount" value="${param.txtAmount}">
                    <input type="hidden" name="txtTypeCar" value="${param.txtTypeCar}">
                    <div class="row fullheight slide">
                        <div class="col-6 fullheight">
                            <!-- PRODUCT INFO -->
                            <div class="product-info">
                                <div class="info-wrapper">
                                    <div class="product-price left-to-right">

                                        <span>$</span>${dto.price}
                                    </div>
                                    <div class="product-name left-to-right">
                                        <h2>
                                            ${dto.namee}
                                        </h2>
                                    </div>
                                    <div class="product-size left-to-right">
                                        <h3>Availble</h3>
                                        <div class="size-wrapper">
                                            <div class="active">${dto.quantity}</div>
                                        </div>
                                    </div>
                                    <div class="product-color left-to-right">
                                        <h3>COLORS</h3>
                                        <div class="color-wrapper">

                                            <div class="active">
                                                <div style="background-color:${dto.color}"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="product-description left-to-right">
                                     
                                    </div>
                                    <div class="button left-to-right">
                                    </div>
                                </div>
                            </div>
                            <!-- END PRODUCT INFO -->

                        </div>
                        <div class="col-6 fullheight img-col" style="background-image: linear-gradient(to top right, antiquewhite,${dto.color})">
                            <!-- PRODUCT IMAGE -->
                            <div class="product-img">
                                <div class="img-wrapper right-to-left">
                                    <div class="bounce">
                                        <img src="${dto.image}" alt="placeholder+image">
                                    </div>
                                </div>
                            </div>
                            <!-- END PRODUCT IMAGE -->
                        </div>
                    </div>
               
            </c:forEach>
            <!-- SLIDE CONTROL -->
            <div id="slide-control" class="slide-control">
                <c:forEach var="dto" items="${listDTO}">
                    <div class="slide-control-item">
                        <img src="${dto.image}" alt="placeholder+image">
                    </div>
                </c:forEach>
            </div>
            <!-- END SLIDE CONTROL -->
        </div>
        <!-- END MAIN -->

    


        <script type="text/javascript" src="index.js"></script>
    </body>
</html>
