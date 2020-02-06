$(document).ready(function(){
	
	$("#btn-mojProfil").click(function(event){
		window.location = "./PacijentProfil.html";
	});
	
	$("#btn-zdravstveniKarton").click(function(event){
		window.location = "./ZdravstveniKarton.html";
	});
	
	$("#btn-listaPregledaIOperacija").click(function(event){
		window.location = "./PacijentObavljeniPreglediIOperacije.html";
	});
	
	$("#btn-ocenjivanjeKlinika").click(function(event){
		window.location = "./OcenjivanjeKlinika.html";
	});
	
	$("#btn-ocenjivanjeLekara").click(function(event){
		window.location = "./OcenjivanjeLekara.html";
	});
	
	$("#btn-pretragaKlinika").click(function(event){
		window.location = "./PretragaKlinika.html";
	});
	
	$("#btn-listZakazanih").click(function(event){
		window.location = "./PacijentZakazaniPregledi.html";
	});
	
	koJeUlogovan();
	prikaziKlinike();
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
//		contentType : 'application/json',
		success : function(user) {
			if (user != undefined) {
				if (user.uloga == "Pacijent") {
					//window.location = "./PacijentHome.html";
				} else if (user.uloga == "AdministratorKlinickogCentra") {
					//window.location = "./AdminKlinickogCentraHome.html";
				} else if (user.uloga == "AdministratorKlinike") {
					//window.location = "./AdministratorKlinikeHome.html";
				} else if (user.uloga == "Lekar") {
					//window.location = "./profilLekara.html";
				} else if (user.uloga == "MedicinskaSestra") {
					//window.location = "./MedicinskaSestraHome.html";
				} else {
					console.log("NIKO NIJE ULOGOVAN");
					window.location = "./index.html";
				}
				
			} else {
				console.log("NIKO NIJE ULOGOVAN");
				window.location = "./index.html";
			}

		}
	});
}

function prikaziKlinike() {
	$.get({
		url: 'klinika/all',
		success : function(klinike) {
			izlistajKlinike(klinike);
		},
		error : function() {
			alert("Neuspesno dobavljeni podaci o klinikama");
		}
	});
}

function izlistajKlinike(klinike) {
	if(klinike.length != 0) {
		
		var tableUDivu = $("<table id='tabelaKlinika' class='display'> </table>");// ili mozda primer $("div").addClass("important");
		
		var thead = $("<thead> </thead>");
		thead.append("<tr><th>Naziv</th><th>Prosečna ocena</th><th>Opis</th><th>Adresa</th><th>Profil</th>")
		
//		var html = '<table id="tabelaKlinika" class="display" ><thead><tr><th>Naziv</th><th>Prosečna ocena</th><th>Adresa</th><th>Cena pregleda</th><th>Odaberi</th></thead><tbody id="teloTabele">';
		
		var tbody = $("<tbody id='teloTabele'> </tbody>");
		
		for(var k of klinike) {
			
			var tr = $("<tr> </tr>");
			var tdNaziv = $("<td> </td>");
			tdNaziv.append(k.naziv);
			var tdOcena = $("<td> </td>");
			tdOcena.append(k.ocena);
			var tdOpis = $("<td> </td>");
			tdOpis.append(k.opis);
			var tdAdresa = $("<td> </td>");
			tdAdresa.append(k.adresa);
			var tdOdaberi = $("<td> </td>");
			var btn = $('<button>Profil Klinike</button>');
			btn.click(goToKlinika(k.id));
			tdOdaberi.append(btn);
			
			tr.append(tdNaziv).append(tdOcena).append(tdOpis).append(tdAdresa).append(tdOdaberi);
			tbody.append(tr);
			
//			************STARO**************
//			var li = $("<li> </li>");
//			var ptag = $("<p> </p>");
//			
////			dodati link ka profilu klinike
//			var btn = $('<button>Profil klinike</button>');
//			btn.click(goToKlinika(k.id));
//			ptag.append(btn).append("<br>");
//			
//			ptag.append("Naziv klinike: " + k.naziv + "<br>");
//			ptag.append("Ocena klinike: " + k.ocena + "<br>")
//			ptag.append("Opis klinike: " + k.opis + "<br>");
//			ptag.append("Adresa klinike: " + k.adresa + "<br>");
//
//			ptag.append("Lekari klinike: <br>");
//			var count = 1;
//			for(var l of k.lekari) {
//				ptag.append("___Lekar " + count + ": " + l.ime + " " + l.prezime + ", ocena: " + l.ocena + "<br>");
////				dodati link ka profilu lekara?
//				count = count + 1;
//			}
//			
//			li.append(ptag);
//			
//			$("#ul-listaKlinika").append(li).append("<hr>");
//			************STARO**************
		}
		
		tableUDivu.append(thead).append(tbody);
		$("#divTabela").append(tableUDivu);
		
		var table = $('#tabelaKlinika').dataTable({
	        "pagingType": "full_numbers",
	        select: false
	    });
		
	} else {
		$("#h3-listaKlinika").append(" - NEMA KLINIKA");
	}
}

function goToKlinika(id) {
	
	return function() {
		window.location = "./KlinikaProfil.html?klinikaID=" + id;
	}
}