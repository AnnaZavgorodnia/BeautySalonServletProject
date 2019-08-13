document.addEventListener('DOMContentLoaded', function() {

    let calendarEl = document.getElementById('calendar');

    (async () => {
        const rawResponse = await fetch('http://localhost:8888/app/api/all_appointments');
        const content = await rawResponse.json();

        console.log(content);

        const ar = content.map(el => {
            let bla = {};
            bla.title = "Master: " + el.master.fullName +
                "\nClient: " + el.client.fullName;
            let date = new Date(el.appDate.year,el.appDate.month-1,el.appDate.day,el.appTime.hour-3);
            bla.start = date.toISOString();
            console.log(bla.start);
            return bla;
        });
        console.log(ar);

        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: [ 'timeGrid','dayGrid' ],
            height: 675,
            header: {
                left: 'dayGridMonth,timeGridWeek,timeGridDay custom1',
                center: 'title',
                right: 'custom2 prevYear,prev,next,nextYear'
            },
            minTime: "09:00:00",
            maxTime: "18:00:00",
            weekends: false,
            events: ar,
            navLinks: true,
            eventLimit: true,
            views: {
                dayGrid: {
                    eventLimit: 3
                },
                timeGrid: {
                    eventLimit: 2
                }
            }
        });

        calendar.render();

    })();
});