//provera ko je ulogovan
function koJeUlogovan() 
{
	
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) 
		{
			//ako nije admin klinickog centra uradi logout
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


//prikazivanje mape
function createMap() {
	  document.getElementById("divMapa").style.display = "block";
	  var lat = $('#latTxt').val();
	  var lng = $('#longTxt').val();
	  var map;
	  var marker;
	  var myLatlng = new google.maps.LatLng(lat, lng);

	  var mapOptions = {
	    zoom: 18,
	    center: myLatlng,
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	  };

	  map = new google.maps.Map(document.getElementById("divMapa"), mapOptions);

	  marker = new google.maps.Marker({
	    map: map,
	    position: myLatlng,
	    draggable: true
	  });

}

//registracija klinike
function registrujKliniku()
{
	var naziv = $('#nazivTxt').val();
	var adresa = $('#adresaTxt').val();
	var opis = $('#opisTxt').val();
	var slobodniTerminiPregleda = '';
	var latitude = $('#latTxt').val();
	var longitude = $('#longTxt').val();
	
	$.post
	({
		url: '/klinika/kreirajKliniku',
		data: JSON.stringify({naziv,adresa,opis,slobodniTerminiPregleda,latitude,longitude}),
		contentType: 'application/json',
		success: function(poruka)
		{
			
			alert(poruka);
			location.reload();

		},
		error: function()
		{
			alert("Greska pri kreiranju klinike");
		}	
	});	
}


$( document ).ready(function() {
    
	//sakrij div od mape
	document.getElementById("divMapa").style.display = "none";
	
	koJeUlogovan();
	
	//na klik prikazi mapu
	$('#prikaziMapu').on('click', createMap);
	
	//submit forme
	$( "#novaKlinika" ).submit(function( event ) {
		event.preventDefault();
		
		registrujKliniku();
		  
	});
	
});