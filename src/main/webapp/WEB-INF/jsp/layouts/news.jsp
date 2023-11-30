<%@ page import="com.lambda.pe.lambdaapp.util.Constants" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.User" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.Comunicado" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lambda.pe.lambdaapp.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 8/10/2023
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Comunicado> comunicadoList = (List<Comunicado>) request.getAttribute("comunicadosSlider");
%>
<div id="carouselExampleCaptions" class="carousel slide">
    <div class="carousel-inner" >
        <%
            for(int i = 0; i<comunicadoList.size(); i++){
        %>
        <div class="carousel-item <%=i==0 ? "active":""%>">
            <a style="text-decoration: none" href="#">
                <div class="carousel-caption d-none d-md-block">
                    <h5><%=DateUtil.convertDateToString(comunicadoList.get(i).getFechaComunicado(), DateUtil.FORMAT_DATE)%></h5>
                    <p><%=comunicadoList.get(i).getDescripcion()%></p>
                </div>
                <img src="<%=request.getContextPath()%>/resource/images/<%=comunicadoList.get(i).getUrlImagen()%>" class="d-block w-100" alt="...">
            </a>


        </div>
        <%}%>

    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
