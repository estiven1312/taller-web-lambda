<%@ page import="com.lambda.pe.lambdaapp.domain.model.Role" %><%-- Created by IntelliJ IDEA. User: Usuario Date:
        29/09/2023 Time: 11:03 To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<% Role role=(Role) request.getAttribute("rol"); %>

<jsp:include page="layouts/menu.jsp"></jsp:include>
<jsp:include page="layouts/news.jsp"></jsp:include>

<main class="d-flex align-items-center justify-content-center flex-wrap">

    <div class="d-flex align-items-center justify-content-center flex-wrap">
        <% if(role.getNombre().equalsIgnoreCase("GERENTE")){ %>
        <a href="<%=request.getContextPath()%>/sala"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break text-decoration-none">Salas</h2>
                <img src="<%=request.getContextPath()%>/assets/img/meetings.png" class="w-100 img-fluid" alt="" />
            </button>
        </a>
        <a href="<%=request.getContextPath()%>/estacionamiento"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break text-decoration-none">
                    Estacionamientos
                </h2>
                <img src="<%=request.getContextPath()%>/assets/img/estacionamientos.png" class="w-80 img-fluid" alt="" />
            </button>
        </a>
        <a href="<%=request.getContextPath()%>/user"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break text-decoration-none">
                    Mi información
                </h2>
                <img src="<%=request.getContextPath()%>/assets/img/user.png" class="w-80 img-fluid" alt="" />
            </button>
        </a>
        <%}%>
        <% if(role.getNombre().equalsIgnoreCase("COLABORADOR")){ %>
        <a href="<%=request.getContextPath()%>/cubiculo"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break">Salas</h2>
                <img src="<%=request.getContextPath()%>/assets/img/cubiculo.png" class="w-80 img-fluid" alt="" />
            </button>
        </a>
        <a href="<%=request.getContextPath()%>/estacionamiento"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break text-decoration-none">
                    Estacionamientos
                </h2>
                <img src="<%=request.getContextPath()%>/assets/img/estacionamientos.png" class="w-80 img-fluid" alt="" />
            </button>
        </a>
        <%}%>
    </div>
</main>




<jsp:include page="layouts/footer.jsp"></jsp:include>