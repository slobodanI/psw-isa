$(document).ready(function() {
	koJeUlogovan()

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
						popuniTabelu2(userId);
					} else if (user.uloga == "Lekar") {
//						window.location = "./LekarHome.html";

					} else if (user.uloga == "MedicinskaSestra") {
//						window.location = "./MedicinskaSestra.html";
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

function popuniTabelu(userId){
	$.get({
		
		url: 'lekarOdsustvo/vratiZahteveZaOdsustva/'+userId,
		contentType: 'application/json',
		success: function(odsustva){
			$("#tabela tbody").empty();
			if(odsustva != undefined){
				if(odsustva.length != 0){
					iscrtajTabelu(odsustva);
			}else{
				alert("Nema zahteva za odsustvo");
				$(".nemaZahteva").append("Trenutno nema zahteva za odsustvo");
			}
			
			
		}else{
			$(".nemaZahteva").append("Trenutno nema zahteva za odsustvo");
		}
		},
	});
}

function popuniTabelu2(userId){
	
	$.get({
		
		url: 'odsustvo/vratiZahteveZaOdsustva/'+userId,
		contentType: 'application/json',
		success: function(odsustva){
			$("#tabela tbody").empty();
			if(odsustva != undefined){
				if(odsustva.length != 0){
					iscrtajTabelu2(odsustva);
			}else{
				alert("Nema zahteva za odsustvo");
				nemaMS = true;
				$(".nemaZahteva").append("Trenutno nema zahteva za odsustvo");
			}
			
			
		}else{
			$(".nemaZahteva").append("Trenutno nema zahteva za odsustvo");
		}
		},
	});
	
}
var data = new google.visualization.DataTable();
function addOdsustvo(o){
	let tr=$('<tr></tr>');
	let tdId=$('<td>'+o.id+'</td>');
	let lekar=o.lekar;
	let tdLekar=$('<td>'+lekar.ime+'</td>');
	let tdPocetak=$('<td>'+o.datumPregledaOd+'</td>');
	let tdKraj=$('<td>'+o.datumPregledaDo+'</td>');
	
	let tdDodajSalu=$('<td class="sala"><a href="dodelaSalePregledu.html?idPregleda='+p.id+'">Dodaj salu pregledu</a></td>');

	tr.append(tdId).append(tdPocetak).append(tdKraj).append(tdDodajSalu);
	$('#tabela tbody').append(tr);

}


function iscrtajTabelu(zahtevi){

	$("#divTabela").empty();
	if(zahtevi != undefined){
		var tableUDivu =$("<table id='tabelaZahteva' class = 'display'></table>");
		
		
		var thead = $("<thead> </thead>");
		thead.append("<tr><th>Id zahteva</th><th>Lekar</th><th>Pocetak odsustva</th><th>Kraj odsustva</th><th>Pihvati</th><th>Odbij</th>");
		
		var tbody = $("<tbody id='teloTabele'> </tbody>");
		
		for(var z of zahtevi){
			var tr = $("<tr> </tr>");
			var tdId = $("<td></td>");
			tdId.append(z.id);
			var tdLekar = $("<td> </td>");
			tdLekar.append(z.lekar);
			var tdPocetak = $("<td> </td>");
			tdPocetak.append(z.pocetak);
			var tdKraj = $("<td> </td>");
			tdKraj.append(z.kraj);
			
			
			var tdPrihvati = $("<td> </td>");
			var btn = $('<button>Prihvati</button>');
			btn.click(prihvatiOdsustvo(z.id));
			tdPrihvati.append(btn);
			
			var tdOdbij = $("<td> </td>");
			var btn2 = $('<button>Odbij</button>');
			btn2.click(odbijOdsustvo(z.id));
			tdOdbij.append(btn2);
			
			tr.append(tdId).append(tdLekar).append(tdPocetak).append(tdKraj).append(tdPrihvati).append(tdOdbij);
			tbody.append(tr);
		}
		tableUDivu.append(thead).append(tbody);
		$("#divTabela").append(tableUDivu);
		
		var table = $('#tabelaSala').dataTable({
	        "pagingType": "full_numbers",
	        
		
		
	});
	}
}

function iscrtajTabelu2(zahtevi){

	$("#divTabela2").empty();
	if(zahtevi != undefined){
		var tableUDivu =$("<table id='tabelaZahteva2' class = 'display'></table>");
		
		
		var thead = $("<thead> </thead>");
		thead.append("<tr><th>Id zahteva</th><th>Medicinska sestra</th><th>Pocetak odsustva</th><th>Kraj odsustva</th><th>Pihvati</th><th>Odbij</th>");
		
		var tbody = $("<tbody id='teloTabele2'> </tbody>");
		
		for(var z of zahtevi){
			var tr = $("<tr> </tr>");
			var tdId = $("<td></td>");
			tdId.append(z.id);
			var tdMedSestra = $("<td> </td>");
			tdMedSestra.append(z.medicisnkaSestra);
			var tdPocetak = $("<td> </td>");
			tdPocetak.append(z.pocetak);
			var tdKraj = $("<td> </td>");
			tdKraj.append(z.kraj);
			
			
			var tdPrihvati = $("<td> </td>");
			var btn = $('<button>Prihvati</button>');
			btn.click(prihvatiOdsustvo2(z.id));
			tdPrihvati.append(btn);
			
			var tdOdbij = $("<td> </td>");
			var btn2 = $('<button>Odbij</button>');
			btn2.click(odbijOdsustvo2(z.id));
			tdOdbij.append(btn2);
			
			tr.append(tdId).append(tdMedSestra).append(tdPocetak).append(tdKraj).append(tdPrihvati).append(tdOdbij);
			tbody.append(tr);
		}
		tableUDivu.append(thead).append(tbody);
		$("#divTabela2").append(tableUDivu);
		
		var table = $('#tabelaSala').dataTable({
	        "pagingType": "full_numbers",
	        
		
		
	});
	}
}


function prihvatiOdsustvo(id){
	return function(){
		$.ajax({
			url:'lekarOdsustvo/odobriOdsustvo/'+id,
			type: 'PUT',
			success: function(){
					window.location = "./prikaziZahteveOdsustva.html";
				}
			
			});
		}
}

function prihvatiOdsustvo2(id){
	return function(){
		$.ajax({
			url:'odsustvo/odobriOdsustvo/'+id,
			type: 'PUT',
			success: function(){
					window.location = "./prikaziZahteveOdsustva.html";
				}
			
			});
		}
}

function odbijOdsustvo(id){
	return function(){
	window.location="./OdbijZahtevZaOdsustvo.html?id="+id;
}
}
function odbijOdsustvo2(id){
		return function(){
		window.location="./OdbijZahtevZaOdsustvo2.html?id="+id;
	}
	
	
	
}

