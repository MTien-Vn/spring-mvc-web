<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/admin/api/category" />
<c:url var="categoryURL" value="/admin/category/listCategory" />
<html>
<head>
<title>Edit category</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li><a href="#">Forms</a></li>
					<li class="active">Form Elements</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">
				<!-- <div class="row"> -->
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">
  							${message}
						</div>
					</c:if>
					<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
						<div>
							<form:input type="hidden" id="categoryId" path="id"/>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="name">Name  </label>

							<div class="col-sm-9">
								<form:input type="text" id="name" placeholder="Name category" path="name"
									class="col-xs-10 col-sm-5" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="name">Code  </label>

							<div class="col-sm-9">
								<form:input type="text" id="code" placeholder="Code category" path="code"
									class="col-xs-10 col-sm-5" />
							</div>
						</div>
						
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" id="btnAddorUpdate">
									<i class="ace-icon fa fa-check bigger-110"></i> Submit
								</button>
    
								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i> Reset
								</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<!-- </div> -->
	<script>
		$('#btnAddorUpdate').click(function(e){
			e.preventDefault();
			var data ={};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i,v){
				data[""+v.name+""] = v.value;
			});
			var id = $("#categoryId").val();
			if(id == ""){
				addNew(data);
			}else{
				updataNew(data);
			}
		})

		function addNew(data){
			$.ajax({
				url: '<c:url value="/admin/api/category"/>',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					window.location.href = "${categoryURL}?page=1&size=10&id="+result.id+"&message=create success";
				},
				error: function(error){
					window.location.href = "${categoryURL}?page=1&size=10&message=error"
				}
			});
		}

		function updataNew(data){
			$.ajax({
				url: '<c:url value="/admin/api/category"/>',
				type: 'PUT',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					window.location.href = "${categoryURL}?page=1&size=10&id="+result.id+"&message=update success"
				},
				error: function(error){
					window.location.href = "${categoryURL}?page=1&size=10&id="+"&message=error"
				}
			});
		}
	</script>
</body>
</html>
