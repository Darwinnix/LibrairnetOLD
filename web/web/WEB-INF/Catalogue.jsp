<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Librairnet-Catalogue</title>
        <jsp:include page="Header.jsp"></jsp:include>
        </head>
        <body>
            <H2>Librairnet\Catalogue</H2>
            <c:forEach items="${liste}" var="livre">
            <form action="Controller">
                <input type="hidden" name="section" value="catalogue" />
                <input type="submit" name="ajout" value="Ajouter" /> ${livre.titre}
                <input type="hidden" name="livre" value="${livre.isbn}" />
            </form>
        </c:forEach>

    </body>
    <jsp:include page="Bottom.jsp"></jsp:include>
</html>
