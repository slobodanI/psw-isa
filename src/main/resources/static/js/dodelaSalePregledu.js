var params = new URL(location.href).searchParams;
var idPregleda = params.get("idPregleda");

$(document).ready(function() {
	$("#nemaSala").hide();
	$("#formSala").show();
	$("#divTabela").show();
	popuniTabelu();
	submitForme();

});


function iscrtajTabelu(sale){

	$("#divTabela").empty();
	if(sale != undefined){
		var tableUDivu =$("<table id='tabelaSala' class = 'display'></table>");
		
		
		var thead = $("<thead> </thead>");
		thead.append("<tr><th>Naziv sale</th><th>Broj sale</th><th>Prikazi kalendar</th>");
		
		var tbody = $("<tbody id='teloTabele'> </tbody>");
		
		for(var s of sale){
			var tr = $("<tr> </tr>");
			var tdNaziv = $("<td></td>");
			tdNaziv.append(s.naziv);
			var tdBroj = $("<td> </td>");
			tdBroj.append(s.id);
			
			var tdOdaberi = $("<td> </td>");
			var btn = $('<button>Odaberi</button>');
			btn.click(odaberiSalu(s.id));
			tdOdaberi.append(btn);
			
			var tdKalendar = $("<td> </td>");
			var btn2 = $('<button>Kalendar</button>');
			btn2.click(prikaziKalendar(s.id));
			tdKalendar.append(btn2);
			
			tr.append(tdNaziv).append(tdBroj).append(tdOdaberi).append(tdKalendar);
			tbody.append(tr);
		}
		tableUDivu.append(thead).append(tbody);
		$("#divTabela").append(tableUDivu);
		
		var table = $('#tabelaSala').dataTable({
	        "pagingType": "full_numbers",
	        
		
		
	});
	}
}

function odaberiSalu(id){
	return function(){
	$.ajax({
		url:'pregledi/dodajSaluZaPregled/'+idPregleda+'/sala/'+id,
		type: 'PUT',
		success: function(){
				window.location = "./prikaziZahteve.html";
			}
		
		});
	}
}
function nemaSale(prosledjen){
		
	$("#nemaSala").show();
	$("#formSala").hide();
	$("#divTabela").hide();
	
	$.get({
		url:'pregledi/prviSlobodanTermin/'+idPregleda,

		contentType:'application/json',
		success: function(string){
			$("#slobodan").append(string);
		}
	
	});	
	
//		$.ajax({
//			url:'pregledi/nemaSlobodneSale/'+idPregleda,
//			type: 'PUT',
//			success: function(){
//					window.location = "./prikaziZahteve.html";
//				}
//			
//			});
	
}

function prikaziKalendar(id){
	return function(){
	window.location = "./salaKalendar.html?idSale="+id;
	}
}


function popuniTabelu(){
		$.get({
			url:'api/sala/prikaziSaleZaPregled/'+idPregleda,

			contentType:'application/json',
			success: function(sale){
				saleSVE=sale;
				$("#divTabela").empty();
				if(sale != undefined){
					if(sale.length == 0) {
			//			alert("NEMA SLOBODNIH SALA,SISTEM CE DODELITI PRVU SLEDECU SLOBODNU SALU I ODRADITI NEOPHODNE PROMENE!");
						nemaSale(idPregleda);
					}
					iscrtajTabelu(sale);
					}
				else{
					window.location ="./prikaziZahteve.html";
				}
				}
		});	
}

function submitForme(){
	$("#formSala").submit(function(event) {
		event.preventDefault();
		var naziv = $('input[name="naziv"]').val();
		var id = $('input[name="brojSale"]').val();
		
		var saleZaPrikaz = [];
		var vecIscrtano = false;
		
		if(naziv === "" && id === null){
			iscrtajTabelu(saleSVE);
			vecIscrtano = true;
			
		}
		
		if(vecIscrtano == false){
			for(var sala of saleSVE){
				var flag = true;
				
				if(naziv != ""){
					var daliSadrziNaziv = sala.naziv.includes(naziv);
					if(daliSadrziNaziv == false){
						flag = false;
						continue;
					}
				}
				if(id != null){
										
					if(sala.id != id){
						flag = false;
						continue;
					}
				}
				if(flag == true){
					saleZaPrikaz.push(sala);
				}
				
			}
			iscrtajTabelu(saleZaPrikaz);
		}
		
	
	});
	
}

function prihvati(){
	$.ajax({
		url:'pregledi/nemaSlobodneSale/'+idPregleda,
		type: 'PUT',
		success: function(){
				window.location = "./prikaziZahteve.html";
			}
		
		});
}

