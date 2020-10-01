

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Title</title>
    <style>
        footer{
            display: inline-block;
        }
        .mainfooter:after{
            display: table;
            content: "";
            clear: both;
        }

        .Footer-aboutus{
            font-family: 'DejaVu Serif', Georgia, "Times New Roman", Times, serif;
            font-size: small;
            position: relative;
            top: 10px;
            width: 25%;
            height: auto;
            float: left;
            display: inline-block;
            background-color: whitesmoke;
            margin:10px 10px 10px 10px;
            padding: 10px 10px 10px 10px;
        }

        .Footer-assist{
            font-family: 'DejaVu Serif', Georgia, "Times New Roman", Times, serif;
            font-size: small;
            position: relative;
            top: 10px;
            width: 25%;
            height: auto;
            float: left;
            display: inline-block;
            left: 50px;
            background-color: whitesmoke;
            margin:10px 10px 10px 10px;
            padding: 10px 10px 10px 10px;
        }

        .footer-copyright{
            font-family: 'DejaVu Serif', Georgia, "Times New Roman", Times, serif;
            font-size: small;
            position: relative;
            top: 10px;
            color:black;
        }

    </style>
</head>
<body>
<footer>
    <div class="mainfooter">
    <div class="Footer-aboutus">
        <br>
        <br>
        <p> <a href="${pageContext.request.contextPath}/showBioServ"> chi siamo</a> </p>
        <p> <a href="${pageContext.request.contextPath}/showConUsServ"> contattaci</a></p>
        <br>
        CI PUOI TROVARE ANCHE SU<br>
        <a href="https://www.facebook.com/" ><i class="fa fa-facebook"></i> </a>
        <a href="https://www.instagram.com/" ><i class="fa fa-instagram"> </i></a>
        <a href="https://twitter.com/" ><i class="fa fa-twitter"> </i></a>
    </div>


    <div class="Footer-assist">
        <br>
        <br>
        <p><a href="">metodi di pagamento</a> </p>
        <p><a href="#" class="colore-link"> Termini e condizioni</a></p>
        <p><a href="#" class="colore-link"> come ordinare</a></p>
        <p><a href="#" class="colore-link"> spedizione</a></p>
        <p><a href="#" class="colore-link"> commenti</a></p>
        <br>
        <br>
        <div class="footer-copyright">© 2021 Copyright:
            <a href="Home.jsp" class="colore-link"> SciLab.com</a>
        </div>
        <br>
        <br>
        <br>
        <br>
        <br>
    </div>
    </div>
</footer>
</body>
</html>
