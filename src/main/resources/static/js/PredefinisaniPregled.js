var id = null;

$(document).ready(function() {
	whoIsLoggedIn();
	
	
	submitForme();
//	submitFormeZaPretragu();
});

function whoIsLoggedIn() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {
				if (user.uloga == "AdministratorKlinike") {
//					popuniKLinike(user.id);
					id = user.id;
					popuniTipovePregleda();
					popuniLekare();
					popuniSale();
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

function popuniTipovePregleda() {
	$.get({
		url: '/api/tipPregleda/tipoviPregleda',
		success: function(tipoviPregleda) {
			if(tipoviPregleda != undefined) {
				for(var tp of tipoviPregleda){
					$('#select-tipPregleda').append('<option value="'+tp.id+'">'+tp.naziv+'</option>');				
				}

			}
		},
		error: function(jqXhr, textStatus, errorThrown) {
//            console.log(errorThrown);
			alert("Neuspesno ucitani tipovi pregleda");
			$("#bodyID").hide();
        }
	});
}

function popuniLekare(){
	$.get({
		url : '/api/lekar/vratiLekareKlinike/'+id,
		contentType: 'application/json',
		success: function(lekari){
			$('#select-Lekara').empty();
			var tp = $('#select-tipPregleda :selected').val();
			if(lekari != undefined){
				for(var l of lekari){
					if(l.idTipPregleda == tp){
						$('#select-Lekara').append('<option value="'+l.id+'">'+l.ime+' '+l.prezime+'</option>');
					}
				}
			}

			},	
			error: function(jqXhr, textStatus, errorThrown){
			alert("Neuspesno ucitani lekari");
		}	
	});	
}

function popuniSale(){
	$.get({
		url : '/api/sala/vratiSaleKlinike/'+id,
		contentType: 'application/json',
		success: function(sale){
			$('#select-salu').empty();
			if(sale != undefined){
				for(var s of sale){

						$('#select-salu').append('<option value="'+s.id+'">'+s.naziv+'</option>');
					
				}
			}

			},	
			error: function(jqXhr, textStatus, errorThrown){
			alert("Neuspesno ucitani lekari");
		}	
	});	
	
	
}

function submitForme(){
	$("#form-dodavanjePredefPregleda").submit(function (event){
		event.preventDefault();
		
		var pocetak = $('input[name="datumPregleda"]').val();
		var pocetakVreme = $('input[name="pocetak"]').val();
		var kraj = $('input[name="datumPregleda"]').val();
		var krajVreme = $('input[name="kraj"]').val();
		var datumPregledaOd = pocetak+'T'+pocetakVreme;
		var datumPregledaDo = kraj+'T'+krajVreme;
		var tipPregleda =  $('#select-tipPregleda :selected').val();
		var lekar = $('#select-Lekara :selected').val();
		var sala = $('#select-salu :selected').val();
		var cena = $('input[name="cena"]').val();
		var popust = $('input[name="popust"]').val();
		
		$.post({
			url : 'pregledi/dodajPredefinisaniPregled',
			contentType: 'application/json',
			data: JSON.stringify({datumPregledaOd,datumPregledaDo, tipPregleda,lekar,sala,cena,popust}),
			
			success: function(response){
				alert(response);
			},
			error: function(response){
				alert(response);
			},
			
			
			
		});
		
		
		
	});
}

	



