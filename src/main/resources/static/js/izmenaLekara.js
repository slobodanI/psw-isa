$(document).ready(function(){
	
	$("#izmenaPodataka").submit(function(event){
	
		var ime = $('input=[name="ime"]').val();
		var prezime = $('input=[name="prezime"]').val();
		var password = $('input=[name="password"]').val();
	
		$.post({
			url: '/api/updateLekar',
			data: JSON.stringify({ime,prezime,password,lekar.username,lekar.password}),
			contentType: 'application/json',
			success: function(){
			alert("Uspešno ste izmenili podatke.");
			},
		
			error: function(){
				alert("Greska pri izmeni podataka");
			}	
		
			
		});
	
	
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
				}
			}
			else {
				console.log("NIKO NIJE ULOGOVAN");
			}

		}
	});
}




function dobaviPodatkeOPacijentu(id) {
	$.get({
		url: 'lekar/' + id,
		success: function(lekar) {
			
			//za formu za izmenu			
			$('input[name="ime"]').val(lekar.ime);
			$('input[name="prezime"]').val(lekar.prezime);
			$('input[name="password"]').val(lekar.password);
			$('input[name="confirmPassword"]').val(lekar.password);

			
		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
}
