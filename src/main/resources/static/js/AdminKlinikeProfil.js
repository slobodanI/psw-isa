$(document).ready(function(){
	
	$.get({
		url:'adminK/{id}',
		contentType : 'application/json',
		success : function(adminKlinike){
			if(adminKlinike != undefined){
				$("#ime").append("     "+ adminKlinike.ime);
				$("#prezime").append("     "+ adminKlinike.prezime);
				$("#email").append("     "+ adminKlinike.email);
				$("#klinika").append("     "+ adminKlinike.klinika);
			}
			else{
				console.log("Nema tog administratora");
			}
		}
		
	});
	
	
});