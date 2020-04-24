<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 入库</title>
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
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/user_main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">入库</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- 水平表单 -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">入库</h3>
                            <c:if test="${baseResult.status == 500}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/cargo/record/user_entrySave" method="post" modelAttribute="tbCargoRecord">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">所属仓库</label>

                                    <div class="col-sm-10">
                                        <form:select cssClass="form-control" path="parentId">
                                            <form:option value="NONE" label="请选择"/>
                                            <form:options cssClass="required" items="${name}"/>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">货物名</label>

                                    <div class="col-sm-10">
                                        <form:input path="name" cssClass="form-control required" placeholder="请输入货物名称"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="number" class="col-sm-2 control-label">货物编号</label>

                                    <div class="col-sm-10">
                                        <form:input path="number" cssClass="form-control required" placeholder="请输入货物编号"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">入库数量</label>

                                    <div class="col-sm-10">
                                        <form:input path="entryQuantity" cssClass="form-control required" placeholder="请输入入库数量"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">操作人员</label>

                                    <div class="col-sm-10">
                                        <form:input path="handlers" cssClass="form-control required" value="${user.username}" readonly="true"/>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
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
