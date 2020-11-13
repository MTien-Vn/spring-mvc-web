<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="categoryURL" value="/admin/category/listCategory"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Category</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value='/admin/category/listCategory'/>" id="formSubmit"
			method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a
							href="<c:url value='/admin/category/listCategory'/>">Home page - admin</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<c:if test="${not empty messageResponse}">
								<div class="alert alert-${alert}">${messageResponse}</div>
							</c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Create Category'
												href="<c:url value='/admin/category/editCategory'/>"> <span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
											</span>
											</a>
											<button id="btnDelete" type="button"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Delete'>
												<span> <i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll"></th>
													<th>Name</th>
													<th>Code</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}"
															value="${item.id}"></td>
														<td>${item.name}</td>
														<td>${item.code}</td>
														<td><c:url var="editURL" value="/admin/category/editCategory">
																<c:param name="id" value="${item.id}" />
															</c:url> 
															<a class="btn btn-sm btn-primary btn-edit"
																data-toggle="tooltip" title="Update News"
																href="${editURL}" ><i class="fa fa-pencil-square-o"
																					 aria-hidden="true"></i> 
															</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page"/>
										<input type="hidden" value="" id="size" name="size" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.main-content -->
	<script>
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		$(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPages,
	            visiblePages: 5,
	            startPage: currentPage,
	            onPageClick: function (event, page) {
	            	if (currentPage != page) {
	            		$('#size').val(10);
						$('#page').val(page);
						$('#formSubmit').submit();
					}
	            }
	        });
	    });

		$('#btnDelete').click(function (e) {
			e.preventDefault();
			swal({
				title: "Are you sure?",
				text: "Once deleted, you will not be able to recover this news !",
				icon: "warning",
				buttons: true,
				dangerMode: true,
			}).then(function(willDelete){
					if (willDelete) {
						var ids = $('tbody input[type=checkbox]:checked').map(function () {
					            return $(this).val();
					        }).get();
							deleteCategory(ids);
						swal("Poof! Your news has been deleted!", {
							icon: "success",
						});
					} else {
						swal("Your News file is safe!");
					}
			});
		})

		function deleteCategory(data){
			$.ajax({
				url : '<c:url value="/admin/api/category"/>',
				type: 'DELETE',
				contentType: 'application/json',
				data: JSON.stringify(data),
				//dataType: 'json',
				success: function(result){
					window.location.href = "${categoryURL}?page=1&size=10&message=delete success"
				},
				error: function(error){
					window.location.href = "${categoryURL}?page=1&size=10&message=error"
				}
			});
		}
	</script>
</body>

</html>