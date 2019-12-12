$(document).ready(function(){
	koJeUlogovan();

	
});

function dobaviPodatke(id){
	
	$.get({
		url:'api/lekar/'+id,
		contentType : 'application/json',
		success : function(lekar){
			if(lekar != undefined){
				var oc=lekar.ukupnaOcena/lekar.brojOcena
				$("#ime").append("     "+ lekar.ime);
				$("#prezime").append("     "+ lekar.prezime);
				$("#ocena").append("     "+ oc);
				$("#klinika").append("     "+ lekar.klinika.naziv);
				$("#radnoVreme").append("     "+lekar.radnoVreme)
			}
			else{
				console.log("Nema tog lekara");
			}
		}
		
	});
	
	
	
}

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {	
				if (user.uloga == "Pacijent") {

				} else if (user.uloga == "AdministratorKlinickogCentra") {
//					window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
//					window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
					dobaviPodatke(user.id);
					id = user.id;
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