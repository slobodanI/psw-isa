$(document).ready(function() {
	submitForme();

});


function submitForme(){
	$("#formSala").submit(function(event) {
		event.preventDefault();
		
		var naziv = $('input[name="naziv"]').val();
		var id = $('input[name="brojSale"]').val();

		$.post({
			url:'api/sala/pretragaSala',
			data:JSON.stringify({naziv, id}),
			contentType:'application/json',
			success: function(sale){
				$(".listaSala").empty();
				if(sale != undefined){
					for(var s of sale){
						$(".listaSala").append('<li class="sale"> Sala: '+s.naziv +",broj sale: "+ s.id+'</li>');
					
					}
				}
			},
            error: function(jqXhr, textStatus, errorThrown) {
            	console.log(errorThrown);
            }
		});
		
	});
}