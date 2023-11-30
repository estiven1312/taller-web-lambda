<%@ page import="com.lambda.pe.lambdaapp.domain.model.User" %>
<%@ page import="com.lambda.pe.lambdaapp.util.Constants" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 07:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessions=request.getSession(false);
    User user=(User) sessions.getAttribute(Constants.USER_KEY_SESSION.label); %>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<main
        class="mt-2 container"
>
    <form class="row" method="POST" enctype="multipart/form-data" action="user/actualizar" >
        <div class="col-12">
            <h1 class="text fs-1 fw-bold text-center pb-4">Mi información</h1>
        </div>
        <div class="row">
            <div class="col-4">
                <div class="col-12">
                    <%
                        if(user.getRutaFoto() != null && !user.getRutaFoto().isEmpty()){
                    %>
                    <img class=" w-75 rounded mx-auto d-block" src="<%=request.getContextPath()%>/resource/images/<%=user.getRutaFoto()%>">
                    <%
                    } else {
                    %>
                    <img src="<%=request.getContextPath()%>/assets/img/user.png"
                         alt="Logo" class="w-75 rounded mx-auto d-block">
                    <%}%>
                </div>
                <div class="mt-2">
                    <input type="file" name="imagen">
                </div>

            </div>
            <div class="row col-8">
                <div class="col-12 col-lg-6">
                    <input type="text" id="usuarioId" hidden="hidden" name="id" value="<%=user.getId()%>"/>
                    <div class="mb-3">
                        <label for="nombresGerente" class="form-label">Nombres</label>
                        <input type="text" class="form-control" id="nombresGerente"  name="nombres" value="<%=user.getNombres()%>" />
                    </div>
                    <div class="mb-3">
                        <label for="apellidosGerente" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" id="apellidosGerente"  name="apellidos" value="<%=user.getApellidos()%>"/>
                    </div>
                    <div class="mb-3">
                        <label for="telefonoGerente" class="form-label">Teléfono</label>
                        <input type="tel" class="form-control" id="telefonoGerente"  name="telefono" value="<%=user.getTelefono()%>" />
                    </div>
                    <!--

                    <div class="mb-3">
                        <label for="tipoDocumentoIdentidadGerente" class="form-label"
                        >Tipo de documento de identidad</label
                        >
                        <select
                                class="form-select"
                                aria-label="Selecciona un tipo de documento de identidad"
                        >
                            <option value="1" selected>Libreta electoral o DNI</option>
                            <option value="2">Carné de extranjería</option>
                            <option value="3">Pasaporte</option>
                        </select>
                    </div>-->
                </div>
                <div class="col-12 col-lg-6">
                    <div class="mb-3">
                        <label for="numeroDocumentoIdentidadGerente" class="form-label"
                        >Número de documento de identidad</label
                        >
                        <input
                                type="text"
                                class="form-control"  name="numeroDocumentoIdentificacion" value="<%=user.getNumeroDocumentoIdentificacion()%>"
                                id="numeroDocumentoIdentidadGerente"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="correoGerente" class="form-label">Correo</label>
                        <input type="email" class="form-control" id="correoGerente"  name="correo"  value="<%=user.getCorreo()%>" />
                    </div>
                    <div class="mb-3">
                        <label for="correoGerente" class="form-label">Usuario</label>
                        <input type="email" class="form-control" id="username" readonly="readonly" name="username"  value="<%=user.getUsername()%>" />
                    </div>
                    <div class="mb-3">
                        <label for="contraseniaGerente" class="form-label"
                        >Password</label
                        >
                        <input
                                name="password"
                                type="password"
                                class="form-control"
                                id="contraseniaGerente"
                        />
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <button
                        type="submit"
                        id="btnActualizarPerfil"
                        class="text btn btn-warning border-0 rounded-2 py-2 px-3 text-black button fw-bold"
                >
                    Actualizar
                </button>
            </div>
        </div>


    </form>
</main>
<!--
<script src="<%=request.getContextPath()%>/assets/js/modules/perfil.js" type="text/javascript"></script>
-->
<jsp:include page="layouts/footer.jsp"></jsp:include>
