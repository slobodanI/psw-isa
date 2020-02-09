$(document).ready(function() {
	
	$('#btn-register').click(function(event) {
		window.location = "./registracija.html";
	});
	
	$('#logInForma').submit(function(event) {
		event.preventDefault();
		var username = $('input[name="username"]').val();
		var password = $('input[name="password"]').val();
//		$('#error').hide();
		
		
		$.post({
			url: 'api/logIn',														
			data: JSON.stringify({username, password}),
			contentType: 'application/json',
			success: function() {
//				$('#success').text('Novi proizvod uspešno kreiran.');
//				$("#success").show().delay(3000).fadeOut();		
				
//				window.location = "./pocetnaPacijent.html";
				koJeUlogovan();
				
			},
			error: function() {
				alert("Neuspešno ste se prijavili");
			}
		});
	});
	
});


function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
//		contentType : 'application/json',
		success : function(user) {
			if (user != undefined) {
				if (user.uloga == "Pacijent") {
					window.location = "./PacijentHome.html";
				} else if (user.uloga == "AdministratorKlinickogCentra") {
					window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
					window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
					window.location = "./profilLekara.html";
				} else if (user.uloga == "MedicinskaSestra") {
					window.location = "./MedicinskaSestraHome.html";
				} else {
					console.log("NIKO NIJE ULOGOVAN");
				}
				
			} else {
				console.log("NIKO NIJE ULOGOVAN");
			}

		}
	});
}