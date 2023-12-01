<%@ page import="com.lambda.pe.lambdaapp.domain.model.Ambiente" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 1/12/2023
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Ambiente> ambientes = (List<Ambiente>) request.getAttribute("ambientes");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<div class="container mx-auto">
    <form class="row" method="POST" action="registrar" >
        <div class="col-12">
            <h1 class="text fs-1 fw-bold text-center pb-4">Registrar incidencia</h1>
        </div>
        <div class="row justify-content-center">
            <div class="row col-8">
                <div class="col-12">
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripcion</label>
                        <input type="text" class="form-control" id="descripcion"  name="descripcion"  />
                    </div>
                    <div class="mb-3">
                        <label for="ambiente" class="form-label">Ambiente</label>
                        <select
                                id="ambiente"
                                class="form-select"
                                name="idAmbiente"
                                aria-label="Selecciona un ambiente"
                        >
                            <% for(int i = 0; i<ambientes.size(); i++) {%>
                            <option value="<%=ambientes.get(i).getId()%>"><%=ambientes.get(i).getNombreAmbiente()%></option>
                            <%}%>

                        </select>
                    </div>

                    <!--

                    <div class="mb-3">
                        <label for="tipoDocumentoIdentidadGerente" class="form-label"
                        >Tipo de documento de identidad</label
                        >

                    </div>-->
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <button
                        type="submit"
                        id="btnActualizarPerfil"
                        class="text btn btn-warning border-0 rounded-2 py-2 px-3 text-black button fw-bold"
                >
                    Registrar incidencia
                </button>
            </div>
        </div>

    </form>


</div>
<jsp:include page="layouts/footer.jsp"></jsp:include>