$(document).ready(function(){
	
	$("#formaZaNovogLekara").submit(function(event){
		event.preventDefault();
		
		var username = $('input[name="username"]').val();
		var password = $('input[name="password"]').val();
		var confirmPassword = $('input[name="confirmPassword"]').val();
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var radnoVreme = $('input[name="radnoVreme"]').val();
		
		if(password == confirmPassword){
		$.post({
			url: '/api/saveLekar',
			data: JSON.stringify({username,password,confirmPassword,ime,prezime,radnoVreme}),
			contentType: 'application/json',
			success: function(){
			alert("Uspešno ste dodali lekara.");
			},
			error: function(){
				alert("Korisnik sa ovim username već postoji.");
			}	
		}
		}
		else{
			alert("Unete lozinke nisu iste");
		}
			
		});	
		
		$("#obrisiLekara").submit(function(event){
			event.preventDefault();
			
			var id = $('input[name="id"]').val();
			
			
			
			$.post({
				url: '/api/lekar/delete/'+id,
				data: JSON.stringify({id}),
				contentType: 'application/json',
				success: function(){
				alert("Uspešno ste dodali salu.");
				},
				error: function(){
					alert("Greska pri dodavanju sale");
				}	
			}
			

				
			});
	});
});