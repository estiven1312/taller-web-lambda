<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Ambiente" %><%-- Created by IntelliJ IDEA. User:
                    Usuario Date: 20/10/2023 Time: 01:09 To change this template use File | Settings | File Templates.
                    --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String error=(String) request.getAttribute("error");
List<Ambiente> salas = (List
        <Ambiente>) request.getAttribute("salas");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<main class="d-flex align-items-center justify-content-center     flex-column">
    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center">

        <button
                class="button button--yellow py-1 px-3 text fw-bold rounded-2 my-2 mx-4 d-flex align-items-center justify-content-center flex-wrap"
                data-bs-toggle="modal"
                data-bs-target="#registrarReservacionEstacionamiento">
            <span class="text p-1">+</span><span class="text p-1">Añadir
                                                sala</span>
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
                            Sala
                        </h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form method="post" enctype="multipart/form-data"
                          action="saveAmbiente">
                        <div class="modal-body">
                            <input name="tipoAmbiente" type="text" hidden="hidden"
                                   value="SALA" />
                            <input name="estado" type="text" hidden="hidden"
                                   value="ACTIVO" />

                            <div class="mb-3">
                                <label for="idPiso"
                                       class="col-form-label text fw-bold">Nº Piso</label>
                                <select name="numeroPiso" id="idPiso"
                                        class="form-select form-control">
                                    <option value="1">Piso 1</option>
                                    <option value="2">Piso 2</option>
                                    <option value="3">Piso 3</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="nombreAmbiente"
                                       class="col-form-label text fw-bold">Etiqueta de la sala </label>
                                <input name="nombreAmbiente" type="text" id="nombreAmbiente"
                                       class="form-control text"  />
                            </div>
                            <div class="mb-3">
                                <label for="cantidadAsientos"
                                       class="col-form-label text fw-bold">Cantidad de asientos </label>
                                <input name="cantidadAsientos" type="number"
                                       class="form-control text" id="cantidadAsientos" />
                            </div>
                            <div class="mb-3">
                                <label class="col-form-label text fw-bold">Proyector: </label>
                                <input type="radio" name="indicadorProyector" value="true" />
                                <label >Si</label>
                                <input type="radio"  name="indicadorProyector" value="false" checked="checked"/>
                                <label >No</label>
                            </div>
                            <div class="mb-3">
                                <label class="col-form-label text fw-bold">Televisor: </label>
                                <input type="radio" name="indicadorTelevisor" value="true" />
                                <label >Si</label>
                                <input type="radio"  name="indicadorTelevisor" value="false" checked="checked"/>
                                <label >No</label>
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
        <% if(salas !=null && !salas.isEmpty()){ for(int i=0; i<salas.size();
                                                           i++){ %>


        <button class="button button--light button--small shadow rounded-4 p-4 m-3"
                data-bs-toggle="modal"
                data-bs-target="#verEstacionamiento<%=salas.get(i).getId()%>">
            <h2 class="text fw-bold text-break">Sala</h2>
            <p class="text fw-bold text-break text-black">
                Piso: <%=salas.get(i).getNumeroPiso()%>
                <br />
                Nombre: <%= salas.get(i).getNombreAmbiente()%>
            </p>
        </button>



        <div class="modal fade" id="verEstacionamiento<%=salas.get(i).getId()%>"
             tabindex="-1" aria-labelledby="verReservacionEstacionamientoLabel"
             aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content my-modal border border-0">
                    <div class="modal-header position-relative p-4 border border-0"
                         data-bs-theme="dark">
                        <h1 class="text modal-title fs-1 fw-bold text-center position-absolute top-20 start-50 translate-middle-x text-white"
                            id="exampleModalLabel">
                            Sala
                        </h1>
                        <button type="button" class="btn-close"
                                data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form method="post" enctype="multipart/form-data" action="saveAmbiente">

                    <div class="modal-body">
                        <input name="tipoAmbiente" type="text" hidden="hidden"
                               value="SALA" />
                        <input name="estado" type="text" hidden="hidden"
                               value="ACTIVO" />
                        <input name="id" type="text" hidden="hidden" value="<%=salas.get(i).getId()%>">
                        <div class="mb-3">
                            <label for="idPiso"
                                   class="col-form-label text fw-bold">Nº Piso</label>
                            <select name="numeroPiso"
                                    class="form-select form-control">
                                <option value="1" <%= salas.get(i).getNumeroPiso().equals(1)? "selected":""%>> Piso 1</option>
                                <option value="2" <%= salas.get(i).getNumeroPiso().equals(2)? "selected":""%>>Piso 2</option>
                                <option value="3" <%= salas.get(i).getNumeroPiso().equals(3)? "selected":""%>>Piso 3</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="nombreAmbiente"
                                   class="col-form-label text fw-bold">Etiqueta de la sala</label>
                            <input name="nombreAmbiente" type="text"
                                   class="form-control text" value="<%=salas.get(i).getNombreAmbiente()%>" />
                        </div>
                        <div class="mb-3">
                            <label for="cantidadAsientos"
                                   class="col-form-label text fw-bold">Cantidad de asientos </label>
                            <input name="cantidadAsientos" type="number"
                                   class="form-control text" value="<%=salas.get(i).getCantidadAsientos()%>" />
                        </div>
                        <%
                            if(salas.get(i).getIndicadorProyector()){
                        %>
                        <div class="mb-3">
                            <label class="col-form-label text fw-bold">Proyector: </label>
                            <input type="radio" name="indicadorProyector" value="true" checked="checked" />
                            <label >Si</label>
                            <input type="radio"  name="indicadorProyector" value="false" />
                            <label >No</label>
                        </div>
                        <% } else {%>

                        <label class="col-form-label text fw-bold">Proyector: </label>
                        <input type="radio" name="indicadorProyector" value="true"  />
                        <label >Si</label>
                        <input type="radio"  name="indicadorProyector" value="false" checked="checked" />
                        <label >No</label>
                        <%}%>


                        <%
                            if(salas.get(i).getIndicadorTelevisor()){
                        %>
                        <div class="mb-3">
                            <label class="col-form-label text fw-bold">Televisor: </label>
                            <input type="radio" name="indicadorTelevisor" value="true" checked="checked"/>
                            <label >Si</label>
                            <input type="radio"  name="indicadorTelevisor" value="false" />
                            <label >No</label>
                        </div>
                        <% } else {%>

                        <div class="mb-3">
                            <label class="col-form-label text fw-bold">Televisor: </label>
                            <input type="radio" name="indicadorTelevisor" value="true"/>
                            <label >Si</label>
                            <input type="radio"  name="indicadorTelevisor" value="false"  checked="checked"/>
                            <label >No</label>
                        </div>
                        <%}%>

                        <div class="mb-3">
                            <label
                                    for="imagen"
                                    class="col-form-label text fw-bold"
                            >Imagen</label
                            >
                            <div class="mt-2">
                                <input type="file" name="imagen">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer border border-0">
                        <a      href="<%=request.getContextPath()%>/ambiente/deleteAmbiente/<%=salas.get(i).getId()%>?tipoAmbiente=SALA"
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
</main>
<script src="<%=request.getContextPath()%>/assets/js/modules/adminAmbiente.js"
        type="text/javascript"></script>
<jsp:include page="layouts/footer.jsp"></jsp:include>