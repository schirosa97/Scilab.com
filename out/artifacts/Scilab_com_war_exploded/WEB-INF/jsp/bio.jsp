<%--
  Created by IntelliJ IDEA.
  User: nicola
  Date: 12/09/20
  Time: 01:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bio</title>
</head>
<style>

    * {
        box-sizing: border-box;
    }
    section{
        display:flex !important;
        justify-content:center;
        width:80%;
        margin:auto;
    }
    .block div{
        text-align: center;
        padding: 10px 10px;
        margin: 10px 20px;
    }
    .text p{
        text-align: center;
        color: black;
        padding: 10px 10px;
        margin: 10px 20px;
    }
    .img1{
        position: relative;
        width: 100px;
        height: auto;
        float: left;
    }
    .bio1{
        display: inline-block;
        float: left;
        padding: 10px 10px 10px 10px;
        margin: 20px 20px 16px 16px;
        background-color: aquamarine;
        color: #403cb8;
    }
    @media screen and(max-width: 600px) {
        .bio1{
            width: 100%;
        }
        .img1{
            max-width: 100px;
            height: 100px;
        }
        section{
            width: 100%;
        }
    }

</style>
<body>

<%@include file="Header.jsp"%>
<div class="bio-devs">
    <img class="img1" style="display: inline-block" src="${pageContext.request.contextPath}/Immagini/Foto/foto1.jpg">
    <p class="bio1">
        Salve sono Alessandro Schirosa<br> studente alla facoltà di informatica nel campus Unisa.<br>
        Sono nato ad Agropoli ma vivo a Salerno ho 23 anni<br>
        mi piace il cibo e viaggare.
        <br>
    </p>
    <img class="img1" style="display: inline-block" src="${pageContext.request.contextPath}/Immagini/Foto/foto2.jpeg">
    <p class="bio1">
        Salve sono Nicola Pagliara<br> studente alla facoltà di informatica nel campus unisa.<br>
        Sono nato a salerno e ci vivo tutt'ora ho 23 anni<br>
         mi piace la fisica e tutto ciò che riguarda l'universo.
        <br>
    </p>
</div>
<section>
    <div class="block">
        <p class="text" style="color: #060606">
            <br>
            <br>
            SciLab è un sito che si occupa della vendita di articoli utilizzati nella ricerca scientifica di Fisica, Chimica, Matematica e informatica
            andando a consolidare le esigenze di tutta la tipologia di clientela; dal principiante alle prime armi fino ad arrivare al ricercatore più esperto.
            la spedizione è rapida e le consegne rispettano sempre le date di scadenza.
            i metodi di pagamento sono: contrassegno, postepay, paypal, iban e carta di credito.
            Le consegne arrivano ovunque tu voglia e il servizio clienti è sempre a disposizione per maggiori informazioni cliccate nelle nostre pagine social.
            <br>
            <br>
            <q>Per scoprire qualcosa, è meglio eseguire esperimenti accurati che impegnarsi in profonde discussioni filosofiche.</q>
        </p>

    </div>
</section>
<%@include file="Footer.jsp"%>
</body>
</html>
