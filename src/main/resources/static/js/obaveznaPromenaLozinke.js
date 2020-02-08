
$(document).ready(function(){

	koJeUlogovan();
	$("#izmenaPodataka").submit(function(event){
		event.preventDefault();
		
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input[name="confirmPassword"]').val();
		
		
		if(password == confirmPassword){
			if(password.length >= 6){
				$.ajax({
					url: 'api/lekar/updateLekar',
					type: 'PUT',
					data: JSON.stringify({password,id,ime,prezime}),
					contentType: 'application/json',
					success: function(){
					alert("Uspešno ste izmenili podatke.");
					window.location = "./index.html"
					},
				
					error: function(){
						alert("Greska pri izmeni podataka");
					}	
				
					
				});
			}
			else {
				alert("Sifra mora sadržati makar 6 karaktera.");
			}
		}
		else{
			alert("Unete lozinke nisu iste");
		}
	
	});
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {	
				if (user.uloga == "Pacijent") {

				} else if (user.uloga == "AdministratorKlinickogCentra") {
					id = user.id;
//					window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
					id = user.id;
//					window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
					dobaviPodatkeOLekaru(user.id);
					$.post({
						url : 'api/logOut',
						success : function() {
							//alert("Uspešno ste se odjavili");
						},
						error : function(jqXhr, textStatus, errorMessage) {
							console.log("Error: ", errorMessage);
						}

					});
					
					id = user.id;
//					window.location = "./LekarHome.html";
				} else if (user.uloga == "MedicinskaSestra") {
					id = user.id;
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

function dobaviPodatkeOLekaru(id) {
	$.get({
		url: 'api/lekar/' + id,
		success: function(lekar) {
			
			//za formu za izmenu
			ime=lekar.ime;
			id = lekar.id;
			prezime=lekar.prezime;

		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
}