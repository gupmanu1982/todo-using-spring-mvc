<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

    <div class="container">
    <H1>Your Todos</H1>
    	<div>
    		<table class="table table-striped">
    			<caption>Your Todos are</caption>

    			<thead>
    				<tr>
    					<th>Description</th>
    					<th>Date</th>
    					<th>Completed</th>
    					<th></th>
    					<th></th>
    				</tr>
    			</thead>

    			<tbody>
    				<c:forEach items="${todos}" var="todo">
    					<tr>
    						<td>${todo.desc}</td>
    						<td><fmt:formatDate pattern="dd/MM/yyyy"
                            							value="${todo.targetDate}" /></td>
    						<td>${todo.done}</td>
    						<td><a type="button" class="btn btn-submit"
                                		href="/edit-todo?id=${todo.id}">Edit</a></td>
    						<td><a type="button" class="btn btn-warning"
                                		href="/delete-todo?id=${todo.id}">Delete</a></td>
    					</tr>
    				</c:forEach>
    			</tbody>
    		</table>
    	</div>


 <a class="button" href="/add-todo">Add</a>
 </div>
 <%@ include file="common/footer.jspf"%>