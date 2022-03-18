$(document).ready(function() {
    var productCount = 0;
    var resultJSON = null;

    function fetchProducts() {
        $.ajax({
            type: "GET",
            url: "/api/v1/getProducts",
            success: function(result) {
                resultStr = "{\"result\":" + JSON.stringify(result) + "}";
                resultJSON = JSON.parse(resultStr);
                localStorage.setItem("resultStr", resultStr);
                productCount = resultJSON["result"].length;
                localStorage.setItem("productCount", productCount);
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

$(document).on("click", "#placeOrder", function() {
    var productCount = localStorage.getItem("productCount");
    var resultStr = localStorage.getItem("resultStr");
    var resultJSON = JSON.parse(resultStr);
    var str = "[";
    var total = parseFloat(0);
    for (var i = 0; i < productCount; i++) {
        if ($('#item_' + resultJSON["result"][i]["productId"]).attr('value') != 0) {
            str += "{ \"productId\":\"" + resultJSON["result"][i]["productId"] + "\",\"quantity\":\"" + $('#item_' + resultJSON["result"][i]["productId"]).attr('value') + "\"},";
            total += parseFloat($('#item_' + resultJSON["result"][i]["productId"]).attr('value')) * parseFloat(resultJSON["result"][i]["price"]);
        }
    }
    str = str.slice(0, -1);
    str += "]"
    strJSON = JSON.parse(str);
    var payload = {
        "userId": 1,
        "order": strJSON,
        "total": parseFloat(total).toFixed(2)
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