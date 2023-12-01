<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Comunicado" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Comunicado> comunicados = (List<Comunicado>) request.getAttribute("comunicados");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<jsp:include page="layouts/news.jsp"></jsp:include>
<main
        class="d-flex align-items-center justify-content-center     flex-column"
>

    <button
            class="button button--yellow py-1 px-3 text fw-bold rounded-2 my-2 mx-4 d-flex align-items-center justify-content-center flex-wrap"
            data-bs-toggle="modal"
            data-bs-target="#registrarReservacionEstacionamiento"
    >
          <span class="text p-1">+</span
          ><span class="text p-1">Añadir noticia</span>
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
                            id="exampleModalLabel"
                    >
                        Nuevo Comunicado
                    </h1>
                    <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                    ></button>
                </div>
                <form method="post" action="comunicado/registrar" enctype="multipart/form-data">
                <div class="modal-body">
                        <div class="mb-3">
                            <label
                                    for="descripcion"
                                    class="col-form-label text fw-bold"
                            >Descripcion</label
                            >
                            <input
                                    type="text"
                                    class="form-control text"
                                    name="descripcion"
                                    id="descripcion"
                            />
                        </div>
                        <div class="mb-3">
                            <label
                                    for="imagen"
                                    class="col-form-label text fw-bold"
                            >Imagen</label
                            >
                            <div class="mt-2">
                                <input type="file" id="imagen" name="imagen">
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
                <th scope="col">Imagen</th>
                <th scope="col">Fecha</th>
                <th scope="col">Descripcion</th>
                <th scope="col">


                </th>
            </tr>
            </thead>
            <tbody>
            <%
                for(int i=0; i<comunicados.size(); i++){
            %>
                <tr>
                    <td>
                        <%=i+1%>
                    </td>
                    <td>
                        <img class=" w-75 rounded mx-auto d-block" src="<%=request.getContextPath()%>/resource/images/<%=comunicados.get(i).getUrlImagen()%>">
                    </td>
                    <td>
                        <%=DateUtil.convertDateToString(comunicados.get(i).getFechaComunicado(), DateUtil.FORMAT_DATE)%>
                    </td>
                    <td>
                        <%=comunicados.get(i).getDescripcion()%>
                    </td>
                    <td>
                        <button class="btn btn-primary"
                                data-bs-toggle="modal"
                                data-bs-target="#verNoticia<%=comunicados.get(i).getId()%>">Editar</button>
                        <div
                                class="modal fade"
                                id="verNoticia<%=comunicados.get(i).getId()%>"
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
                                                id="exampleModalLabel"
                                        >
                                            Editar Comunicado
                                        </h1>
                                        <button
                                                type="button"
                                                class="btn-close"
                                                data-bs-dismiss="modal"
                                                aria-label="Close"
                                        ></button>
                                    </div>
                                    <form method="post" action="comunicado/registrar" enctype="multipart/form-data">
                                        <div class="modal-body">
                                            <input id="id" hidden="hidden" name="id" value="<%=comunicados.get(i).getId()%>" type="number">
                                            <div class="mb-3">
                                                <label
                                                        for="descripcionEdit"
                                                        class="col-form-label text fw-bold"
                                                >Descripcion</label
                                                >
                                                <input
                                                        type="text"
                                                        class="form-control text"
                                                        name="descripcion"
                                                        id="descripcionEdit"
                                                        value="<%=comunicados.get(i).getDescripcion()%>"
                                                />
                                            </div>
                                            <div class="mb-3">
                                                <div class="mw-70">
                                                    <img src="<%=request.getContextPath()+"/resource/images/"+comunicados.get(i).getUrlImagen()%>">
                                                </div>
                                                <label
                                                        for="imagenEdit"
                                                        class="col-form-label text fw-bold"
                                                >Imagen</label
                                                >
                                                <div class="mt-2">
                                                    <input type="file" id="imagenEdit" name="imagen">
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
                                                    id="editComunicado"
                                                    class="text border-0 rounded-2 py-2 px-3 text-white button button--dark fw-bold"
                                            >
                                                Registrar
                                            </button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>

                        <a  href="<%=request.getContextPath()+"/comunicado/delete/"+comunicados.get(i).getId()%>" class="btn btn-danger">Eliminar</a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>


    </div>

</main>
<script src="<%=request.getContextPath()%>/assets/js/modules/estacionamiento.js" type="text/javascript"></script>
<jsp:include page="layouts/footer.jsp"></jsp:include>