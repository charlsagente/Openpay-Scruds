/**
 * Created by carlos on 30/03/16.
 */

payments = {};
payments.config = {};
payments.config.BASE_URL = location.protocol + '//' + location.host + '/' + window.location.pathname.split("/")[1];


/*
Convierte objeto
{name:"email",value:"jkld@jfkl.com"} => {email:"jkld@jfkl.com"}
*/
function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

$(function(){

$("#btnSubmit").click(function (event) {
    var $form = $("#createform");
    var formdata = getFormData($form);
    $.ajax({
        type: "PUT",
        url: payments.config.BASE_URL + '/payments/openpay/plans',
        data: JSON.stringify(formdata),
        cache: false,
        dataType: 'json',
        success: function (data)
        {
            console.log(data);
            $("#responsecreation").text(JSON.stringify(data));
        },
        error: function (data) {
            //En caso de error mostramos una ventan a de error.
            console.log(data)
            $("#responsecreation").text("<br>"+JSON.stringify(data)+"</br>");
        }
    });

});

$('#btnEliminar').click(function(event){

    var r = confirm("¿Desea eliminar el plan?");
    if (r == true) {
        $.ajax({
            type: "DELETE",
            url: payments.config.BASE_URL + '/payments/openpay/plans?'+$.param({'idPlan':$('#idplann').val()}),

            cache: false,
            dataType: 'json',
            success: function (data)
            {
                if (data.success)
                    $("#idplann").val("");

                else
                    $("#deleteconfirm").text(data.description);
            },
            error: function (data) {
                //En caso de error mostramos una ventan a de error.

                $("#deleteconfirm").text(data.responseText);
            }
        });
    } else {
        console.log("cancel");
    }
});

});