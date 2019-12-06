$(document).ready(function(){

	koJeUlogovan();
	$("#izmenaPodataka").submit(function(event){
		
		
	
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input[name="confirmPassword"]').val();
		var email = $('input[name="email"]').val();
		
		if(password == confirmPassword){
			if(password.length >= 6){
				$.ajax({
					url: 'adminK/updateAdminK',
					type: 'PUT',
					data: JSON.stringify({ime,prezime,password,email,id}),
					contentType: 'application/json',
					success: function(){
					alert("Uspešno ste izmenili podatke.");
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
					dobaviPodatkeOAdminu(user.id);
					id = user.id;
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

function dobaviPodatkeOAdminu(id) {
	$.get({
		url: 'adminK/' + id,
		success: function(admin) {
			
			//za formu za izmenu			
			$('input[name="ime"]').val(admin.ime);
			$('input[name="prezime"]').val(admin.prezime);
			$('input[name="email"]').val(amdin.email);
			//$('input[name="confirmPassword"]').val(lekar.password);
			var id = lekar.id;
			var username = lekar.username;
			

		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
}
