const wsRoot = "http://localhost:8080/"
let indexSelected = null;
let initDate = null;
let endDate = null;
$(document).ready(async function () {

    await getEstacionamientos();

    $("#registrarReservaNueva").click(async function () {
        console.log( $("#fechaEstacionamientoReservaRequest").val());
        console.log( $("#horaInicioEstacionamientRequest").val());
        console.log( $("#horaFinEstacionamientoRequest").val());
        let body = {
            date: $("#fechaEstacionamientoReservaRequest").val(),
            initHour: $("#horaInicioEstacionamientRequest").val(),
            endHour: $("#horaFinEstacionamientoRequest").val(),
            idEstacionamiento: $("#numeroEstacionamientoReservaRequest").val(),

        }
        await $.ajax({
            type: "POST",
            url: wsRoot + 'estacionamiento/reservar',
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
    $("#numeroEstacionamientoReservaRequest").focus(async function () {
        console.log( $("#fechaEstacionamientoReservaRequest").val());
        console.log( $("#horaInicioEstacionamientRequest").val());
        console.log( $("#horaFinEstacionamientoRequest").val());
        let body = {
            date: $("#fechaEstacionamientoReservaRequest").val(),
            initHour: $("#horaInicioEstacionamientRequest").val(),
            endHour: $("#horaFinEstacionamientoRequest").val()
        }
        await $.ajax({
            type: "GET",
            url: wsRoot + 'estacionamiento/findEstacionamientosDisponibles',
            data: body,
            contentType: 'application/json',
            error: function () {
                alert("Algo ocurrió. Recargue la página, por favor");
            },
            success: function (data, href, xhr) {
                    drawCombo("#numeroEstacionamientoReservaRequest", data);
            }
        });
    });
});


function drawCombo(idComboBox, list) {
    $(idComboBox).append(`
            <option selected disabled>Seleccione</option>
        `);
    list.forEach(function (val, i) {
        $(idComboBox).append(`
            <option value="${val.id}">${val.numero}</option>
        `);
    });
}







async function dataOfReservation(index){
    await $.ajax({
        type: "GET",
        url: wsRoot + 'estacionamiento/getReservaEstacionamiento/' + index,
        contentType: 'application/json',
        error: function () {
            alert("Algo ocurrió. Recargue la página, por favor");
        },
        success: async function (data, href, status) {
            console.log(data);
            indexSelected = data.id;
            drawInfoReadOnly(data);

        }
    });
}

async function getEstacionamientos(){
    await $.ajax({
        type: "GET",
        url: wsRoot + 'estacionamiento/getEstacionamientos',
        contentType: 'application/json',
        error: function () {
            alert("Algo ocurrió. Recargue la página, por favor");
        },
        success: function (data, href, xhr) {
            drawCombo("#numeroEstacionamientoReserva", data);
        }
    });
}
async function deleteReservation(){
    await $.ajax({
        type: "DELETE",
        url: wsRoot + 'estacionamiento/deleteReserva/'+indexSelected,
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



function drawInfoReadOnly(data){
    $("#fechaEstacionamientoReserva").val(getDataString(data.fechaInicio));
    $("#horaInicioEstacionamientoReserva").val(getHours(data.fechaInicio));
    $("#horaFinEstacionamientoReserva").val(getHours(data.fechaFin));
    $("#numeroEstacionamientoReserva").val(data.estacionamiento.id)
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