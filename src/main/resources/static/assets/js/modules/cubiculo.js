const wsRoot = "http://64.23.217.53:8080/"
let indexSelected = null;
let initDate = null;
let endDate = null;
$(document).ready(async function () {


    $("#registrarReservaNueva").click(async function () {
        console.log( $("#fechaEstacionamientoReservaRequest").val());
        console.log( $("#horaInicioEstacionamientRequest").val());
        console.log( $("#horaFinEstacionamientoRequest").val());
        let body = {
            date: $("#fechaEstacionamientoReservaRequest").val(),
            initHour: $("#horaInicioEstacionamientRequest").val(),
            endHour: $("#horaFinEstacionamientoRequest").val(),
            idAmbiente: $("#numeroEstacionamientoReservaRequest").val(),

        }
        await $.ajax({
            type: "POST",
            url: wsRoot + 'cubiculo/reservar',
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
    $("#buttonEditarReservaEstacionamiento").click(async function () {
        console.log( $("#fechaSalaReservaRequest").val());
        console.log( $("#horaInicioSalaReservaRequest").val());
        console.log( $("#horaFinSalaReservaRequest").val());
        let body = {
            date: $("#fechaEstacionamientoReserva").val(),
            initHour: $("#horaInicioEstacionamientoReserva").val(),
            endHour: $("#horaFinEstacionamientoReserva").val(),
            idAmbiente: $("#numeroEstacionamientoReserva").val(),
            idReserva: indexSelectedSala,

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
            url: wsRoot + 'cubiculo/findCubiculosDisponibles',
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
    $(idComboBox).empty();
    $(idComboBox).append(`
            <option selected disabled>Seleccione</option>
        `);
    list.forEach(function (val, i) {
        $(idComboBox).append(`
            <option value="${val.id}">${val.nombreAmbiente}</option>
        `);
    });
}







async function dataOfReservation(index){
    await $.ajax({
        type: "GET",
        url: wsRoot + 'cubiculo/getReservaCubiculo/' + index,
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
        url: wsRoot + 'cubiculo/getCubiculos',
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
        url: wsRoot + 'cubiculo/deleteReserva/'+indexSelected,
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
    $("#fechaEstacionamientoReserva").val(getDataString(data.init));
    $("#horaInicioEstacionamientoReserva").val(getHours(data.end));
    $("#horaFinEstacionamientoReserva").val(getHours(data.init));
    $("#numeroEstacionamientoReserva").val(data.ambiente.id)
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
async function dataOfReservationEstacionamiento(index){
    await $.ajax({
        type: "GET",
        url: wsRoot + 'cubiculo/getReservaCubiculo/' + index,
        contentType: 'application/json',
        error: function () {
            alert("Algo ocurrió. Recargue la página, por favor");
        },
        success: async function (data, href, status) {
            console.log(data);
            indexSelectedSala = data.id;
            await getEstacionamientosDisponiblesByIndex(data.id);
            drawInfoReadOnly(data);

        }
    });
}

async function  getEstacionamientosDisponiblesByIndex(index){
    await $.ajax({
        type: "GET",
        url: wsRoot + 'cubiculo/findCubiculosDisponiblesEdit/' + index,
        contentType: 'application/json',
        error: function () {
            alert("Algo ocurrió. Recargue la página, por favor");
        },
        success: async function (data, href, status) {
            console.log(data);
            drawCombo("#numeroEstacionamientoReserva", data);

        }
    });
}