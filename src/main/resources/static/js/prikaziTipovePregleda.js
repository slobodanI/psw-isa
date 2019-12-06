	$(document).ready(function(){

		popuniTabelu();
		
		$("#obrisiTipPregleda").submit(function(event){
			event.preventDefault();
			
			var id = $('input[name="id"]').val();
			
			
			
			$.ajax({
				url: '/api/tipPregleda/delete/'+id,
				data: JSON.stringify({id}),
				contentType: 'application/json',
				type: 'DELETE',
				success: function(){
				alert("Uspešno ste obrisali tip pregleda");
				},
				error: function(){
					alert("Greska pri brisanju tip pregleda");
				}	
			});
					
			});
			
	});



function popuniTabelu(){
	$.get({
		
		url: '/api/tipPregleda/tipoviPregleda',
		success: function(tipoviPregleda){
			$("#tabela tbody").empty();
			if(tipoviPregleda != undefined){
				if(tipoviPregleda.length != 0){
					for(let t of tipoviPregleda){
					addSalu(t);
				}
				
			}else{
				$(".nemaSala").append("Nema tipova pregleda na ovoj klinici");
		}
		}else{
			$(".nemaSala").append("Nema tipova pregleda na ovoj klinici");
		}
	},
		
		});	
}


function addSalu(t){
	let tr=$('<tr></tr>');
	let tdNaziv=$('<td>'+t.naziv+'</td>');
	let tdBroj=$('<td>'+t.id+'</td>');


	tr.append(tdNaziv).append(tdBroj);
	$('#tabela tbody').append(tr);
	
}