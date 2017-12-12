<%@ include file="/template/header.jsp"%>

<div class="panel-heading">
	FILES</a><div style="float: right;"><b>${bucketName}</b></div>
</div>

<div class="panel-body div-heigth">

	<c:forEach items="${bucketFiles}" var="objectSummary" varStatus="loop">
	
		<li class="list-group-item text-left">${objectSummary.key} <span id="id_${loop.index}"> </span> 
			<div style="float: right;">
				<a href="${linkTo[S3Controller].download(objectSummary.key)}" title="download" target="_blank"><span class="glyphicon glyphicon-download"></span></a>&nbsp;
				<a href="${linkTo[S3Controller].deleteFile(objectSummary.key)}" onclick="return fileDeleteConfirm('${objectSummary.key} ')" title="delete"><span class="glyphicon glyphicon-trash"></span></a>
			</div>
		</li>
		
		 <script type="text/javascript">
		 		var byteFormated = bytesToSize(${objectSummary.size});
		 		var id = "id_"+${loop.index};
		 		document.getElementById(id).innerHTML="("+byteFormated+")";
         </script>
		
		
	</c:forEach>
</div>

<script type="text/javascript">
	$('a').each(function() {
		  var value = $(this).attr('href');		  
		  $(this).attr('href', value.replace('%2F','*'));		  
	});
</script>

<%@ include file="/template/footer.jsp"%>
