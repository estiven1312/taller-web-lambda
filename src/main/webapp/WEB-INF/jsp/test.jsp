<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lambda.pe.lambdaapp.domain.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 15/10/2023
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include  page="layouts/menu.jsp"></jsp:include>
<jsp:include page="layouts/news.jsp"></jsp:include>

<article>
    <section>
        <div class="container my-3">
            <div class="accordion" id="accordionExample">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            Accordion Item #1
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                        <div class="row">
                            <div class="row col-12">
                                <div class="col">
                                    <input type="date" class="form-control"  aria-label="Inicio">
                                </div>
                                <div class="col">
                                    <input type="date" class="form-control" aria-label="Fin">
                                </div>
                            </div>

                            <div class="col-12">
                                <div>
                                    <button type="button" class="btn btn-success">Buscar</button>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>
</article>

<jsp:include page="layouts/footer.jsp"></jsp:include>