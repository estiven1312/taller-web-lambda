<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Ambiente" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Visitante" %><%-- Created by IntelliJ IDEA. User:
                    Usuario Date: 20/10/2023 Time: 01:09 To change this template use File | Settings | File Templates.
                    --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String error=(String) request.getAttribute("error");
    List<Ambiente> estacionamientos = (List
        <Ambiente>) request.getAttribute("estacionamientos");
    List<Ambiente> salas = (List
            <Ambiente>) request.getAttribute("salas");
    List<Visitante> visitantes = (List
            <Visitante>) request.getAttribute("visitantes");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<main class="d-flex align-items-center justify-content-center     flex-column">
    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center">
        <div class="m-2">
            <form class="text d-flex flex-wrap" role="search">
                <input type="date" class="form-control text m-2 filter-input"
                       id="fechaInicioFiltro" type="search" placeholder="Search"
                       aria-label="Search" />
                <input type="date" class="form-control text m-2 filter-input"
                       id="fechaFinFiltro" type="search" placeholder="Search"
                       aria-label="Search" />
                <button
                        class="border border-0 rounded-2 py-2 px-3 text-white button button--dark m-2 fw-bold"
                        type="submit">
                    Search
                </button>
            </form>
        </div>
        <button
                class="button button--yellow py-1 px-3 text fw-bold rounded-2 my-2 mx-4 d-flex align-items-center justify-content-center flex-wrap"
                data-bs-toggle="modal"
                data-bs-target="#registrarReservacionEstacionamiento">
            <span class="text p-1">+</span><span class="text p-1">Añadir
                                                visita</span>
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
                            Visita
                        </h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form method="post" enctype="multipart/form-data"
                          action="visitante/saveVisitante">
                        <div class="modal-body">

                            <div class="mb-3">
                                <label for="idAmbiente"
                                       class="col-form-label text fw-bold">Nº Sala</label>
                                <select name="idAmbiente" id="idAmbiente"
                                        class="form-select form-control">
                                    <option value="null">No Sala</option>
                                    <%for(int i=0; i<salas.size(); i++) { %>
                                        <option value="<%=salas.get(i).getId()%>"><%=salas.get(i).getNombreAmbiente()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="nombres"
                                       class="col-form-label text fw-bold">Nombres</label>
                                <input name="nombres" type="text"
                                       class="form-control text" id="nombres" />
                            </div>
                            <div class="mb-3">
                                <label for="apellidos"
                                       class="col-form-label text fw-bold">Apellidos</label>
                                <input name="apellidos" type="text"
                                       class="form-control text" id="apellidos" />
                            </div> <div class="mb-3">
                            <label for="dni"
                                   class="col-form-label text fw-bold">DNI</label>
                            <input name="dni" type="text"
                                   class="form-control text" id="dni" />
                        </div>
                            <div class="mb-3">
                            <label for="telefono"
                                   class="col-form-label text fw-bold">Telefono</label>
                            <input name="telefono" type="text"
                                   class="form-control text" id="telefono" />
                            </div>
                            <div class="mb-3">
                                <label for="correo"
                                       class="col-form-label text fw-bold">Correo</label>
                                <div class="mt-2">
                                    <input type="text" id="correo" class="form-control text" name="correo">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label
                                        for="date"
                                        class="col-form-label text fw-bold"
                                >Fecha</label
                                >
                                <input
                                        type="date"
                                        class="form-control text"
                                        id="date"
                                        name="date"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="init"
                                        class="col-form-label text fw-bold"
                                >Hora Inicio</label
                                >
                                <input
                                        type="time"
                                        name="init"
                                        class="form-control text"
                                        id="init"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="end"
                                        class="col-form-label text fw-bold"
                                >Hora Fin</label
                                >
                                <input
                                        id="end"
                                        name="end"
                                        type="time"
                                        class="form-control text"
                                />
                            </div>
                            <div class="mb-3">
                                <label for="idEstacionamiento"
                                       class="col-form-label text fw-bold">Nº Estacionamiento</label>
                                <select name="idEstacionamiento" id="idEstacionamiento"
                                        class="form-select form-control">
                                    <option value="null">No Estacionamiento </option>
                                    <%for(int i=0; i<estacionamientos.size(); i++) { %>
                                    <option value="<%=estacionamientos.get(i).getId()%>"><%=estacionamientos.get(i).getNombreAmbiente()%></option>
                                    <%}%>
                                </select>
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
    <div class="d-flex align-items-center justify-content-center flex-wrap">
        <% if(visitantes !=null && !visitantes.isEmpty()){ for(int i=0; i<visitantes.size();
                                                           i++){ %>


        <button class="button button--light button--small shadow rounded-4 p-4 m-3"
                data-bs-toggle="modal"
                data-bs-target="#verEstacionamiento<%=visitantes.get(i).getId()%>">
            <h2 class="text fw-bold text-break">Visitante</h2>
            <p class="text fw-bold text-break text-black">
                DNI: <%=visitantes.get(i).getDni()%>
                <br />
                Nombre: <%= visitantes.get(i).getNombres() + " " + visitantes.get(i).getApellidos()%>
            </p>
        </button>



        <div class="modal fade" id="verEstacionamiento<%=visitantes.get(i).getId()%>"
             tabindex="-1" aria-labelledby="verReservacionEstacionamientoLabel"
             aria-hidden="true">


            <div class="modal-dialog">
                <div class="modal-content my-modal border border-0">
                    <div class="modal-header position-relative p-4 border border-0"
                         data-bs-theme="dark">
                        <h1 class="text modal-title fs-1 fw-bold text-center position-absolute top-20 start-50 translate-middle-x text-white"
                            id="exampleModalLabel">
                            Reserva Visita
                        </h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form method="post" enctype="multipart/form-data"
                          action="visitante/saveVisitante">
                        <input name="id" value="<%=visitantes.get(i).getId()%>" hidden="hidden">
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="idAmbiente"
                                       class="col-form-label text fw-bold">Nº Sala</label>
                                <select name="idAmbiente" id="idAmbiente1"
                                        class="form-select form-control">
                                    <option value="null">No Sala</option>
                                    <%for(int j=0; j<salas.size(); j++) { %>
                                    <option value="<%=salas.get(j).getId()%>" <%=salas.get(j).getId().equals(visitantes.get(i).getReservaVisita().getAmbiente().getId())?"selected":""%>><%=salas.get(j).getNombreAmbiente()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="nombres1"
                                       class="col-form-label text fw-bold">Nombres</label>
                                <input name="nombres" type="text"
                                       class="form-control text" id="nombres1" value="<%=visitantes.get(i).getNombres()%>"/>
                            </div>
                            <div class="mb-3">
                                <label for="apellidos1"
                                       class="col-form-label text fw-bold">Apellidos</label>
                                <input name="apellidos" type="text"
                                       class="form-control text" id="apellidos1" value="<%=visitantes.get(i).getApellidos()%>"/>
                            </div> <div class="mb-3">
                            <label for="dni1"
                                   class="col-form-label text fw-bold">DNI</label>
                            <input name="dni" type="text"
                                   class="form-control text" id="dni1" value="<%=visitantes.get(i).getDni()%>"/>
                        </div>
                            <div class="mb-3">
                                <label for="telefono1"
                                       class="col-form-label text fw-bold">Telefono</label>
                                <input name="telefono" type="text"
                                       class="form-control text" id="telefono1" value="<%=visitantes.get(i).getTelefono()%>"/>
                            </div>
                            <div class="mb-3">
                                <label for="correo1"
                                       class="col-form-label text fw-bold">Correo</label>
                                <div class="mt-2">
                                    <input type="text" class="form-control text" id="correo1" name="correo" value="<%=visitantes.get(i).getCorreo()%>">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label
                                        for="date1"
                                        class="col-form-label text fw-bold"
                                >Fecha</label
                                >
                                <input
                                        value="<%=DateUtil.convertDateToString(visitantes.get(i).getReservaVisita().getInit(), DateUtil.FORMAT_DATE_XML)%>"
                                        type="date"
                                        class="form-control text"
                                        id="date1"
                                        name="date"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="init1"
                                        class="col-form-label text fw-bold"
                                >Hora Inicio</label
                                >
                                <input
                                        value="<%=DateUtil.convertDateToString(visitantes.get(i).getReservaVisita().getInit(), DateUtil.FORMAT_HOUR)%>"
                                        type="time"
                                        name="init"
                                        class="form-control text"
                                        id="init1"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="end1"
                                        class="col-form-label text fw-bold"
                                >Hora Fin</label
                                >
                                <input
                                        value="<%=DateUtil.convertDateToString(visitantes.get(i).getReservaVisita().getEnd(), DateUtil.FORMAT_HOUR)%>"
                                        id="end1"
                                        name="end"
                                        type="time"
                                        class="form-control text"
                                />
                            </div>
                            <div class="mb-3">
                                <label for="idEstacionamiento1"
                                       class="col-form-label text fw-bold">Nº Estacionamiento</label>
                                <select name="idEstacionamiento" id="idEstacionamiento1"
                                        class="form-select form-control">
                                    <option value="null">No Estacionamiento</option>
                                    <%for(int h=0; h<estacionamientos.size(); h++) { %>
                                    <option value="<%=estacionamientos.get(h).getId()%>" <%=visitantes.get(i).getReservaEstacionamiento() != null && estacionamientos.get(h).getId().equals(visitantes.get(i).getReservaEstacionamiento().getAmbiente().getId()) ? "selected":""%>><%=estacionamientos.get(h).getNombreAmbiente()%></option>
                                    <%}%>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer border border-0">
                            <a      href="<%=request.getContextPath()%>/visitante/deleteVisitante/<%=visitantes.get(i).getId()%>"
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

    </div>

    <% } } %>
    </div>
</main>
<script src="<%=request.getContextPath()%>/assets/js/modules/adminAmbiente.js"
        type="text/javascript"></script>
<jsp:include page="layouts/footer.jsp"></jsp:include>