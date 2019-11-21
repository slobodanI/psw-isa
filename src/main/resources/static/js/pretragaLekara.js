$(document).ready(function() {
	submitForme();

});


function submitForme(){
	$("#formLekara").submit(function(event) {
		event.preventDefault();
		
		var ime = $('input[name="ime"]').val();
		var prezime = $('input[name="prezime"]').val();
		var prosecnaOcenaOd = $('input[name="prosecnaOcenaOd"]').val();
		var prosecnaOcenaDo = $('input[name="prosecnaOcenaDo"]').val();
		var radnoVremeOd = $('input[name="radnoVremeOd"]').val();
		var radnoVremeDo = $('input[name="radnoVremeDo"]').val();

		$.post({
			url: 'api/lekar/pretragaLekara',
			data: JSON.stringify({ime, prezime,prosecnaOcenaOd,prosecnaOcenaDo,radnoVremeOd,radnoVremeDo}),
			contentType: 'application/json',
			success: function(lekari){
				$(".listaLekara").empty();
				if(lekari != undefined){
					for(var l of lekari){
						$(".listaLekara").append('<li class="lekari"> Lekar: '+l.ime +" "+ l.prezime+'</li>');
					
					}
				}
			},
            error: function(jqXhr, textStatus, errorThrown) {
            	console.log(errorThrown);
            }
		});
		
	});