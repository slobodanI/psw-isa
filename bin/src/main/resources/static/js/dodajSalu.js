$(document).ready(function(){
	
	$("#registracionaForma").submit(function(event){
		event.preventDefault();
		
		var naziv = $('input[name="naziv"]').val();
		
		
		
		$.post({
			url: '/api/sala/addSalu',
			data: JSON.stringify({naziv}),
			contentType: 'application/json',
			success: function(){
			alert("Uspešno ste dodali salu.");
			},
			error: function(){
				alert("Greska pri dodavanju sale");
			}	
		}
		

			
		});
		
		
		$("#obrisiSalu").submit(function(event){
			event.preventDefault();
			
			var id = $('input[name="id"]').val();
			
			
			
			$.post({
				url: '/api/sala/delete/'+id,
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