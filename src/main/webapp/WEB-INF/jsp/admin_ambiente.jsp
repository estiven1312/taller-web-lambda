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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


<jsp:include page="layouts/menu.jsp"></jsp:include>
<main
        class="d-flex align-items-center justify-content-center     flex-column"
>
    <div
            class="pt-5 pb-4 d-flex flex-row flex-wrap align-items-center justify-content-center"
    >
        <div>Ambientes</div>
    </div>
    <div class="d-flex align-items-center justify-content-center flex-wrap">
        <a href="<%=request.getContextPath()%>/ambiente/cubiculo"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break">Cubiculos</h2>
                <img src="<%=request.getContextPath()%>/assets/img/cubiculo.png" class="w-80 img-fluid" alt="" />
            </button>
        </a>
        <a href="<%=request.getContextPath()%>/ambiente/estacionamiento"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break text-decoration-none">
                    Estacionamientos
                </h2>
                <img src="<%=request.getContextPath()%>/assets/img/estacionamientos.png" class="w-80 img-fluid" alt="" />
            </button>
        </a>
        <a href="<%=request.getContextPath()%>/ambiente/sala"
           class="d-flex align-items-center justify-content-center text-decoration-none button-management">
            <button class="button button--light button--big shadow rounded-4 p-4 m-3">
                <h2 class="text fw-bold text-break">Salas</h2>
                <img src="<%=request.getContextPath()%>/assets/img/oficina.png" class="w-80 img-fluid" alt="" />
            </button>
        </a>
    </div>
</main>
<script src="<%=request.getContextPath()%>/assets/js/modules/estacionamiento.js" type="text/javascript"></script>
<jsp:include page="layouts/footer.jsp"></jsp:include>