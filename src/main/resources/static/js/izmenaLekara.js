$(document).ready(function(){

	koJeUlogovan();
	$("#izmenaPodataka").submit(function(event){
		event.preventDefault();
		
	
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input[name="confirmPassword"]').val();
		
		
		if(password == confirmPassword){
			if(password.length >= 6){
				$.ajax({
					url: 'api/lekar/updateLekar',
					type: 'PUT',
					data: JSON.stringify({ime,prezime,password,id}),
					contentType: 'application/json',
					success: function(){
					alert("Uspešno ste izmenili podatke.");
					window.location = "./profilLekara.html"
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
//					window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
//					window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
					dobaviPodatkeOLekaru(user.id);
					id = user.id;
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




function dobaviPodatkeOLekaru(id) {
	$.get({
		url: 'api/lekar/' + id,
		success: function(lekar) {
			
			//za formu za izmenu			
			$('input[name="ime"]').val(lekar.ime);
			$('input[name="prezime"]').val(lekar.prezime);
			//$('input[name="password"]').val(lekar.password);
			//$('input[name="confirmPassword"]').val(lekar.password);
			var id = lekar.id;
			var username = lekar.username;
			

		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
}
