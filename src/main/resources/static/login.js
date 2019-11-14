$(document).ready(function() {
	
	$('#logInForma').submit(function(event) {
		event.preventDefault();
		var username = $('input[name="username"]').val();
		var password = $('input[name="password"]').val();
//		$('#error').hide();
		
		
		$.post({
			url: 'api/logIn',														
			data: JSON.stringify({username, password}),
			contentType: 'application/json',
			success: function() {
//				$('#success').text('Novi proizvod uspešno kreiran.');
//				$("#success").show().delay(3000).fadeOut();		
				
//				window.location = "./pocetnaPacijent.html";
				
			},
			error: function() {
				alert("Neuspešno ste se prijavili");
			}
		});
	});
	
});
