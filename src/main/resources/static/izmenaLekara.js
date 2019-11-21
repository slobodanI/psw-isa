$(document).ready(function(){
	
	$("#izmenaPodataka").submit(function(event){
	
		var ime = $('input=[name="ime"]').val();
		var prezime = $('input=[name="prezime"]').val();
		var password = $('input=[name="password"]').val();
	
		$.post({
			url: '/api/updateLekar',
			data: JSON.stringify({ime,prezime,password}),
			contentType: 'application/json',
			success: function(){
			alert("Uspešno ste izmenili podatke.");
			},
		
			error: function(){
				alert("Greska pri izmeni podataka");
			}	
		
			
		});
	
	
	});
	
});
