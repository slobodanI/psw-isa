$(document).ready(function(){
	koJeUlogovan();

	
});

function dobaviPodatke(id){
	
	$.get({
		url:'adminK/'+id,
		contentType : 'application/json',
		success : function(admin){
			if(admin != undefined){
				$("#ime").append("     "+ admin.ime);
				$("#prezime").append("     "+ admin.prezime);
				$("#klinika").append("     "+ admin.klinika.naziv);
				$("#email").append("     "+admin.email)
			}
			else{
				console.log("Nema tog administratora");
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
					dobaviPodatke(user.id);
					id = user.id;
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