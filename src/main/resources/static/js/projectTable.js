/**
 * Create by 菲尼莫斯 on 2018/7/17
 * Description :
 */
layui.use(['table', 'layer'], function () {
  //layui table 的js对象实例
  var table = layui.table,
    //layui 弹窗提示的js对象实例
    layer = layui.layer,
    //layui jquery的实例
    $ = layui.$;

  //渲染table
  table.render({
    //layui table 的js对象实例的id
    id: 'layuiTableId',
    //html中的table本体id
    elem: '#tableId',
    //数据获取接口url
    url: 'project/getData',
    //使用自动分页
    page: true,
    //默认每页数据量
    limit: 5,
    //可选每页数据量
    limits: [1, 2, 3, 4, 5],
    //表格头声明
    cols: [[
      //第一列为多选框
      {type: 'checkbox', fixed: 'left'},
      //field 表示该列要显示的数据名，title表示表头名，sort表示是否排序，width表示该列的宽度，templet表示对该列的额外处理
      {field: 'id', width: '5%', title: 'id', sort: true},
      {field: 'name', width: '20%', title: '项目名称', edit: 'text'},
      {
        field: 'time', width: '20%', title: '项目时间', templet: function (d) {
          //输入参数d包含了该行的所有参数，这一行里面我们取出time数据并对其进行格式化
          return dateFormat('yyyy年MM月dd日 HH:mm', d.time);
        }
      },
      {field: 'description', width: '50%', title: '项目描述', edit: 'text'}
    ]]
  });


  //按钮的对应操作
  var active = {
    del: function () {
      //使用ajax传值（不跳转页面）示例
      sendCheckedIdByAjaxPost('project/del', 'POST');
    },
    edit: function () {
      //使用表单传值并跳转示例
      sendCheckedIdByFrom('project/edit', 'post');
    },
    add: function () {
      //直接跳转示例
      window.location.href = 'projectRegister.html';
    },
    reload: function () {
      table.reload('layuiTableId');
    }
  };

  //根据按钮的data-type来执行对应函数
  $('.layui-btn').on('click', function () {
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });


  //使用ajax传值（不跳转页面）示例
  function sendCheckedIdByAjaxPost(url, method) {
    //获取选中项
    var checkStatus = table.checkStatus('layuiTableId'),
      data = checkStatus.data,
      idList = [];
    //复选框勾选不能为空
    if (data.length < 1) {
      return layer.msg('请选择数据');
    }

    //将选中的id放入列表
    $.each(data, function (index, item) {
      idList.push(item.id);
    });


    //弹窗询问
    layer.confirm('真的删除' + data.length + '行吗？', function (index) {
      //关闭弹窗
      layer.close(index);
      //执行ajax
      $.ajax(url, {
        //post or get
        type: method,
        //要传值的数据
        data: {idList: idList},
        //成功时的处理
        success: function (result) {
          //再次弹窗打印结果
          layer.alert(result, function () {
            //刷新页面
            return window.location.reload();
          });
        },
        //错误时的处理
        error: function (XMLHttpRequest, msg) {
          layer.msg('请求错误：' + msg);
        }
      });
    });
  }

  //使用表单传值并跳转示例
  function sendCheckedIdByFrom(url, method) {
    //获取选中项
    var checkStatus = table.checkStatus('layuiTableId'),
      data = checkStatus.data;
    //复选框勾选不能为空
    if (data.length < 1) {
      return layer.msg('请选择数据');
    }
    if (data.length > 1) {
      return layer.msg('一次编辑1条数据');
    }

    //建立一个表单
    var form = $("<form style='display: none'></form>");
    //写入表单的必要参数
    form.attr({'action': url, 'method': method});
    //将选中的id以input形式放入表单中
    var input = $("<input type='hidden'>");
    input.attr({"name": "id"});
    input.val(data[0].id);
    form.append(input);
    //将表单放入页面中
    $("body").append(form);
    //提交表单
    form.submit();

  }

});