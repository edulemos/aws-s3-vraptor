
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
	    text: msg.replace(/\s{2,}/g, ' '),  
	    icon: 'error',
	    hideAfter: false,
	    position: 'top-right'
	})
}

$(document).ready(function() {
	
	$("form").submit(function( event ) {		
		$(".panel-body").loading();		
	});
	
	$(".loader").click(function( event ) {		
		$(".panel-body").loading();		
	});
	
});			

function bytesToSize(bytes) {
   var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
   if (bytes == 0) return '0 Byte';
   var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
   var conversion = Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
   return conversion;
};

function bucketDeleteConfirm(bucketName) {
	var txt;
	var r = confirm("Confirm deletion the bucket " +  bucketName + "? \n \n ALL FILES WILL BE REMOVED!!!");
	if (r == true) {
		$(".panel-body").loading();
	}
	return r;
}

function fileDeleteConfirm(fileName) {
	var txt;
	var r = confirm("Confirm deletion the file: " + fileName);
	if (r == true) {
		$(".panel-body").loading();
	}
	return r;
}



