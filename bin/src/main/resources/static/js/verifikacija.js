//metoda dodaje pacijenta kad se verifikuje
function dodajPacijenta(ind)
{
	$.post
	({
		url:'api/prihvatiPacijenta/'+ind,
		contentType: 'application/json',
		success: function()
		{
			//poruka za uspeh
			myHTML = '<div id="verUspesno" class="card-deck mb-3 text-center"><div class="card mb-4 box-shadow"><div class="card-header"><h4 class="my-0 font-weight-normal">Verifikacija</h4></div><div class="card-body"><h1 class="card-title pricing-card-title">Verifikacija uspešna</h1><a href="index.html">Idi na logovanje</a></div></div></div>';
			$("#content").append(myHTML);
		},
		error()
		{
			//poruka za neuspeh
			myHTML = '<div id="verUspesno" class="card-deck mb-3 text-center"><div class="card mb-4 box-shadow"><div class="card-header"><h4 class="my-0 font-weight-normal">Verifikacija</h4></div><div class="card-body"><h1 class="card-title pricing-card-title">Verifikacija neuspešna</h1></div></div></div>';
			$("#content").append(myHTML);
		}
	});
}

$(document).ready(function () {
	
	//iscitaj id pacijenta iz linka
	var url_string = window.location.href;
	var url = new URL(url_string);
	var id = url.searchParams.get("info");
	
	//alert(id.toString('utf8'));
	var decrypted = CryptoJS.AES.decrypt(id, "nesto");
	  
	//alert(decrypted.toString(CryptoJS.enc.Utf8));
	
	//dodaj pacijenta
	dodajPacijenta(decrypted.toString(CryptoJS.enc.Utf8));
	
});