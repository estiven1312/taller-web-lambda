<%@ page import="com.lambda.pe.lambdaapp.util.Constants" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.User" %><%-- Created by IntelliJ IDEA. User: Usuario Date:
            8/10/2023 Time: 18:12 To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession sessions=request.getSession(false); User user=(User)
        sessions.getAttribute(Constants.USER_KEY_SESSION.label); %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link href="<%=request.getContextPath()%>/assets/css/layouts/footer.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/assets/css/home.css" rel="stylesheet" />
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/Tesla_Logo.png">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css" />

    <script src="https://kit.fontawesome.com/847b56c7ec.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



    <title>Lambda Project</title>
</head>

<body class="greenBody h-100">
<header class="position-sticky top-0 left-0 z-index-3 p-0" style="z-index: 4;">
    <nav class="navbar navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand ms-lg-4" href="<%=request.getContextPath()%>/home">
                <div class="d-flex">
                    <img src="<%=request.getContextPath()%>/assets/img/complete-logo.png"
                         alt="Logo" class="complete_logo">
                </div>
            </a>


            <div>
                <div class="d-flex">
                    <div class="d-flex me-1">
                        <img src="<%=request.getContextPath()%>/assets/img/perfil.png"
                             alt="Logo" height="50" class="d-inline-block align-text-top">

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
                        <button
                                class="navbar-toggler collapsed d-flex flex-column justify-content-around ms-2 me-md-3 me-lg-2 mt-1 p-0 border border-0 position-relative shadow-none"
                                type="button" data-bs-toggle="offcanvas"
                                data-bs-target="#offcanvasDarkNavbar"
                                aria-controls="offcanvasDarkNavbar" aria-label="Toggle navigation">
                                                    <span
                                                            class="navbar-toggler__icon navbar-toggler__icon--top-bar d-block position-absolute bg-white w-100 start-0 mt-0"></span>
                            <span
                                    class="navbar-toggler__icon navbar-toggler__icon--middle-bar d-block position-absolute bg-white w-100 start-0 mt-0"></span>
                            <span
                                    class="navbar-toggler__icon navbar-toggler__icon--bottom-bar d-block position-absolute bg-white w-100 start-0 mt-0"></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <div class="left-bar-color offcanvas offcanvas-end zindex-offcanvas" tabindex="-1"
         id="offcanvasDarkNavbar" aria-labelledby="left-menuLabel">
        <div class="offcanvas-header">
            <a class="navbar-brand" href="#">
                <img src="<%=request.getContextPath()%>/assets/img/complete-logo.png" alt="" class="complete_logo" />
            </a>
            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas"
                    aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">

            <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                <li class="nav-item">
                    <a class="nav-link active text fw-bold" aria-current="page"
                       href="<%=request.getContextPath()%>/home">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text fw-bold"
                       href="<%=request.getContextPath()%>/user">Mi perfil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text fw-bold" href="<%=request.getContextPath()%>/incidencia/register-page">Registrar incidencia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text fw-bold" aria-current="page"
                       href="<%=request.getContextPath()%>/reportes">Reportar</a>
                </li>
            </ul>
        </div>

        <div class="offcanvas-end">

            <div class="list-group">

                <a href="<%=request.getContextPath()%>/logout"
                   class="my-2 list-group-item list-group-item-action left-bar-color"><i
                        class="fa-solid fa-right-from-bracket"></i> Salir</a>

            </div>
        </div>
    </div>
</header>