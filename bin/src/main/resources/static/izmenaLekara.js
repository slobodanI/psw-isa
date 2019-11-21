$(document).ready(function(){
	
	$("#izmenaPodataka").submit(function(event){
	
		var ime = $('input=[name="ime"]').val();
		var prezime = $('input=[name="prezime"]').val();
	
		$.post({
			url: '/api/updateLekar',
			data: JSON.stringify({ime,prezime}),
			contentType: 'application/json',
			alert("Uspešno ste izmenili podatke.");
			},
			error: function(){
				alert("Greska pri izmeni podataka");
			}	
			
			s
		});
	
	
	});
	
});
