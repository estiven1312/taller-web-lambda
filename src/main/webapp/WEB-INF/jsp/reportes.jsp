<%@ page import="com.lambda.pe.lambdaapp.domain.model.User" %>
<%@ page import="com.lambda.pe.lambdaapp.util.Constants" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 20/10/2023
  Time: 07:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessions=request.getSession(false);
    User user=(User) sessions.getAttribute(Constants.USER_KEY_SESSION.label);
    String errors = (String) request.getAttribute("errorMessage");
    String ok = (String) request.getAttribute("okMessage");
%>

<jsp:include page="layouts/menu.jsp"></jsp:include>
<% if(errors != null && !errors.isEmpty() ){%>
<div class="alert alert-danger mt-1" role="alert">
    <%=errors%>
</div>
<%
    }
%>
<% if(ok != null && !ok.isEmpty() ){%>
<div class="alert alert-primary mt-1" role="alert">
    <%=ok%>
</div>
<%
    }
%>
<main
        class="mt-2 container"
>
    <form class="row" method="post" enctype="multipart/form-data" action="reportes" >
        <div class="col-12">
            <h1 class="text fs-1 fw-bold text-center pb-4">Reporte de residuos</h1>
        </div>
        <div class="row">
            <div class="row col-12">
                <div class="mt-2">
                    <input type="file" name="imagen">
                </div>
                <div class="col-12 col-lg-6">
                    <div class="mb-3">
                        <label for="comentario" class="form-label">Comentarios</label>
                        <input type="text" class="form-control" id="comentario"  name="comentario"  />
                    </div>
                    <div class="mb-3">
                        <label for="referencia" class="form-label">Referencia</label>
                        <input type="text" class="form-control" id="referencia"  name="referencia" />
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-start">
                <button
                        type="submit"
                        id="submitButton"
                        class="text btn btn-warning border-0 rounded-2 py-2 px-3 text-black button fw-bold"
                >
                    Reportar
                </button>
            </div>
        </div>


    </form>
</main>
<!--
<script src="<%=request.getContextPath()%>/assets/js/modules/perfil.js" type="text/javascript"></script>
-->
<jsp:include page="layouts/footer.jsp"></jsp:include>
