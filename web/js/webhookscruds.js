/**
 * Created by carlos on 1/04/16.
 */
$(function(){

    $("#btnEnviar").click(function (event) {
        var $form = $("#hookform");
        var formdata = getFormData($form);
        formdata.events=[]
        formdata.events.push('charge.succeeded');
        formdata.events.push('payout.succeeded');
        formdata.events.push('transfer.succeeded');
        formdata.events.push('spei.received');
        formdata.events.push('order.payment.received');
        formdata.events.push('order.completed');

        $.ajax({
            type: "PUT",
            url: payments.config.BASE_URL + '/payments/openpay/webhooks',
            data: JSON.stringify(formdata),
            cache: false,
            dataType: 'json',
            success: function (data)
            {
                if (data.id){
                    console.log(data);
                    $("#responsecreation").text(JSON.stringify(data));
                }
                else
                    console.log(data.description)
            },
            error: function (data) {
                //En caso de error mostramos una ventan a de error.
                console.log(data)
                $("#responsecreation").text("<br>"+JSON.stringify(data)+"</br>");
            }
        });

    });

    $("#btnEliminar").click(function (event) {
        var $form = $("#hookdelete");
        var formdata = getFormData($form);
        $.ajax({
            type: "DELETE",
            url: payments.config.BASE_URL + '/payments/openpay/webhooks',
            data: JSON.stringify(formdata),
            cache: false,
            dataType: 'json',
            success: function (data)
            {
                if (data.id){
                    console.log(data);
                    $("#responsecreation").text(JSON.stringify(data));
                }
                else
                    console.log(data.description)
            },
            error: function (data) {
                //En caso de error mostramos una ventan a de error.
                console.log(data)
                $("#responsecreation").text("<br>"+JSON.stringify(data)+"</br>");
            }
        });
    });

});