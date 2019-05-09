<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <%@include file="../base/docHeader.jsp"%>
    <title>UserList</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/home">Home</a>

<h2>Users List</h2>
<a href="${pageContext.request.contextPath}/user/form">Add user</a>
<c:forEach items="${users}" var="user">

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th>
                    <th>Login</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th>
                    <th>Login</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </tfoot>
                <tbody>
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.login}</td>
                    <td><a href="${pageContext.request.contextPath}/user/edit/${user.id}">Edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/user/delete/${user.id}">Delete</a></td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
<%--    <li>--%>
<%--            ${user.email}--%>
<%--        <a href="${pageContext.request.contextPath}/user/edit/${user.id}">Edit</a>--%>
<%--        <a href="${pageContext.request.contextPath}/user/delete/${user.id}">Delete</a>--%>

<%--    </li>--%>
</c:forEach>


</body>
<%@include file="../base/docFooter.jsp"%>
