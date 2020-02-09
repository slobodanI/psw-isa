//metoda koja kreira html tabele koja prikazuje pacijenata klinike
function RenderHtmlOnSuccess(id) {
    
    $.get({
		
		url:'pacijent/vratiPacijente/' + id,
		contentType: 'application/json',
		success: function(pacijenti)
		{	
			//poruke - lista svih pacijenata klinike
			var data = pacijenti;
			
			//pocetak kreiranja html-a
			var html = '<table id="tabelaPacijenataKlinike" class="display" ><thead><tr><th>Id pacijenta</th><th>Ime pacijenta</th><th>Prezime pacijenta</th><th>LBO pacijenta</th><th>Telefon pacijenta</th><th>eMail pacijenta</th><th>Grad pacijenta</th><th>Adresa pacijenta</th></thead><tbody id="teloZahtevi">';
			data.forEach((item)=>{
				
				//pacijenti - red u tabeli
				  html+='<tr>';
				  
				  html+='<td>';
				  html+=item.id;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.ime;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.prezime;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.lbo;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.brojTel;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.email;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.grad;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.adresa;
				  html+='</td>';
				  
				  html+='</tr>';
				  
			})
		    html += '</tbody></table>';

			//postavljanje tabele u div na html-u
		    $(html).appendTo('#tabelaPacijenata');

		    //konvertovanje tabele u dataTable
		    var table = $('#tabelaPacijenataKlinike').dataTable({
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
			
			RenderHtmlOnSuccess(user.id);
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
}

$(document).ready(function() {
	koJeUlogovan();
});