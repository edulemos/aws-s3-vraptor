<%@ include file="/template/header.jsp"%>

<div class="panel-heading">
	FILES</a><div style="float: right;"><b>${bucketName}</b></div>
</div>

<div class="panel-body div-heigth">
	<c:forEach items="${bucketFiles}" var="objectSummary" varStatus="loop">
		<li class="list-group-item text-left">${objectSummary.key} <span id="id_${loop.index}"> </span> 
			<div style="float: right;">
				<a href="${linkTo[S3Controller].download(objectSummary.key)}" target="_blank"><span class="glyphicon glyphicon-download"></span></a>
				<a href="${linkTo[S3Controller].deleteFile(objectSummary.key)}" target="_blank" onclick="return confirm('Confirm file ${objectSummary.key} deletion?')"><span class="glyphicon glyphicon-trash"></span></a>
			</div>
		</li>
		
		 <script type="text/javascript">
		 		var byteFormated = bytesToSize(${objectSummary.size});
		 		var id = "id_"+${loop.index};
		 		document.getElementById(id).innerHTML="("+byteFormated+")";
         </script>
		
		
	</c:forEach>
</div>

<%@ include file="/template/footer.jsp"%>
