//metoda koja kreira html tabele koja prikazuje listu lekova
function RenderHtmlOnSuccess() {
    
    $.get({
		
		url:'lek/getLekovi',
		contentType: 'application/json',
		success: function(lekovi)
		{	
			//poruke - lista svih primljenih poruka
			var data = lekovi;
			
			//pocetak kreiranja html-a
			var html = '<table id="tabelaLekova" class="display" ><thead><tr><th>Id leka</th><th>Šifra leka</th><th>Naziv leka</th></thead><tbody id="teloLekovi">';
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
		    $('#tabelaLekova').dataTable({
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

function kreirajLek()
{
	var sifra = $('#sifraTxt').val();
	var naziv = $('#nazivTxt').val();
	
	$.post
	({
		url: '/lek/kreirajLek',
		data: JSON.stringify({sifra,naziv}),
		contentType: 'application/json',
		success: function(poruka)
		{
			
			alert(poruka);
			location.reload();

		},
		error: function()
		{
			alert("Greska pri kreiranju leka");
		}	
	});	
}

$(document).ready(function() {

	koJeUlogovan();
	
	//renderovanje tabele sa lekovima
	RenderHtmlOnSuccess();
	
	//submit forme
	$( "#noviLekForm" ).submit(function( event ) {
		event.preventDefault();
		
		kreirajLek();
		  
	});
	
});