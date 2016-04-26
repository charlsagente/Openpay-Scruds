/**
 * Created by carlos on 1/04/16.
 */
/**
 * Created by carlos on 1/04/16.
 */

$(function(){
    $('#btnEnviar').click(function(event){
        var $form = $("#storeform");
        var formdata = getFormData($form);

        $.ajax({
            type: "POST",
            url: payments.config.BASE_URL + '/payments/openpay/payment/bank',
            data: JSON.stringify(formdata),
            cache: false,
            dataType: 'json',
            success: function (data)
            {
                if (data.id)
                //http://www.openpay.mx/docs/store-charge.html
                    location.href="https://sandbox-dashboard.openpay.mx"+"/spei-pdf/"+"maqn8tpsmsyckkdy1vif/"+data.id;

                else
                    $("#deleteconfirm").text(data.description);
            },
            error: function (data) {
                //En caso de error mostramos una ventan a de error.

                $("#deleteconfirm").text(data.responseText);
            }
        });

    });

});