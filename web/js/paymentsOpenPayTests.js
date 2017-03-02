/**
 * Created by Charls on 29/03/2016.
 */
var payments = {};
payments.config = {};
payments.config.BASE_URL = location.protocol + '//' + location.host + '/' + window.location.pathname.split("/")[1];
payments.config.idMerchant = 'maqn8tpsmsyckkdy1vif';
payments.config.publicId = 'pk_c0d8a62f60494046baedcdf413dd240f';


$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results == null) {
        return null;
    }
    else {
        return results[1] || 0;
    }
}

$(document).ready(function () {

    OpenPay.setId(payments.config.idMerchant);
    OpenPay.setApiKey(payments.config.publicId);
    OpenPay.setSandboxMode(true);
    //Se genera el id de dispositivo
    var deviceSessionId = OpenPay.deviceData.setup("payment-form", "deviceIdHiddenFieldName");

    //Para reutilizar esta misma pagina se usa el parametro payment, para diferenciar pago con (payment=2)
    // o sin suscripcion(payment=1)
    if ($.urlParam('payment') == "2") { //pago con suscripcion

        $('#atempselect').append("<label>Seleccione su plan: </label>" +
            "<select id=\"plan_selection\" name=\"plan_selection\">" +
            "<option value=\"0\">---------</option>" +
            "</select>");


        $.ajax({
            type: "GET",
            url: payments.config.BASE_URL + '/payments/openpay/plans',
            cache: false,
            dataType: 'json',
            success: function (data) //Si se ejecuta correctamente
            {
                console.log(data);

                $.each(data, function (i, item) {
                    $('#plan_selection').append($('<option>', {
                        value: item.id,
                        text: item.name + " (" + item.amount + " " + item.currency + " )",
                        amount: item.amount,
                        repeat_every: item.repeat_every,
                        repeat_unit: item.repeat_unit,
                        trial_days: item.trial_days
                    }));
                });
            },
            error: function (data) {
                //En caso de error mostramos una ventan a de error.
                console.log(data)
                $("#responsecreation").text("<br>" + JSON.stringify(data) + "</br>");
            }
        });
    }
    else if ($.urlParam('payment') == "1") {

        $('#atempselect').append("<h1>2 Licencias por demanda: $200</h1>");
    }
    else if ($.urlParam('payment') == "3") {

        $('#atempselect').append("<h1>Pago on Demand, pago inicial $200 MXN.</h1>");
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////77

    $('#pay-button').on('click', function (event) { //http://www.openpay.mx/docs/card-charge.html
        event.preventDefault();
        $("#pay-button").prop("disabled", true);
        if (payments.config.PROVIDER == "openpay")
            OpenPay.token.extractFormAndCreate('payment-form', sucess_callbak, error_callbak);
    });

    var sucess_callbak = function (response) {//la API de openpay ya validó la TDC y la guardó en su servidor


        var payment = {};
        var service;
        payment.token_id = response.data.id; //Lo único que se necesita enviar al servidor es el token_id de la TDC
        payment.device_sesion_id = deviceSessionId; //sistema antifraudes....
        payment.email = $('input[name=email]').val();
        payment.user_name = $('input[data-openpay-card=holder_name]').val();
        payment.preaprobed = $('#preaprobado').is(":checked");

        var param = $.urlParam('payment');
        if (param == "1") {//pago normal con TDC
            service = "payment/tdc";
            payment.amount = 200;
        }
        else if (param == "2") { //suscripción con TDC
            service = "payment/recurrent";
            payment.planId = $('#plan_selection').val()
        }
        else if (param == "3") { //Implementación de funcionalidad OnDemand
            service = "payment/ondemand";

        }


        $.ajax({
            type: "POST",
            url: payments.config.BASE_URL + '/payments/openpay/' + service,
            data: JSON.stringify(payment),
            cache: false,
            dataType: 'json',
            success: function (data) //Si se ejecuta correctamente
            {
                console.log(data);
                $(".bkng-tb-cntnt").empty();
                $(".bkng-tb-cntnt").html(JSON.stringify(data));

                $("#responsecreation").text = JSON.stringify(data);
                //window.location=(payments.config.BASE_URL+"/Return.html");
            },
            error: function (data) {
                //En caso de error mostramos una ventan a de error.
                console.log(data)
                $("#responsecreation").text = "<br>" + JSON.stringify(data) + "</br>";
                //window.location=(payments.config.BASE_URL+"/Cancel.html");
            }
        });

    };
    //Hubo problemas al validar la TDC con openpay.
    var error_callbak = function (response) {
        var desc = response.data.description != undefined ? response.data.description : response.message;
        alert("ERROR [" + response.status + "] " + desc);
        $("#pay-button").prop("disabled", false);
    };

///////////////////////////////////////////////////////////////
    /**
     * Validar el número de la TDC y comprobar que tipo de chip es.
     */
    $("input[data-openpay-card='card_number']").bind('change paste keyup', function () {
        var cardNumber = $('input[data-openpay-card=card_number]').val();

        if (OpenPay.card.validateCardNumber(cardNumber)) {

            var cardType = OpenPay.card.cardType(cardNumber);
            console.log(cardType);
            payments.config.PROVIDER = "openpay";


        }

    });
});

