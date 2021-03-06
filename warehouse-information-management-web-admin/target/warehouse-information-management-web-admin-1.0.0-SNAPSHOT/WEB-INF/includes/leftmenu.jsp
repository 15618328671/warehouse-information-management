<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 左侧导航栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../../static/assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${administrator.username}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">功能菜单</li>
            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-user"></i> <span>用户管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li class="active"><a href="/admin/list"><i class="fa fa-circle-o"></i> 管理员列表</a></li>
                    <li class="active"><a href="/user/list"><i class="fa fa-circle-o"></i> 用户列表</a></li>
                    <li class="active"><a href="/admin/information?id=${administrator.id}"><i class="fa fa-circle-o"></i> 个人信息</a></li>
                </ul>
            </li>
            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-book"></i> <span>内容管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li class="active"><a href="/warehouse/list"><i class="fa fa-circle-o"></i> 内容分类</a></li>
                    <li class="active"><a href="/cargo/list"><i class="fa fa-circle-o"></i> 货物列表</a></li>
                    <li class="active"><a href="/cargo/record/list"><i class="fa fa-circle-o"></i> 货物流动记录</a></li>
                </ul>
            </li>
        </ul>
    </section>
</aside>