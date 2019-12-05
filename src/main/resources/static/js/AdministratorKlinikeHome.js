$(document).ready(function() {
	$("#logout").click(function() {
		logOut();
	});
	

});


function logOut() {
	$.post({
		url : 'api/logOut',
		success : function() {
			//alert("Uspešno ste se odjavili");
			window.location = "./index.html";
		},
		error : function(jqXhr, textStatus, errorMessage) {
			console.log("Error: ", errorMessage);
		}

	});
}