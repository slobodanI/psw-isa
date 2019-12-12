//metoda koja dobavlja medicinsku sestru po id-u
function dobaviPodatkeOSestri(id) 
{
	
	$.get({
		url: 'medicinska_sestra/getMS/' + id,
		contentType : 'application/json',
		success: function(sestra) 
		{	
			popuniPolja(sestra);
		},
		error: function() 
		{
			alert("Neuspešno dobavljeni podaci o medicinskoj sestri");
		}
	});
}

//metoda koja popunjava profilnu formu medicinske sestre
function popuniPolja(user)
{
	$('#imeTxt').val(user.ime);	
	$('#prezimeTxt').val(user.prezime);
	$('#korisnickoTxt').val(user.username);
	$('#lozinkaTxt').val(user.password);
	$('#emailTxt').val(user.email);
	
	var loz_promenjena = user.promenjenaLozinka;
	var uloga = user.uloga;
	var idMS = user.id;
	
	$('#idMedSestre').append(idMS);
	
	//ako nije medicinska sestra izloguj je
	if(uloga != 'MedicinskaSestra')
	{
		//prelazak na index
		window.location.href = 'http://localhost:8080/index.html';
	}
	
	//prikaz poruke da li mora da se promeni lozinka
	if(loz_promenjena == true)
	{
		tekst = 'Vaša lozinka je promenjena';
	}
	else
	{
		tekst = 'Morate promeniti vašu lozinku';
	}
	
	$('#promenjenaLozinka').append(tekst);
	
	$('#forma').show();
}

//proverava ko je ulogovan iz sesije
function koJeUlogovan() 
{
	
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) 
		{
			if(user.uloga != "MedicinskaSestra")
			{
				logout();
			}
			
			dobaviPodatkeOSestri(user.id);
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
}

$(document).ready(function() {
	koJeUlogovan();
});
