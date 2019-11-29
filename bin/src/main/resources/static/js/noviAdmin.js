function selectOnChange()
{
	//generisanje html-a
	if($("#typeOfAdmin").val()=="adminkc")
	{
		//html admin klinickog centra
		html = '<div id="forma" class="card mb-4 box-shadow">';
		html+='<div class="card-header text-center"><h4 class="my-0 font-weight-normal">Registracija administratora kliničkog centra</h4></div>';
		html+='<br/><div class="form-group col-md-6"><label>Ime *:</label><input id="imeTxt" class="form-control" title="Unesite ime" type="text" required></div>';	
        html+='<div class="form-group col-md-6"><label>Prezime *:</label><input id="prezimeTxt" class="form-control" title="Unesite prezime" type="text"></div>';
        html+='<div class="form-group col-md-6"><label>Korisničko ime * (dozvoljeno slova, brojevi i _):</label><input id="korisnickoTxt"  class="form-control" title="Unesite korisnicko ime" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label>Lozinka * (min 6 karaktera):</label><input id="lozinkaTxt"  class="form-control" title="Unesite lozinku sa min 6 karaktera" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label>Potvrdi lozinku *:</label><input id="lozinkaPonovoTxt"  class="form-control" title="Unesite lozinku ponovo" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label>eMail *:</label><input id="emailTxt" class="form-control" title="Unesite email adresu" type="text" required></div>';
	    html+='<div class="form-group col-md-6"><label id="promenjenaLozinka" hidden>false</label></div>';
		html+='<input id="regAdminKC" type="submit" class="btn btn-lg btn-block btn-outline-primary" value="Registruj"><input id="clear" type="button" onclick="clearForm()" class="btn btn-lg btn-block btn-outline-primary" value="Clear form"></div>";';	
		$('#forma').html(html);
	}
	else if($("#typeOfAdmin").val()=="admink")
	{
		//html admin klinike
		html= '<div id="forma2" class="card mb-4 box-shadow">';
		html+='<div class="card-header text-center"><h4 class="my-0 font-weight-normal">Registracija administratora klinike</h4></div>';
		html+='<br><select id="klinikeSelect" class="form-control" required><option value="0">Izaberi kliniku</option>';
		
		vratiKlinike(function(data){
			data.forEach((item)=>{
				html+='<option value=' + item.id + '>' + item.naziv + '</option>';
			});
			html+='</select>';
			html+='<br/><div class="form-group col-md-6"><label>Ime *:</label><input id="imeTxt" class="form-control" title="Unesite ime" type="text" required></div>';	
	        html+='<div class="form-group col-md-6"><label>Prezime *:</label><input id="prezimeTxt" class="form-control" title="Unesite prezime" type="text"></div>';
	        html+='<div class="form-group col-md-6"><label>Korisničko ime * (dozvoljeno slova, brojevi i _):</label><input id="korisnickoTxt"  class="form-control" title="Unesite korisnicko ime" type="text" required></div>';
		    html+='<div class="form-group col-md-6"><label>Lozinka * (min 6 karaktera):</label><input id="lozinkaTxt"  class="form-control" title="Unesite lozinku sa min 6 karaktera" type="text" required></div>';
		    html+='<div class="form-group col-md-6"><label>Potvrdi lozinku *:</label><input id="lozinkaPonovoTxt"  class="form-control" title="Unesite lozinku ponovo" type="text" required></div>';
		    html+='<div class="form-group col-md-6"><label>eMail *:</label><input id="emailTxt" class="form-control" title="Unesite email adresu" type="text" required></div>';
		    
		    html+='<div class="form-group col-md-6"><label id="promenjenaLozinka" hidden>false</label></div>';
			html+='<input id="regAdminK" type="submit" class="btn btn-lg btn-block btn-outline-primary" value="Registruj"><input id="clear" type="button" onclick="clearForm()" class="btn btn-lg btn-block btn-outline-primary" value="Clear form"></div>";';
			$('#forma').html(html);
		});
			
	}
	else
	{
		//isprazni formu
		html = '';
		$('#forma').html(html);
	}	
}

//metoda vraca sve klinike da ih postavi u select
function vratiKlinike(callback)
{
	$.get
	({
		url: '/api/klinika/klinike',
		contentType: 'application/json',
		success: function(klinike)
		{
			//alert(klinike[0].naziv);
			
			callback(klinike);
			
		},
		error: function()
		{
			alert("Klinike nisu uspesno dobavljene");
		}	
	});
}

//funkcija pronalazi kliniku po id-ju
function vratiKlinikuPoId(id,callback)
{
	$.get
	({
		url: '/api/klinika/klinika/' + id,
		contentType: 'application/json',
		success: function(klinika)
		{	
			callback(klinika);
			
		},
		error: function()
		{
			alert("Klinika nije uspesno dobavljena");
		}	
	});
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
				
				alert("Uspesno registrovan administrator klinickog centra");
	
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

//funkcija za registovanje administratora klinike
function registrujAdminK()
{
	
	vratiKlinikuPoId($('#klinikeSelect').val(), function(data){
		
		var ime = $('#imeTxt').val();
		var prezime = $('#prezimeTxt').val();
		var username = $('#korisnickoTxt').val();
		var password = $('#lozinkaTxt').val();
		var passwordPonovo = $('#lozinkaPonovoTxt').val();
		var email = $('#emailTxt').val();
		var klinika = data;
		var promenjenaLozinka = false;
		var uloga = "AdministratorKlinike";
		
		//alert(data.id);
		//var klinika_id = data.id
		
		//provera da li je uneta ista sifra u oba polja
		if(password == passwordPonovo)
		{
			
			$.post
			({
				url: '/adminK/registrujAdminK',
				data: JSON.stringify({ime,prezime,username,password,email,klinika,promenjenaLozinka,uloga}),
				contentType: 'application/json',
				success: function()
				{
					
					alert("Uspesno registrovan administrator klinike");
		
				},
				error: function()
				{
					alert("Greska pri registrovanju administratora klinike");
				}	
			});	
		}	
		else
		{
			alert("Unete lozinke nisu iste");
		}
		
	});
	
	
			
		
}

//funkcija za ciscenje forme
function clearForm()
{
	if($("#typeOfAdmin").val() == 'adminkc')
	{
		$('#imeTxt').val('');
		$('#prezimeTxt').val('');
		$('#korisnickoTxt').val('');
		$('#lozinkaTxt').val('');
		$('#lozinkaPonovoTxt').val('');
		$('#emailTxt').val('');
	}
	else if($("#typeOfAdmin").val() == 'admink')
	{
		$('#imeTxt').val('');
		$('#prezimeTxt').val('');
		$('#korisnickoTxt').val('');
		$('#lozinkaTxt').val('');
		$('#lozinkaPonovoTxt').val('');
		$('#emailTxt').val('');
		$('#klinikeSelect').val('0');
	}  
}

function koJeUlogovan() 
{
	
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) 
		{

			if(user.uloga != "AdministratorKlinickogCentra")
			{
				logout();
			}
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
}

$( document ).ready(function() {
    
	koJeUlogovan();
	
	//submit forme
	$( "#forma" ).submit(function( event ) {
		event.preventDefault();
		
		if($("#typeOfAdmin").val() == 'adminkc')
		{
			//alert("KC");
			registrujAdminKC();
		}
		else if($("#typeOfAdmin").val() == 'admink')
		{
			//alert("K");
			registrujAdminK();
		}  
		  
		});
	
});