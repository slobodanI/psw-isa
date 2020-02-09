$(document).ready(function(){
	
	$("#registracionaForma").submit(function(event){
		event.preventDefault();
		
		var username = $('input[name="username"]').val();
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input[name="confirmPassword"]').val();
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var email = $('input[name="email"]').val();
		var brojTel = $('input[name="brTelefona"]').val();
		var adresa = $('input[name="adresa"]').val();
		var grad = $('input[name="grad"]').val();
		var drzava = $('input[name="drzava"]').val();
		var lbo = $('input[name="lbo"]').val();
	
		if(password == confirmPassword){
			if(password.length >= 6){
				$.post({
					url: '/api/savePacijent',
					data: JSON.stringify({username,password,confirmPassword,ime,prezime,email,brojTel,adresa,grad,drzava,lbo}),
					contentType: 'application/json',
					success: function(){
						alert("Uspešno ste se registrovali.");
						window.location = "./index.html";
					},
					error: function(){
						alert("Korisnik sa ovim username-om/lbo-m već postoji.");
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
	});
