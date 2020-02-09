
$(document).ready(function() {
	
	
	
	$("#formaZaGrafik").submit(function(event){
		event.preventDefault();
		$('#myChart').empty();
		var Od = $('input[name="Od"]').val();
		var datumOd = Od+'T00:00';
		var Do = $('input[name="Do"]').val();
		var datumDo = Do+'T00:00';
		var Tip = $('input[name="Tip"]').val();
		$.get({
			url: '/pregledi/vratiZaKalendar/od/'+datumOd+'/do/'+datumDo+'/tip/'+Tip,
			success: function(brojevi){
				brojeviZaGrafik=brojevi;
				
				
				$.get({
					url: '/pregledi/vratiZaKalendarDatum/od/'+datumOd+'/do/'+datumDo+'/tip/'+Tip,
					success: function(datumi){
						datumiZaKalendar = datumi;
						
						nacrtaj();
					},
					error: function(){
						alert("Korisnik sa ovim username već postoji.");
					}	
				});
				
				
			},
			error: function(){
				alert("Greska");
			}	
		});
		
		prikaziPrihode(datumOd,datumDo);

		
		});	
	koJeUlogovan();

	});
	
function prikaziPrihode(datumOd,datumDo){
	$.get({
		url:'/pregledi/prihodi/od/'+datumOd+'/do/'+datumDo,

		contentType:'application/json',
		success: function(prihodi){
	

			if(prihodi != undefined){
				$("#prihodi").append(prihodi);
				
				}
			else{
				window.location ="./prikaziZahteve.html";
			}
			}
	});
	
}

function nacrtaj(){
	var myChart=document.getElementById('myChart').getContext('2d');
	
	var barChart = new Chart(myChart,{
		type:'bar',
		data:{
			labels:datumiZaKalendar,
			datasets:[{
				label:'Pregledi',
				data: brojeviZaGrafik,
				backgroundColor: 'lightBlue'
				
			}]
			
		},
		oprions:{},
		
	});
}

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
					popuniTabelu(idUsera);
					getOcenuKlinike(idUsera);

				} else if (user.uloga == "Lekar") {
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


function popuniTabelu(idUsera){
	$.get({
		url:'api/lekar/vratiLekareKlinike/'+idUsera,

		contentType:'application/json',
		success: function(lekari){
			lekariSVI=lekari;
			$("#divTabela").empty();
			if(lekari != undefined){
				if(lekari.length == 0) {
		//			alert("NEMA SLOBODNIH SALA,SISTEM CE DODELITI PRVU SLEDECU SLOBODNU SALU I ODRADITI NEOPHODNE PROMENE!");
					$("#divTabela").hide();
				}
				iscrtajTabelu(lekari);
				}
			else{
				window.location ="./prikaziZahteve.html";
			}
			}
	});	
}

function getOcenuKlinike(idUsera){
	$.get({
		url:'klinika/getKlinikaAdmin/'+idUsera,

		contentType:'application/json',
		success: function(klinika){
	

			if(klinika != undefined){
				$("#ocena").append(klinika.ocena);
				
				}
			else{
				window.location ="./prikaziZahteve.html";
			}
			}
	});

}

function iscrtajTabelu(lekari){
	
	
	$("#divTabela").empty();
	if(lekari != undefined){
		var tableUDivu =$("<table id='tabelaSala' class = 'display'></table>");
		
		
		var thead = $("<thead> </thead>");
		thead.append("<tr><th>Ime</th><th>Prezime</th><th>Prosecna ocena</th>");
		
		var tbody = $("<tbody id='teloTabele'> </tbody>");
		
		for(var l of lekari){
			kl=l.klinika;
			var tr = $("<tr> </tr>");
			var tdNaziv = $("<td></td>");
			tdNaziv.append(l.ime);
			var tdPrezime = $("<td> </td>");
			tdPrezime.append(l.prezime);
			var tdOcena = $("<td> </td>");
			var oc=l.ukupnaOcena/l.brojOcena;
			oc=oc.toFixed(2);
			tdOcena.append(oc);
			

			
			tr.append(tdNaziv).append(tdPrezime).append(tdOcena);
			tbody.append(tr);
		}
		tableUDivu.append(thead).append(tbody);
		$("#divTabela").append(tableUDivu);
		
		var table = $('#tabelaSala').dataTable({
	        "pagingType": "full_numbers",
	
	});
	}
}