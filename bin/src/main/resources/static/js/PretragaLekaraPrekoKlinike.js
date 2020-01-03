var params = new URL(location.href).searchParams;
var klinikaID = params.get("klinikaID");

$(document).ready(function() {
	whoIsLoggedIn();
	
	popuniTipovePregleda();
	submitFormeZaPretragu();
	
	if(klinikaID != undefined){
		if(klinikaID === ""){
			alert("Neodgovarajuci parametri u url-u...");
			$("#bodyID").hide();
		} 
	} else {
		alert("Neodgovarajuci parametri u url-u...");
		$("#bodyID").hide();
	}
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
					$("#bodyID").hide(); // bolje je mozda skociti na stranu za login
					window.location = "./index.html";
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
	$("#form-searchLekare").submit(function(event) {
		event.preventDefault();

		//
		var datum = $('input[name="datumPregleda"]').val();	// preuzima datum kao YYYY-MM-DD
		datum = datum + "T00:00";
		tipPregleda =  $('#select-tipPregleda :selected').val();
		var imeLekara = $('input[name="imeLekara"]').val();
		var prezimeLekara = $('input[name="prezimeLekara"]').val();
		var ocenaVecaOd = $('#select-ocena :selected').val();
		
		
		$.post({
			url: 'klinika/pretraziLekarePrekoKlinike',
			data: JSON.stringify({klinikaID, datum, tipPregleda, imeLekara, prezimeLekara, ocenaVecaOd}),
			contentType: 'application/json',
			success: function(lekari) { // ovu funkciju mogu izbrisati, vec postoji dole, samo treba da zapamtim globalnu lekariSVI
				$("#divTabela").empty();
				if(lekari != undefined){
//					$("divTabela").empty();
					
					var tableUDivu = $("<table id='tabelaKlinika' class='display'> </table>");// ili mozda primer $("div").addClass("important"); / moze i ovako kako sam napisao
					
					var thead = $("<thead> </thead>");
					thead.append("<tr><th>Ime</th><th>Prezime</th><th>Ocena</th><th>Mogući termini</th><th>Odaberi</th>")
					
					var tbody = $("<tbody id='teloTabele'> </tbody>");
					
					for(var l of lekari){					
						var tr = $("<tr> </tr>")
						var tdIme = $("<td> </td>");
						tdIme.append(l.ime);
						var tdPrezime = $("<td> </td>");
						tdPrezime.append(l.prezime);
						var tdOcena = $("<td> </td>");
						tdOcena.append(l.ocena);
						var tdMoguciTermini = $("<td> </td>");

						var selectTermin = $('<select id="sel' + l.id + '"> </select>'); // id selecta izgleda : sel1, sel2, sel3,...
						for(var termin of l.moguciTermini){
							selectTermin.append('<option value="'+termin+'">'+termin+'</option>');	
						}
						tdMoguciTermini.append(selectTermin);
						
						var tdOdaberi = $("<td> </td>");
						var btn = $('<button>Odaberi</button>');
						var idSelectTaga = selectTermin.attr('id');
						btn.click(odaberiLekara(l.id, idSelectTaga)); // saljem id odabranog lekara i id select liste iz koje cu uzeti ono sto je selektovano
						tdOdaberi.append(btn);
						
						tr.append(tdIme).append(tdPrezime).append(tdOcena).append(tdMoguciTermini).append(tdOdaberi);
						tbody.append(tr);

					}
					
					tableUDivu.append(thead).append(tbody);
					$("#divTabela").append(tableUDivu);
					
					var table = $('#tabelaKlinika').dataTable({
				        "pagingType": "full_numbers",
				        select: false
				    });
				}
			},
            error: function(jqXhr, textStatus, errorThrown) {
            	console.log(errorThrown); //ako je unet datum pre danasnjeg
            	$("#divTabela").empty();
            	alert("Izabrani datum mora biti najranije sutra.");
            }
		});
	});
}

function odaberiLekara(idLekara, idSelecta) {
	
	return function() {
		var odabranTermin = $('#'+idSelecta+' :selected').val();
		window.location = "./PotvrdaPregleda.html?klinikaID=" + klinikaID + "&tipPregleda=" + tipPregleda + "&lekarID=" + idLekara + "&termin=" + odabranTermin;
	}
	
}