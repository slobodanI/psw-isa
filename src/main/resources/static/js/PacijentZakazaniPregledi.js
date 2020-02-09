$(document).ready(function(){
		
	koJeUlogovan();
		
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {	
				if (user.uloga == "Pacijent") {
					dobaviZakazanePreglede(user.id);
					//pacijentID = user.id;
				} else if (user.uloga == "AdministratorKlinickogCentra") {
					window.location = "./index.html"
				} else if (user.uloga == "AdministratorKlinike") {
					window.location = "./index.html"
				} else if (user.uloga == "Lekar") {
					window.location = "./index.html"
				} else if (user.uloga == "MedicinskaSestra") {
					window.location = "./index.html"
				} else {
					console.log("NIKO NIJE ULOGOVAN");
					window.location = "./index.html";
				}
			}
			else {
				console.log("NIKO NIJE ULOGOVAN");
				window.location = "./index.html";
			}

		}
	});
}

function dobaviZakazanePreglede(pacijentID) {
	$.get({
		url: 'pregledi/zakazaniPregleda/' + pacijentID,
		success: function(pregledi) {
			if(pregledi.length != 0){
				for(var pregled of pregledi) {
					var li = $("<li> </li>");
					var ptag = $("<p> </p>");
					
					ptag.append("Datum i vreme OD: " + pregled.datumPregledaOD +  "<br>"); 
					ptag.append("Datum i vreme DO: " + pregled.datumPregledaDO +  "<br>"); 
					ptag.append("Sala: " + pregled.sala + "<br>");
					ptag.append("Lekar: " + pregled.lekar + "<br>");
					ptag.append("Tip pregleda: " + pregled.tipPregleda + "<br>");
					ptag.append("Originalna cena: " + pregled.cena + "<br>");
					ptag.append("Popust: " + pregled.popust + "<br>");
					
					var btn = $('<button>Otkaži</button>');
					btn.click(otkaziPregled(pregled.id));
					ptag.append(btn).append("<br>");
					
					li.append(ptag);
					
					$("#ul-listaPregleda").append(li).append("<hr>");
				}				
			}
			else {
				$("#h4-zakazaniPregledi").append(" NEMA IH");
			}
		}
	});
}

function otkaziPregled(pregledID) {
	return function() {
		$.ajax({
			url: 'pregledi/otkaziPregled/' + pregledID,
			type: 'PUT',
			success: function(pacijent){
				alert("Uspesno ste otkazali pregled");
				window.location = "./PacijentZakazaniPregledi.html";
			},
			error: function(){
				alert("Neuspesno ste otkazali pregled, pregled se može otkazati najkasnije 24h pre početka pregleda");
			}
		});
	}
}
