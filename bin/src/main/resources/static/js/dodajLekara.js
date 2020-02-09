$(document).ready(function(){
	
	koJeUlogovan();
	
	$("#formaZaNovogLekara").submit(function(event){
		event.preventDefault();
		
		var username = $('input[name="username"]').val();
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input[name="confirmPassword"]').val();
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var radnoVremeOd = $('input[name="radnoVremeOd"]').val();
		var radnoVremeDo = $('input[name="radnoVremeDo"]').val();
		var uloga = "Lekar";
		var radniKalendar = "radniKalendar";
		var brojOcena=0;
		var ukupnaOcena=0;
		var idKlinike=1;
		if(password == confirmPassword){
		$.post({
			url: '/api/lekar/saveLekar',
			data: JSON.stringify({username,password,ime,prezime,radnoVremeOd,radnoVremeDo,idKlinike,uloga,radniKalendar,ukupnaOcena,brojOcena}),
			contentType: 'application/json',
			success: function(){
			alert("Uspešno ste dodali lekara.");
			window.location = "./AdministratorKlinikeHome.html"
			},
			error: function(){
				alert("Korisnik sa ovim username već postoji.");
			}	
		});
		}
		else{
			alert("Unete lozinke nisu iste");
		}
			
		});	
		

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
//					window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
//					window.location = "./AdministratorKlinikeHome.html";
					idUsera=user.id;
					dobaviPodatkeOAdminu(idUsera);

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

function dobaviPodatkeOAdminu(id){
	$.get({
		url: 'adminK/' + idUsera,
		success: function(admin){
			var idKlinike=admin.idKlinike;
		}
		
	});
}

