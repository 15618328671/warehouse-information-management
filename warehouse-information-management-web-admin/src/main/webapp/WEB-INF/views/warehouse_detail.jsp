<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 仓库详情</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box-body">
    <table class="table table-hover">
        <thead></thead>
        <tbody>
        <tr>
            <td>仓库名：</td>
            <td>${tbWarehouse.name}</td>
        </tr>
        <tr>
            <td>仓库编号：</td>
            <td>${tbWarehouse.number}</td>
        </tr>
        <tr>
            <td>所属仓库：</td>
            <td>${tbWarehouse.parentId}</td>
        </tr>
        <tr>
            <td>仓库总容量：</td>
            <td>${tbWarehouse.capacity}</td>
        </tr>
        <tr>
            <td>仓库现容量：</td>
            <td>${tbWarehouse.inventory}</td>
        </tr>
        <tr>
            <td>最新入库数量：</td>
            <td>${tbWarehouse.entryQuantity}</td>
        </tr>
        <tr>
            <td>最新入库时间：</td>
            <td><fmt:formatDate value="${tbWarehouse.entryTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
        <tr>
            <td>最新出库数量：</td>
            <td>${tbWarehouse.deliveryQuantity}</td>
        </tr>
        <tr>
            <td>最新出库时间：</td>
            <td><fmt:formatDate value="${tbWarehouse.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
        <tr>
            <td>创建时间：</td>
            <td><fmt:formatDate value="${tbWarehouse.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
        <tr>
            <td>更新时间：</td>
            <td><fmt:formatDate value="${tbWarehouse.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
