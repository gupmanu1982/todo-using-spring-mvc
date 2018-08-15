<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<body>
                <c:choose>
                    <c:when test="${todo.getId()==null || todo.getId()==0}">
                        <c:set var="url" value="/add-todo"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="url" value="/edit-todo"/>
                    </c:otherwise>
                </c:choose>

<div class="container">
        <form:form action="${url}" method="POST" modelAttribute="todo">
        <form:hidden path="id"/>
        <fieldset class="form-group">
        	<form:label path="desc">Description</form:label>
        	<form:input path="desc" name="desc" type="text" class="form-control" required="required"/>
        	<form:errors path="desc" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="targetDate">Target Date</form:label>
            <form:input path="targetDate" type="text" class="form-control"
                    required="required" />
            <form:errors path="targetDate" cssClass="text-warning" />
        </fieldset>
        <input type="submit" value="add" />
    </form:form>
    <script>
            $('#targetDate').datepicker({
                format : 'dd/mm/yyyy'
            });
    </script>
<%@ include file="common/footer.jspf"%>