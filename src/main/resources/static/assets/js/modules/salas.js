let indexSelectedSala = null;
$(document).ready(async function () {

    await getSalas();

    $("#buttonNuevaReservaSala").click(async function () {
        console.log( $("#fechaSalaReservaRequest").val());
        console.log( $("#horaInicioSalaReservaRequest").val());
        console.log( $("#horaFinSalaReservaRequest").val());
        let body = {
            date: $("#fechaSalaReservaRequest").val(),
            initHour: $("#horaInicioSalaReservaRequest").val(),
            endHour: $("#horaFinSalaReservaRequest").val(),
            idSala: $("#numeroSalaReservaRequest").val(),

        }
        await $.ajax({
            type: "POST",
            url: wsRoot + 'sala/reservar',
            data: JSON.stringify(body),
            contentType: 'application/json',
            error: function () {
                alert("Algo ocurrió. Recargue la página, por favor");
            },
            success: function (data, href, xhr) {
                if(data.STATUS === "OK"){
                    setTimeout(function(){window.location = window.location}, 1000);
                }

            }
        });
    });
    $("#numeroSalaReservaRequest").focus(async function () {
        console.log( $("#fechaSalaReservaRequest").val());
        console.log( $("#horaInicioSalaReservaRequest").val());
        console.log( $("#horaFinSalaReservaRequest").val());
        let body = {
            date: $("#fechaSalaReservaRequest").val(),
            initHour: $("#horaInicioSalaReservaRequest").val(),
            endHour: $("#horaFinSalaReservaRequest").val(),
        }
        await $.ajax({
            type: "GET",
            url: wsRoot + 'sala/findSalasDisponibles',
            data: body,
            contentType: 'application/json',
            error: function () {
                alert("Algo ocurrió. Recargue la página, por favor");
            },
            success: function (data, href, xhr) {
                    drawComboSalas("#numeroSalaReservaRequest", data);
            }
        });
    });
});


function drawComboSalas(idComboBox, list) {
    $(idComboBox).append(`
            <option selected disabled>Seleccione</option>
        `);
    list.forEach(function (val, i) {
        $(idComboBox).append(`
            <option value="${val.id}">${val.id}</option>
        `);
    });
}







async function dataOfReservationSala(index){
    await $.ajax({
        type: "GET",
        url: wsRoot + 'sala/getReservaSala/' + index,
        contentType: 'application/json',
        error: function () {
            alert("Algo ocurrió. Recargue la página, por favor");
        },
        success: async function (data, href, status) {
            console.log(data);
            indexSelectedSala = data.id;
            drawInfoReadOnlySala(data);

        }
    });
}

async function getSalas(){
    await $.ajax({
        type: "GET",
        url: wsRoot + 'sala/getSalas',
        contentType: 'application/json',
        error: function () {
            alert("Algo ocurrió. Recargue la página, por favor");
        },
        success: function (data, href, xhr) {
            drawComboSalas("#numeroSalaReserva", data);
        }
    });
}
async function deleteReservationSala(){
    await $.ajax({
        type: "DELETE",
        url: wsRoot + 'sala/deleteReserva/'+indexSelectedSala,
        contentType: 'application/json',
        error: function () {
            alert("Algo ocurrió. Recargue la página, por favor");
        },
        success: function (data, href, xhr) {
            if(data.STATUS === "OK"){
                setTimeout(function(){window.location = window.location}, 1000);
            }

        }
    });
}



function drawInfoReadOnlySala(data){
    $("#fechaSalaReserva").val(getDataString(data.horaInicio));
    $("#horaInicioSalaReserva").val(getHours(data.horaInicio));
    $("#horaFinSalaReserva").val(getHours(data.horaFin));
    $("#numeroSalaReserva").val(data.sala.id)
}




function getDataString(date) {
    try {
        const fecha = new Date(date);
        let day = ("0" + fecha.getDate()).slice(-2);
        let month = ("0" + (fecha.getMonth() + 1)).slice(-2);
        let fechaData = fecha.getFullYear()  +"-"+ (month) + "-" +(day);
        console.log(fechaData);
        return fechaData;
    } catch (ex) {
        return "";
    }

}

function getHours(date) {
    try {
        const fecha = new Date(date);
        let hour = ("0" + fecha.getHours()).slice(-2);
        let min = ("0" + fecha.getMinutes()).slice(-2);
        console.log(hour+":"+min);
        return hour+":"+min;

    } catch (ex) {
        return "";
    }

}