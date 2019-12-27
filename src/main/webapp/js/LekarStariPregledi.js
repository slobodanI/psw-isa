var receptiList;

//proveri ko je ulogovan
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
			
			//renderuj tabelu starih pregleda
			RenderHtmlOnSuccess(user.id);
			$('#divPodaci').hide();
			
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
}

//metoda koja kreira html tabele koja prikazuje listu starih pregleda
function RenderHtmlOnSuccess(id) {
    
    $.get({
		
		url:'pregledi/vratiStarePreglede/' + id,
		contentType: 'application/json',
		success: function(pacijenti)
		{	
			//poruke - lista svih starih pregleda
			var data = pacijenti;
			
			//pocetak kreiranja html-a
			var html = '<table id="tabelaPacijenata" class="display" ><thead><tr><th>Id pregleda</th><th>Ime pacijenta</th><th>Dijagnoza</th><th>Tip pregleda</th><th>Promeni</th></thead><tbody id="teloZahtevi">';
			
			data.forEach((item)=>{
 			//console.log("Index = " + index + " value = " + value); 
			
			//pacijenti - prikaz u redu
				  html+='<tr>';
				  
				  html+='<td>';
				  html+=item.id;
				  html+='</td>';
		
				  html+='<td>';
				  html+=item.pacijentIme + ' ' + item.pacijentPrezime;
				  html+='</td>'; 
				  
				  html+='<td>';
				  html+=item.dijagnozaNaziv;
				  html+='</td>';
				  
				  html+='<td>';
				  html+=item.tipPregledaNaziv;
				  html+='</td>';
				  
				  html+='<td>';
				  html+='<input type="button" class="promeniButton" value="Promeni">';
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
		    $('.promeniButton').on("click", function () {
		    	 var data = table.api().row( $(this).parents('tr') ).data();
				 id_pregleda = data[0];
				  
				 
				 $.get({
						
						url:'pregledi/vratiPregled/' + id_pregleda,
						contentType: 'application/json',
						success: function(pregled)
						{	
						    $('#idPregledaTxt').val(pregled.id);
						    $('#infoTxt').val(pregled.informacije);
						    
						    //kreiranje select taga sa dijagnozama
							$('#selectDijagnozaDiv').html('');
							formirajSelectDijagnoze(function(selectHtml){
								//alert(selectHtml);
								$('#selectDijagnozaDiv').append(selectHtml);
								//odaberi pravu opciju
								$('#diagnozaType').val(pregled.dijagnozaId);
							});
						    					    
						    //kreiranje select taga sa tipovima
							$('#selectTipDiv').html('');
							formirajSelectTip(function(selectHtml){
								//alert(selectHtml);
								$('#selectTipDiv').append(selectHtml);
								//odaberi pravu opciju
								$('#tipType').val(pregled.tipId);
							});
						    
						    $('#divPodaci').show();
						    $("#forma :input").focus();
						},
						error: function()
						{
							alert('Greška pri dobavljanju podataka o starom pregledu');
						}
						
					});
				 
		         
			});
		    
		    $('.dataTables_length').addClass('bs-select');
		    
		},
		error: function()
		{
			//ako nema pregleda prikazi praznu tabelu
			alert('Nema starih pregleda');
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

//popunjavanje select-a 
function formirajSelectDijagnoze(callback)
{
	$.get({
		url : 'dijagnoza/getDijagnoza',
		success : function(dijagnoze) 
		{
			//formiranje select html taga
			selectHtml = '<select id="diagnozaType" class="form-control" required>';
			
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

//formiranje select-a tipova pregleda
function formirajSelectTip(callback)
{
	$.get({
		url : '/api/tipPregleda/tipoviPregleda',
		success : function(tipovi) 
		{
			//formiranje select html taga
			selectHtml = '<select id="tipType" class="form-control" required>';
			
			tipovi.forEach((item)=>{
				
				selectHtml += '<option value=' + item.id + '>' + item.naziv + '</option>';
				
			});
			
			selectHtml += '</select>';
			
			//alert(selectHtml);
			callback(selectHtml);
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o lekovima');
		}
	});
}

//funkcija za promenu informacija o pregledu
function promeni()
{
	 var id =$('#idPregledaTxt').val();
	 var informacije = $('#infoTxt').val();
	 var dijagnozaId = $('#diagnozaType :selected').val();
	 
	 var sn = $('#diagnozaType :selected').text();
	 var split = sn.split(" - ");
	 
	 var dijagnozaSifra = split[0];
	 var dijagnozaNaziv = split[1];
	 
	 var tipId = $('#tipType :selected').val();
	 var tipNaziv = $('#tipType :selected').text();
	 
	 $.post
		({
			url: '/pregledi/promeniPregled',
			data: JSON.stringify({id,informacije,dijagnozaId,dijagnozaSifra,dijagnozaNaziv,tipId,tipNaziv}),
			contentType: 'application/json',
			success: function(poruka)
			{
				
				alert(poruka);
				location.reload();
	
			},
			error: function()
			{
				alert("Greska pri unošenju informacija o starom pregledu");
			}	
		});	
}

$( document ).ready(function() {
    
	koJeUlogovan();
	
	//submit forme
	$( "#forma" ).submit(function( event ) {
		event.preventDefault();
		
		promeni();
		  
		});
	
});