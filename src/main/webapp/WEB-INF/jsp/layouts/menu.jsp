<%@ page import="com.lambda.pe.lambdaapp.util.Constants" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 8/10/2023
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessions = request.getSession(false);
    User user = (User) sessions.getAttribute(Constants.USER_KEY_SESSION.label);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="assets/css/layouts/footer.css" rel="stylesheet" />
    <link href="assets/css/home.css" rel="stylesheet" />
    <link rel="icon" href="assets/img/Tesla_Logo.png">
    <link href="assets/css/layouts/menu.css" rel="stylesheet" />

    <script src="https://kit.fontawesome.com/847b56c7ec.js" crossorigin="anonymous"></script>
    <script src="assets/" crossorigin="anonymous"></script>

    <title>Lambda Project</title>
</head>
<body>
<header>
    <nav id="navbar-personalized" class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <div>
                <a class="navbar-brand text-center align-middle" style="display: inline-block;" href="#">
                    <div class="d-flex" >
                        <img  src="assets/img/lamda_logo.png" alt="Logo" height="50" class="d-inline-block align-text-top">
                        <div class="nav-title text-center align-middle">
                            <span class="">
                                LAMBDA
                            </span>

                        </div>
                    </div>

                </a>
            </div>

            <div>
                <div class="d-flex" >
                    <div class="d-flex me-1">
                        <img  src="assets/img/perfil.png" alt="Logo" height="50" class="d-inline-block align-text-top">

                    </div>
                    <div class="row nav-title text-center align-middle d-block">
                            <span class="d-flex">
                                <%=user.getNombres()%>
                            </span>
                            <span class="d-flex">
                                <%=user.getCorreo()%>
                            </span>
                    </div>
                    <div class="d-flex ms-2">
                        <button class="btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#left-menu" aria-controls="left-menu"><i class="fa-solid fa-bars"></i></button>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <div  class="left-bar-color offcanvas offcanvas-end" tabindex="-1" id="left-menu" aria-labelledby="left-menuLabel">
        <div class="offcanvas-header">
            <h1 id="left-menuLabel">Menú</h1>
            <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">

            <div class="list-group">

                <a href="#" class="my-2 list-group-item list-group-item-action left-bar-color"><i class="fa-regular fa-newspaper"></i> Noticias</a>
                <a href="#" class="my-2 list-group-item list-group-item-action left-bar-color"><i class="fa-solid fa-book"></i> Reservas</a>
                <a href="#" class="my-2 list-group-item list-group-item-action left-bar-color"><i class="fa-solid fa-ticket"></i> Tickets</a>
                <a href="#" class="my-2 list-group-item list-group-item-action left-bar-color"><i class="fa-solid fa-plane"></i> Vacaciones</a>
            </div>
        </div>

        <div class="offcanvas-end">

            <div class="list-group">

                <a href="<%=request.getContextPath()%>/logout" class="my-2 list-group-item list-group-item-action left-bar-color"><i class="fa-solid fa-right-from-bracket"></i> Salir</a>

            </div>
        </div>
    </div>
</header>






