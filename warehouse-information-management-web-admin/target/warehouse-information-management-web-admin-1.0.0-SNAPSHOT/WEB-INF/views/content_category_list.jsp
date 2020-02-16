<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="../../static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
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
                <li class="active">分类列表</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">分类列表</h3>

                            <c:if test="${baseResult.status == 200}">
                                <div class="alert alert-success alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>

                            <div class="box-body">
                                <a href="#" type="button" class="btn btn-sm btn-default "><i class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" class="btn btn-sm btn-default " ><i class="fa fa-trash-o"></i>删除</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm btn-default "><i class="fa fa-upload"></i>导入</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm btn-default "><i class="fa fa-download"></i>导出</a>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <td>id</td>
                                    <td>名称</td>
                                    <td>排序</td>
                                </tr>
                                </thead>
                                <c:forEach items="${tbContentCategories}" var="tbContentCategory">
                                    <tr id="${tbContentCategory.id}" pId="${tbContentCategory.parentId}">
                                        <td>${tbContentCategory.id}</td>
                                        <td>${tbContentCategory.name}</td>
                                        <td>${tbContentCategory.sortOrder}</td>
                                    </tr>
                                </c:forEach>
                                <tbody>
                                </tbody>

                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>

    <!-- 底部版权 -->
    <jsp:include page="../includes/copyright.jsp"/>
    <script src="../../static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- 自定义模态框 -->
<sys:delete_modal />
<sys:detail_modal/>

<script>
    $(function () {
        $('#treeTable').treeTable({
            expandLevel:2
        })
    })
</script>

</body>
</html>

