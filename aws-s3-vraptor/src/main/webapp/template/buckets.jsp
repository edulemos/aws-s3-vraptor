
<div class="col-lg-4">
	<div class="panel panel-default">
	
		
<div class="panel-heading">BUCKETS
   <div style="float:right;"><a href="${linkTo[S3Controller].bucketForm()}"><span class="glyphicon glyphicon-plus"></span></a></div>

</div>


		
		<div class="panel-body div-heigth">
			<c:forEach items="${bucketsList}" var="bucket">
				<li class="list-group-item text-left">
					${bucket.name}
					<div style="float: right;">
				     <a href="${linkTo[S3Controller].listBucketFiles(bucket.name)}"><span class="glyphicon glyphicon-search" title="view" ></span></a>
				     	&nbsp;
					 <a href="${linkTo[S3Controller].upload()}"> <span class="glyphicon glyphicon-upload" title="upload"></span></a>
					 	&nbsp;
	 			     <a href="${linkTo[S3Controller].deleteBucket(bucket.name)}"  onclick="return confirm('Confirm deletion the bucket ${bucket.name}? \n \n ALL FILES WILL BE REMOVED!!!')"><span class="glyphicon glyphicon-trash"></span></a>					 
					 </div>
					</li>
					
			</c:forEach>
		</div>
		
	</div>
</div>
