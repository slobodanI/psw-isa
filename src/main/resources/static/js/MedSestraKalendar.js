$(document).ready(function(){
		
	koJeUlogovan();
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {
				if (user.uloga == "Pacijent") {
					window.location = "./index.html";
				} else if (user.uloga == "AdministratorKlinickogCentra") {
					window.location = "./index.html";
				} else if (user.uloga == "AdministratorKlinike") {
					window.location = "./index.html";
				} else if (user.uloga == "Lekar") {
					window.location = "./index.html";
				} else if (user.uloga == "MedicinskaSestra") {
//					window.location = "./index.html";
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