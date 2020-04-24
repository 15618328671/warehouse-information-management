<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>兴仁农贸市场果菜仓库信息管理系统 | 货物管理</title>
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
                货物管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/user_main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">货物列表</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">货物列表</h3>

                            <c:if test="${baseResult.status == 200}">
                                <div class="alert alert-success alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>
                            <c:if test="${baseResult.status == 500}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        ${baseResult.message}
                                </div>
                            </c:if>

                            <div class="box-body">
                                <a href="/cargo/user_export" type="button" class="btn btn-sm btn-default "><i class="fa fa-download"></i>导出</a>
                            </div>

                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">货物名</label>

                                        <div class="col-sm-8">
                                            <input id="name" name="name" class="form-control" placeholder="货物名"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="number" class="col-sm-4 control-label">货物编号</label>

                                        <div class="col-sm-8">
                                            <input id="number" name="number" class="form-control" placeholder="货物编号"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="parentId" class="col-sm-4 control-label">所属仓库</label>

                                        <div class="col-sm-8">
                                            <input id="parentId" name="parentId" class="form-control" placeholder="所属仓库"/>
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
                                    <th>货物编号</th>
                                    <th>货物名</th>
                                    <th>所属仓库</th>
                                    <th>货物总量</th>
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
            { "data": "number" },
            { "data": "name" },
            { "data": "parentId" },
            { "data": "inventory" },
            {
                "data": "updated",
                "render": function changeDateFormat(date) {
                    var changeDate = new Date(date).toJSON();
                    return new Date(+new Date(changeDate) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
                }
            },
            {
                "data": function ( row, type, val, meta ){
                    var _detailUrl = "/cargo/detail?id="+row.id;
                    return '<button type="button" class="btn btn-sm btn-default" onclick="App.searchDetail(\''+_detailUrl+'\')"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;'
                        +'<a href="/cargo/record/user_entry?parentId='+row.parentId+'&name='+row.name+'&number='+row.number+'" type="button" class="btn btn-sm btn-primary"><i class="fa fa-plus"></i> 入库</a>&nbsp;&nbsp;&nbsp;'
                        +'<a href="/cargo/record/user_delivery?parentId='+row.parentId+'&name='+row.name+'&number='+row.number+'" type="button" class="btn btn-sm btn-primary"><i class="fa fa-minus"></i> 出库</a>&nbsp;&nbsp;&nbsp;'
                }
            }
        ];
        _dataTables = App.initPage("/cargo/page",_columns);
    });

    function search() {
        var name = $("#name").val();
        var number = $("#number").val();
        var parentId = $("#parentId").val();

        var param = {
            "name":name,
            "number":number,
            "parentId":parentId
        };
        _dataTables.settings()[0].ajax.data = param;
        _dataTables.ajax.reload();
    }
</script>
</body>
</html>

