<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 8/10/2023
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.Year" %>
<%
    int year = Year.now().getValue();
%>
<!-- Footer -->
<footer class="bg-body-tertiary text-center ">
    <!-- Grid container -->
    <!-- Copyright -->
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
        <%out.print(year);%> @Copyright:
        <a class="text-dark" href="./home">Lambda Project</a>
    </div>
    <!-- Copyright -->

</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
