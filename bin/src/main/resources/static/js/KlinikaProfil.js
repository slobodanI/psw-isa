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