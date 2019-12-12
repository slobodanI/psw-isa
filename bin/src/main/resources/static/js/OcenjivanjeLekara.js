$(document).ready(function(){
	
	whoIsLoggedIn();
	
	// posalji ocenu za izabranog lekara
	$("#form-ocenjivanje").submit(function(event) {
		event.preventDefault();
		
		var idLekara =  $('#select-lekar :selected').val();
		var ocena = $('#select-ocena :selected').val();
					
		$.ajax({
			url: 'api/lekar/oceniLekara',
			type: 'PUT',
			data: JSON.stringify({idLekara, ocena}),
			contentType: 'application/json',
			success: function(){
				alert("Uspešno ocenjen lekar");
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
					popuniLekare(user.id);
				} else {
//					console.log("NIKO NIJE ULOGOVAN");
					$("#bodyID").hide();
				}
				
			} else {
				console.log("NIKO NIJE ULOGOVAN");
			}

		}
	});
}

function popuniLekare(idPacijenta) {
	$.get({
		url: 'pacijent/getPoseceneLekare/' + idPacijenta,
		success: function(lekari) {
			if(lekari != undefined) {
				for(var l of lekari){
					$('#select-lekar').append('<option value="'+l.id+'">'+l.ime+ ' ' + l.prezime +'</option>');				
				}
			}
		},
		error: function(jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        }
	});
}