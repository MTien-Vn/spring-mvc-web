<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api/admin/new" />
<c:url var="NewURL" value="/admin/news/list" />
<html>
<head>
<title>Edit News</title>
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
						<div class="alert alert-${alert}">${message}</div>
					</c:if>
					<form:form class="form-horizontal" role="form" id="formSubmit"
						modelAttribute="model">
						<div>
							<form:input type="hidden" id="newsId" path="id" />
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="title">Title </label>

							<div class="col-sm-9">
								<form:input type="text" id="title" placeholder="Title"
									path="title" class="col-xs-10 col-sm-5" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="shortDescription"> Short description </label>

							<div class="col-sm-9">
								<form:textarea path="shortDescription" rows="5" cols="10"
									cssClass="form-control" id="content"></form:textarea>
							</div>
						</div>
						<div class="space-4"></div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">Thumb
								nail </label>
							<form:input path="thumbnail" type="file" id="uploadFile" />
						</div>
						<div class="space-4"></div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="content"> Content </label>

							<div class="col-sm-9">
								<form:textarea path="content" rows="5" cols="10"
									cssClass="form-control" id="content"></form:textarea>
							</div>
						</div>
						<div class="space-4"></div>

						<div class="form-group">
							<label for="categoryCode"
								class="col-sm-3 control-label no-padding-right">Category:</label>
							<div class="col-sm-9">
								<form:select path="categoryCode" id="categoryCode">
									<form:option value="" label="-- Category --" />
									<form:options items="${categories}" />
								</form:select>
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

	    var data = {};
		$('#uploadFile').change(function(e) {
			var file = e.target.files[0];
			var reader = new FileReader();
			dataArry  = {};
			if (file != undefined) {
			    reader.onload = function(e){
			      data["thumbnail"] = e.target.result;
			      dataArry["nameFile"] = file.name;
				 // upLoad(dataArry);
			    };
			    reader.readAsDataURL(file);
			};
		})
		
		$('#btnAddorUpdate').click(function(e) {
				e.preventDefault();
				var formData = $('#formSubmit').serializeArray();
				$.each(formData, function(i, v) {
					data["" + v.name + ""] = v.value;
				});
				
				var id = $('#newsId').val();
				if (id == "") {
					addNew(data);
				} else {
					updataNew(data);
				}
		})
		

		function addNew(data) {
			$.ajax({
				url : '<c:url value="/admin/api/news"/>',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${NewURL}?page=1&size=2&id="
									+ result.id + "&message=create success";
					},
				error : function(error) {
					window.location.href = "${NewURL}?page=1&size=2&message=error"
					}
			});
		}

		function updataNew(data) {
			$.ajax({
				url : '<c:url value="/admin/api/news"/>',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${NewURL}?page=1&size=2&id="
							+ result.id + "&message=update success"
				},
				error : function(error) {
					window.location.href = "${NewURL}?page=1&size=2&id="
							+ result.id + "&message=error"
				}
			});
		}
		
		function upLoad(dataArray){
			$.ajax({
				url: '<c:url value="/admin/api/news/files"/>',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(dataArray),
				success: function(res){
					
				},
				error: function(res){
					
				}
			})
		}
	</script>
</body>
</html>
