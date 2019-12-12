$(document).ready(function(){
	
	$("#formaZaTipPregleda").submit(function(event){
		event.preventDefault();
		
		var naziv = $('input[name="naziv"]').val();
		
		
		
		$.post({
			url: '/api/tipPregleda/addTipPregleda',
			data: JSON.stringify({naziv}),
			contentType: 'application/json',
			success: function(){
			alert("Uspešno ste dodali tip pregleda.");
			},
			error: function(){
				alert("Greska pri dodavanju tipa pregleda");
			}	
		});
		

			
		});
		
		
		$("#obrisiTipPregleda").submit(function(event){
			event.preventDefault();
			
			var id = $('input[name="id"]').val();
			
			
			
			$.post({
				url: '/api/tipPregleda/delete/'+id,
				data: JSON.stringify({id}),
				contentType: 'application/json',
				success: function(){
				alert("Uspešno ste obrisali tip pregleda.");
				},
				error: function(){
					alert("Greska pri brisanju tipa pregleda");
				}	
			});
			

				
			});
			
	});