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
            <td>${tbCargoRecord.name}</td>
        </tr>
        <tr>
            <td>货物编号：</td>
            <td>${tbCargoRecord.number}</td>
        </tr>
        <tr>
            <td>所属仓库：</td>
            <td>${tbCargoRecord.parentId}</td>
        </tr>
        <tr>
            <td>货物现存量：</td>
            <td>${tbCargoRecord.inventory}</td>
        </tr>
        <c:if test="${tbCargoRecord.entryQuantity != null}">
            <tr>
                <td>最新入库数量：</td>
                <td>${tbCargoRecord.entryQuantity}</td>
            </tr>
            <tr>
                <td>最新入库时间：</td>
                <td><fmt:formatDate value="${tbCargoRecord.entryTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            </tr>
        </c:if>
        <c:if test="${tbCargoRecord.deliveryQuantity != null}">
            <tr>
                <td>最新出库数量：</td>
                <td>${tbCargoRecord.deliveryQuantity}</td>
            </tr>
            <tr>
                <td>最新出库时间：</td>
                <td><fmt:formatDate value="${tbCargoRecord.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            </tr>
        </c:if>
        <tr>
            <td>创建时间：</td>
            <td><fmt:formatDate value="${tbCargoRecord.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
        </tr>
        <tr>
            <td>更新时间：</td>
            <td><fmt:formatDate value="${tbCargoRecord.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
            <td>操作人员：</td>
            <td>${tbCargoRecord.handlers}</td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
