var params = new URL(location.href).searchParams;
var klinikaID = params.get("klinikaID");
var datum = params.get("datum");
var tipPregleda = params.get("tipPregleda"); // id tipa pregleda

$(document).ready(function() {
	whoIsLoggedIn();
	
	popuniTabeluLekara();
	submitFormeZaPretragu(); // radim pretragu na frontu, ne saljem nista na backend
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
				window.location = "./index.html";
			}

		}
	});
}

function popuniTabeluLekara() {
	
	if(klinikaID != undefined || datum != undefined || tipPregleda != undefined){
		if(klinikaID !== "" || datum !== "" || tipPregleda !== ""){
			$.get({
				url: 'klinika/pretraziLekareOdabraneKlinike/' + klinikaID + '/datum/' + datum + '/idTipaPregleda/' + tipPregleda, 
//				data: JSON.stringify({klinikaID, datum, tipPregleda}),
//				contentType: 'application/json',
				success: function(lekari) { // ovu funkciju mogu izbrisati, vec postoji dole, samo treba da zapamtim globalnu lekariSVI
					$("#divTabela").empty();
					if(lekari != undefined){
						
						lekariSVI = lekari; // globalna promenljiva, sa njom cu vrsiti pretragu
						//a za sad ucitam sve lekare
//						$("divTabela").empty();
						
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
//		            console.log(errorThrown);
					alert("Neuspesno ucitani tipovi pregleda");
					$("#bodyID").hide();
		        }
			});
		} else {
			alert("Neodgovarajuci parametri u url-u...");
		}
	} else {
		alert("Nedostaju parametri u url-u...");
	}
	
}

function odaberiLekara(idLekara, idSelecta) {
	
	return function() {
		var odabranTermin = $('#'+idSelecta+' :selected').val();
		window.location = "./PotvrdaPregleda.html?klinikaID=" + klinikaID + "&tipPregleda=" + tipPregleda + "&lekarID=" + idLekara + "&termin=" + odabranTermin;
	}
	
}


function submitFormeZaPretragu() {
	$("#form-searchLekare").submit(function(event) {
		event.preventDefault();

		var imeLekara = $('input[name="imeLekara"]').val();
		var prezimeLekara = $('input[name="prezimeLekara"]').val();
		var ocenaVecaOd = $('input[name="ocenaLekara"]').val();
		
		var lekariZaPrikaz = [];
		var vecIscrtano = false;
		
		if(imeLekara === "" && prezimeLekara === "" && ocenaVecaOd === ""){
			iscrtajTabeluLekara(lekariSVI);
			vecIscrtano = true;
		}
		
		if(vecIscrtano == false){
			for(var lekar of lekariSVI) {
				var flag = true;
				
				if(imeLekara !== "") {
					var daLiSadrziIme = lekar.ime.includes(imeLekara);
					if(daLiSadrziIme == false){
						flag = false;
						continue;
					}
				}
				
				if(prezimeLekara !== "") {
					var daLiSadrziPrezime = lekar.prezime.includes(prezimeLekara);
					if(daLiSadrziPrezime == false){
						flag = false;
						continue;
					}
				}
				
				if(ocenaVecaOd != "") {
					if(lekar.ocena < ocenaVecaOd){
						flag = false;
						continue;
					}
				}
				
				if(flag == true){
					lekariZaPrikaz.push(lekar);
				}
				
			}
			
			iscrtajTabeluLekara(lekariZaPrikaz);
		}
		
		
	});
}

function iscrtajTabeluLekara(lekari) {
	$("#divTabela").empty();
	if(lekari != undefined){
//		$("divTabela").empty();
		
		var tableUDivu = $("<table id='tabelaKlinika' class='display'> </table>");
		
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
}


