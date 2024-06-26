<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Ambiente" %><%-- Created by IntelliJ IDEA. User:
                    Usuario Date: 20/10/2023 Time: 01:09 To change this template use File | Settings | File Templates.
                    --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String error=(String) request.getAttribute("error"); List<Ambiente> ambiente = (List
        <Ambiente>) request.getAttribute("estacionamientos");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<main class="d-flex align-items-center justify-content-center flex-column">
    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center">

        <button
                class="button button--yellow py-1 px-3 text fw-bold rounded-2 my-2 mx-4 d-flex align-items-center justify-content-center flex-wrap"
                data-bs-toggle="modal"
                data-bs-target="#registrarReservacionEstacionamiento">
            <span class="text p-1">+</span><span class="text p-1">Añadir
                                                estacionamiento</span>
        </button>
        <div class="modal fade" id="registrarReservacionEstacionamiento" tabindex="-1"
             aria-labelledby="registrarReservacionEstacionamientoLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content my-modal border border-0">
                    <div class="modal-header position-relative p-4 border border-0"
                         data-bs-theme="dark">
                        <h1 class="text modal-title fs-1 fw-bold text-center position-absolute top-20 start-50 translate-middle-x text-white"
                            id="agregarEstacionamiento">
                            Estacionamiento
                        </h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form method="post" enctype="multipart/form-data"
                          action="saveAmbiente">
                        <div class="modal-body">
                            <input name="tipoAmbiente" type="text" hidden="hidden"
                                   value="ESTACIONAMIENTO" />
                            <input name="estado" type="text" hidden="hidden"
                                   value="ACTIVO" />

                            <div class="mb-3">
                                <label for="idPiso"
                                       class="col-form-label text fw-bold">Nº Piso</label>
                                <select name="numeroPiso" id="idPiso"
                                        class="form-select form-control">
                                    <option value="-1">Sotano 1</option>
                                    <option value="-2">Sotano 2</option>
                                    <option value="-3">Sotano 3</option>
                                    <option value="-4">Sotano 4</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="nombreAmbiente"
                                       class="col-form-label text fw-bold">Etiqueta del
                                    estacionamiento</label>
                                <input name="nombreAmbiente" type="text"
                                       class="form-control text" id="nombreAmbiente" />
                            </div>
                            <div class="mb-3">
                                <label for="imagen"
                                       class="col-form-label text fw-bold">Imagen</label>
                                <div class="mt-2">
                                    <input type="file" id="imagen" name="imagen">
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer border border-0">
                            <button type="button"
                                    class="button btn btn-secondary text fw-bold"
                                    data-bs-dismiss="modal">
                                Cancelar
                            </button>
                            <button type="submit" id="registrarReservaNueva"
                                    class="text border-0 rounded-2 py-2 px-3 text-white button button--dark fw-bold">
                                Registrar
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="d-flex align-items-center justify-content-center flex-wrap">
            <% if(ambiente !=null && !ambiente.isEmpty()){ for(int i=0; i<ambiente.size();
                                                               i++){ %>


            <button class="button button--light button--small shadow rounded-4 p-4 m-3"
                    data-bs-toggle="modal"
                    data-bs-target="#verEstacionamiento<%=ambiente.get(i).getId()%>">
                <h2 class="text fw-bold text-break">Estacionamiento</h2>
                <p class="text fw-bold text-break text-black">
                    Piso: <%switch (ambiente.get(i).getNumeroPiso().intValue()) {
                    case -1:%>SÓTANO 1<%break;
                    case -2:%>SÓTANO 2<%break;
                    case -3:%>SÓTANO 3<%break;
                    case -4:%>SÓTANO 4<%break;
                }%>
                    <br />
                    Nombre: <%= ambiente.get(i).getNombreAmbiente()%>
                </p>
            </button>



            <div class="modal fade" id="verEstacionamiento<%=ambiente.get(i).getId()%>"
                 tabindex="-1" aria-labelledby="verReservacionEstacionamientoLabel"
                 aria-hidden="true">


                <div class="modal-dialog">
                    <div class="modal-content my-modal border border-0">
                        <div class="modal-header position-relative p-4 border border-0"
                             data-bs-theme="dark">
                            <h1 class="text modal-title fs-1 fw-bold text-center position-absolute top-20 start-50 translate-middle-x text-white"
                                id="exampleModalLabel">
                                Reserva
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <form method="post" enctype="multipart/form-data"
                              action="saveAmbiente">
                            <div class="modal-body">
                                <input name="tipoAmbiente" type="text" hidden="hidden"
                                       value="ESTACIONAMIENTO" />
                                <input name="estado" type="text" hidden="hidden"
                                       value="ACTIVO" />
                                <input name="id" type="text" hidden="hidden" value="<%=ambiente.get(i).getId()%>">

                                <div class="mb-3">
                                    <label for="idPiso"
                                           class="col-form-label text fw-bold">Nº Piso</label>
                                    <select name="numeroPiso"
                                            class="form-select form-control">
                                        <option value="-1"
                                                <%=ambiente.get(i).getNumeroPiso().equals(-1)? "selected"
                                                        :""%>> Sotano 1</option>
                                        <option value="-2"
                                                <%=ambiente.get(i).getNumeroPiso().equals(-2)? "selected"
                                                        :""%>>Sotano 2</option>
                                        <option value="-3"
                                                <%=ambiente.get(i).getNumeroPiso().equals(-3)? "selected"
                                                        :""%>>Sotano 3</option>
                                        <option value="-4"
                                                <%=ambiente.get(i).getNumeroPiso().equals(-4)? "selected"
                                                        :""%>>Sotano 4</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="nombreAmbiente"
                                           class="col-form-label text fw-bold">Etiqueta del
                                        estacionamiento</label>
                                    <input name="nombreAmbiente" type="text"
                                           class="form-control text"
                                           value="<%=ambiente.get(i).getNombreAmbiente()%>" />
                                </div>
                                <div class="mb-3">
                                    <label for="imagen"
                                           class="col-form-label text fw-bold">Imagen</label>
                                    <div class="mt-2">
                                        <input type="file" name="imagen">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer border border-0">
                                <a      href="<%=request.getContextPath()%>/ambiente/deleteAmbiente/<%=ambiente.get(i).getId()%>?tipoAmbiente=ESTACIONAMIENTO"
                                        type="button"
                                        class="button btn btn-danger text fw-bold"
                                >Eliminar</a>
                                <button type="submit"
                                        class="text btn btn-warning border-0 rounded-2 py-2 px-3 text-black button fw-bold">
                                    Actualizar
                                </button>
                                <button type="button"
                                        class="button btn btn-secondary text fw-bold"
                                        data-bs-dismiss="modal">
                                    Cancelar
                                </button>
                            </div>
                        </form>

                    </div>
                </div>

            </div>
            <% } } %>

        </div>
    </div>


</main>
<script src="<%=request.getContextPath()%>/assets/js/modules/adminAmbiente.js"
        type="text/javascript"></script>
<jsp:include page="layouts/footer.jsp"></jsp:include>