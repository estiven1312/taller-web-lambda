<%@ page import="com.lambda.pe.lambdaapp.domain.model.ReservaSala" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 04:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ReservaSala> reservas = (List<ReservaSala>) request.getAttribute("reservas");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<jsp:include page="layouts/news.jsp"></jsp:include>
<main
        class="d-flex align-items-center justify-content-center flex-column"
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
                data-bs-target="#registrarReservacionSala"
        >
          <span class="text p-1">+</span
          ><span class="text p-1">Añadir reservación</span>
        </button>
        <div
                class="modal fade"
                id="registrarReservacionSala"
                tabindex="-1"
                aria-labelledby="registrarReservacionSalaLabel"
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
                                        for="numeroSalaReservaRequest"
                                        class="col-form-label text fw-bold"
                                >Nº Sala</label
                                >
                                <select
                                        id="numeroSalaReservaRequest"
                                        class="form-select form-control"
                                >

                                </select>
                            </div>
                            <div class="mb-3">
                                <label
                                        for="fechaSalaReservaRequest"
                                        class="col-form-label text fw-bold"
                                >Fecha</label
                                >
                                <input
                                        type="date"
                                        class="form-control text"
                                        id="fechaSalaReservaRequest"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaInicioSalaReservaRequest"
                                        class="col-form-label text fw-bold"
                                >Hora Inicio</label
                                >
                                <input
                                        type="time"
                                        class="form-control text"
                                        id="horaInicioSalaReservaRequest"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaFinSalaReservaRequest"
                                        class="col-form-label text fw-bold"
                                >Hora Fin</label
                                >
                                <input
                                        type="time"
                                        class="form-control text"
                                        id="horaFinSalaReservaRequest"
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
                                id="buttonNuevaReservaSala"
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
        <button onclick="dataOfReservationSala(<%=reservas.get(i).getId()%>)"
                class="button button--light button--small shadow rounded-4 p-4 m-3"
                data-bs-toggle="modal"
                data-bs-target="#verReservacionSala"
        >
            <h2 class="text fw-bold text-break">Sala</h2>
            <p class="text fw-bold text-break text-black">
                <%=reservas.get(i).getSala().getId()%>
                <br />
                Fecha:  <%=DateUtil.convertDateToString(reservas.get(i).getHoraInicio(), DateUtil.FORMAT_DATE)%>
                <br />
                Hora: <%= DateUtil.convertDateToString(reservas.get(i).getHoraInicio(), DateUtil.FORMAT_HOUR)%> - <%= DateUtil.convertDateToString(reservas.get(i).getHoraFin(), "HH:mm")%>
            </p>
        </button>
        <%
                }
            }
        %>
        <div
                class="modal fade"
                id="verReservacionSala"
                tabindex="-1"
                aria-labelledby="verReservacionSalaLabel"
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
                                        for="numeroSalaReserva"
                                        class="col-form-label text fw-bold"
                                >Nº Sala</label
                                >
                                <select
                                        id="numeroSalaReserva"
                                        class="form-select form-control"
                                >
                                    <option selected value="1" class="text">1</option>
                                    <option value="2" class="text">2</option>
                                    <option value="3" class="text">3</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label
                                        for="fechaSalaReserva"
                                        class="col-form-label text fw-bold"
                                >Fecha</label
                                >
                                <input
                                        type="date"
                                        class="form-control text"
                                        id="fechaSalaReserva"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaInicioSalaReserva"
                                        class="col-form-label text fw-bold"
                                >Hora Inicio</label
                                >
                                <input
                                        type="time"
                                        class="form-control text"
                                        id="horaInicioSalaReserva"
                                />
                            </div>
                            <div class="mb-3">
                                <label
                                        for="horaFinSalaReserva"
                                        class="col-form-label text fw-bold"
                                >Hora Fin</label
                                >
                                <input
                                        type="time"
                                        class="form-control text"
                                        id="horaFinSalaReserva"
                                />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer border border-0">
                        <button
                                onclick="deleteReservationSala()"
                                type="button"
                                class="button btn btn-danger text fw-bold"
                        >
                            Eliminar
                        </button>
                        <button
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
<jsp:include page="layouts/footer.jsp"></jsp:include>