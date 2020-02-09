	$(document).ready(function(){
		var id;
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

					} else if (user.uloga == "Lekar") {
//						window.location = "./LekarHome.html";
//						id=user.klinika;
						id=user.id;
						popuniTabelu(id);
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
	//	popuniTabelu(id);
		

			
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

					} else if (user.uloga == "Lekar") {
//						window.location = "./LekarHome.html";
						id=user.klinika;
						idUsera=user.id;
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
	
	function popuniTabelu(id){
		$.get({
			
			url: 'pacijent/vratiPacijenteLekaraKlinike/'+id,
			contentType: 'application/json',
			success: function(pacijenti){
				$("#tabela tbody").empty();
				if(pacijenti != undefined){
					if(pacijenti.length != 0){
						for(let p of pacijenti){
						addPacijent(p);
					}
					
				}else{
					$(".nemaLekara").append("Nema pacijenata na ovoj klinici");
			}
			}else{
				$(".nemaLekara").append("Nema pacijenata na ovoj klinici");
			}
		},
			
			});	
	}
	function addPacijent(p){
		let tr=$('<tr></tr>');
		let tdIme=$('<td>'+p.ime+'</td>');
		let tdPrezime=$('<td>'+p.prezime+'</td>');
		let tdEmail=$('<td>'+p.email+'</td>')
		let tdAdresa = $('<td>'+p.adresa+'</td>');
		let tdGrad = $('<td>'+p.grad+'</td>');
		let tdDrzava = $('<td>'+p.drzava+'</td>');
		let tdBrojTel = $('<td>'+p.brojTel+'</td>');
		let tdLbo = $('<td>'+p.lbo+'</td>');
		let tdProfil = $('<td class="profil"><a href="PacijentProfil.html?pacijenti='+p.id+'">Profil pacijenta</a></td>');
		

		tr.append(tdIme).append(tdPrezime).append(tdEmail).append(tdAdresa).append(tdGrad).append(tdDrzava).append(tdBrojTel).append(tdLbo).append(tdProfil);
		$('#tabela tbody').append(tr);

		
	}
	