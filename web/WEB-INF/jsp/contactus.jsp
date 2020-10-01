<%--
  Created by IntelliJ IDEA.
  User: nicola
  Date: 12/09/20
  Time: 01:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ConcatUs</title>
    <style>

    </style>
</head>
<body>
<%@include file="Header.jsp"%>

<div class="row" id="tabel">
    <div class="column">
        <div id="ID1">
            <div class="aiuto"> <b>Hai bisogno di un aiuto?</b> <hr style="color: black"></div>
            <div class="telefono" style="color: #060606">telefono: 0891236540</div>
            <div class="email">scilab@gmail.com</div>
            </div>
            <div class="sede">via garibaldi n:42</div>
            <div class="orario"> dal lunedi al venerdi; dalle 8:00 alle 18:00</div>
        </div>
    </div>

    <div class="form">
        <b>
            Invia un messaggio </b><br> <br> Compila il formulario e mandaci un
        messaggio: <br> <br> Paese: <select name="Paese">
        <option value="italia">Italia</option>
        <option value="germania">Germania</option>
        <option value="svizzera">Svizzera</option>
        <option value="spagna">Spagna</option>
    </select> <br> <br> <input type="text" placeholder="Nome" id="nome"> <br> <br>
        <div class="message-container">
        <input type="text" placeholder="Email" id="email"> <br> <br>
        <div class="email"> <label for="soggetto">
            oggetto:
        </label>
            <input type="text"id="soggetto" size="10" placeholder="soggetto">
        </div>
        <textarea rows=7
                  placeholder="Qui potrai inserire il messaggio che desideri inviare al nostro staff" id="txtarea"></textarea>
        <br> <br> <input type="submit" value="Confermare"
                         style="background-color: rgb(182, 3, 3);" id="conf">
    </div>
    </div>
</div>
<br>
<%@include file="Footer.jsp"%>
</body>
</html>
