<%@ page import="java.util.List" %>

<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Reserva" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Comunicado" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Visitante" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Visitante> visitantes = (List<Visitante>) request.getAttribute("visitantes");
%>
<jsp:include page="layouts/menu.jsp"></jsp:include>
<main
        class="d-flex align-items-center justify-content-center     flex-column"
>


    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center"
    >
        <div>
            <a class="button btn-primary" href="<%=request.getContextPath()%>/visitante/export-pdf" target="_blank">Exportar</a>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nombres</th>
                <th scope="col">Apellidos</th>
                <th scope="col">Doc Identidad</th>
                <th scope="col">Fecha Visita</th>
                <th scope="col">Correo</th>

            </tr>
            </thead>
            <tbody>
            <%
                for(int i=0; i<visitantes.size(); i++){
            %>
                <tr>
                    <td>
                        <%=i+1%>
                    </td>
                    <td>
                        <%=visitantes.get(i).getNombres()%>

                    </td>
                    <td>
                        <%=visitantes.get(i).getApellidos()%>

                    </td>
                    <td>
                        <%=visitantes.get(i).getDni()%>

                    </td>
                    <td>
                        <%=DateUtil.convertDateToString(visitantes.get(i).getReservaVisita().getInit(), DateUtil.FORMAT_DATE)%>
                    </td>
                    <td>
                        <%=visitantes.get(i).getCorreo()%>
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