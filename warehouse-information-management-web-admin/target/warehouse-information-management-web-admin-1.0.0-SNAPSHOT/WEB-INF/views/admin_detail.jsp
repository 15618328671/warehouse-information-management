<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 用户详情</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box-body">
    <table class="table table-hover">
        <thead></thead>
        <tbody>
        <tr>
            <td>用户名：</td>
            <td>${tbAdministrator.username}</td>
        </tr>
        <tr>
            <td>手机号：</td>
            <td>${tbAdministrator.phone}</td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>${tbAdministrator.email}</td>
        </tr>
        <tr>
            <td>创建时间：</td>
            <td><fmt:formatDate value="${tbAdministrator.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
        <tr>
            <td>更新时间：</td>
            <td><fmt:formatDate value="${tbAdministrator.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
