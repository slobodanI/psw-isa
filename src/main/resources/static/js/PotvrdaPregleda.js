var params = new URL(location.href).searchParams;
var klinikaID = params.get("klinikaID");
var termin = params.get("termin");
var tipPregleda = params.get("tipPregleda"); // id tipa pregleda
var lekarID = params.get("lekarID");

$(document).ready(function(){
	
	koJeUlogovan();
	
	if(klinikaID != undefined || termin != undefined || tipPregleda != undefined || lekarID != undefined){
		if(klinikaID !== "" || termin !== "" || tipPregleda !== "" || lekarID !== ""){
			popuniPodatke();
		} else {
			logout();
		}
	} else {
		logout();
	}

	$("#btn-potvrda").click(function(){
		zakaziPregled();
	});
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {
					
				if (user.uloga == "Pacijent") {
					pacijentID = user.id;
				} else if (user.uloga == "AdministratorKlinickogCentra") {
//					window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
//					window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
//					window.location = "./LekarHome.html";
				} else if (user.uloga == "MedicinskaSestra") {
//					window.location = "./MedicinskaSestra.html";
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

function logout() {
	$.post({
		url: 'api/logOut',
		contentType : 'application/json',
		success: function() {		
			window.location = "./index.html";			
		},
		error: function() {		
			window.location = "./index.html";	
		}
	});
}

function popuniPodatke() {
	//dobavi naziv klinike
//	$.get({
//		url: 'klinika/' + klinikaID,
//		success: function(klinika) {
//			klinika.naziv
//		},
//		error: function() {
//			alert("NEuspešno dobavljeni podaci o pacijentu");
//		}
//	});
	
	//dobavi naziv tipa pregleda
	
//	$.get({
//		url: 'api/tipPregleda/' + tipPregleda,
//		success: function(tipPregleda) {
//			tipPregleda.naziv
//		},
//		error: function() {
//			alert("NEuspešno dobavljeni podaci o pacijentu");
//		}
//	});
	
	//dobavi ime i prezime lekara lekara
	//lekar sadrzi i kliniku i tip pregleda...
	$.get({
		url: 'api/lekar/' + lekarID,
		success: function(lekar) {
			$("#li-lekar").append(lekar.ime + " " +  lekar.prezime);
			
			//ako se izabrani tipPregleda i klinika ne poklapaju sa lekarom, logout i vrati na login stranicu
			if(lekar.klinika.id == klinikaID) {
				$("#li-klinika").append(lekar.klinika.naziv + ", " + lekar.klinika.adresa);
			} else {
				logout();
			}
			
			if(lekar.tipPregleda.id == tipPregleda) {
				$("#li-tipPregleda").append(lekar.tipPregleda.naziv);
			} else {
				logout();
			}
			
			$("#li-termin").append(termin);
		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
	
}

function zakaziPregled() {
	
	$.post({
		url: 'pregledi/zakaziPregled',
		data: JSON.stringify({lekarID, termin, pacijentID}),
		contentType: 'application/json',
		success: function(){
			alert("Uspešno ste zakazali pregled");
		},
		error: function(){
			alert("Već je zakazan pregled kod ovog lekara sa ovim terminom.");
		}
	});
}