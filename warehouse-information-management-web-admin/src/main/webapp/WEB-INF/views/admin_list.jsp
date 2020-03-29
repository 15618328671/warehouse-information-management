<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
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
                <li class="active">管理员列表</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">管理员列表</h3>

                            <c:if test="${baseResult.status == 200}">
                                <div class="alert alert-success alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>

                            <div class="box-body">
                                <a href="/admin/form" type="button" class="btn btn-sm btn-default "><i class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default " onclick="App.deleteMulti('/admin/delete')"><i class="fa fa-trash-o"></i>删除</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm btn-default "><i class="fa fa-download"></i>导出</a>
                            </div>

                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-3 control-label">用户名</label>

                                        <div class="col-sm-9">
                                            <input id="username" name="username" class="form-control" placeholder="用户名"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-3 control-label">手机号</label>

                                        <div class="col-sm-9">
                                            <input id="phone" name="phone" class="form-control" placeholder="手机号"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-3 control-label">邮箱</label>

                                        <div class="col-sm-9">
                                            <input id="email" name="email" class="form-control" placeholder="邮箱"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <button type="button" class="btn btn-info" onclick="search()">搜索</button>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal iCheck-master"/></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>

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
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- 自定义模态框 -->
<sys:delete_modal />
<sys:detail_modal/>

<script>
    var _dataTables;
    $(function () {
        var _columns =  [
                {
                    "data": function ( row, type, val, meta ){
                        return '<input id="'+row.id+'" type="checkbox" class="minimal"/>'
                    }
                },
                { "data": "id" },
                { "data": "username" },
                { "data": "phone" },
                { "data": "email" },
                {
                    "data": "updated",
                    "render": function changeDateFormat(date) {
                        var changeDate = new Date(date).toJSON();
                        return new Date(+new Date(changeDate) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
                    }
                },
                {
                    "data": function ( row, type, val, meta ){
                        var _detailUrl = "/admin/detail?id="+row.id;
                        var _deleteUrl = "/admin/delete?ids="+row.id;
                        return '<button type="button" class="btn btn-sm btn-default" onclick="App.searchDetail(\''+_detailUrl+'\')"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;'
                            +'<a href="/admin/form?id='+row.id+'" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;'
                            +'<button type="button" class="btn btn-sm btn-danger" onclick="App.delete(\''+_deleteUrl+'\')"><i class="fa fa-trash-o"></i> 删除</button> '
                    }
                }
            ];
        _dataTables = App.initPage("/admin/page",_columns);
    });

    function search() {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();

        var param = {
            "username":username,
            "phone":phone,
            "email":email
        };
        _dataTables.settings()[0].ajax.data = param;
        _dataTables.ajax.reload();
    }
</script>
</body>
</html>

