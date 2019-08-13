const time = ["09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00","16:00","17:00"];

document.addEventListener('DOMContentLoaded', function() {

    let elems = document.querySelectorAll('select');
    let instances = M.FormSelect.init(elems,{});

    var elems_modal = document.querySelectorAll('.modal');
    var instances_modal = M.Modal.init(elems_modal,{startingTop: "4%",endingTop: "30%"});

    document.getElementById("select-service").addEventListener("change",function(e){
        console.log(e.target);
        let inp = document.getElementById('select-service').M_FormSelect.input.value;
        console.log(inp);
        document.getElementById("service-modal").innerHTML = inp;
        let val = document.getElementById(inp);
        document.getElementById("price-modal").innerHTML = val.dataset.price + "UAH";
        document.getElementById("select_label").style.color = "black";
    });

    Date.prototype.addDays = function(days) {
        let date = new Date(this.valueOf());
        date.setDate(date.getDate() + days);
        return date;
    };

    let date = new Date();

    M.Datepicker.init(document.getElementsByClassName('datepicker')[0],
        {
            disableWeekends: true,
            firstDay: 1,
            minDate: new Date(),
            maxDate: date.addDays(14),
            onClose: ondraw
        });


    document.getElementById("button-book").addEventListener("click", makeApp);
});

async function getApps(id,date) {
    let response = await fetch(`http://localhost:8888/app/api/appointments?masterId=${id}&date=${date}`);
    return await response.json();
}

const ondraw = async function fullTime(){

    clearTime();

    let instance = M.Datepicker.getInstance(document.getElementsByClassName('datepicker')[0]);

    let date = new Date(instance.date);
    date.setHours(date.getHours()+3);

    let master_id = document.getElementById("master_id").value;

    const data = await getApps(master_id, date.toISOString());
    console.log(data);

    time.forEach(el => {
        createSchedule(el, data);
    });

};

function createSchedule(time, data){
    let timeRow = document.createElement("div");
    timeRow.classList = "time__row";
    let title = document.createElement("h4");
    title.innerHTML = time;
    let btn = document.createElement("button");
    btn.addEventListener("click", fullModal);
    //todo
    btn.innerHTML = "book";
    btn.setAttribute("class","btn-black btn-active modal-trigger");
    btn.setAttribute("id",time);
    btn.dataset.time = time;
    data.forEach(el => {
        if(el.appTime.hour-3 === 9)
            datetext = "09";
        else
            datetext = (el.appTime.hour-3).toString();
        if(datetext === time.substring(0,2)){
            console.log(true);
            btn.setAttribute("disabled",true);
            btn.setAttribute("class","btn-black btn-disabled");
        }
    });
    timeRow.appendChild(title);
    timeRow.appendChild(btn);
    let timeTable = document.getElementById("time-table");
    timeTable.appendChild(timeRow);
}

function clearTime(){
    document.getElementById("time-table").innerHTML = "";
}

function fullModal(e) {
    let serviceName = document.getElementById('select-service').M_FormSelect.input.value;
    let serviceElement = document.getElementById(serviceName);
    if(serviceElement == null){
        console.log(true);
        document.getElementById("select_label").style.color = "red";

    } else{

        let someDate = document.getElementsByClassName('datepicker')[0];
        let instance = M.Datepicker.getInstance(someDate);
        let idate = instance.date;

        let finalDate = new Date(idate.getFullYear(),
            idate.getMonth(),
            idate.getDate(),
            e.target.dataset.time.substring(0,2));
        console.log(finalDate);
        finalDate.setHours(finalDate.getHours()+3);
        let appDate = finalDate.toISOString().substring(0,10);
        let appTime = finalDate.toISOString().substring(11,19);

        let date_el = document.getElementById("date-modal");
        let time_el = document.getElementById("time-modal");
        date_el.value = appDate;
        time_el.value = appTime;
        date_el.innerHTML = appDate;
        time_el.innerHTML = appTime.substring(0,5);

        var inst = M.Modal.getInstance(document.getElementById("modal"));
        inst.open();
    }
}

function makeApp(e){

    let master_id = document.getElementById("master_id").value;

    let serviceName = document.getElementById('select-service').M_FormSelect.input.value;
    let serviceElement = document.getElementById(serviceName);
    if(serviceElement == null){
        console.log(true);
        document.getElementById("select_label").style.color = "red";

    } else{

        let serviceId = serviceElement.value;
        let appDate = document.getElementById("date-modal").value;
        let appTime = document.getElementById("time-modal").value;

        console.log(master_id);
        console.log(appDate);
        console.log(appTime);
        console.log(serviceId);

        fetch(`http://localhost:8888/app/create_appointment?masterId=${master_id}&appDate=${appDate}&appTime=${appTime}&serviceId=${serviceId}`)
            .then(data => {
                document.getElementById(appTime.substring(0,5)).setAttribute("class","btn-black btn-disabled");
            })
            .catch(e => console.log(e));
    }
}
