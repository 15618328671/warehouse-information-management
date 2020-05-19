<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 货物详情</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box-body">
    <table class="table table-hover">
        <thead></thead>
        <tbody>
        <tr>
            <td>货物名：</td>
            <td>${tbCargo.name}</td>
        </tr>
        <tr>
            <td>货物编号：</td>
            <td>${tbCargo.number}</td>
        </tr>
        <tr>
            <td>所属仓库：</td>
            <td>${tbCargo.parentId}</td>
        </tr>
        <tr>
            <td>货物总量：</td>
            <td>${tbCargo.inventory}</td>
        </tr>
        <c:if test="${tbCargo.entryQuantity != null}">
            <tr>
                <td>最新入库数量：</td>
                <td>${tbCargo.entryQuantity}</td>
            </tr>
            <tr>
                <td>最新入库时间：</td>
                <td><fmt:formatDate value="${tbCargo.entryTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            </tr>
        </c:if>
        <c:if test="${tbCargo.deliveryQuantity != null}">
            <tr>
                <td>最新出库数量：</td>
                <td>${tbCargo.deliveryQuantity}</td>
            </tr>
            <tr>
                <td>最新出库时间：</td>
                <td><fmt:formatDate value="${tbCargo.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            </tr>
        </c:if>
        <tr>
            <td>创建时间：</td>
            <td><fmt:formatDate value="${tbCargo.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
        <tr>
            <td>更新时间：</td>
            <td><fmt:formatDate value="${tbCargo.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
