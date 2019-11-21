$(document).ready(function(){

	$(".div-form-edit").hide();		
	$("#btn-prikazForme").click(function() {
		$(".div-form-edit").toggle();		
	});
	
	koJeUlogovan();
	
	
});

function koJeUlogovan() {
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) {
			if (user != undefined) {
				dobaviPodatkeOPacijentu(user.id);		
			}
			else {
				console.log("NIKO NIJE ULOGOVAN");
			}

		}
	});
}

function dobaviPodatkeOPacijentu(id) {
	$.get({
		url: 'pacijent/' + id,
		success: function(pacijent) {
			
			$("#pacUsername").empty();
			$("#pacIme").empty();
			$("#pacPrezime").empty();
			$("#pacEmail").empty();
			$("#pacAdresa").empty();
			$("#pacGrad").empty();
			$("#pacDrzava").empty();
			$("#pacBrojTel").empty();
			$("#pacLbo").empty();
						
			$("#pacUsername").append(pacijent.username);
			$("#pacIme").append("Ime: " + pacijent.ime);
			$("#pacPrezime").append("Prezime: " + pacijent.prezime);
			$("#pacEmail").append("Email: " + pacijent.email);
			$("#pacAdresa").append("Adresa: " + pacijent.adresa);
			$("#pacGrad").append("Grad: " + pacijent.grad);
			$("#pacDrzava").append("Država: " + pacijent.drzava);
			$("#pacBrojTel").append("Broj telefona: " + pacijent.brojTel);
			$("#pacLbo").append("Kategorija: " + pacijent.lbo);
			
		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
}