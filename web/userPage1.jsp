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
        <title>SHOPPING PAGE</title>
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
                    dd1 = "0" + dd1;
                if (mm1 < 10)
                    mm1 = "0" + mm1;
                Minn1 = yy + "-" + mm1 + "-" + dd1;
                document.getElementById("rentDate").min = Minn1;
//                document.getElementById("rentDate").value = Minn1;
                setMinReturnDay();
            }
            function editTimeButton() {
                var a = confirm("Change date will be reset cart!");
                if (a) {
                    document.getElementById("btnSetTime").disabled = false;
                    document.getElementById("btnEditTime").disabled = true;
                }
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

    <c:if test="${ empty requestScope.NOTIFICATION}"><body></c:if>
        <c:if test="${ not empty requestScope.NOTIFICATION}">  
        <body  onload="return alert('${requestScope.NOTIFICATION}')" >
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


                <a href="setTime" class="active">Home</a>
                <a href="history">History</a>
                <a href="#">About</a>
                <a href="#">Contacts</a>
            </div>
            <div class="navbar-right" id="navb">
                <a  class="cart" onclick="clickOpenModal()">
                    <i class="bx bx-cart-alt" ></i>
                    <span class="badge">${cart.size}</span>
                </a>
                <c:if test="${empty sessionScope.EMAIL}">
                    <a href="loginPage" class="active"><button type="submit" style="padding: 2px">Login</button></a>
                </c:if>
                <c:if test="${not empty sessionScope.EMAIL}">
                    <a href="logout" class="active"><button type="submit" style="padding: 2px">Logout</button></a>
                </c:if>
            </div>

        </div>
        <!-- END TOP NAV -->
        <c:set var="listDTO" value="${requestScope.LISTCAR}"></c:set>
            <!-- MAIN -->

            <div id="slider" class="slider">
            <c:forEach var="dto" items="${listDTO}">
                <form action="addCart" method="POST">

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
                                        <button>
                                            Add to cart
                                        </button>
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
                </form>
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

        <!-- MODAL -->
        <c:if test="${ empty requestScope.DISPLAY}">
            <div id="modal" class="modal" style="display: none; " >
            </c:if>
            <c:if test="${not empty requestScope.DISPLAY}">
                <div id="modal" class="modal" style="display: block; " >
                </c:if>

                <span id="modal-close" class="close">&times;</span>
                <img id="modal-content" class="modal-content">
                <div >
                    <table style="width:70%; background-color: whitesmoke;margin-left:  15% ;opacity: 90%" >
                        <thead>
                            <tr style="background-color: yellowgreen">
                                <th>Img</th>
                                <th>Name</th>
                                <th>Price unit</th>
                                <th>Amount</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:if test="${not empty cart}">
                                <c:forEach var="pro" items="${cart.items}" varStatus="counter">
                                    <c:set var="dto" value="${pro.key}"/>
                                    <tr>
                                <form action="update" method="POST">
                                    <input type="hidden"  name="txtId" value="${dto.carID}"/>
                                    <td><img src="${dto.image}" style="width: 150px;height: 150px"></td>
                                    <td>${dto.namee}</td>
                                    <td>${dto.price}</td>
                                    <td><input type="number" name="valuePro" min=1 max="${dto.quantity}" value="${pro.value}"></td>
                                    <td><button type="submit" value="update" >Update</button></td>
                                </form>
                                <form action="delete">
                                    <input type="hidden" name="txtId" value="${dto.carID}"/>
                                    <td><button type="submit" value="delete" onclick="return confirm('Are you sure to delete?')">Delete</button></td>
                                </form>
                                </tr>
                            </c:forEach>
                        </c:if>

                        </tbody>

                    </table>
                    <div style="text-align: right;background-color: yellowgreen;margin-left: 15%;margin-right: 15%" >
                        <strong class="cart-total-title" style="color:red;font-size: 30px">Total:</strong>
                        <span class="cart-total-price" style="font-size: 30px">${sessionScope.total}</span>
                    </div>
                </div>
                <div>
                    <div style="float: right ;margin-right: 17%">
                        <c:if test="${cart.size!=0}">
                              <form action="checkOut" method="POST">
                            <button name="btAction" value="PayNormal" style="padding: 15px">Pay</button>
                            <button name="btAction" value="ZaloPay" style="padding: 15px">Zalopay</button>
                        </form>
                        </c:if>
                      
                    </div>

                </div>
            </div>






            <!-- END MODAL -->
            <c:if test="${ empty requestScope.DISPLAYH}">
                <div id="modal1" class="modal" style="display: none; " >
                </c:if>
                <c:if test="${not empty requestScope.DISPLAYH}">
                    <div id="modal1" class="modal" style="display: block; " >
                    </c:if>

                    <span id="modal-close" class="close">&times;</span>
                    <img id="modal-content" class="modal-content">
                    <div >
                        <table style="width:70%; background-color: whitesmoke;margin-left:  15% ;opacity: 90%" >
                            <c:set var="listHis" value="${requestScope.HISTORY}"/>

                            <thead>
                                <tr style="background-color: yellowgreen">
                                    <th>IdOrder</th>
                                    <th>Total Price</th>
                                    <th>Date Order</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${listHis}">
                                <form action="detail" method="POST">
                                    <input type="hidden"  name="txtIdOrder" value="${dto.idOrder}"/>
                                    <td>${dto.idOrder}</td>
                                    <td>${dto.totalPrice}</td>
                                    <td>${dto.dateOrder}</td>
                                    <td><button type="submit" value="detail">Detail</button></td>
                                </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>




                    <script type="text/javascript" src="index.js"></script>
                    </body>
                    </html>
