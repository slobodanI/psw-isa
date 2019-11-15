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
			
			var html = '<table id="tabelaZahteva" class="table table-striped table-bordered table-sm" ><thead><tr><th>Id</th><th>Naslov</th><th>Telo</th><th>eMail</th></thead><tbody>';
			data.forEach((item)=>{
				
				if(item.odgovoreno==true)
				{
					  html+='<tr>';
					  
					  html+='<td>';
					  html+=item.id;
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
					  
					  html+='</tr>';
				}
				  
			})
		    html += '</tbody></table>';

		    $(html).appendTo('#divTabela');

		    $('#tabelaZahteva').dataTable({
		        "pagingType": "full_numbers"
		    });
		    
		    $('.dataTables_length').addClass('bs-select');
		    
		},
		error: function()
		{
			alert('greska');
		}
		
	});
    
    

    
}

$(document).ready(function() {
	RenderHtmlOnSuccess();
});

