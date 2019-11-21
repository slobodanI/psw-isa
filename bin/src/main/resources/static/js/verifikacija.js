//metoda dodaje pacijenta kad se verifikuje
function dodajPacijenta(ind)
{
	$.post
	({
		url:'api/prihvatiPacijenta/'+ind,
		contentType: 'application/json',
		success: function()
		{
			window.location.href = 'http://localhost:8080/uspeh.html';
			//ako se jednom izvrsilo postavi executed na true da se ne izvrsava ponovo
			localStorage.setItem("executed", "true");
		},
		error()
		{
			//ako se jednom izvrsilo postavi executed na true da se ne izvrsava ponovo
			window.location.href = 'http://localhost:8080/neuspeh.html';
			localStorage.setItem("executed", "true");
		}
	});
}

$(document).ready(function () {
	
	//da li se vec izvrsilo? ako jeste preskoci
	if(localStorage.getItem("executed")==null)
	{
		//iscitaj id pacijenta iz linka
		var url_string = window.location.href;
		var url = new URL(url_string);
		var id = url.searchParams.get("id_pacijenta");
		
		//dodaj pacijenta
		dodajPacijenta(id);
	}
	
});