<%@ page import="java.util.List" %>
<%@ page import="Model.Categoria" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Scilab.Com-HomePage</title>
<style>

  * {
    box-sizing: border-box;
  }

  #HomeProdContainer:after{
    display: table;
    content: "";
    clear: both;
    margin: 16px 16px;
    border: 16px;
    padding: 15px 15px 10px 10px;
  }

  .ContainerCategory{
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
    .img-cat{
      position: relative;
      width: 25%;
      height: auto;
    }

  .btnProd {
    position: relative;
    border-radius: 10%;
    background-color: blueviolet;
    top: 10%;
  }

  .CategoryContainer > img.img-cat{
    width: 200px;
    height: 200px;
  }

  .btnProd > a.LinkProduct {
    text-decoration: none;
    font-size: medium;
    background-color: yellow;
    font-family: Courier, sans-serif;
  }
  body{
    background:url("Immagini/WS.jpg") no-repeat fixed center;
    background-size: 100% 100%;
  }
  @media screen and(max-width: 600px) {
    .ContainerCategory{
      display:flex !important;
      flex-flow: row wrap;
      justify-content: space-around;
      padding: 0px;
      margin: 0px;
      list-style: none;
    }
    .img-cat{
      max-width: 100px;
      height: 100px;
    }
  }

</style>
</head>
<body>
<%@include file="WEB-INF/jsp/Header.jsp"%>

<div id="HomeProdContainer">
  <c:forEach var="categoria" items='${categorie}'>
    <div class="ContainerCategory">
    <span > <c:out value="${categoria.nome}"></c:out></span>
      <span style="display: inline-block"><c:out value="${categoria.descrizione}"></c:out> </span>
    <p><c:out value="${categoria.idcategory}"></c:out></p>
    <img class="img-cat" style="display: inline-block" src="${pageContext.request.contextPath}/Immagini/category/<c:out value="${categoria.idcategory}"></c:out>.png">
    <button style="display: block" title="Dettagli prodotto"  class="btnProd">
      <a  class="LinkProduct" href="${pageContext.request.contextPath}/PrdServ" style="color: blueviolet" target="_blank">prodotti</a>
    </button>
    </div>
  </c:forEach>
</div>
<%@include file="WEB-INF/jsp/Footer.jsp"%>
</body>
</html>
