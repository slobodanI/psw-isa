$(document).ready(function(){
		
	$(".div-form-edit").hide();		
	$("#btn-prikazForme").click(function() {
		$(".div-form-edit").toggle();		
	});
	
	koJeUlogovan();
	
	izmenaPodataka();
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {	
				if (user.uloga == "Pacijent") {
S
				} else if (user.uloga == "AdministratorKlinickogCentra") {
//					window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
//					window.location = "./AdministratorKlinikeHome.html";
					id=user.id;
					dobaviPodatekOKlinici(user.id);
				} else if (user.uloga == "Lekar") {
					window.location = "./index.html";

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

function dobaviPodatekOKlinici(id) {
	$.get({
		url: 'klinika/getKlinikaAdmin/' + id,
		success: function(klinika) {
			
			$("#pacNaziv").empty();
			$("#pacAdresa").empty();
			$("#pacOpis").empty();


			$("#pacNaziv").append("Naziv: " + klinika.naziv);
			$("#pacAdresa").append("Adresa: " + klinika.adresa);
			$("#pacOpis").append("Opis: " + klinika.opis);

			
			//za formu za izmenu			
			$('input[name="naziv"]').val(klinika.naziv);
			$('input[name="adresa"]').val(klinika.adresa);
			$('input[name="opis"]').val(klinika.opis);

			
		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
}

function izmenaPodataka() {
	
	$("#form-EditKlinika").submit(function(event){
		event.preventDefault();
		
		var naziv = $('input[name="naziv"]').val();
		var adresa = $('input[name="adresa"]').val();
		var opis = $('input[name="opis"]').val();


				$.ajax({
					url: 'klinika/updateKlinika/' + id,
					type: 'PUT',
					data: JSON.stringify({naziv,adresa,opis}),
					contentType: 'application/json',
					success: function(pacijent){
						dobaviPodatekOKlinici(pacijent.id);
					},
					error: function(){
						alert("Neuspesno ste izmenii podatke");
					}
				});

			
		});
}


