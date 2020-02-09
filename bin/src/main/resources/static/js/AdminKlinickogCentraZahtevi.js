//metoda koja kreira html tabele koja prikazuje listu poruka
function RenderHtmlOnSuccess() {
    
    $.get({
		
		url:'apiPoruke/getPoruke',
		contentType: 'application/json',
		success: function(poruke)
		{	
			//poruke - lista svih primljenih poruka
			var data = poruke;
			
			//pocetak kreiranja html-a
			var html = '<table id="tabelaZahteva" class="display" ><thead><tr><th>Id zahteva</th><th>Id pacijenta</th><th>Naslov</th><th>Telo</th><th>eMail</th><th>Odgovor</th></thead><tbody id="teloZahtevi">';
			data.forEach((item)=>{
				
				if(item.odgovoreno==true)
				{
					  //poruke na koje je odgovoreno
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
					  //poruke na koje nije odgovoreno
					  //podaci u tabeli su strong
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

			//postavljanje tabele u div na html-u
		    $(html).appendTo('#divTabela');

		    //konvertovanje tabele u dataTable
		    var table = $('#tabelaZahteva').dataTable({
		        "pagingType": "full_numbers",
		        select: false
		    });
		    
		    //kad se klikne na dugme Odgovori u tabeli uzimaju se podaci iz reda
		    //i salju se u odgovarajuca polja u formi za odgovaranje
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
		         
		         //kriptovanje id-ja pacijenta za verifikacioni link
		         kriptovano = CryptoJS.AES.encrypt(id_pacijenta, "nesto");
		         
		         
		         //standardna poruka za korisnika kome je zahtev prihvacen
		         link = 'Poštovani, \n ovo je vaš \n';
		         link += '<a href="http://localhost:8080/verify.html?info=' + encodeURIComponent(kriptovano) + '">link za verifikaciju</a>';
		         link += '\n Srdačan pozdrav,\n Administrator kliničkog centra';
		         //alert(id_pacijenta + ' ' + kriptovano);
		         
		         $('#naslovTxt').val('Zahtev za registraciju prihvaćen');
		         $('#porukaTxt').val(link);
		         //prikazuje se dijalog za odgovaranje na poruku
		         showDialog();
		         //fokusira se na kreiranu formu za odgovaranje
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

//funkcija za prikazivanje forme za odgovaranje na poruku
function showDialog()
{
	$('#forma').show();
}

//funkcija koja u bazi obelezi da je na poruku odgovoreno
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

//funkcija koja u bazi obelezava da je pacijentov nalog aktiviran
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

//funkcija koja brise pacijenta iz baze ako mu je zahtev za kreiranje profila odbijen
function ukloniPacijenta(ind)
{
	$.post
	({
		url:'api/ukloniPacijenta/'+ind,
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


//funkcija za slanje email-a
function posaljiEmail()
{
	var id_pacijenta = $('#idPacijentaTxt').val();
	var naslov = $('#naslovTxt').val();
	
	//email se salje kao html pa se newline zamejuje sa <br>
	sadrzajHTML = $('#porukaTxt').val().replace(/\n/g,'<br>')
	
	//formatiraje
	sadrzaj = '<p style="outline: 2px dashed blue;">';
	sadrzaj += sadrzajHTML;
	sadrzaj += '</p>';
	
	var telo = sadrzaj
	var mail = $('#emailPacijentaTxt').val();
	

	
	$.post({
		url: '/apiPoruke/posaljiEmail',
		data: JSON.stringify({id_pacijenta,naslov,telo,mail}),
		contentType: 'application/json',
		success: function()
		{
			//alert("Uspešno poslat email");
		},
		error: function(){
			//alert("Greska pri slanju emaila");
		}	
	});
}

//onChange za select
//menja se sadrzaj textbox-ova u zavisnosti od odarane opcije
function selectOnChange()
{
	if($("#selectOdgovorType").val()=="prihvati")
	{
		//prihvaceno
		 $('#porukaTxt').val(link);
		 $('#naslovTxt').val('Zahtev za registraciju prihvaćen');
	}
	else
	{
		//odbijeno
		 $('#porukaTxt').val('Razlog odbijanja zahteva:');
		 $('#naslovTxt').val('Zahtev za registraciju odbijen');
	}
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

$(document).ready(function() {
	koJeUlogovan();
	
	//renderovanje tabele sa porukama
	RenderHtmlOnSuccess();
	
	//sakrivanje forme za odgovor na poruke
	$('#forma').hide();
	
	//funkcija za odgovaranje na poruku
	$("#posaljiMailBtn").click(function() 
	{
		if($("#selectOdgovorType").val()=="prihvati")
		{
			//ako je odgovor prihvaceno
			//dodajPacijenta($('#idPacijentaTxt').val());
			odgovoriNaPoruku($('#idPoruke').val());
			posaljiEmail();
			//alert('zahtev prihvacen');
			location.reload();
		}
		else
		{
			//ako je odgovor Odbijeno
			//ukloniPacijenta($('#idPacijentaTxt').val());
			odgovoriNaPoruku($('#idPoruke').val());
			posaljiEmail();
			//alert('zahtev odbijen');
			location.reload();
		}
	});
});

