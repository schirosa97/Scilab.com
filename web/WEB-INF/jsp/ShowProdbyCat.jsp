<%--
  Created by IntelliJ IDEA.
  User: alessandroSchirosa
  Date: 22/09/2020
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Categoria" %>
<%@ page import="Model.Prodotto" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SciLab.com-Prodotti</title>
    <style>
        * {
            box-sizing: border-box;
        }
        .ShowProductContainer::after{
            display: table;
            content: "";
            clear: both;
            margin: 16px 16px;
            border: 16px;
            padding: 15px 15px 10px 10px;
        }
        .ProductContainer{
            background-color: azure;
            margin: 10px 10px 20px 15px;
            border: 16px;
            padding: 10px;
            display: inline-block;
            overflow: hidden;
            position: relative;
            float: left;
            width: 20%;
            bottom: 20px;
        }
        #img-prd{
            width: 25%;
            height: auto;
        }
        .btnDettagliProd{
            position: relative;
            border-radius: 10%;
            background-color: blueviolet;
            top: 10%;
        }
        .ProductContainer > img.img-prd{
            width: 200px;
            height: 200px;
        }
        .btnDettagliProd > a.Linkdettagli{
            text-decoration: none;
            font-size: medium;
            background-color: yellow;
            font-family: Courier, sans-serif;
        }
    </style>
</head>
<body>
<%@include file="Header.jsp"%>

<div class="ShowProductContainer">
    <c:forEach var="prodotto" items="${prodotto}">
        <div class="ProductContainer">
            <span> <c:out value="${prodotto.idprod}"></c:out> </span>
            <span> <c:out value="${prodotto.nome}"></c:out> </span>
            <span><c:out value="${prodotto.prezzo}"></c:out> </span>
            <span><c:out value="${prodotto.quantProdotto}"></c:out></span>
            <img id="img-prd"  style="display: inline-block" src="${pageContext.request.contextPath}/Immagini/product/<c:out value="${prodotto.idprod}"></c:out>.png">
            <button class="btnDettagliProd">
                <a class="Linkdettagli" href="${pageContext.request.contextPath}/HomeServ">Dettagli Prodotto</a>
            </button>
        </div>
    </c:forEach>
</div>
<%@include file="Footer.jsp"%>
</body>
</html>
