function addPorukeTr(poruka)
{
	let tr=$('<tr></tr>');
	let tdId=$('<td>' +poruka.id +'</td>');
	let tdNaslov=$('<td>'+poruka.naslov +'</td>');
	let tdePosiljaoca=$('<td>'+poruka.email_posiljaoca +'</td>');
	
	tr.append(tdId).append(tdNaslov).append(tdePosiljaoca);
	$('#tabelaZahteva tbody').append(tr);
}

function getPoruke()
{
	$.get({
		
		url:'apiPoruke/getPoruke',
		contentType: 'application/json',
		success: function(poruke)
		{	
			$('#tabelaZahteva tbody').html('');
			
			for(let poruka of poruke)
			{
				addPorukeTr(poruka);
			}
			alert('get uspeo');
		},
		error: function()
		{
			alert('greska');
		}
		
	});
}

$(document).ready(function() {
    getPoruke();
});

