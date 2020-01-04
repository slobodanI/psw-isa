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
       
      ]
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
		                allDay: false
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