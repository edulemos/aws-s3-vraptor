<%@ include file="/template/header.jsp"%>

<div class="panel-heading">
	UPLOAD</a><div style="float: right;"><b>${bucketName}</b></div>
</div>

<div class="panel-body div-heigth">

	<form action="<c:url value='/uploadFile'/>" method="post" enctype="multipart/form-data">
		
		<div class="row">
			<div class="form-group col-md-6">
				<label for="name">File:</label>
				 <input type="file" id="bucketName" name="file" required="required" />
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-6">
				<button type="submit" class="btn btn-primary form-control" id="bt-submit">Submit</button>
			</div>
		</div>

	</form>

</div>

<%@ include file="/template/footer.jsp"%>
