<%--
  Created by IntelliJ IDEA.
  User: nicola
  Date: 12/09/20
  Time: 01:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <style>
        .section_item{
            background-color: cadetblue;
            display: inline-block;
            text-align: center;
            color: whitesmoke;
            padding: 10px 10px 20px 20px;
            margin: 10px 10px;
        }
        .para{
            color: #403cb8;
        }
        .ancora{
            color: darkslategray;
        }


    </style>
</head>
<body>
<%@include file="Header.jsp"%>
<div id="main_news_container">
    <section class="section_item">
        <p class="para"> NASA: tutti i piani per la colonizzazione della Luna, dal Gateway ad Artemis</p>
        <a class="ancora" href="https://www.hdblog.it/scienza/articoli/n519214/nasa-piani-colonizzazione-luna-gateway-artemis/">  Articolo di HDblog.it</a>
    </section>

    <section class="section_item">
        <p class="para"> Con Oculus Infinite Office l’ufficio diventa virtuale</p>
        <a class="ancora" href="https://www.tomshw.it/hardware/con-oculus-infinite-office-lufficio-diventa-virtuale/">Articolo di tom's Hardware</a>
    </section>

    <section class="section_item">
        <p class="para">ORTO - perché utilizzare il Biochar BioDea e Distillato di Legno BioDea per il trattamento del terreno?</p>
        <a  class="ancora" href="https://www.lavocediasti.it/2020/09/23/leggi-notizia/argomenti/attualita-15/articolo/orto-perche-utilizzare-il-biochar-biodea-e-distillato-di-legno-biodea-per-il-trattamento-del-terr-6.html">Articolo di la voce di Asti.it</a>
    </section>
</div>
<%@include file="Footer.jsp"%>
</body>
</html>