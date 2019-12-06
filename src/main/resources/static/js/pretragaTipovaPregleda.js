$(document).ready(function() {
	submitForme();

});


function submitForme(){
	$("#formTipPregleda").submit(function(event) {
		event.preventDefault();
		
		var naziv = $('input[name="naziv"]').val();

		$.post({
			url:'api/tipPregleda/pretragaTipovaPregleda',
			data:JSON.stringify({naziv}),
			contentType:'application/json',
			success: function(tipoviPregleda){
				$(".listaSala").empty();
				if(tipoviPregleda != undefined){
					for(var t of tipoviPregleda){
						$(".listaSala").append('<li class="sale"> Tip pregleda: '+t.naziv);
					
					}
				}
			},
            error: function(jqXhr, textStatus, errorThrown) {
            	console.log(errorThrown);
            }
		});
		
	});
}