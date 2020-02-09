$(document).ready(function() {
	
	$('#btn-register').click(function(event) {
		window.location = "./registracija.html";
	});
	
	koJeUlogovan();
	
	$('#logInForma').submit(function(event) {
		event.preventDefault();
		var email = $('input[name="email"]').val();
		var password = $('input[name="password"]').val();
//		$('#error').hide();
		
		
		$.post({
			url: 'api/logIn',														
			data: JSON.stringify({email, password}),
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
				//	window.location = "./AdminKlinickogCentraHome.html";
					proveriAKC(user.id);
				} else if (user.uloga == "AdministratorKlinike") {
					proveriAK(user.id);
			//		window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
					proveriLekara(user.id);
				//	window.location = "./profilLekara.html";
				} else if (user.uloga == "MedicinskaSestra") {
					proveriMS(user.id)
				//	window.location = "./MedicinskaSestraHome.html";
				} else {
					console.log("NIKO NIJE ULOGOVAN");
				//	window.location = "./index.html";
				}
				
			} else {
				console.log("NIKO NIJE ULOGOVAN");
			//	window.location = "./index.html";
			}

		}
	});
}

function proveriMS(id){
	
	$.get({
		url: 'medicinska_sestra/promenjenaLozinka/'+id,
		success: function(tacno){
			if(tacno != undefined){
				if(tacno == false){
					window.location="./obaveznaPromenaLozinkeMS.html";
				}
				else{
					window.location = "./MedicinskaSestraHome.html";
				}
			}
		}
		
	});

}

function proveriAK(id){
	
	$.get({
		url: 'adminK/promenjenaLozinka/'+id,
		success: function(tacno){
			if(tacno != undefined){
				if(tacno == false){
					window.location="./obaveznaPromenaLozinkeAK.html";
				}
				else{
					window.location = "./AdministratorKlinikeHome.html";
				}
			}
		}
		
	});

}
function proveriAKC(id){
	
	$.get({
		url: 'adminKC/promenjenaLozinka/'+id,
		success: function(tacno){
			if(tacno != undefined){
				if(tacno == false){
					window.location="./obaveznaPromenaLozinkeAKC.html";
				}
				else{
					window.location = "./AdminKlinickogCentraHome.html";
				}
			}
		}
		
	});

}
function proveriLekara(id){
	
	$.get({
		url: '/api/lekar/promenjenaLozinka/'+id,
		success: function(tacno){
			if(tacno != undefined){
				if(tacno == false){
					window.location="./obaveznaPromenaLozinke.html";
				}
				else{
					window.location = "./profilLekara.html";
				}
			}
		}
		
	});

}

