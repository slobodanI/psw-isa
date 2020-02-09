//ovo ce student 2 da koristi
var params = new URL(location.href).searchParams;
var oglasName = params.get("oglas");
var pacijent=params.get("pacijenti");

$(document).ready(function(){
	
	koJeUlogovan();
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {	
				if (user.uloga == "Pacijent") {
					ucitajPodatke(user.id);
				} else if (user.uloga == "AdministratorKlinickogCentra") {

				} else if (user.uloga == "AdministratorKlinike") {

				} else if (user.uloga == "Lekar") {
					ucitajPodatke(pacijent);

				} else if (user.uloga == "MedicinskaSestra") {

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

function ucitajPodatke(idPacijenta) {
	
	$.get({
		url : 'zdravstveniKarton/' + idPacijenta,
		success : function(zk) {
			$("#zkId").append("Id: " + zk.id);
			$("#zkKrvnaGrupa").append("Krvna Grupa: " + zk.krvnaGrupa);
			$("#zkDioptrija").append("Dioptrija: " + zk.dioptrija);
			$("#zkVisina").append("Visina(cm): " + zk.visina);
			$("#zkTezina").append("Tezina(kg): " + zk.tezina);
			$("#zkAlergije").append("Alergije: " + zk.alergije);
			$("#zkListaBolesti").append("Bolesti: " + zk.listaBolesti);
			
			izlistajListuObavljenihPregleda(zk.listaPregleda);
			izlistajListuObavljenihOperacija(zk.listaOperacija);
		},
		error : function() {
			alert("Neuspesno dobavljeni podacio iz zdravstvenog kartona");
		}
	});
	
}

function izlistajListuObavljenihPregleda(pregledi) {
	if(pregledi.length != 0) {
var tableUDivu1 = $("<table id='tabelaPregleda' class='display'> </table>");// ili mozda primer $("div").addClass("important");
		
		var thead1 = $("<thead> </thead>");
		thead1.append("<tr><th>Tip pregleda</th><th>Informacije</th><th>Dijagnoza</th><th>Datum pregleda</th><th>Lekar</th>")
		
		var tbody1 = $("<tbody id='teloTabele'> </tbody>");
		
		for(var p of pregledi) {
			
			var tr1 = $("<tr> </tr>");
			var tdTipPregleda1 = $("<td> </td>");
			tdTipPregleda1.append(p.tipPregleda.naziv);
			var tdInformacije1 = $("<td> </td>");
			tdInformacije1.append(p.informacije);
			var tdDijagnoza = $("<td> </td>");
			if(p.dijagnoza != null){
				tdDijagnoza.append(p.dijagnoza.naziv);
			} else {
				tdDijagnoza.append("Nema dijagnoze");
			}
			var tdDatumPregleda1 = $("<td> </td>");
			tdDatumPregleda1.append(p.datumPregledaOD);
			var tdLekarPregled = $("<td> </td>");
			tdLekarPregled.append(p.lekar.ime + " " + p.lekar.prezime);
			
			tr1.append(tdTipPregleda1).append(tdInformacije1).append(tdDijagnoza).append(tdDatumPregleda1).append(tdLekarPregled);
			tbody1.append(tr1);
			
		}
		
		tableUDivu1.append(thead1).append(tbody1);
		$("#divTabela1").append(tableUDivu1);
		
		var table = $('#tabelaPregleda').dataTable({
	        "pagingType": "full_numbers",
	        select: false
	    });
		
	} else {
		$("#h3-listaPregleda").append(" - NEMATE OBAVLJENIH PREGLEDA");
	}
}

function izlistajListuObavljenihOperacija(operacije) {
	if(operacije.length != 0) {
var tableUDivu2 = $("<table id='tabelaOperacija' class='display'> </table>");// ili mozda primer $("div").addClass("important");
		
		var thead2 = $("<thead> </thead>");
		thead2.append("<tr><th>Informacije</th><th>Vreme OD</th><th>Vreme DO</th><th>Cena</th><th>Lekari</th>")
		
		var tbody2 = $("<tbody id='teloTabele'> </tbody>");
		
		for(var o of operacije) {
			
			var tr2 = $("<tr> </tr>");
			var tdInformacije2 = $("<td> </td>");
			tdInformacije2.append(o.informacije);
			var tdVremeOD = $("<td> </td>");
			tdVremeOD.append(o.datumOperacijeOD);
			var tdVremeDO = $("<td> </td>");
			tdVremeDO.append(o.datumOperacijeDO);
			var tdCena2 = $("<td> </td>");
			tdCena2.append(o.cena);
			var tdLekari = $("<td> </td>");
			for(var l of o.lekari) {
				tdLekari.append(l.ime + " " + l.prezime + "<br>");
			}
			
			tr2.append(tdInformacije2).append(tdVremeOD).append(tdVremeDO).append(tdCena2).append(tdLekari);
			tbody2.append(tr2);
			
		}
		
		tableUDivu2.append(thead2).append(tbody2);
		$("#divTabela2").append(tableUDivu2);
		
		var table = $('#tabelaOperacija').dataTable({
	        "pagingType": "full_numbers",
	        select: false
	    });
		
	} else {
		$("#h3-listOperacija").append(" - NEMATE OBAVLJENIH OPERACIJA");
	}
}
