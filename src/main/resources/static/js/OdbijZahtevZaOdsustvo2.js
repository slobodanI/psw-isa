var params = new URL(location.href).searchParams;
var id = params.get("id");


$(document).ready(function(){
	

});


function odbijOdsustvo(){
	var string = $('input[name="string"]').val();
	alert(string);
	
	$.ajax({
		url:'odsustvo/odbijOdsustvo/'+id,
		type: 'PUT',
		data: JSON.stringify({string}),
		contentType:'application/json',
		success: function(){
				window.location = "./prikaziZahteveOdsustva.html";
			}
		
		});
	}
