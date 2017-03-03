/**
 * Created by Charls on 03/03/2017.
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

$(function() {

    $("#btnSubmit").click(function (event) {
        var $form = $("#refund");
        var formdata = getFormData($form);
        $.ajax({
            type: "POST",
            url: payments.config.BASE_URL + '/payments/openpay/refund',
            data: JSON.stringify(formdata),
            cache: false,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                $("#responsecreation").text(JSON.stringify(data));
            },
            error: function (data) {
                //En caso de error mostramos una ventan a de error.
                console.log(data)
                $("#responsecreation").text("<br>" + JSON.stringify(data) + "</br>");
            }
        });

    });


});