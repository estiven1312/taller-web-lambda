const wsRoot = "http://64.23.217.53:8080/"

async function deleteAmbiente(id,tipoAmbiente){
    await $.ajax({
        type: "DELETE",
        url: wsRoot + 'ambiente/deleteAmbiente/'+id+"?tipoAmbiente="+tipoAmbiente,
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





