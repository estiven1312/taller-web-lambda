const wsRoot = "http://64.23.217.53:8080/"
$(document).ready(async function () {

    $("#btnActualizarPerfil").click(async function () {
        console.log("AAAAAAAAAAAAAAAAAAA");
        let body = {
            id: $("#usuarioId").val(),
            nombres: $("#nombresGerente").val(),
            apellidos: $("#apellidosGerente").val(),
            telefono: $("#telefonoGerente").val(),
            numeroDocumentoIdentificacion: $("#numeroDocumentoIdentidadGerente").val(),
            correo: $("#correoGerente").val(),
            username: $("#username").val(),
            password:  $("#contraseniaGerente").val(),
        }
        await $.ajax({
            type: "POST",
            url: wsRoot + 'user/actualizar',
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
});


