var msID;

//metoda koja kreira html tabele koja prikazuje tabelu recepata
function RenderHtmlOnSuccess(id) {
    
    $.get({
		
		url:'recept/recepti/' + id,
		contentType: 'application/json',
		success: function(recepti)
		{	
			//recepti za datu kliniku
			var data = recepti;
			
			//pocetak kreiranja html-a
			var html = '<table id="tabelaRecepata" class="display" ><thead><tr><th>Id recepta</th><th>Ime pacijenta</th><th>Prezime pacijenta</th><th>LBO pacijenta</th><th>Šifra leka</th><th>Lek</th><th>Odobravanje</th></thead><tbody id="teloZahtevi">';
			data.forEach((item)=>{
				
				//pacijenti - red u tabeli
				  html+='<tr>';
				  
				  html+='<td>';
				  html+=item.id;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.imePacijenta;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.prezimePacijenta;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.lbo;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.sifraLeka;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.nazivLeka;
				  html+='</td>';
				  
				  html+='<td>';
				  html+='<input type="button" class="overiButton" value="Overi">'
				  html+='</td>';
				  
				  html+='</tr>';
				  
			})
		    html += '</tbody></table>';

			//postavljanje tabele u div na html-u
		    $(html).appendTo('#tabelaRecepataDiv');

		    //konvertovanje tabele u dataTable
		    var table = $('#tabelaRecepata').dataTable({
		        "pagingType": "full_numbers",
		        select: false
		    });
		    
		    $('.overiButton').on("click", function () {
		    	var data = table.api().row( $(this).parents('tr') ).data();
		    	var id_recepta = Number(data[0]);
		    	
		    	$.post
		    	({
		    		url: 'recept/overi/' + msID,
		    		data: JSON.stringify({id_recepta}),
		    		contentType: 'application/json',
		    		success: function(poruka)
		    		{
		    			
		    			alert(poruka);
		    			location.reload();

		    		},
		    		error: function()
		    		{
		    			alert("Greska pri overi recepta");
		    		}	
		    	});	
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
			
			msID = user.id;
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