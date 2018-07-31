<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Librairnet</title>
        <jsp:include page="Header.jsp"></jsp:include>
    </head>
    <body>
        <h3>Librairnet\Panier</h3>
    <c:if test="${isempty}">
        Le panier est vide!
    </c:if>
    <c:if test="${!isempty}">
        <c:forEach var="lp" items="${listePanier}">
            ${lp.titre} - ${lp.qty}
        </c:forEach>
    </c:if>
    </body>
    <jsp:include page="Bottom.jsp"></jsp:include>
</html>
