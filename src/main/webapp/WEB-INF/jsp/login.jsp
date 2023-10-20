<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Map" %>
<%
    String errors = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="<%=request.getContextPath()%>/assets/img/lambda-logo.png" rel="icon" >

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css" />

    <title>Lambda Project</title>
</head>

<body     class="d-flex justify-content-center align-items-center h-100 login__background">
<form
        id="formularioIniciarSesion"
        class="px-4 py-5 m-4 rounded-4 px-lg-5 shadow"
        method="post" action="login"
>
    <div class="py-lg-5">
        <div
                class="navbar-brand d-flex flex-row justify-content-center align-items-center flex-wrap flex-lg-nowrap"
        >
            <h1 class="title lilita_font display-1 m-0 h-1 text-white">LAMBDA</h1>
            <div class="logo">
                <a href="./index.html">
                    <img
                            src="<%=request.getContextPath()%>/assets/img/lamda_logo.png"
                            alt="Logo de Lambda"
                            class="w-100"
                    /></a>
            </div>
        </div>
        <% if(errors != null && !errors.isEmpty() ){%>
        <div class="alert alert-danger mt-1" role="alert">
            <%=errors%>
        </div>
        <%
            }
        %>
        <label
                for="correoIniciarSesion"
                class="d-block my-2 lilita_font text-white fs-4"
        >Correo</label
        >
        <input
                type="email"
                id="correoIniciarSesion"
                name="username" value="${username}"
                class="d-block w-100 border border-0 login__input rounded-3 py-2 px-2"
                required
        />


        <label
                for="contraseniaIniciarSesion"
                class="d-block my-2 lilita_font text-white fs-4"
        >Contraseña</label
        >
        <input
                type="password"
                id="contraseniaIniciarSesion"
                name="password"
                class="d-block w-100 border border-0 login__input rounded-3 py-2 px-2"
                required
        />
        <div class="text-center">
            <button
                    type="submit"
                    class="text button button--dark d-block mb-2 mt-3 mx-auto border border-0 rounded-3 py-2 px-3 text-white fw-bold"
            >
                Iniciar sesión
            </button>
            <a
                    href="#"
                    class="text text-decoration-none d-block my-2 lilita_font text-white fs-5"
            >Recuperar contraseña</a
            >
        </div>
    </div>
</form>

<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"
></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
        integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
        crossorigin="anonymous"
></script>

</body>

</html>