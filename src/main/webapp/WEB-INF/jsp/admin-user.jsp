<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Comunicado" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users = (List<User>) request.getAttribute("usuarios");
%>

<jsp:include page="layouts/menu.jsp"></jsp:include>
<main
        class="d-flex align-items-center justify-content-center     flex-column"
>

    <button
            class="button button--yellow py-1 px-3 text fw-bold rounded-2 my-2 mx-4 d-flex align-items-center justify-content-center flex-wrap"
            data-bs-toggle="modal"
            data-bs-target="#registrarReservacionEstacionamiento"
    >
          <span class="text p-1">+</span
          ><span class="text p-1">Añadir Usuario</span>
    </button>
    <div
            class="modal fade"
            id="registrarReservacionEstacionamiento"
            tabindex="-1"
            aria-labelledby="registrarReservacionEstacionamientoLabel"
            aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content my-modal border border-0">
                <div
                        class="modal-header position-relative p-4 border border-0"
                        data-bs-theme="dark"
                >
                    <h1
                            class="text modal-title fs-1 fw-bold text-center position-absolute top-20 start-50 translate-middle-x text-white"
                            id="aaa"
                    >
                        Nuevo Usuario
                    </h1>
                    <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                    ></button>
                </div>
                <form method="post" action="user-admin/registrar" enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-4">

                                <div class="mt-2">
                                    <input type="file" name="imagen">
                                </div>

                            </div>
                            <div class="row col-8">
                                <div class="col-12 col-lg-6">
                                    <input type="text" id="usuarioId" hidden="hidden" name="id" />
                                    <div class="mb-3">
                                        <label for="nombresGerente" class="form-label">Nombres</label>
                                        <input type="text" class="form-control" id="nombresGerente"  name="nombres"  />
                                    </div>
                                    <div class="mb-3">
                                        <label for="apellidosGerente" class="form-label">Apellidos</label>
                                        <input type="text" class="form-control" id="apellidosGerente"  name="apellidos" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="telefonoGerente" class="form-label">Teléfono</label>
                                        <input type="tel" class="form-control" id="telefonoGerente"  name="telefono"  />
                                    </div>
                                    <div class="mb-3">
                                        <label for="rol" class="form-label"
                                        >Rol</label
                                        >
                                        <select
                                                id="rol"
                                                name="rol"

                                                class="form-select"
                                                aria-label="Selecciona un rol"
                                        >
                                            <option value="1" selected>Administrador</option>
                                            <option value="2">Gerente</option>
                                            <option value="3">Colaborador</option>
                                            <option value="4">Seguridad</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="tipoDocumentoIdentidad" class="form-label"
                                        >Tipo de documento de identidad</label
                                        >
                                        <select name="tipoIdentificacion"
                                                id="tipoDocumentoIdentidad"
                                                class="form-select"
                                                aria-label="Selecciona un tipo de documento de identidad"
                                        >
                                            <option value="DNI" selected>Libreta electoral o DNI</option>
                                            <option value="PASAPORTE">Pasaporte</option>
                                            <option value="RUC">Ruc</option>
                                        </select>
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
                                                class="form-control"  name="numeroDocumentoIdentificacion"
                                                id="numeroDocumentoIdentidadGerente"
                                        />
                                    </div>
                                    <div class="mb-3">
                                        <label for="correoGerente" class="form-label">Correo</label>
                                        <input type="email" class="form-control" id="correoGerente"  name="correo"  />
                                    </div>
                                    <div class="mb-3">
                                        <label for="correoGerente" class="form-label">Usuario</label>
                                        <input type="email" class="form-control" id="username" name="username" />
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer border border-0">
                        <button
                                type="button"
                                class="button btn btn-secondary text fw-bold"
                                data-bs-dismiss="modal"
                        >
                            Cancelar
                        </button>
                        <button
                                type="submit"
                                id="registrarReservaNueva"
                                class="text border-0 rounded-2 py-2 px-3 text-white button button--dark fw-bold"
                        >
                            Registrar
                        </button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center"
    >
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Foto</th>
                <th scope="col">Nombres</th>
                <th scope="col">Apellidos</th>
                <th scope="col">Correo</th>
                <th scope="col">Telefono</th>
                <th scope="col">Rol</th>
                <th scope="col">Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(int i=0; i<users.size(); i++){
            %>
            <tr>
                <td>
                    <%=i+1%>
                </td>
                <td>
                    <%
                        if(users.get(i).getRutaFoto() != null && !users.get(i).getRutaFoto().isEmpty()){
                    %>
                    <img class="rounded d-block" style="height: 250px;" src="<%=request.getContextPath()%>/resource/images/<%=users.get(i).getRutaFoto()%>">
                    <%
                    } else {
                    %>
                    <img src="<%=request.getContextPath()%>/assets/img/user.png" style="height: 250px;"
                         alt="Logo" class="rounded mx-auto d-block">
                    <%}%>
                </td>
                <td>
                    <%=users.get(i).getNombres()%>
                </td>
                <td>
                    <%=users.get(i).getApellidos()%>
                </td>
                <td>
                    <%=users.get(i).getCorreo()%>
                </td>
                <td>
                    <%=users.get(i).getTelefono()%>
                </td>
                <td>
                    <%=users.get(i).getRol().getNombre()%>
                </td>
                <td>
                    <a  href="<%=request.getContextPath()+"/user-admin/delete/"+users.get(i).getId()%>" class="button btn-danger">Eliminar</a>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>


    </div>

</main>
<jsp:include page="layouts/footer.jsp"></jsp:include>