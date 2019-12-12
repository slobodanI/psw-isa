//skripta za logout
function logout()
{
	$.post({
		url: 'api/logOut',
		contentType : 'application/json',
		success: function() 
		{	
			//alert('Uspesno ste se izlogovali');
			window.location = "./index.html";			
		},
		error: function() 
		{
			//alert('Greska prilikom logout-a');
		}
	});
}
