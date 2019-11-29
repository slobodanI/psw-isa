//metoda koja kreira html tabele koja prikazuje listu dijagnoza
function RenderHtmlOnSuccess() {
    
    $.get({
		
		url:'dijagnoza/getDijagnoza',
		contentType: 'application/json',
		success: function(dijagnoze)
		{	
			//poruke - lista svih primljenih poruka
			var data = dijagnoze;
			
			//pocetak kreiranja html-a
			var html = '<table id="tabelaDijagnoza" class="display" ><thead><tr><th>Id dijagnoze</th><th>Šifra dijagnoze</th><th>Naziv dijagnoze</th></thead><tbody id="teloDijagnoze">';
			data.forEach((item)=>{
				
				html+='<tr>';
				  
				  html+='<td>';
				  html+=item.id;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.sifra;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.naziv;
				  html+='</td>';
				  
				  html+='</tr>';
				  
			})
		    html += '</tbody></table>';

			//postavljanje tabele u div na html-u
		    $(html).appendTo('#divTabela');

		    //konvertovanje tabele u dataTable
		    $('#tabelaDijagnoza').dataTable({
		        "pagingType": "full_numbers",
		        select: false
		    });
		    
		    $('.dataTables_length').addClass('bs-select');
		    
		},
		error: function()
		{
			alert('greska');
		}
		
	});
    
}

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

function kreirajDijagnozu()
{
	var sifra = $('#sifraTxt').val();
	var naziv = $('#nazivTxt').val();
	
	$.post
	({
		url: '/dijagnoza/kreirajDijagnozu',
		data: JSON.stringify({sifra,naziv}),
		contentType: 'application/json',
		success: function(poruka)
		{
			
			alert(poruka);
			location.reload();

		},
		error: function()
		{
			alert("Greska pri kreiranju dijagnoze");
		}	
	});	
}

$(document).ready(function() {

	//koJeUlogovan();
	
	//renderovanje tabele sa dijagnozama
	RenderHtmlOnSuccess();
	
	//submit forme
	$( "#novaDijagnozaForm" ).submit(function( event ) {
		event.preventDefault();
		
		kreirajDijagnozu();
		  
	});
	
});