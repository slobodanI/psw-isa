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
			var html = '<table id="tabelaPacijenata" class="display" ><thead><tr><th>Id pregleda</th><th>Id pacijenta</th><th>LBO pacijenta</th><th>Ime</th><th>Prezime</th><th>Pregledaj</th></thead><tbody id="teloZahtevi">';
			
			$.each( data, function(index,value){
 			//console.log("Index = " + index + " value = " + value); 
			
			//pacijenti - prikaz u redu
				  html+='<tr>';
				  
				  html+='<td>';
				  html+=index;
				  html+='</td>';
		
				  html+='<td>';
				  html+=value.id;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=value.lbo;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=value.ime;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=value.prezime;
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
				 id_pregleda = data[0];
				 $('#idPregledaTxt').val(id_pregleda);
		    	 id_pacijenta = data[1];
		    	 $('#idPacijentaTxt').val(id_pacijenta);
		    	 lbo_pacijenta = data[2];
		    	 $('#lboPacijentaTxt').val(lbo_pacijenta);
		    	 ime_pacijenta = data[3];
		    	 $('#imePacijentaTxt').val(ime_pacijenta);
		    	 prezime_pacijenta = data[4];
		    	 $('#prezimePacijentaTxt').val(prezime_pacijenta);

				 $('#selectDijagnozaDiv').html('');
				 formirajSelectDijagnoze(function(selectHtml){
					//alert(selectHtml);
					$('#selectDijagnozaDiv').append(selectHtml);
				 });
				 
			

		    	 $('#formaDiv').show();
		    	 $("#forma :input").focus();
		         
			});
		    
		    $('.dataTables_length').addClass('bs-select');
		    
		},
		error: function()
		{
			alert('Nema pacijenata');
			var html = '<table id="tabelaPacijenata" class="display" ><thead><tr><th>Id pregleda</th><th>Id pacijenta</th><th>LBO pacijenta</th><th>Ime</th><th>Prezime</th><th>Pregledaj</th></thead><tbody id="teloZahtevi">';
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

function formirajSelectDijagnoze(callback)
{
	$.get({
		url : 'dijagnoza/getDijagnoza',
		success : function(dijagnoze) 
		{
			selectHtml = '<select id="diagnozaType" class="form-control" onchange="selectOnChange()" required><option value=-1>Odaberite dijagnozu</option>';
			
			dijagnoze.forEach((item)=>{
				
				selectHtml += '<option value=' + item.id + '>' + item.sifra + ' - ' + item.naziv + '</option>';
				
			});
			
			selectHtml += '</select>';
			
			//alert(selectHtml);
			callback(selectHtml);
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o dijagnozama');
		}
	});
}

function zavrsiPregled()
{
	var id_pregleda = $('#idPregledaTxt').val();
	var info = $('#infoTxt').val();
	var id_dijagnoze = $('#diagnozaType').val();
	
	$.post
		({
			url: '/pregledi/zavrsiPregled',
			data: JSON.stringify({id_pregleda,info,id_dijagnoze}),
			contentType: 'application/json',
			success: function()
			{
				
				alert("Uspešno unete informacije o pregledu");
				location.reload();
	
			},
			error: function()
			{
				alert("Greska pri unošenju informacija o pregledu");
			}	
		});	
}

$( document ).ready(function() {
    
	koJeUlogovan();
	
	//submit forme
	$( "#forma" ).submit(function( event ) {
		event.preventDefault();
		
		if($('#diagnozaType').val() < 0)
		{
			alert('Morate odabrati dijagnozu');
		}
		else
		{
			zavrsiPregled();
		}
		  
		});
	
});
