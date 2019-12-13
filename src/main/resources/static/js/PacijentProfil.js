var params = new URL(location.href).searchParams;
var pacijent=params.get('pacijenti');


$(document).ready(function(){

	$(".div-form-edit").hide();		
	$("#btn-prikazForme").click(function() {
		$(".div-form-edit").toggle();		
	});
	
	koJeUlogovan();
	
	izmenaPodataka();
});

function koJeUlogovan() {
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
				} else if (user.uloga == "Lekar") {
					dobaviPodatkeOPacijentu(pacijent);
//					window.location = "./LekarHome.html";
				} else if (user.uloga == "MedicinskaSestra") {
//					window.location = "./MedicinskaSestra.html";
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
			
			//za formu za izmenu			
			$('input[name="username"]').val(pacijent.username);
			$('input[name="password"]').val(pacijent.password);
			$('input[name="confirmPassword"]').val(pacijent.password);
			$('input[name="ime"]').val(pacijent.ime);
			$('input[name="prezime"]').val(pacijent.prezime);
			$('input[name="brojTel"]').val(pacijent.brojTel);
			$('input[name="adresa"]').val(pacijent.adresa);
			$('input[name="grad"]').val(pacijent.grad);
			$('input[name="drzava"]').val(pacijent.drzava);
			
		},
		error: function() {
			alert("NEuspešno dobavljeni podaci o pacijentu");
		}
	});
}

function izmenaPodataka() {
	
	$("#form-EditPacijent").submit(function(event){
		event.preventDefault();
		
		var username = $('input[name="username"]').val();
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input[name="confirmPassword"]').val();
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var brojTel = $('input[name="brojTel"]').val();
		var adresa = $('input[name="adresa"]').val();
		var grad = $('input[name="grad"]').val();
		var drzava = $('input[name="drzava"]').val();
	
		if(password == confirmPassword){
			if(password.length >= 6){
				$.ajax({
					url: 'pacijent/' + id,
					type: 'PUT',
					data: JSON.stringify({username,password,confirmPassword,ime,prezime,brojTel,adresa,grad,drzava}),
					contentType: 'application/json',
					success: function(pacijent){
						dobaviPodatkeOPacijentu(pacijent.id);
					},
					error: function(){
						alert("Neuspesno ste izmenii podatke");
					}
				});
			}
			else {
				alert("Sifra mora sadržati makar 6 karaktera.");
			}
		}
		else{
			alert("Unete lozinke nisu iste");
		}
			
		});
}