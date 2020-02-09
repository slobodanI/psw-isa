var params = new URL(location.href).searchParams;
var idSale = params.get("idSale");


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

			
			
			//kasnije promeniti da prikazuje samo odobrena odsustva
			prikaziZauzetost(idSale, function (odsustva){
				
				odsustva.forEach((item)=>{

					calendar.addEvent({
		                id: item.id,
						title: 'Zauzeto',
		                start: item.pocetak,
		                end: item.kraj,
		                backgroundColor: "green",
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
  
  
function prikaziZauzetost(id, callback)
  {
	  $.get({
			url : '/api/sala/getZauzetostSala/' + id,
			success : function(odsustva){
				//alert(odsustva);
				callback(odsustva);
			},
			error : function(){
				alert('Greška pri zauzetosti sala');
			}
				
	  })
  } 

function nazad(){
	window.location = "./prikaziZahteve.html";
}
  
  
  
  