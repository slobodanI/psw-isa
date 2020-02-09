var id;
$(document).ready(function(){
		koJeUlogovan();
		popuniTabelu();
		
		$("#obrisiTipPregleda").submit(function(event){
			event.preventDefault();
			
			var id = $('input[name="id"]').val();
			
			
			
			$.ajax({
				url: '/api/tipPregleda/delete/'+id,
				data: JSON.stringify({id}),
				contentType: 'application/json',
				type: 'DELETE',
				success: function(){
				window.location="./prikaziTipovePregleda.html";
				alert("Uspešno ste obrisali tip pregleda");
				},
				error: function(){
					alert("Greska pri brisanju tip pregleda");
				}	
			});
					
			});
		$("#form-EditCena").submit(function(event){
			event.preventDefault();
			
			var cena = $('input[name="cena"]').val();


					$.ajax({
						url: '/api/tipPregleda/addCenu',
						type: 'POST',
						data: JSON.stringify({cena,admin,tipPregleda}),
						contentType: 'application/json',
						success: function(){
							window.location = "./prikaziTipovePregleda.html"
						},
						error: function(){
							alert("Neuspesno ste izmenii podatke");
						}
					});

				
			});
			
	});



function popuniTabelu(){
	$.get({
		
		url: '/api/tipPregleda/tipoviPregleda',
		success: function(tipoviPregleda){
			$("#tabela tbody").empty();
			if(tipoviPregleda != undefined){
				if(tipoviPregleda.length != 0){
					for(let t of tipoviPregleda){
					addSalu(t);
				}
				
			}else{
				$(".nemaSala").append("Nema tipova pregleda na ovoj klinici");
		}
		}else{
			$(".nemaSala").append("Nema tipova pregleda na ovoj klinici");
		}
	},
		
		});	
}


function addSalu(t){
	let tr=$('<tr></tr>');
	let tdNaziv=$('<td>'+t.naziv+'</td>');
	let tdBroj=$('<td>'+t.id+'</td>');
	let tdCena=$('<button onclick = "cena('+t.id+')">Izmeni cenu</button>');

	tr.append(tdNaziv).append(tdBroj).append(tdCena);
	$('#tabela tbody').append(tr);
	
}

function cena(idTP){
	
	 admin = id;
	 tipPregleda = idTP;
	$.ajax({
		
		url: '/api/tipPregleda/getCenu',
		data: JSON.stringify({cena,admin,tipPregleda}),
		type: 'PUT',
		contentType: 'application/json',
		success: function(c){
			if(c != undefined){
				$('input[name="cena"]').val(c);
			}
	},
		
		});	
	
	
	

}

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

