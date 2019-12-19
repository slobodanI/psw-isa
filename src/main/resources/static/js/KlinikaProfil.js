var params = new URL(location.href).searchParams;
var klinikaID = params.get("klinikaID");

$(document).ready(function(){
		
	koJeUlogovan();
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {	
//				ostavicu ovake jer ne znam ko sve treba da gleda ovu stranicu
				if (user.uloga == "Pacijent") {
					popuniPodatke(klinikaID);
					dobaviPredefinisanePreglede(klinikaID);
					pacijentID = user.id;
				} else if (user.uloga == "AdministratorKlinickogCentra") {
					
				} else if (user.uloga == "AdministratorKlinike") {
//					window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
//					window.location = "./LekarHome.html";
				} else if (user.uloga == "MedicinskaSestra") {
//					window.location = "./MedicinskaSestra.html";
				} else {
					console.log("NIKO NIJE ULOGOVAN");
				}
			}
			else {
				console.log("NIKO NIJE ULOGOVAN");
			}

		}
	});
}

function popuniPodatke(klinikaID) {
	$.get({
		url: 'klinika/' + klinikaID,
		success: function(klinika) {							
			$("#klinikaNaziv").append(klinika.naziv);
			$("#opis").append("Opis: " + klinika.opis);
			$("#ocena").append("Ocena: " + klinika.ocena);
			$("#adresa").append("Adresa: " + klinika.adresa);
			
//			za lekare
			var ptag = $("<p> </p>");
			
			ptag.append("Lekari klinike: <br>");
			var count = 1;
			for(var l of klinika.lekari) {
				ptag.append("___Lekar " + count + ": " + l.ime + " " + l.prezime + ", ocena: " + l.ocena + "<br>");
//				dodati link ka profilu lekara?
				count = count + 1;
			}
					
			$("#lekari").append(ptag);
					
		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o klinici");
		}
	});
}

function dobaviPredefinisanePreglede(klinikaID) {
	$.get({
		url: 'pregledi/predefPregledi/' + klinikaID,
		success: function(pregledi) {
			if(pregledi.length != 0){
				for(var pregled of pregledi) {
					var li = $("<li> </li>");
					var ptag = $("<p> </p>");
					
					ptag.append("Datum i vreme: " + pregled.datumPregleda + " " + pregled.satnica + "<br>"); // OVO CE SE MENJATI, BICE SAMO JEDNO POLJE a ne i datum i vreme
					ptag.append("Sala: " + pregled.sala + "<br>");
					ptag.append("Lekar: " + pregled.lekar + "<br>");
					ptag.append("Tip pregleda: " + pregled.tipPregleda + "<br>");
					ptag.append("Originalna cena: " + pregled.cena + "<br>");
					ptag.append("Popust: " + pregled.popust + "<br>");
					
					var btn = $('<button>Zakaži</button>');
					btn.click(zakaziPregled(pregled.id));
					ptag.append(btn).append("<br>");
					
					li.append(ptag);
					
					$("#ul-listaPregleda").append(li).append("<hr>");
				}				
			}
			else {
				$("#h4-predefPregledi").append(" NEMA IH");
			}
		}
	});
}

function zakaziPregled(pregledID) {
	return function() {
		$.ajax({
			url: 'pregledi/zakaziPredefPregled/' + pregledID + '/pacijent/' + pacijentID,
			type: 'PUT',
			success: function(pacijent){
				alert("Uspesno ste zakazali pregled");
				window.location = "./KlinikaProfil.html?klinikaID=" + klinikaID;
			},
			error: function(){
				alert("Neuspesno ste zakazali pregled");
			}
		});
	}
}