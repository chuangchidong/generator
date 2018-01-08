$(function () {
    $("#jqGrid").jqGrid({
        url: 'free/api/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '项目ID', name: 'projectId', index: 'project_id', width: 80 }, 			
			{ label: '项目模块ID', name: 'moduleId', index: 'module_id', width: 80 }, 			
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: 'url路径', name: 'url', index: 'url', width: 80 }, 			
			{ label: '访问方式', name: 'method', index: 'method', width: 80 }			
        ],
		viewrecords: true,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
		caption: "接口",
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
			jQuery("#jqGrid").jqGrid('gridResize',{minHeight:80, maxHeight:385});

		}
    });


	$("#jqGridRequest").jqGrid({
		url: 'free/request/list',
		datatype: "json",
		colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '项目ID', name: 'projectId', index: 'project_id', width: 80 },
			{ label: '项目模块ID', name: 'moduleId', index: 'module_id', width: 80 },
			{ label: 'API接口ID', name: 'apiId', index: 'api_id', width: 80 },
			{ label: '上一级字段ID', name: 'parentId', index: 'parent_id', width: 80 },
			{ label: '字段', name: 'field', index: 'field', width: 80 },
			{ label: '类型', name: 'type', index: 'type', width: 80 },
			{ label: '是否能为空', name: 'isNullable', index: 'is_nullable', width: 80 },
			{ label: '描述', name: 'desc', index: 'desc', width: 80 }
		],
		viewrecords: true,
		height: 385,
		rowNum: 10,
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth:true,
		multiselect: true,
		jsonReader : {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames : {
			page:"page",
			rows:"limit",
			order: "order"
		},
		caption: "请求参数",
		gridComplete:function(){
			//隐藏grid底部滚动条
			$("#jqGridRequest").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
		}
	});


	$("#jqGridResponse").jqGrid({
		url: 'free/response/list',
		datatype: "json",
		colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '项目ID', name: 'projectId', index: 'project_id', width: 80 },
			{ label: '项目模块ID', name: 'moduleId', index: 'module_id', width: 80 },
			{ label: 'API接口ID', name: 'apiId', index: 'api_id', width: 80 },
			{ label: '上一级字段ID', name: 'parentId', index: 'parent_id', width: 80 },
			{ label: '字段', name: 'field', index: 'field', width: 80 },
			{ label: '类型', name: 'type', index: 'type', width: 80 },
			{ label: '描述', name: 'desc', index: 'desc', width: 80 }
		],
		viewrecords: true,
		height: 385,
		rowNum: 10,
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth:true,
		multiselect: true,
		jsonReader : {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames : {
			page:"page",
			rows:"limit",
			order: "order"
		},
		caption: "返回结果",
		gridComplete:function(){
			//隐藏grid底部滚动条
			$("#jqGridResponse").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
		}
	});

});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		api: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.api = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.api.id == null ? "free/api/save" : "free/api/update";
			$.ajax({
				type: "POST",
			    url: url,
                contentType: "application/json",
			    data: JSON.stringify(vm.api),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "free/api/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("free/api/info/"+id, function(r){
                vm.api = r.api;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});