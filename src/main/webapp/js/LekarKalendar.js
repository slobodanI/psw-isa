  //mapa lekova koje treba poslati u bazu
  var receptiMap; 
  
  var userID;
  
  //kreiranje kalendara
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      defaultDate: new Date(),
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: false,
      events: [
       
      ],
      eventClick: function(info) {
          if(info.event.title == "Pregled")
          {
        	  zapocniPregled(info.event.id);
          }
      }
    });

    calendar.render();
    
    //proveri ko je ulogovan
    $.get({
		url : 'api/whoIsLoggedIn',
		success : function(user) 
		{
			if(user.uloga != "Lekar")
			{
				logout();
			}
			
			userID = user.id;
			
			//kasnije promeniti da prikazuje samo odobrena odsustva
			prikaziOdsustva(user.id, function (odsustva){
				
				odsustva.forEach((item)=>{
					var i;
					if(item.tip == 'odsustvo')
					{
						i = 'Odsustvo';
					}
					else
					{
						i = 'Godišnji odmor';
					}
					calendar.addEvent({
		                id: item.id,
						title: i,
		                start: item.pocetak,
		                end: item.kraj,
		                backgroundColor: "green",
		                allDay: true
					})
				})
				
			})
			
			prikaziPreglede(user.id, function (pregledi){
				pregledi.forEach((item)=>{
					calendar.addEvent({
		                id: item.id,
						title: "Pregled",
		                start: item.pocetak,
		                end: item.kraj,
		                backgroundColor: "blue",
		                allDay: false,
		                
					})
				})
			})
			
			prikaziOperacije(user.id, function (operacije){
				operacije.forEach((item)=>{
					calendar.addEvent({
		                id: item.id,
						title: "Operacija",
		                start: item.pocetak,
		                end: item.kraj,
		                backgroundColor: "red",
		                allDay: false
					})
				})
			})
			
		},
		error : function()
		{
			alert('Greska pri dobavljanju informacija o ulogovanoj osobi');
		}
	});
    
  });
  
  function prikaziOdsustva(id, callback)
  {
	  $.get({
			url : '/api/lekar/getLekarOdsustva/' + id,
			success : function(odsustva){
				//alert(odsustva);
				callback(odsustva);
			},
			error : function(){
				alert('Greška pri dobavljanju lekarovih odsustava');
			}
				
	  })
  }
  
  function prikaziPreglede(id, callback)
  {
	  $.get({
			url : '/api/lekar/getLekarPregledi/' + id,
			success : function(pregledi){
				callback(pregledi);
			},
			error : function(){
				alert('Greška pri dobavljanju lekarovih pregleda');
			}
				
	  })
  }
  
  function prikaziOperacije(id, callback)
  {
	  $.get({
			url : '/api/lekar/getLekarOperacije/' + id,
			success : function(operacije){
				callback(operacije);
			},
			error : function(){
				alert('Greška pri dobavljanju lekarovih operacija');
			}
				
	  })
  }
  
  function zapocniPregled(pregledID)
  {
	  sakrijKarton();
	  $('#divPokreniPregled').show();
	  $('#calendar').hide();
	  //alert('get');
	  
	  $.get({
			url : 'pregledi/vratiPregledKalendar/' + pregledID,
			success : function(pregled)
			{
				  $('#idPregledaTxt').val(pregled.id);
			 	  $('#idPacijentaTxt').val(pregled.pac_id);
			 	  $('#lboPacijentaTxt').val(pregled.lbo);
			 	  $('#imePacijentaTxt').val(pregled.ime);
			 	  $('#prezimePacijentaTxt').val(pregled.prezime);
			 	  
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
			},
			error : function(){
				alert('Greška pri dobavljanju pacijentovih operacija');
			}	
	  })
	  
	  
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
	  $('#divPokreniPregled').hide();
	  
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
  })