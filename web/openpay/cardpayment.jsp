<%--
  Created by IntelliJ IDEA.
  User: carlos
  Date: 31/03/16
  Time: 04:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pago con TDC</title>
    <link rel="stylesheet" type="text/css" href="../css/formpaymentstyles.css">

    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://openpay.s3.amazonaws.com/openpay.v1.min.js"></script>
    <script type='text/javascript'
            src="https://openpay.s3.amazonaws.com/openpay-data.v1.min.js"></script>
    <script type="text/javascript" src="../js/paymentsOpenPayTests.js"></script>
</head>
<body>
<br>
<div class="bkng-tb-cntnt">
    <div class="pymnts">
        <form action="#" method="POST" id="payment-form">
            <input type="hidden" name="token_id" id="token_id">
            <div class="pymnt-itm card active">
                <h2>Tarjeta de crédito o débito</h2>
                <div class="pymnt-cntnt">
                    <div class="sctn-row" id="atempselect">

                    </div>
                    <div class="card-expl">
                        <div class="credit"><h4>Tarjetas de crédito</h4></div>
                        <div class="debit"><h4>Tarjetas de débito</h4></div>
                    </div>



                    <div class="sctn-row">
                        <div class="sctn-col l">
                            <label>Nombre del titular</label><input type="text" placeholder="Como aparece en la tarjeta" autocomplete="off" data-openpay-card="holder_name">
                        </div>
                        <div class="sctn-col">
                            <label>Correo electrónico</label><input type="email" autocomplete="off" name="email"></div>
                    </div>
                    <div class="sctn-row">

                        <div class="sctn-col l">
                            <label>Número de tarjeta</label><input type="text" autocomplete="off" data-openpay-card="card_number">
                        <div id="cardtype"></div></div>

                        <div class="sctn-col">
                            <label>Fecha de expiración</label>
                            <div class="sctn-col half"><input type="text" placeholder="Mes" data-openpay-card="expiration_month"></div>
                            <div class="sctn-col half"><input type="text" placeholder="Año" data-openpay-card="expiration_year"></div>
                        </div>
                    </div>
                    <div class="sctn-row">
                        <div class="sctn-col cvv l"><label>Código de seguridad</label>
                            <div class="sctn-col half l"><input type="text" placeholder="3 dígitos" autocomplete="off" data-openpay-card="cvv2"></div>
                        </div>
                        <div class="sctn-col">
                            <label>Preaprobar:</label>
                            <div class="sctn-col half">
                            <input type="checkbox" name="preaprobado" id="preaprobado">
                            </div>

                        </div>
                    </div>
                    <div class="sctn-row">
                        <div class="sctn-col l">
                            <label>Cantidad</label><input type="text" autocomplete="off" id="amount" value="300">
                        </div>

                        <div class="sctn-col">
                            <label>MSI:</label>

                            <input type="radio" name="msi" value="3">3<br>
                            <input type="radio" name="msi" value="6">6<br>
                            <input type="radio" name="msi" value="9">9<br>
                            <input type="radio" name="msi" value="12">12<br>
                            <input type="radio" name="msi" value="18">18<br>
                        </div>

                    </div>

                    <div class="openpay"><div class="logo">Transacciones realizadas vía:</div>
                        <div class="shield">Tus pagos se realizan de forma segura con encriptación de 256 bits</div>
                    </div>
                    <div class="sctn-row">
                        <a class="button rght" id="pay-button">Pagar</a>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>
</body>
</html>
