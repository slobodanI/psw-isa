var userId;

$(document).ready(function(){
	
	koJeUlogovan();
	

});


function koJeUlogovan(){
		$.get({
			url : 'api/whoIsLoggedIn',
			success : function(user) {
				if (user != undefined) {	
					if (user.uloga == "Pacijent") {
//						dobaviPodatkeOPacijentu(user.id);
//						id = user.id;
					} else if (user.uloga == "AdministratorKlinickogCentra") {
//						window.location = "./AdminKlinickogCentraHome.html";
					} else if (user.uloga == "AdministratorKlinike") {
//						window.location = "./AdministratorKlinikeHome.html";
						id=user.klinika;
						userId=user.id;
						popuniTabelu(userId);
					} else if (user.uloga == "Lekar") {
//						window.location = "./LekarHome.html";

					} else if (user.uloga == "MedicinskaSestra") {
//						window.location = "./MedicinskaSestra.html";
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


function popuniTabelu(userId){
	$.get({
		
		url: 'pregledi/prikaziZahteve/'+userId,
		contentType: 'application/json',
		success: function(pregledi){
			$("#tabela tbody").empty();
			if(pregledi != undefined){
				if(pregledi.length != 0){
				for(let p of pregledi){
					addPregled(p);
				}
			}else{
				$(".nemaZahteva").append("Trenutno nema zahteva za pregled");
			}
			
			
		}else{
			$(".nemaZahteva").append("Trenutno nema zahteva za pregled");
		}
		},
	});
}

function addPregled(p){
	let tr=$('<tr></tr>');
	let tdId=$('<td>'+p.id+'</td>');
	let tdPocetak=$('<td>'+p.datumPregledaOd+'</td>');
	let tdKraj=$('<td>'+p.datumPregledaDo+'</td>');
	let tdDodajSalu=$('<td class="sala"><a href="dodelaSalePregledu.html?idPregleda='+p.id+'">Dodaj salu pregledu</a></td>');

	tr.append(tdId).append(tdPocetak).append(tdKraj).append(tdDodajSalu);
	$('#tabela tbody').append(tr);

}








