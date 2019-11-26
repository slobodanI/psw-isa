function selectOnChange()
{
	//generisanje html-a
	if($("#typeOfAdmin").val()=="adminkc")
	{
		//html admin klinickog centra
		html = '<div id="forma" class="card mb-4 box-shadow">';
		html+='<div class="card-header text-center"><h4 class="my-0 font-weight-normal">Registracija administratora kliničkog centra</h4></div>';
		html+='<br/><div class="form-group col-md-6"><label>Ime *:</label><input id="imeTxt" class="form-control" pattern="(?:[^\\s]*)" title="Unesite ime bez razmaka" type="text" required></div>';	
        html+='<div class="form-group col-md-6"><label>Prezime *:</label><input id="prezimeTxt" class="form-control" pattern="(?:[^\\s]*)" title="Unesite prezime bez razmaka" type="text"></div>';
        html+='<div class="form-group col-md-6"><label>Korisničko ime *:</label><input id="korisnickoTxt" pattern="(?:[^\\s]*)" class="form-control" title="Unesite korisnicko ime bez razmaka" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label>Lozinka (min 6 karaktera) *:</label><input id="lozinkaTxt" pattern="(?:[^\\s]*)" class="form-control" title="Unesite lozinku bez razmaka, min 6 karaktera" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label>Potvrdi lozinku *:</label><input id="lozinkaPonovoTxt" pattern="(?:[^\\s]*)" class="form-control" title="Unesite lozinku bez razmaka, min 6 karaktera" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label>eMail *:</label><input id="emailTxt" class="form-control" pattern="(?:[^\\s]*)" title="Unesite email adresu bez razmaka" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label id="promenjenaLozinka" hidden>false</label></div>';
		html+='<input id="regAdminKC" type="submit" class="btn btn-lg btn-block btn-outline-primary" value="Registruj"></div>";';	
		
	}
	else if($("#typeOfAdmin").val()=="admink")
	{
		//html admin klinike
		html = '<h1>Registracija administratora klinike</h1>';
	}
	else
	{
		//isprazni formu
		html = '';
	}
	
	$('#forma').html(html);
}

//funkcija za registrovanje administratora klinickog centra
function registrujAdminKC()
{
	var ime = $('#imeTxt').val();
	var prezime = $('#prezimeTxt').val();
	var username = $('#korisnickoTxt').val();
	var password = $('#lozinkaTxt').val();
	var passwordPonovo = $('#lozinkaPonovoTxt').val();
	var email = $('#emailTxt').val();
	var promenjenaLozinka = false;
	var uloga = "AdministratorKlinickogCentra";
	
	//provera da li je uneta ista sifra u oba polja
	if(password == passwordPonovo)
	{
		
		$.post
		({
			url: '/adminKC/registrujAdminKC',
			data: JSON.stringify({ime,prezime,username,password,email,promenjenaLozinka,uloga}),
			contentType: 'application/json',
			success: function()
			{
				
				alert("Uspesno refgistrovan administrator klinickog centra");
	
			},
			error: function()
			{
				alert("Greska pri registrovanju administratora klinickog centra");
			}	
		});	
	}	
	else
	{
		alert("Unete lozinke nisu iste");
	}
			
		
}

$( document ).ready(function() {
    
	//submit forme
	$( "#forma" ).submit(function( event ) {
		  event.preventDefault();
		  
		  registrujAdminKC();
		  
		});
	
});