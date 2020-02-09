	$(document).ready(function(){

		popuniTabelu();
		
		$("#obrisiLekara").submit(function(event){
			event.preventDefault();

			
			var id = $('input[name="id"]').val();
			
			
			
			$.ajax({
				url: '/api/lekar/delete/'+id,
				data: JSON.stringify({id}),
				contentType: 'application/json',
				type: 'DELETE',
				success: function(){
				alert("Uspešno ste obrisali lekara");
				window.location="./PrikaziLekare.html";
				},
				error: function(){
					alert("Greska pri brisanju lekara");
				}	
			});
					
			});
			
	});

function popuniTabelu(){
	$.get({
		
		url: '/api/lekar/lekari',
		success: function(lekari){
			$("#tabela tbody").empty();
			if(lekari != undefined){
				if(lekari.length != 0){
					for(let l of lekari){
					addLekar(l);
				}
				
			}else{
				$(".nemaLekara").append("Nema lekara na ovoj klinici");
		}
		}else{
			$(".nemaLekara").append("Nema lekara na ovoj klinici");
		}
	},
		
		});	
}


function addLekar(l){
	let tr=$('<tr></tr>');
	let tdIme=$('<td>'+l.ime+'</td>');
	let tdPrezime=$('<td>'+l.prezime+'</td>');
	let tdId=$('<td>'+l.id+'</td>')
	tr.append(tdIme).append(tdPrezime);
	var oc=l.ukupnaOcena/l.brojOcena;
	if(isNaN(oc)){
		let tdProsecnaOcena = $('<td>Nije ocenjen</td>');
		tr.append(tdProsecnaOcena);
	}
	else{
	let tdProsecnaOcena = $('<td>'+oc+'</td>');
	tr.append(tdProsecnaOcena);
	}
	tr.append(tdId);
	$('#tabela tbody').append(tr);

	
}