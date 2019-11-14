$(document).ready(function(){
	
	$("#registracionaForma").submit(function(event){
		event.preventDefault();
		
		var username = $('input[name="username"]').val();
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input=[name="confirmPassword"]').val();
		var ime = $('input=[name="ime"]').val();
		var prezime = $('input=[name="prezime"]').val();
		var email = $('input=[name="email"]').val();
		var brTelefona = $('input=[name="brTelefona"]').val();
		var adresa = $('input=[name="adresa"]').val();
		var grad = $('input=[name="grad"]').val();
		var drzava = $('input=[name="drzava"]').val();
		var lbo = $('input=[name="lbo"]').val();
	
		if(password == confirmPassword){
		$.post({
			url: '/api/savePacijent',
			data: JSON.stringify({username,password,confirmPassword,ime,prezime,email,brTelefona,adresa,grad,drzava,lbo}),
			contentType: 'application/json',
			alert("Uspešno ste se registrovali.");
			},
			error: function(){
				alert("Korisnik sa ovim username9=/lbo već postoji.");
			}	
		}
		else{
			alert("Unete lozinke nisu iste");
		}
			
		});
	
		
	});
});