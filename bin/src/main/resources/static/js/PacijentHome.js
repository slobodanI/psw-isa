$(document).ready(function(){
	
	$("#btn-mojProfil").click(function(event){
		window.location = "./PacijentProfil.html";
	});
	
	$("#btn-zdravstveniKarton").click(function(event){
		window.location = "./ZdravstveniKarton.html";
	});
	
	$("#btn-listaPregledaIOperacija").click(function(event){
		window.location = "./PacijentObavljeniPreglediIOperacije.html";
	});
	
	prikaziKlinike();
	
});

function prikaziKlinike() {
	$.get({
		url: 'klinika/all',
		success : function(klinike) {
			izlistajKlinike(klinike);
		},
		error : function() {
			alert("Neuspesno dobavljeni podaci o klinikama");
		}
	});
}

function izlistajKlinike(klinike) {
	if(klinike.length != 0) {
		for(var k of klinike) {
			var li = $("<li> </li>");
			var ptag = $("<p> </p>");
			
//			dodati link ka profilu klinike
			var btn = $('<button>Profil klinike</button>');
			btn.click(goToKlinika(k.id));
			ptag.append(btn).append("<br>");
			
			ptag.append("Naziv klinike: " + k.naziv + "<br>");
			ptag.append("Ocena klinike: " + k.ocena + "<br>")
			ptag.append("Opis klinike: " + k.opis + "<br>");
			ptag.append("Adresa klinike: " + k.adresa + "<br>");

			ptag.append("Lekari klinike: <br>");
			var count = 1;
			for(var l of k.lekari) {
				ptag.append("___Lekar " + count + ": " + l.ime + " " + l.prezime + ", ocena: " + l.ocena + "<br>");
//				dodati link ka profilu lekara?
				count = count + 1;
			}
			
			li.append(ptag);
			
			$("#ul-listaKlinika").append(li).append("<hr>");
		}
	} else {
		$("#h3-listaKlinika").append(" - NEMA KLINIKA");
	}
}

function goToKlinika(id) {
	
	return function() {
		window.location = "./KlinikaProfil.html?klinikaID=" + id;
	}
}