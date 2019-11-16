$('#filterOdgovoreno').on('change', function(){

    if(this.checked){
        $('.searchable tr').hide();
        $('.searchable tr').filter(function() {
            return $(this).find('td').eq(5).text() !== "false"
        }).show();
    }else{
        $('.searchable tr').show();
    }            

});

function RenderHtmlOnSuccess() {
    
    $.get({
		
		url:'apiPoruke/getPoruke',
		contentType: 'application/json',
		success: function(poruke)
		{	
			var data = poruke;
			
			var html = '<table id="tabelaZahteva" class="display" ><thead><tr><th>Id zahteva</th><th>Id pacijenta</th><th>Naslov</th><th>Telo</th><th>eMail</th><th>Odgovor</th></thead><tbody id="teloZahtevi">';
			data.forEach((item)=>{
				
				if(item.odgovoreno==true)
				{
					  html+='<tr>';
					  
					  html+='<td>';
					  html+=item.id;
					  html+='</td>';
					  
					  html+='<td>';
					  html+=item.pacijent_id;
					  html+='</td>';
					  
					  html+='<td>';
					  html+=item.naslov;
					  html+='</td>';
					  
					  html+='<td>';
					  html+=item.telo;
					  html+='</td>';
					  
					  html+='<td>';
					  html+=item.email_posiljaoca;
					  html+='</td>';
					  
					  html+='<td>';
					  html+='<input type="button" class="odgovoriButton" value="Odgovoreno" disabled>'
					  html+='</td>';
					  
					  html+='</tr>';
				}
				else
				{
					  html+='<tr>';
					  
					  html+='<td>';
					  html+='<strong>';
					  html+=item.id;
					  html+='</strong>';
					  html+='</td>';
					  
					  html+='<td>';
					  html+='<strong>';
					  html+=item.pacijent_id;
					  html+='</strong>';
					  html+='</td>';
					  
					  html+='<td>';
					  html+='<strong>';
					  html+=item.naslov;
					  html+='</strong>';
					  html+='</td>';
					  
					  html+='<td>';
					  html+='<strong>';
					  html+=item.telo;
					  html+='</strong>';
					  html+='</td>';
					  
					  html+='<td>';
					  html+='<strong>';
					  html+=item.email_posiljaoca;
					  html+='</strong>';
					  html+='</td>';
					  
					  html+='<td>';
					  html+='<input type="button" class="odgovoriButton" value="Odgovori">'
					  html+='</td>';
					  
					  html+='</tr>';
				}
				  
			})
		    html += '</tbody></table>';

		    $(html).appendTo('#divTabela');

		    var table = $('#tabelaZahteva').dataTable({
		        "pagingType": "full_numbers",
		        select: false
		    });
		    
		    $('.odgovoriButton').on("click", function () {
		    	 var data = table.api().row( $(this).parents('tr') ).data();
		    	 id_poruke = data[0];
		    	 id_poruke = id_poruke.replace("<strong>", "");
		    	 id_poruke = id_poruke.replace("</strong>", "");
		    	 $('#idPoruke').val(id_poruke);
		    	 id_pacijenta = data[1];
		    	 id_pacijenta = id_pacijenta.replace("<strong>", "");
		    	 id_pacijenta = id_pacijenta.replace("</strong>", "");
		    	 email_pacijenta = data[4];
		    	 email_pacijenta = email_pacijenta.replace("<strong>", "");
		    	 email_pacijenta = email_pacijenta.replace("</strong>", "");
		    	 $('#emailPacijentaTxt').val(email_pacijenta);
		         $('#idPacijentaTxt').val(id_pacijenta);
		         showDialog();
		         $("#forma :input").focus();
			});
		    
		    $('.dataTables_length').addClass('bs-select');
		    
		},
		error: function()
		{
			alert('greska');
		}
		
	});
    
}

function showDialog()
{
	$('#forma').show();
}

function odgovoriNaPoruku(ind)
{
	$.post
	({
		url:'apiPoruke/odgovoreno/'+ind,
		contentType: 'application/json',
		success: function()
		{
			
		},
		error()
		{
			alert('greska pri odgovoru');
		}
	});
}

function dodajPacijenta(ind)
{
	$.post
	({
		url:'api/prihvatiPacijenta/'+ind,
		contentType: 'application/json',
		success: function()
		{
			
		},
		error()
		{
			alert('greska pri odgovoru');
		}
	});
}

$(document).ready(function() {
	RenderHtmlOnSuccess();
	
	$('#forma').hide();
	
	$("#posaljiMailBtn").click(function() 
	{
		if($("#selectOdgovorType").val()=="prihvati")
		{
			dodajPacijenta($('#idPacijentaTxt').val())
			odgovoriNaPoruku($('#idPoruke').val());
			alert('zahtev prihvacen');
			location.reload();
		}
		else
		{
			alert('zahtev odbijen');
		}
	});
});

