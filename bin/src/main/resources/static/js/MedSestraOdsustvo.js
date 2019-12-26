//renderovanje html-a
function RenderHtmlOnSuccess()
{
	//podesavanje da ne moze kraj odsustva da bude pre pocetka i druga podesavnja za datepicker
	$("#datepickerOd").datepicker({
		  changeMonth: true,
		  changeYear: true,
		  dateFormat : "dd-mm-yy",
		  minDate : 0,
		  onSelect: function(){
		     $("#datepickerDo").datepicker({changeMonth: true, changeYear: true, dateFormat : "dd-mm-yy", minDate : $("#datepickerOd").datepicker("getDate")});
		  }
		});
	
	formirajSelectTip();
}

//formiranje select taga sa tipovima odsustva
function formirajSelectTip()
{
	html = '<select id="selectTipOdsustva" class="form-control"><option value="odsustvo">Odsustvo</option><option value="godisnji">Godišnji odmor</option></select>';
	$('#TipDiv').html(html);
}

//proverava ko je ulogovan iz sesije
function koJeUlogovan() 
{
	
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) 
		{
			if(user.uloga != "MedicinskaSestra")
			{
				logout();
			}
			
			//cuvanje id-ja ulogovane osobe
			$('#idHidden').val(user.id);
			RenderHtmlOnSuccess();
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
}

//metoda za kreiranje odsustva
function kreirajOdsustvo()
{
	//formatiranje datuma pocetka i kraja u dd.MM.yyyy
	var p = $("#datepickerOd").datepicker("getDate");
	p.setDate(p.getDate() + 1);
	var p2 = new Date(p).toISOString().split("T")[0];
	var split = p2.split("-");
	var pocetak = split[2]+'-'+split[1]+'-'+split[0];
	
	var k = $("#datepickerDo").datepicker("getDate");
	k.setDate(k.getDate() + 2);
	var k2 = new Date(k).toISOString().split("T")[0];
	var split = k2.split("-");
	var kraj = split[2]+'-'+split[1]+'-'+split[0];
	

	var tip = $('#selectTipOdsustva :selected').val();
	
	var medSestraId = $('#idHidden').val();
	var medSestraId = Number(medSestraId);
	
	$.post
	({
		url: 'odsustvo/kreirajOdsustvo',
		data: JSON.stringify({pocetak,kraj,tip,medSestraId}),
		contentType: 'application/json',
		success: function(poruka)
		{
			//alert(kraj);
			alert(poruka);

		},
		error: function()
		{
			alert("Greska pri kreiranju odsustva");
		}	
	});	
	

}

$(document).ready(function() {
	koJeUlogovan();
	
	//submit forme
	$( "#forma" ).submit(function( event ) {
		event.preventDefault();
		
		kreirajOdsustvo();
		  
	});
});