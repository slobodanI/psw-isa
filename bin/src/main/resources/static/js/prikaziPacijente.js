	$(document).ready(function(){

		popuniTabelu();
		

			
	});
	
	
	
	function koJeUlogovan(){
		$.get({
			url : 'api/whoIsLoggedIn',
			success : function(user) {
				if (user != undefined) {	
					if (user.uloga == "Pacijent") {
						dobaviPodatkeOPacijentu(user.id);
						id = user.id;
					} else if (user.uloga == "AdministratorKlinickogCentra") {
//						window.location = "./AdminKlinickogCentraHome.html";
					} else if (user.uloga == "AdministratorKlinike") {
//						window.location = "./AdministratorKlinikeHome.html";

					} else if (user.uloga == "Lekar") {
//						window.location = "./LekarHome.html";
						idUsera=user.id;
					} else if (user.uloga == "MedicinskaSestra") {
//						window.location = "./MedicinskaSestra.html";
					} else {
						console.log("NIKO NIJE ULOGOVAN");
					}
				}
				else {
					console.log("NIKO NIJE ULOGOVAN");
				}

			}
		});