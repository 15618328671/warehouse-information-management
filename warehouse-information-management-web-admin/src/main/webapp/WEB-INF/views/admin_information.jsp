<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 操作面板</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- 顶部导航 -->
    <jsp:include page="../includes/user_nav.jsp"/>

    <!-- 左侧菜单 -->
    <jsp:include page="../includes/user_leftmenu.jsp"/>

    <!--文本内容 -->
    <div class="content-wrapper">
        <!-- 文本头 -->
        <section class="content-header">
            <h1>
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">个人信息</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- 水平表单 -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">个人信息</h3>
                            <c:if test="${baseResult.status == 500}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/admin/information/form" method="get" modelAttribute="tbAdministrator">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="username" class="col-sm-2 control-label">用户名</label>

                                    <div class="col-sm-10">
                                        <form:input path="username" cssClass="form-control required" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="col-sm-2 control-label">手机号</label>

                                    <div class="col-sm-10">
                                        <form:input path="phone" cssClass="form-control  mobile" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">邮箱</label>

                                    <div class="col-sm-10">
                                        <form:input path="email" cssClass="form-control  email"  readonly="true"/>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button type="submit" class="btn btn-info pull-right">修改</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- 底部版权 -->
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>
</body>
</html>

