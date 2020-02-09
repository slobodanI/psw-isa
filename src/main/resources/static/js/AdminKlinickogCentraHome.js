function koJeUlogovan() 
{
	
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) 
		{
			if(user.uloga != "AdministratorKlinickogCentra")
			{
				logout();
			}
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
}

$( document ).ready(function() {
    
	koJeUlogovan();
	
});
