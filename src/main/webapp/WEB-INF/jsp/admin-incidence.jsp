<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Comunicado" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Visitante" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.IncidenciaAmbiente" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<IncidenciaAmbiente> incidenciaAmbientes = (List<IncidenciaAmbiente>) request.getAttribute("incidencias");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<main
        class="d-flex align-items-center justify-content-center     flex-column"
>


    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center"
    >
        <h1>Lista de incidencias</h1>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Ambiente</th>
                <th scope="col">Descripcion</th>
                <th scope="col">Usuario</th>
                <th scope="col">Estado</th>
                <th scope="col">Accion</th>

            </tr>
            </thead>
            <tbody>
            <%
                for(int i=0; i<incidenciaAmbientes.size(); i++){
            %>
            <tr>
                <td>
                    <%=i+1%>
                </td>
                <td>
                    <%=incidenciaAmbientes.get(i).getAmbiente().getNombreAmbiente()%>

                </td>
                <td>
                    <%=incidenciaAmbientes.get(i).getDescripcion()%>

                </td>
                <td>
                    <%=incidenciaAmbientes.get(i).getUsuarioEmisor().getUsername()%>

                </td>
                <td>
                    <%=incidenciaAmbientes.get(i).getEstado().getDescripcionCorta()%>
                </td>
                <td>
                    <a  href="<%=request.getContextPath()+"/incidencia/change-state/"+incidenciaAmbientes.get(i).getId()%>" class="btn btn-primary">Cambiar estado</a>
                    <a  href="<%=request.getContextPath()+"/incidencia/delete/"+incidenciaAmbientes.get(i).getId()%>" class="btn btn-danger">Eliminar incidencia</a>

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