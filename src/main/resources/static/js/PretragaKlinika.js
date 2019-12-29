$(document).ready(function() {
	whoIsLoggedIn();
	
	popuniTipovePregleda();
	submitFormeZaPretragu();
});

function whoIsLoggedIn() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {
				if (user.uloga == "Pacijent") {
//					popuniKLinike(user.id);
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

function submitFormeZaPretragu() {
	$("#form-searchKlinike").submit(function(event) {
		event.preventDefault();

		//
		var datum = $('input[name="datumPregleda"]').val();	// preuzima datum kao YYYY-MM-DD
		var tipPregleda =  $('#select-tipPregleda :selected').val();
		
		$.post({ 
			url: 'klinika/pretraziKlinike',
			data: JSON.stringify({datum, tipPregleda}),
			contentType: 'application/json',
			success: function(klinike){
				$("#divTabela").empty();
				if(klinike != undefined){
					//dodati izgled tabele
					$("divTabela").empty();
					
					var tableUDivu = $("<table id='tabelaKlinika' class='display'> </table>");// ili mozda primer $("div").addClass("important");
					
					var thead = $("<thead> </thead>");
					thead.append("<tr><th>Naziv</th><th>Prosečna ocena</th><th>Adresa</th><th>Cena pregleda</th><th>Odaberi</th>")
					
//					var html = '<table id="tabelaKlinika" class="display" ><thead><tr><th>Naziv</th><th>Prosečna ocena</th><th>Adresa</th><th>Cena pregleda</th><th>Odaberi</th></thead><tbody id="teloTabele">';
					
					var tbody = $("<tbody id='teloTabele'> </tbody>");
					
					for(var k of klinike){ // id klinike cu ubaciti u click na button
						tbody.append('<tr><td>' + html+=k.naziv; + '</td><td>' + k.ocena + '</td><td>' + k.adresa + '</td><td>' + k.cena + '</td><td>');
						  
						var btn = $('<button>Odaberi</button>');
						btn.click(odaberiKliniku(k.id, datum, tipPregleda));
						
						tbody.append(btn).append("</td></tr>");
					}
					
					tableUDivu.append(thead).append(tbody);
										
					var table = $('#tabelaKlinika').dataTable({
				        "pagingType": "full_numbers",
				        select: false
				    });
				}
			},
            error: function(jqXhr, textStatus, errorThrown) {
            	console.log(errorThrown);
            }
		});
}
	
}

function odaberiKliniku(idKLinike, datum, tipPregleda) {
	
	return function() {
		window.location = "./PretragaLekara.html?klinikaID=" + id + "&datum=" + datum + "&tipPregleda=" + tipPregleda;
	}
	
} 


