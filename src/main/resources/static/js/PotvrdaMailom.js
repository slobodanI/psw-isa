var params = new URL(location.href).searchParams;
var odluka = params.get("odluka");
var pregledID = params.get("pregledID");

$(document).ready(function() {
	
	$("#p-pozitivno").hide();
	$("#p-negativno").hide();
	$("#p-greska").hide();
	
	//ako su svi parametri iz url-a validni
	if(odluka != undefined || pregledID != undefined) {
		if(odluka != "" || pregledID != "") {
				
			if(odluka == "potvrdi" || odluka == "odustani"){
				//ovo je potvrdjivanje ili odustajanje
				potvrdaPregleda();
			} else {
				$("#p-greska").show();
			}
				

		} else {
			$("#p-greska").show();
		}
	} else {
		$("#p-greska").show();
	}
	
});
	
function potvrdaPregleda() {
	$.ajax({
		url: 'pregledi/potvrdaPregleda',
		type: 'PUT',
		data: JSON.stringify({pregledID, odluka}),
		contentType: 'application/json',
		success: function() {
			if(odluka == "potvrdi"){
				$("#p-pozitivno").show();
			}
			
			if(odluka == "odustani"){
				$("#p-negativno").show();
			}
		},
		error: function(){
			$("#p-greska").show();
		}
	});
} 
