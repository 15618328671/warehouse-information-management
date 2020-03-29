var App = function () {
    //iCheck
    var _masterCheckbox;
    var _checkbox;

    //用于存放Id的数组;
    var _idArray;

    /**
     * 私有方法，初始化iCheck
     */
    var handleInitCheckbox = function () {
        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        //获取主Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.iCheck-master');
        //获取全部Checkbox
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * 全选功能
     */
    var handleAllCheckbox = function () {
        _masterCheckbox.on("ifClicked",function (e) {
            //返回true表示未选中
            if (e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            //返回false表示选中
            else {
                _checkbox.iCheck("check");
            }
        })
    };

    /**
     * 删除
     */
    var handleDelete = function (url,msg) {
        //可选参数
        if (!msg) msg =null;

        $("#modal-default").modal("show");
        $("#modal-message").html(msg == null ?"您确定要删除这项数据吗？":msg);
        $(".modal-footer .btn-primary").bind("click",function () {
            btnDelete();
        });

        function btnDelete() {
            // var _id = $(this).attr("id");
            $.ajax({
                "url":url,
                "type":"POST",
                "dataType":"JSON",
                "success":function (data) {
                    //删除成功刷新页面
                    if (data.status === 200){
                        window.location.reload();
                    }
                }
            })
        }


    };

    /**
     * 批量删除
     */
    var handleDeleteMulti = function (url) {
        _idArray = new Array();
        //将选中的id存放入数组
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefined" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        $("#modal-default").modal("show");

        //没有选择删除项
        if (_idArray.length === 0){
            $("#modal-message").html("请选择需要删除的数据");
        }
        //选中删除项
        else {
            $("#modal-message").html("您确定要删除这项数据吗？");
        }

        $(".modal-footer .btn-primary").bind("click",function () {
            btnModalOK();
        });

        function btnModalOK() {
            if (_idArray.length ===0){
                $("#modal-default").modal("hide");
            }
            else {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "data":{"ids" :_idArray.toString()},
                    "dataType":"JSON",
                    "success":function (data) {
                        //删除成功刷新页面
                        if (data.status === 200){
                            window.location.reload();
                        }
                    }
                })
            }
        }
    };

    /**
     * 查看详情
     */
    var handleSearchDetail = function (url) {
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            success:function (data) {
                $("#modal-detail").modal("show");
                $("#modal-detail-message").html(data);
            }
        });

        $(".modal-footer .btn-primary").bind("click",function () {
            reload();
        });

        function reload() {
            window.location.reload();
        }
    };

    /**
     * 分页功能
     * @param url
     * @param columns
     */
    var handleInitPage = function (url,columns) {
        var _dataTables = $("#dataTable").DataTable({
            "autoWidth":false,
            "info":true,
            "lengthChange":false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide":true,
            "deferRender": true,
            "ajax":{
                "url":url
            },
            "columns":columns,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                handleInitCheckbox();
                handleAllCheckbox();
            }
        });

        return _dataTables;
    };

    return{
        init:function () {
            handleInitCheckbox();
            handleAllCheckbox();
        },

        deleteMulti:function (url) {
            handleDeleteMulti(url);
        },

        searchDetail:function(url){
            handleSearchDetail(url);
        },

        initPage:function (url,columns) {
            return handleInitPage(url,columns);
        },

        delete:function (url,msg) {
            handleDelete(url,msg);
        }
    }

}();

$(document).ready(function () {
    App.init();
});