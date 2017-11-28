function addMsgSucesso(msg) {
	$.toast({
	    heading: 'Information',
	    text: msg,
	    showHideTransition: 'slide',
	    icon: 'info',
	    hideAfter: 5000 ,
	    position: 'top-right'
	})
}

function addMsgErro(msg) {
	$.toast({
	    heading: 'Error',
	    text: msg,	    
	    icon: 'error',
	    hideAfter: false,
	    position: 'top-right'
	})
}


$(document).ready(function() {

	$("form").submit(function( event ) {
		$('<br></br><div class="loader">uploading...</div>').insertAfter( "#bt-submit" );
	});

});
			

	function bytesToSize(bytes) {
	   var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
	   if (bytes == 0) return '0 Byte';
	   var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
	   var conversion = Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
	   console.log('aa'+conversion);
	   return conversion;
	};
