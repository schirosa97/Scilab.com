<%--
  Created by IntelliJ IDEA.
  User: nicola
  Date: 07/09/20
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/Header.css">--%>
        <style>
                .mainNav ul {
                        float: left;
                        background-color: #403cb8;
                        padding: 10px;
                        margin: 0px;
                        border: 0px;
                        list-style-type: none;
                }

                .HeadList h1{
                        size: A4;
                        color: cadetblue;
                        text-align: center;
                }
                .HeadList{
                        width: 100%;
                        padding: 10px 10px 16px;
                        margin: 10px 10px;
                        overflow: auto;
                        position: relative;
                        top:0px;
                }
                .ListItem a{
                        padding: 16px;
                        color: #060606;
                        margin-left: 10px;
                        margin-right: 10px;
                        text-align: center;
                        text-decoration: none;
                }
                .logo{
                        position:relative;
                        float: left;
                        width: 50px;
                        height: 50px;
                        right: 10px;
                }
                @media screen and(max-width: 600px){
                        .ListItem,.ListItem {
                                flex-direction: column;
                        }
                        img.logo{
                                display: none;
                        }
                }
        </style>
</head>
<body>
        <header class="HeadList">
                <h1> Benvenuti sullo store di SciLab.com</h1>
                <img class="logo" src="${pageContext.request.contextPath}/Immagini/iconsito.png">
                <nav  class="mainNav">
                <ul id="LinkList" class="navList">
                        <li id="LinkItem" class="ListItem">
                                <a href="HomeServ" title="Pagina Principale">Home</a>
                                 <a href="LogServ" title="Pagina Login">Login user</a>
                                <a href="showConUsServ" title="Pagina Social">Contattaci</a>
                                <a href="ShowNewsServ" title="Pagina News">Notizie</a>
                                <a href="showBioServ" title="Pagina sugli Sviluppatori">Chi siamo</a>
                                <a class="icon-responsive"><i class="fa-fa-bars"></i> </a>
                        </li>
                </ul>
                </nav>
        </header>
</body>
</html>

