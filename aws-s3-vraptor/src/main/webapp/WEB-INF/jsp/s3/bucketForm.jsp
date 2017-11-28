<%@ include file="/template/header.jsp"%>

<div class="panel-heading">CREATE BUCKET</div>

<div class="panel-body div-heigth">

	<form action="<c:url value='/savebucket'/>" method="post">

		<div class="row">
			<div class="form-group col-md-6">
				<label for="name">Bucket name:</label>
				 <input type="text"	class="form-control" id="bucketName" name="bucketName" required="required" />
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-6">
				<button type="submit" class="btn btn-primary form-control">Submit</button>
			</div>
		</div>

	</form>

</div>

<%@ include file="/template/footer.jsp"%>
