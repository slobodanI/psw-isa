$(document).ready(function(){
	
	$.get({
		url:'api/lekar/lekari/{id}',
		contentType : 'application/json',
		success : function(lekar){
			if(lekar != undefined){
				$("#ime").append("     "+ lekar.ime);
				$("#prezime").append("     "+ lekar.prezime);
				$("#ocena").append("     "+ lekar.ocena);
				$("#klinika").append("     "+ lekar.klinika);
			}
			else{
				console.log("Nema tog lekara");
			}
		}
		
	});
	
	
});