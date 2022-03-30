$(document).ready(function() {
    var productCount = 0;
    var resultJSON = null;

    function fetchProducts() {
        $.ajax({
            type: "GET",
            url: "/api/v1/products",
            success: function(result) {
                resultStr = "{\"result\":" + JSON.stringify(result) + "}";
                resultJSON = JSON.parse(resultStr);
                localStorage.setItem("resultStr", resultStr);
                productCount = resultJSON["result"].length;
                localStorage.setItem("productCount", productCount);
                var str="";
                for(var i=0;i<productCount;i++) {
                    str += "<div class='float-child card-body border mt-n1 py-4 text-center'> <h2 class='h5 mb-1'>"+resultJSON["result"][i]["productName"]+"</h2> <span class='d-block mb-3 font-size-xs text-muted'>Price <span class='font-weight-semibold'>"+resultJSON["result"][i]["price"]+"</span></span> <div class='div'> <p class='showError' style='display:none;'>Only <span>"+resultJSON["result"][i]["quantity"]+"</span> are left.</p> <input type='button' id='down' value='-'> <input class='item' id='item_"+resultJSON["result"][i]["productId"]+"' style='width:50px;' type='text' value='0' max='"+resultJSON["result"][i]["quantity"]+"'> <input type='button' id='up' value='+'> <br> </div> </div>";

                }
                $(".products-container").append(str);
            },
            error: function(e) {
                console.log("ERROR: ", e);
            },
        });
    }
    fetchProducts();

});

$(document).on("click", '[id^="up"]', function() {
    var itemId = $(this).closest('div').find('.item').attr('id');
    value = $("#" + itemId).attr('value');
    value = isNaN(value) ? parseInt("0") : parseInt(value);
    max = $("#" + itemId).attr('max');
    if (value <= max - 1) {
        value = parseInt(value) + parseInt("1");
    } else {
        $(this).closest('div').find('.showError').attr("style", "display:block");
    }
    $("#" + itemId).attr("value", value);
    $("#" + itemId).val(value);
});

$(document).on("click", '[id^="down"]', function() {
    var itemId = $(this).closest('div').find('.item').attr('id');
    value = $("#" + itemId).attr('value');
    value = isNaN(value) ? parseInt("0") : parseInt(value);
    value = parseInt(value) - parseInt("1");
    if (value < 0) {
        value = parseInt("0");
    }
    if (value <= max) {
        $(this).closest('div').find('.showError').attr("style", "display:none");
    }
    $("#" + itemId).attr("value", value);
    $("#" + itemId).val(value);
});

$(document).on('input', '[id^="item_"]', function() {
    var value = $(this).val();
    var max = $(this).attr('max');
    if (parseInt(value) > parseInt(max)) {
        value = parseInt(max);
        $(this).closest('div').find('.showError').attr("style", "display:block");
    } else {
        $(this).closest('div').find('.showError').attr("style", "display:none");
    }
    $(this).attr("value", value);
    $(this).val(value);
});

$(document).on('change', '[id^="item_"]', function() {
    var value = $(this).val();
    var max = $(this).attr('max');
    if (parseInt(value) > parseInt(max)) {
        $(this).closest('div').find('.showError').attr("style", "display:block");
    } else {
        $(this).closest('div').find('.showError').attr("style", "display:none");
    }
});

$(document).on("click", "#fetchUserInput", function() {
    var productCount = localStorage.getItem("productCount");
    var resultStr = localStorage.getItem("resultStr");
    var resultJSON = JSON.parse(resultStr);
    var str = "[";
    var total = parseFloat(0);
    for (var i = 0; i < productCount; i++) {
        if ($('#item_' + resultJSON["result"][i]["productId"]).attr('value') != 0) {

            str += "{ \"productId\":\"" + resultJSON["result"][i]["productId"] + "\",\"quantity\":\"" + $('#item_' + resultJSON["result"][i]["productId"]).attr('value') + "\"},";
            console.log(str);
            total += parseFloat($('#item_' + resultJSON["result"][i]["productId"]).attr('value')) * parseFloat(resultJSON["result"][i]["price"]);
        }
    }
    str = str.slice(0, -1);
    str += "]"
    localStorage.setItem("orderJSONStr", str);
    localStorage.setItem("orderTotal", total);
});

$(document).on("click", "#placeOrder", function() {
    var str = localStorage.getItem("orderJSONStr");
    var total = localStorage.getItem("orderTotal");
    var firstName = $('#firstname').attr('value');
    var lastName = $('#lastname').attr('value');
    var address = $('#address').attr('value');
    var country = $('#country').attr('value');
    var postalCode = $('#postalcode').attr('value');
    var city = $('#city').attr('value');
    var province = $('#province').attr('value');
    var address = "[{\"address\": \""+address+"\", \"city\": \""+ city+"\", \"province\": \""+province+"\", \"country\": \""+country+"\", \"postalCode\": \""+postalCode+"\"}]";
    var strJSON = JSON.parse(str);
    var addressJSON = JSON.parse(address);
    var payload = {
        "userId": 1,
        "order": strJSON,
        "total": parseFloat(total).toFixed(2),
        "address": addressJSON
    };

    $.ajax({
        type: "POST",
        url: "/api/v1/order",
        contentType: "application/json",
        dataType: "json",
        async: false,
        data: JSON.stringify(payload),
        success: function(result) {
            location.reload();
             $(".orderPlaced").attr("style", "display:block");
        },
        error: function(e) {
            console.log("ERROR: ", e);
        },
    });
});

$(document).on('change', '.field__input', function() {
    var value = $(this).val();
    $(this).attr("value", value);
});