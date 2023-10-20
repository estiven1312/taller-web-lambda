<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 29/09/2023
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<jsp:include  page="layouts/menu.jsp"></jsp:include>
<jsp:include page="layouts/news.jsp"></jsp:include>

<article>
    <section>
        <div class="container my-3">
            <div class="row">
                <div class="col-4">
                    <a  href="#" style="text-decoration: none;">
                        <div class="card">
                            <img class="card-img-top " src="assets/img/oficina.png" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            </div>
                        </div>
                    </a>


                </div>
                <div class="col-4">
                    <a  href="#" style="text-decoration: none;">
                        <div class="card">
                            <img class="card-img-top " src="assets/img/estacionamiento.png" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            </div>
                        </div>
                    </a>

                </div>
                <div class="col-4">
                    <a href="#" style="text-decoration: none;">
                        <div class="card">
                            <img class="card-img-top" src="assets/img/cubiculo.png" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            </div>
                        </div>
                    </a>

                </div>
            </div>


        </div>
    </section>
</article>




<jsp:include page="layouts/footer.jsp"></jsp:include>


