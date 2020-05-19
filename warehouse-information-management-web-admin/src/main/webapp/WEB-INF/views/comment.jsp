<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 货物管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="../../static/assets/plugins/wangEditor/wangEditor.min.css"/>
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
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">${tbCargoRecord.comment == null || tbCargoRecord.comment ==''?"添加":"修改"}评论</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- 水平表单 -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbCargoRecord.comment == null || tbCargoRecord.comment ==''?"添加":"修改"}评论</h3>
                            <c:if test="${baseResult.status == 500}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/cargo/record/comment_save" method="post" modelAttribute="tbCargoRecord">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">评论</label>
                                    <div class="col-sm-11">
                                        <form:hidden path="comment"/>
                                        <div id="editor">
                                            ${tbCargoRecord.comment}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button id="editSubmit" type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- 底部版权 -->
    <jsp:include page="../includes/copyright.jsp"/>
    <script src="../../static/assets/plugins/wangEditor/wangEditor.min.js"></script>
</div>
<jsp:include page="../includes/footer.jsp"/>

<script>
    $(function () {
        var E = window.wangEditor;
        var editor = new E('#editor');
        editor.create();

        $("#editSubmit").bind("click",function () {
            var commentHtml = editor.txt.html();
            $("#comment").val(commentHtml);
        });
    });
</script>

</body>
</html>
