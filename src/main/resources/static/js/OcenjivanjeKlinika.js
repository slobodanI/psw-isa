$(document).ready(function(){
	
	whoIsLoggedIn();
	
	// posalji ocenu za izabranu kliniku
	$("#form-ocenjivanje").submit(function(event) {
		event.preventDefault();
		
		var idKlinike =  $('#select-klinike :selected').val();
		var ocena = $('#select-ocena :selected').val();
					
		$.ajax({
			url: 'klinika/oceniKliniku',
			type: 'PUT',
			data: JSON.stringify({idKlinike, ocena}),
			contentType: 'application/json',
			success: function(){
				alert("Uspešno ocenjena klinika");
			},
			error: function(jqXhr, textStatus, errorMessage) {
	            console.log("Error: ", errorMessage);
	        }
		});
	});
	
});

function whoIsLoggedIn() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {
				if (user.uloga == "Pacijent") {
					popuniKLinike(user.id);
				} else {
//					console.log("NIKO NIJE ULOGOVAN");
					$("#bodyID").hide();
					window.location = "./index.html";
				}
				
			} else {
				console.log("NIKO NIJE ULOGOVAN");
				window.location = "./index.html";
			}

		}
	});
}

function popuniKLinike(idPacijenta) {
	$.get({
		url: 'pacijent/getPoseceneKlinike/' + idPacijenta,
		success: function(klinike) {
			if(klinike != undefined) {
				for(var k of klinike){
					$('#select-klinike').append('<option value="'+k.id+'">'+k.naziv+'</option>');				
				}
			}
		},
		error: function(jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        }
	});
}