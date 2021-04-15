<%-- 
    Document   : newjsp
    Created on : Mar 20, 2021, 8:02:46 PM
    Author     : TriCuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HistoryDetail Page</title>
        <style>
            #modal2 td{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <c:set var="dtoOD" value="${requestScope.OD}"/>
        <h1>Customer:${sessionScope.NAME}</h1>
        <p>Email:${sessionScope.EMAIL}</p>
        <p>Create Bill:${dtoOD.dateOrder}</p>
        <p>TotalPrice Bill:${dtoOD.totalPrice} $</p>
        <a href="setTime">Back</a>
       
                
                            <div id="modal2" class="modal" style="display: block; " >

                            <span id="modal-close" class="close">&times;</span>
                            <img id="modal-content" class="modal-content">
                            <div >
                                <table style="width:70%; background-color: whitesmoke;margin-left:  15% ;opacity: 90%" >
                                    <thead>
                                        <tr style="background-color: yellowgreen">
                                            <th>Img</th>
                                            <th>Unit Price</th>
                                            <th>Amount</th>
                                            <th>Price</th>
                                            <th>Date Rent</th>
                                            <th>Date Return</th>
                                        </tr>
                                    </thead>
                                    <tbody>


                                           <c:set var="listHisD" value="${requestScope.HISTORYHD}"/>
                                        <c:forEach var="dto1" items="${listHisD}">
                                            <tr>    
                                                <td><img src="${dto1.img}" style="width: 150px;height: 150px"></td>
                                                <td>${dto1.unitPrice}</td>
                                                <td>${dto1.quantity}</td>
                                                <td>${dto1.price}</td>
                                                <td>${dto1.rentDate}</td>
                                                <td>${dto1.returnDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>

    </body>
</html>
