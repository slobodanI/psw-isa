function koJeUlogovan() 
{
	
	$.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) 
		{
			if(user.uloga != "Lekar")
			{
				logout();
			}
			
			RenderHtmlOnSuccess(user.id);
			$('#formaDiv').hide();
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
}

//metoda koja kreira html tabele koja prikazuje listu pacijenata
function RenderHtmlOnSuccess(id) {
    
    $.get({
		
		url:'pacijent/vratiSvePacijenteLekara/' + id,
		contentType: 'application/json',
		success: function(pacijenti)
		{	
			//poruke - lista svih primljenih poruka
			var data = pacijenti;
			
			//pocetak kreiranja html-a
			var html = '<table id="tabelaPacijenata" class="display" ><thead><tr><th>Id pacijenta</th><th>LBO pacijenta</th><th>Ime</th><th>Prezime</th><th>Pregledaj</th></thead><tbody id="teloZahtevi">';
			data.forEach((item)=>{
				
				//pacijenti - prikaz u redu
				  html+='<tr>';
				  
				  html+='<td>';
				  html+=item.id;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.lbo;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.ime;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.prezime;
				  html+='</td>';
				  
				  html+='<td>';
				  html+='<input type="button" class="pregledajButton" value="Pregledaj">';
				  html+='</td>';
				  
				  html+='</tr>';
				  
			})
		    html += '</tbody></table>';

			//postavljanje tabele u div na html-u
		    $(html).appendTo('#divTabela');

		    //konvertovanje tabele u dataTable
		    var table = $('#tabelaPacijenata').dataTable({
		        "pagingType": "full_numbers",
		        select: false
		    });
		    
		    //kad se klikne na dugme Odgovori u tabeli uzimaju se podaci iz reda
		    //i salju se u odgovarajuca polja u formi za odgovaranje
		    $('.pregledajButton').on("click", function () {
		    	 var data = table.api().row( $(this).parents('tr') ).data();
		    	 id_pacijenta = data[0];
		    	 $('#idPacijentaTxt').val(id_pacijenta);
		    	 lbo_pacijenta = data[1];
		    	 $('#lboPacijentaTxt').val(lbo_pacijenta);
		    	 ime_pacijenta = data[2];
		    	 $('#imePacijentaTxt').val(ime_pacijenta);
		    	 prezime_pacijenta = data[3];
		    	 $('#prezimePacijentaTxt').val(prezime_pacijenta);
		    	 $('#formaDiv').show();
		    	 $("#forma :input").focus();
		         
			});
		    
		    $('.dataTables_length').addClass('bs-select');
		    
		},
		error: function()
		{
			alert('Nema pacijenata');
			var html = '<table id="tabelaPacijenata" class="display" ><thead><tr><th>Id pacijenta</th><th>LBO pacijenta</th><th>Ime</th><th>Prezime</th><th>Pregledaj</th></thead><tbody id="teloZahtevi">';
			html += '</tbody></table>';
			//postavljanje tabele u div na html-u
		    $(html).appendTo('#divTabela');

		    //konvertovanje tabele u dataTable
		    var table = $('#tabelaPacijenata').dataTable({
		        "pagingType": "full_numbers",
		        select: false
		    });
		}
		
	});
    
}

$( document ).ready(function() {
    
	koJeUlogovan();
	
	//submit forme
	$( "#forma" ).submit(function( event ) {
		event.preventDefault();
		
		alert('submitovano');
		  
		});
	
});
