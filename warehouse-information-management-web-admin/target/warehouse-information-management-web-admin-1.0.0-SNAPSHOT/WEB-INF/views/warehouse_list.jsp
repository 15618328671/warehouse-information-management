<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 内容分类</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="../../static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- 顶部导航 -->
    <jsp:include page="../includes/nav.jsp"/>

    <!-- 左侧菜单 -->
    <jsp:include page="../includes/leftmenu.jsp"/>

    <!--文本内容 -->
    <div class="content-wrapper">
        <!-- 文本头 -->
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容分类</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容分类</h3>

                            <c:if test="${baseResult.status == 200}">
                                <div class="alert alert-success alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                                        &times;
                                    </button>
                                        ${baseResult.message}
                                </div>
                            </c:if>

                            <div class="box-body">
                                <a href="/warehouse/form" type="button" class="btn btn-sm btn-default "><i
                                        class="fa fa-plus"></i>新增仓库</a>&nbsp;&nbsp;&nbsp;
                            </div>

                            <div class="box-body table-responsive no-padding">
                                <table id="treeTable" class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>名称</th>
                                        <th>编号</th>
                                        <th>排序</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${tbWarehouses}" var="tbWarehouse">
                                        <tr id="${tbWarehouse.name}" pId="${tbWarehouse.parentId}">
                                            <td>${tbWarehouse.name}</td>
                                            <td>${tbWarehouse.number}</td>
                                            <td>${tbWarehouse.sortOrder}</td>
                                            <td>
                                                <c:if test="${tbWarehouse.isParent}">
                                                    <button type="button" class="btn btn-sm btn-default" onclick="App.searchDetail('/warehouse/detail?id=${tbWarehouse.id}')"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;
                                                    <a href="/warehouse/form?id=${tbWarehouse.id}" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;
                                                    <button type="button" class="btn btn-sm btn-danger" onclick="App.delete('/warehouse/delete?name=${tbWarehouse.name}','警告：该操作将删除该仓库下的所有货物，您确定要删除吗？')"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                                    <a href="/cargo/form?parentId=${tbWarehouse.name}" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增货物</a>
                                                </c:if>
                                                <c:if test="${!tbWarehouse.isParent}">
                                                    <button type="button" class="btn btn-sm btn-default" onclick="App.searchDetail('/cargo/detail?id=${tbWarehouse.id}')"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;
                                                    <a href="/cargo/form?id=${tbWarehouse.id}" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;
                                                    <button type="button" class="btn btn-sm btn-danger" onclick="App.delete('/cargo/delete?ids=${tbWarehouse.id}')"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- 底部版权 -->
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>
<script src="../../static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>

<!-- 自定义模态框 -->
<sys:delete_modal/>
<sys:detail_modal/>

<script>
    $(function () {
        $("#treeTable").treeTable({
            //树为两层
            expandLevel: 2
        });
    })
</script>
</body>
</html>

