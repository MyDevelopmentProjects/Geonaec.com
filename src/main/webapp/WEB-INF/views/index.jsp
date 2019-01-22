<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="loadPageContent" value="${empty content?'main':content}"/>
<%@include file="includes/header.jsp" %>
<jsp:include page="pages/${loadPageContent}.jsp" />
<%@include file="includes/footer.jsp" %>