$(document).ready(function(){
	koJeUlogovan();
	
	$("#formaZaNovuSalu").submit(function(event){
		event.preventDefault();
		
		var naziv = $('input[name="naziv"]').val();
		var zauzetost="slobodna";
		var idKlinike=1;
		
		$.post({
			url: '/api/sala/addSalu',
			data: JSON.stringify({naziv,zauzetost,idKlinike}),
			contentType: 'application/json',
			success: function(){
			alert("Uspešno ste dodali salu.");
			},
			error: function(){
				alert("Greska pri dodavanju sale");
			}	
		});
		

			
		});
		
		
		$("#obrisiSalu").submit(function(event){
			event.preventDefault();
			
			var id = $('input[name="id"]').val();
			
			
			
			$.post({
				url: '/api/sala/delete/'+id,
				data: JSON.stringify({id}),
				contentType: 'application/json',
				success: function(){
				alert("Uspešno ste dodali salu.");
				},
				error: function(){
					alert("Greska pri dodavanju sale");
				}	
			});
			

				
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



