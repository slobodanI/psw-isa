	$(document).ready(function(){

		popuniTabelu();
		
		$("#obrisiSalu").submit(function(event){
			event.preventDefault();

			
			var id = $('input[name="id"]').val();
			
			
			
			$.ajax({
				url: '/api/sala/delete/'+id,
				data: JSON.stringify({id}),
				contentType: 'application/json',
				type: 'DELETE',
				success: function(){
				alert("Uspešno ste obrisali salu");
				},
				error: function(){
					alert("Greska pri brisanju salu");
				}	
			});
					
			});
			
	});



function popuniTabelu(){
	$.get({
		
		url: '/api/sala/sale',
		success: function(sale){
			$("#tabela tbody").empty();
			if(sale != undefined){
				if(sale.length != 0){
					for(let s of sale){
					addSalu(s);
				}
				
			}else{
				$(".nemaSala").append("Nema sala na ovoj klinici");
		}
		}else{
			$(".nemaSala").append("Nema sala na ovoj klinici");
		}
	},
		
		});	
}


function addSalu(s){
	let tr=$('<tr></tr>');
	let tdNaziv=$('<td>'+s.naziv+'</td>');
	let tdBroj=$('<td>'+s.id+'</td>');
	let tdZauzetost = $('<td>'+s.zauzetost+'</td>');

	tr.append(tdNaziv).append(tdBroj).append(tdZauzetost);
	$('#tabela tbody').append(tr);

	
}