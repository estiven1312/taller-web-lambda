<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<main
        class="d-flex align-items-center justify-content-center     flex-column"
>
    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center"
    >
        <div class="m-2">
            <form class="text d-flex flex-wrap" role="search">
                <input
                        type="date"
                        class="form-control text m-2 filter-input"
                        id="fechaInicioFiltro"
                        type="search"
                        placeholder="Search"
                        aria-label="Search"
                />
                <input
                        type="date"
                        class="form-control text m-2 filter-input"
                        id="fechaFinFiltro"
                        type="search"
                        placeholder="Search"
                        aria-label="Search"
                />
                <button
                        class="border border-0 rounded-2 py-2 px-3 text-white button button--dark m-2 fw-bold"
                        type="submit"
                >
                    Search
                </button>
            </form>
        </div>
        <button
                class="button button--yellow py-1 px-3 text fw-bold rounded-2 my-2 mx-4 d-flex align-items-center justify-content-center flex-wrap"
                data-bs-toggle="modal"
                data-bs-target="#registrarReservacionEstacionamiento"
        >
          <span class="text p-1">+</span
          ><span class="text p-1">Añadir reservación</span>
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
                            Reserva
                        </h1>
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"
                        ></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label
                                        for="numeroEstacionamientoReservaRequest"
                                        class="col-form-label text fw-bold"
                                >Nº Estacionamiento</label
                                >
                                <select
                                        id="numeroEstacionamientoReservaRequest"
                                        class="form-select form-control"
                                >
                                </select>
                            </div>
                            <div class="mb-3">
                                <label
                                        for="fechaEstacionamientoReservaRequest"
                                        class="col-form-label text fw-bold"
                                >Fecha</label
                                >
                                <input
                                        type="date"
                                        class="form-control text"
                                        id="fechaEstacionamientoReservaRequest"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaInicioEstacionamientRequest"
                                        class="col-form-label text fw-bold"
                                >Hora Inicio</label
                                >
                                <input
                                        type="time"
                                        class="form-control text"
                                        id="horaInicioEstacionamientRequest"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaFinEstacionamientoRequest"
                                        class="col-form-label text fw-bold"
                                >Hora Fin</label
                                >
                                <input
                                        type="time"
                                        class="form-control text"
                                        id="horaFinEstacionamientoRequest"
                                />
                            </div>
                        </form>
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
                                type="button"
                                id="registrarReservaNueva"
                                class="text border-0 rounded-2 py-2 px-3 text-white button button--dark fw-bold"
                        >
                            Registrar
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="d-flex align-items-center justify-content-center flex-wrap">
        <% if(reservas != null && !reservas.isEmpty()){
            for(int i = 0; i<reservas.size(); i++){
        %>


        <button
                onclick="dataOfReservation(<%=reservas.get(i).getId()%>)"
                class="button button--light button--small shadow rounded-4 p-4 m-3"
                data-bs-toggle="modal"
                data-bs-target="#verReservacionEstacionamiento"
        >
            <h2 class="text fw-bold text-break">Cubiculo</h2>
            <p class="text fw-bold text-break text-black">
                <%=reservas.get(i).getAmbiente().getNombreAmbiente()%>
                <br />
                Fecha: <%=DateUtil.convertDateToString(reservas.get(i).getInit(), DateUtil.FORMAT_DATE)%>
                <br />
                Hora: <%= DateUtil.convertDateToString(reservas.get(i).getInit(), DateUtil.FORMAT_HOUR)%> - <%= DateUtil.convertDateToString(reservas.get(i).getEnd(), DateUtil.FORMAT_HOUR)%>
            </p>
        </button>


        <%
            }
        }
        %>

        <div
                class="modal fade"
                id="verReservacionEstacionamiento"
                tabindex="-1"
                aria-labelledby="verReservacionEstacionamientoLabel"
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
                            Reserva
                        </h1>
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"
                        ></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label
                                        for="numeroEstacionamientoReserva"
                                        class="col-form-label text fw-bold"
                                >Nº Estacionamiento</label
                                >
                                <select
                                        id="numeroEstacionamientoReserva"
                                        class="form-select form-control" readonly="readonly" disabled="disabled"
                                >

                                </select>
                            </div>
                            <div class="mb-3">
                                <label
                                        for="fechaEstacionamientoReserva"
                                        class="col-form-label text fw-bold"
                                >Fecha</label
                                >
                                <input
                                        type="date" readonly="readonly"
                                        class="form-control text"
                                        id="fechaEstacionamientoReserva"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaInicioEstacionamientoReserva"
                                        class="col-form-label text fw-bold"
                                >Hora Inicio</label
                                >
                                <input
                                        type="time" readonly="readonly"
                                        class="form-control text"
                                        id="horaInicioEstacionamientoReserva"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaFinEstacionamientoReserva"
                                        class="col-form-label text fw-bold"
                                >Hora Fin</label
                                >
                                <input
                                        type="time" readonly="readonly"
                                        class="form-control text"
                                        id="horaFinEstacionamientoReserva"
                                />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer border border-0">
                        <button
                                type="button"
                                class="button btn btn-danger text fw-bold"
                                onclick="deleteReservation()"
                        >
                            Eliminar
                        </button>
                        <button
                                id="buttonEditarReservaEstacionamiento"
                                type="button"
                                class="text btn btn-warning border-0 rounded-2 py-2 px-3 text-black button fw-bold"
                        >
                            Actualizar
                        </button>
                        <button
                                type="button"
                                class="button btn btn-secondary text fw-bold"
                                data-bs-dismiss="modal"
                        >
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script src="<%=request.getContextPath()%>/assets/js/modules/estacionamiento.js" type="text/javascript"></script>
<jsp:include page="layouts/footer.jsp"></jsp:include>