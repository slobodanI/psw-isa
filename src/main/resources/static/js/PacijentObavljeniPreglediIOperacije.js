//ovo ce student 2 da koristi
var params = new URL(location.href).searchParams;
var oglasName = params.get("oglas"); 

$(document).ready(function(){
	
	koJeUlogovan();
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {	
				if (user.uloga == "Pacijent") {
					ucitajPodatke(user.id);
				} else if (user.uloga == "AdministratorKlinickogCentra") {

				} else if (user.uloga == "AdministratorKlinike") {

				} else if (user.uloga == "Lekar") {

				} else if (user.uloga == "MedicinskaSestra") {

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

function ucitajPodatke(idPacijenta) {
	
	$.get({
		url : 'zdravstveniKarton/' + idPacijenta,
		success : function(zk) {
			izlistajListuObavljenihPregleda(zk.listaPregleda);
			izlistajListuObavljenihOperacija(zk.listaOperacija);
		},
		error : function() {
			alert("Neuspesno dobavljeni podaci iz zdravstvenog kartona");
		}
	});
	
}

function izlistajListuObavljenihPregleda(pregledi) {
	if(pregledi.length != 0) {
		for(var p of pregledi) {
			var li = $("<li> </li>");
			var ptag = $("<p> </p>");
			ptag.append("Id pregleda: " + p.id + "<br>");
			ptag.append("Tip pregleda: " + p.tipPregleda + "<br>")
			ptag.append("Informacije: " + p.informacije + "<br>");
			
			if(p.dijagnoza != null){
				ptag.append("Dijagnoza: " + p.dijagnoza.naziv + "<br>");
			} else {
				ptag.append("Dijagnoza: Nema dijagnoze <br>");
			}
			ptag.append("Datum pregleda: " + p.datumPregleda + "<br>");
			
			ptag.append("Ime Lekara: " + p.lekar.ime + "<br>");
			ptag.append("Prezime Lekara: " + p.lekar.prezime + "<br>");
			ptag.append("Ocena Lekara: " + p.lekar.ocena + "<br>");
			
			li.append(ptag);
			
			$("#ul-listaPregleda").append(li).append("<hr>");
		}
	} else {
		$("#h3-listaPregleda").append(" - NEMATE OBAVLJENIH PREGLEDA");
	}
}

function izlistajListuObavljenihOperacija(operacije) {
	if(operacije.length != 0) {
		for(var o of operacije) {
			var li = $("<li> </li>");
			var ptag = $("<p> </p>");
			ptag.append("Id operacije: " + o.id + "<br>");
			ptag.append("Informacije: " + o.informacije + "<br>");
			ptag.append("Datum operacije: " + o.datumVreme + "<br>");			
			ptag.append("Cena operacije: " + o.cena + "<br>");
			
			ptag.append("Lekari: <br>");
			var count = 1;
			for(var l of o.lekari) {
				ptag.append("___Lekar " + count + ": " + l.ime + " " + l.prezime + ", ocena: " + l.ocena + "<br>");
				count = count + 1;
			}
			
			
			li.append(ptag);
			
			$("#ul-listaOperacija").append(li).append("<hr>");
		}
	} else {
		$("#h3-listOperacija").append(" - NEMATE OBAVLJENIH OPERACIJA");
	}
}
