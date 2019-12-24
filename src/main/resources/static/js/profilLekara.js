$(document).ready(function(){
	koJeUlogovan();
	$("#logout").click(function() {
		logOut();
	});

	
});

function dobaviPodatke(id){
	
	$.get({
		url:'api/lekar/'+id,
		contentType : 'application/json',
		success : function(lekar){
			if(lekar != undefined){
				var oc=lekar.ukupnaOcena/lekar.brojOcena;
				oc=oc.toFixed(2);
				$("#ime").append("     "+ lekar.ime);
				$("#prezime").append("     "+ lekar.prezime);
				$("#ocena").append("     "+ oc);
				$("#klinika").append("     "+ lekar.klinika.naziv);
				$("#radnoVreme").append("     "+lekar.radnoVremeOd).append(":").append(lekar.radnoVremeDo)
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
function logOut() {
	$.post({
		url : 'api/logOut',
		success : function() {
			//alert("Uspešno ste se odjavili");
			window.location = "./index.html";
		},
		error : function(jqXhr, textStatus, errorMessage) {
			console.log("Error: ", errorMessage);
		}

	});
}