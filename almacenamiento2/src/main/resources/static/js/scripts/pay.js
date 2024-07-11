const url = "/api/pagos";

function save(bandera) {
    $("#modal-update").modal("hide");
    let id = $("#guardar").data("id");
    let pago = {
        id: id,
        documentoInquilino: $("#documentoInquilino").val(),
        fecha: $("#fecha").val(),  // Asegúrate de que el formato de fecha sea correcto
        montoPago: $("#montoPago").val(),
        metodoPago: $("#metodoPago").val(),
        descripcion: $("#descripcion").val(),
        activo: $("#activo").prop("checked") // Si es un checkbox, usa prop
    };
    let metodo = (bandera == 1) ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: url,
        data: JSON.stringify(pago),
        dataType: "text",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if (data == 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'El pago ya está registrado',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                let texto = (bandera == 1) ? "guardado" : "actualizado";
                getTabla();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha ' + texto + ' el pago',
                    showConfirmButton: false,
                    timer: 1500
                });
                clear();
            }
        },
    }).fail(function () {
        Swal.fire({
            icon: 'error',
            title: 'Error en el servidor',
            text: 'No se pudo completar la operación',
            showConfirmButton: false,
            timer: 1500
        });
    });
}

function deleteFila(id) {
    $.ajax({
        type: "DELETE",
        url: url + "/" + id,
        cache: false,
        timeout: 600000,
        success: function () {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado el pago',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error en el servidor',
                text: 'No se pudo eliminar el pago',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function getTabla() {
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        cache: false,
        success: function (data) {
            let t = $("#tablaPagos").DataTable();
            t.clear().draw(false);

            let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
                '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';

            $.each(data, function (index, pago) {
                t.row.add([pago.id, pago.documentoInquilino, pago.fecha, pago.montoPago, pago.metodoPago, pago.descripcion, pago.activo ? 'Sí' : 'No', botonera]);
            });

            t.draw(false);
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error en el servidor',
                text: 'No se pudo obtener la lista de pagos',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function getFila(id) {
    $.ajax({
        type: "GET",
        url: url + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Pago");
            $("#documentoInquilino").val(data.documentoInquilino);
            $("#fecha").val(data.fecha); // Asegúrate de que el formato de fecha sea correcto
            $("#montoPago").val(data.montoPago);
            $("#metodoPago").val(data.metodoPago);
            $("#descripcion").val(data.descripcion);
            $("#activo").prop("checked", data.activo);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error en el servidor',
                text: 'No se pudo obtener los datos del pago',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function clear() {
    $("#modal-title").text("Nuevo Pago");
    $("#documentoInquilino").val("");
    $("#fecha").val(""); // Asegúrate de que el formato de fecha sea correcto
    $("#montoPago").val("");
    $("#metodoPago").val("");
    $("#descripcion").val("");
    $("#activo").prop("checked", false); // Desmarcar el checkbox
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

$(document).ready(function () {

    $("#tablaPagos").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, width: "5%" },
            { targets: 1, width: "15%" },
            { targets: 2, width: "15%" },
            { targets: 3, width: "15%" },
            { targets: 4, width: "15%" },
            { targets: 5, width: "20%" },
            { targets: 6, width: "10%" },
            { targets: 7, orderable: false, width: "5%" }
        ],
    });

    clear();

    $("#nuevo").click(function () {
        clear();
    });

    $("#guardar").click(function () {
        let bandera = $("#guardar").data("bandera");
        save(bandera);
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Pago',
            text: '¿Está seguro de querer eliminar este pago?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $(this).closest('tr').find('td:eq(0)').text();
                deleteFila(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        let id = $(this).closest('tr').find('td:eq(0)').text();
        getFila(id);
    });

    getTabla();
});
