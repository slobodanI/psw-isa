
$(document).ready(function() {
	
	
	
	$("#formaZaGrafik").submit(function(event){
		event.preventDefault();
		
		var Od = $('input[name="Od"]').val();
		var datumOd = Od+'T00:00';
		var Do = $('input[name="Do"]').val();
		var datumDo = Do+'T00:00';
		var Tip = $('input[name="Tip"]').val();
		$.get({
			url: '/pregledi/vratiZaKalendar/od/'+datumOd+'/do/'+datumDo+'/tip/'+Tip,
			contentType: 'application/json',
			success: function(brojevi){
				brojeviZaGrafik=brojevi;
			},
			error: function(){
				alert("Greska");
			}	
		});
		
		$.get({
			url: '/pregledi/vratiZaKalendarDatum/od/'+datumOd+'/do/'+datumDo+'/tip/'+Tip,
			contentType: 'application/json',
			success: function(datumi){
				datumiZaKalendar = datumi;
			},
			error: function(){
				alert("Korisnik sa ovim username već postoji.");
			}	
		});

		nacrtaj();
		});	
		

	});
	


function nacrtaj(){
	var myChart=document.getElementById('myChart').getContext('2d');
	
	var barChart = new Chart(myChart,{
		type:'bar',
		data:{
			labels:datumiZaKalendar,
			datasets:[{
				label:'Pregledi',
				data: brojeviZaGrafik,
				backgroundColor: 'lightBlue'
				
			}]
			
		},
		oprions:{},
		
	});
}