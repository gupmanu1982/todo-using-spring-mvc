<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<body>

<div class="container">
        <form:form action="/login" method="POST" modelAttribute="login">
        <fieldset class="form-group">
        	<form:label path="username">Username</form:label>
        	<form:input path="username" name="username" type="text" class="form-control" required="required"/>
        	<form:errors path="username" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="password">Password</form:label>
        	<form:input path="password" name="password" type="text" class="form-control" required="required"/>
        	<form:errors path="password" cssClass="text-warning" />
        </fieldset>
        <input type="submit" value="add" />
    </form:form>
    <script>
            $('#targetDate').datepicker({
                format : 'dd/mm/yyyy'
            });
    </script>
<%@ include file="common/footer.jspf"%>