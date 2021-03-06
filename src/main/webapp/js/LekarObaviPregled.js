//mapa lekova koje treba poslati u bazu
var receptiMap;
var lekarId;
var idPac;

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
			lekarId=user.id;
			//renderuj tabelu pacijenata
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
		    	 sakrijKarton();
		    	 $("#zp").show();
		    	 $("#zo").show();
		    	 var data = table.api().row( $(this).parents('tr') ).data();
				 id_pregleda = data[0];
				 $('#idPregledaTxt').val(id_pregleda);
		    	 id_pacijenta = data[1];
		    	 idPac=id_pacijenta;
		    	 $('#idPacijentaTxt').val(id_pacijenta);
		    	 lbo_pacijenta = data[2];
		    	 $('#lboPacijentaTxt').val(lbo_pacijenta);
		    	 ime_pacijenta = data[3];
		    	 $('#imePacijentaTxt').val(ime_pacijenta);
		    	 prezime_pacijenta = data[4];
		    	 $('#prezimePacijentaTxt').val(prezime_pacijenta);

		    	 //kreiranje select taga sa dijagnozama
				 $('#selectDijagnozaDiv').html('');
				 formirajSelectDijagnoze(function(selectHtml){
					//alert(selectHtml);
					$('#selectDijagnozaDiv').append(selectHtml);
				 });
				 
				 //kreiranje select taga sa lekovima
				 $('#selectLekoviDiv').html('');
				 formirajSelectLekovi(function(selectHtml){
					 $('#selectLekoviDiv').append(selectHtml);
				 });
				 
				 //kreiranje mape sa lekovima
				 receptiMap = new Map();
				 
				 $('#buttonPrikaziDiv').show();

				 //prikazi i fokusiraj na formu za pregled
		    	 $('#formaDiv').show();
		    	 $("#forma :input").focus();
		         
			});
		    
		    $('.dataTables_length').addClass('bs-select');
		    
		},
		error: function()
		{
			//ako nema pacijenata prikazi praznu tabelu
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

//funkcija za dodavanje recepata na listu
function dodajRecept()
{	
	//ako je lek selektovan
	if($('#lekoviType').val()>-1)
	{
		//alert("Lek odabran");
		
		//postavi selektovani lek u listu
		receptiMap[$('#lekoviType :selected').val()] = $('#lekoviType :selected').text()
		
		$('#receptiStavkeDiv').html('');
		
		html = '';
		
		$.each(receptiMap, function(index,value){
			
			html += '<label>' + value + '</label></br>';
			//alert(value);
		})
		//alert(html);
		
		//prikazi listu lekova
		$('#receptiStavkeDiv').append(html);
	}
	else
	{
		//ako lek nije odabran
		alert("Morate odabrati lek");
	}
}

//funkcija za formiranje select taga za dijagnoze
function formirajSelectDijagnoze(callback)
{
	$.get({
		url : 'dijagnoza/getDijagnoza',
		success : function(dijagnoze) 
		{
			//formiranje select html taga
			selectHtml = '<select id="diagnozaType" class="form-control" required><option value=-1>Odaberite dijagnozu</option>';
			
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

//funkcija za formiranje select taga za lekove
function formirajSelectLekovi(callback)
{
	$.get({
		url : 'lek/getLekovi',
		success : function(lekovi) 
		{
			//formiranje select html taga
			selectHtml = '<select id="lekoviType" class="form-control" required><option value=-1>Odaberite lek</option>';
			
			lekovi.forEach((item)=>{
				
				selectHtml += '<option value=' + item.id + '>' + item.sifra + ' - ' + item.naziv + '</option>';
				
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

//funkcija koja se poziva kad lekar zavrsi pregled
function zavrsiPregled()
{
	var id_pregleda = $('#idPregledaTxt').val();
	var info = $('#infoTxt').val();
	var id_dijagnoze = $('#diagnozaType').val();
	var id_leka_lista = [];
	
	//iscitaj kljuceve lekova iz mape lekova i stavi u niz
	$.each( receptiMap, function(index,value){
		id_leka_lista.push(index);
	})
	//alert(id_leka_lista);
	
	$.post
		({
			url: '/pregledi/zavrsiPregled',
			data: JSON.stringify({id_pregleda,info,id_dijagnoze,id_leka_lista}),
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

function kreirajSelectKrvnaGrupa(callback)
{
	html = '<select id="kgSelect" class="form-control" required>';
	html += '<option value="A">A</option>';
	html += '<option value="B">B</option>';
	html += '<option value="AB">AB</option>';
	html += '<option value="O">O</option>';
	html += '</select>';
	
	callback(html);
}

function prikaziKarton()
{
	$('#buttonPrikaziDiv').hide();
	$('#buttonSakrijDiv').show();
	
	kreirajSelectKrvnaGrupa(function(html){
		$('#selectKrvnaGrupa').html(html);
	})
	
	var id = $('#idPacijentaTxt').val();
	
	$.get({
		url : 'pacijent/getKartonInfo/'+id,
		success : function(karton) 
		{
			$('#idKartonaTxt').val(karton.id);
			$('#kgSelect').val(karton.krvnaGrupa);
			$('#dioptrijaTxt').val(karton.dioptrija);
			$('#visinaTxt').val(karton.visina);
			$('#tezinaTxt').val(karton.tezina);
			$('#alergijeTxt').val(karton.alergije);
			$('#listaBolestiTxt').val(karton.listaBolesti);
			
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o zdravstvenom kartonu');
		}
	});
	
	$('#formaKarton').show();
	$("#formaKarton :input").focus();
}

function sakrijKarton()
{
	$('#buttonPrikaziDiv').show();
	$('#buttonSakrijDiv').hide();
	$('#formaKarton').hide();
}

function azurirajKarton()
{
	var idP = $('#idPacijentaTxt').val();
	
	var id = $('#idKartonaTxt').val();
	var krvnaGrupa =$('#kgSelect').val();
	var dioptrija = $('#dioptrijaTxt').val();
	var visina = $('#visinaTxt').val();
	var tezina = $('#tezinaTxt').val();
	var alergije = $('#alergijeTxt').val();
	var listaBolesti = $('#listaBolestiTxt').val();
	
	$.post
	({
		url: 'pacijent/setKartonInfo/'+idP,
		data: JSON.stringify({id,krvnaGrupa,dioptrija,visina,tezina,alergije,listaBolesti}),
		contentType: 'application/json',
		success: function(poruka)
		{
			
			alert(poruka);
			//location.reload();

		},
		error: function()
		{
			alert("Greska pri unošenju informacija u karton");
		}	
	});	
}

$( document ).ready(function() 
{
    
	koJeUlogovan();
	submitFormeZaNoviPregled();
	submitFormeZaNovuOperaciju();
	
	$("#zp").hide();
	$("#zo").hide();
	$('#zakaziPregled').hide();
	$('#zakaziOperaciju').hide();
	$("#zp").click(function(){
		$('#zakaziPregled').toggle();
	});
	$("#zo").click(function(){
		$('#zakaziOperaciju').toggle();
	});
	
	
	
	$('#buttonPrikaziDiv').hide();
	$('#buttonSakrijDiv').hide();
	$('#formaKarton').hide();
	
	//submit forme
	$( "#forma" ).submit(function( event ) 
	{
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
	
	//submit forme sa informacijama o kartonu
	$( "#formaKarton" ).submit(function( event ) {
		event.preventDefault();
		
		azurirajKarton();
	});
	
});


////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
//Za studenta 2

function submitFormeZaNoviPregled(){
	$("#form-dodavanjePregleda").submit(function (event){
		event.preventDefault();
		var pocetak = $('input[name="datumPregleda"]').val();
		var pocetakVreme = $('input[name="pocetak"]').val();
		var kraj = $('input[name="datumPregleda"]').val();
		var krajVreme = $('input[name="kraj"]').val();
		var datumPregledaOd = pocetak+'T'+pocetakVreme;
		var datumPregledaDo = kraj+'T'+krajVreme;
		var lekar = lekarId;
		var pacijent = idPac;
		
		$.post({
			url : 'pregledi/dodajNoviPregled',
			contentType: 'application/json',
			data: JSON.stringify({datumPregledaOd,datumPregledaDo,lekar,pacijent}),
			
			success: function(response){
				alert(response);
			},
			error: function(response){
				alert(response);
			},
			
			
			
		});
		
		
		
	});
}
function submitFormeZaNovuOperaciju(){
	$("#form-dodavanjeOperacije").submit(function (event){
		event.preventDefault();
		var datumOperacije = $('input[name="datumOperacije"]').val();
		datumOperacije = datumOperacije+"T00:00"
		var pacijent=idPac;
		
		
		$.post({
			url : 'operacije/dodajNovuOperaciju',
			contentType: 'application/json',
			data: JSON.stringify({datumOperacije,pacijent}),
			
			success: function(response){
				alert(response);
			},
			error: function(response){
				alert(response);
			},
			
			
			
		});
	
	});
	
}

