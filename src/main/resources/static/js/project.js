$(function () {
    var lastsel;

    $("#jqGrid").jqGrid({
        url: 'free/project/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 30, key: true },
			{ label: '名称', name: 'name', index: 'name', width: 60 },
			{ label: '版本', name: 'version', index: 'version', width: 40 },
			{ label: '描述', name: 'desc', index: 'desc', width: 80,editable : true },
			{ label: '说明', name: 'remark', index: 'remark', width: 80,editable : true },
			{ label: '操作', name : 'act', width : 100, sortable : false}

		],
		viewrecords: true,
        height: 385,
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
        //onSelectRow : function(id) {
        //    if (id && id !== lastsel) {
        //        jQuery('#jqGrid').jqGrid('restoreRow', lastsel);
        //        jQuery('#jqGrid').jqGrid('editRow', id, true);
        //        lastsel = id;
        //    }
        //},
        editurl : "free/project/edit",
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
			var ids = jQuery("#jqGrid").jqGrid('getDataIDs');
			for ( var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				be = "<input style='margin:3px;' type='button' value='编辑' onclick=\"jQuery('#jqGrid').editRow('"+ cl + "');\" />";
				se = "<input style='margin:3px;' type='button' value='保存' onclick=\"calSaveRow(jQuery('#jqGrid'),'"+ cl + "');\" />";
				ce = "<input style='margin:3px;' type='button' value='取消' onclick=\"jQuery('#jqGrid').restoreRow('"+ cl + "');\" />";
				//de = "<input style='margin:3px;' type='button' value='删除' onclick=\"jQuery('#jqGrid').delGridRow('"+ cl + "');\" />";
				jQuery("#jqGrid").jqGrid('setRowData', ids[i],
					{
						act : be + se + ce
					});
			}
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		project: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.project = {};
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
			var url = vm.project.id == null ? "free/project/save" : "free/project/update";
			$.ajax({
				type: "POST",
			    url:  url,
                contentType: "application/json",
			    data: JSON.stringify(vm.project),
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
				    url:  "free/project/delete",
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
			$.get( "free/project/info/"+id, function(r){
                vm.project = r.project;
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