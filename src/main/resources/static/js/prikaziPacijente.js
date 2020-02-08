	$(document).ready(function(){
//		var id;
//		$.get({
//			url : 'api/whoIsLoggedIn',
//			success : function(user) {
//				if (user != undefined) {	
//					if (user.uloga == "Pacijent") {
////						dobaviPodatkeOPacijentu(user.id);
////						id = user.id;
//					} else if (user.uloga == "AdministratorKlinickogCentra") {
////						window.location = "./AdminKlinickogCentraHome.html";
//					} else if (user.uloga == "AdministratorKlinike") {
////						window.location = "./AdministratorKlinikeHome.html";
//
//					} else if (user.uloga == "Lekar") {
////						window.location = "./LekarHome.html";
////						id=user.klinika;
//						id=user.id;
//						popuniTabelu(id);
//					} else if (user.uloga == "MedicinskaSestra") {
////						window.location = "./MedicinskaSestra.html";
//					} else {
//						console.log("NIKO NIJE ULOGOVAN");
//					}
//				}
//				else {
//					console.log("NIKO NIJE ULOGOVAN");
//				}
//
//			}
//		});
	//	popuniTabelu(id);
		
		koJeUlogovan();
		submitForme();
		
			
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
						popuniTabelu(idUsera);
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
				pacijentiSVI=pacijenti;
				$("#divTabela").empty();
				if(pacijenti != undefined){
					if(pacijenti.length != 0){
						iscrtajTabelu(pacijenti);
					}
					
				}else{
					$(".nemaPacijenata").append("Nema pacijenata na ovoj klinici");
			}
			
		}
			
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
	
function profil(id){
	return function(){
		window.location ='./PacijentProfil.html?pacijenti='+id;
	}
}	
	
	

function iscrtajTabelu(pacijenti){

		$("#divTabela").empty();
		if(pacijenti != undefined){
			var tableUDivu =$("<table id='tabelaPacijenata' class = 'display'></table>");
			
			
			var thead = $("<thead> </thead>");
			thead.append("<tr><th>Ime</th><th>Prezime</th><th>Lbo</th><th>Profil</th>");
			
			var tbody = $("<tbody id='teloTabele'> </tbody>");
			
			for(var p of pacijenti){
				var tr = $("<tr> </tr>");
				var tdIme = $("<td></td>");
				tdIme.append(p.ime);
				var tdPrezime = $("<td> </td>");
				tdPrezime.append(p.prezime);
				var tdLbo = $("<td> </td>");
				tdLbo.append(p.lbo);
				
				var tdProfil = $("<td> </td>");
				var btn = $('<button>Profil</button>');
				btn.click(profil(p.id));
				tdProfil.append(btn);
				
				tr.append(tdIme).append(tdPrezime).append(tdLbo).append(tdProfil);
				tbody.append(tr);
			}
			tableUDivu.append(thead).append(tbody);
			$("#divTabela").append(tableUDivu);
			
			var table = $('#tabelaPacijenata').dataTable({
		        "pagingType": "full_numbers",
		     		
			
		});
		}
	}
	
	
	
	
function submitForme(){
	$("#formPacijent").submit(function(event) {
		event.preventDefault();
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var lbo = $('input[name="lbo"]').val();
		
		var pacijentiZaPrikaz = [];
		var vecIscrtano = false;
		
		if(ime === "" && prezime ==="" && lbo === null){
			iscrtajTabelu(pacijentiSVI);
			alert("aaa");
			vecIscrtano = true;
			
		}
		
		if(vecIscrtano == false){
			for(var pacijent of pacijentiSVI){
				var flag = true;
				
				if(ime != ""){
					var daliSadrziIme = pacijent.ime.includes(ime);
					if(daliSadrziIme == false){
						flag = false;
						continue;
					}
				}
				if(prezime != ""){
					var daliSadrziPrezime = pacijent.prezime.includes(prezime);
					if(daliSadrziPrezime == false){
						flag = false;
						continue;
					}
				}
				
				if(lbo != null){
										
					if(pacijent.lbo != lbo){
						flag = false;
						continue;
					}
				}
				if(flag == true){
					pacijentiZaPrikaz.push(pacijent);
				}
				
			}
			iscrtajTabelu(pacijentiZaPrikaz);
		}
		
	
	});
	
	
	
}	
	