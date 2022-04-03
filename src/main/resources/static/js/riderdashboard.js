$(function () {
    $('#city').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "/riderDashboard/keyword",
                dataType: "json",
                data: {
                    q: request.term
                },
                success: function (data) {
                    //alert(data);
                    console.log(data);
                    response(data);
                }
            });
        },
        minLength: 1,
        cache: false
    });

});

function addOrder() {

    $.ajax({
        type: "GET",
        url: "/api/v1/donor/getOrders",
        success: function (result) {


            resultStr = "{\"result\":" + JSON.stringify(result) + "}";
            resultJSON = JSON.parse(resultStr);
            var myList = new Array();
            var orderList = new Array();
            for (var i = 0; i < resultJSON.result.length; i++) {
                var n = (resultJSON.result[i].orderId);
                if ($('#c' + n).is(':checked')) {
                    myList.push(n);

                }

            }
            console.log(myList.toString());

        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
    });

}


$(document).on("click", ".close", function (evt) {
    evt.preventDefault();
    console.log("hELLO");
    $(this).parent().parent().parent().prop('hidden', true);
});


function load(orderId) {

    var order_id = orderId;
    console.log(orderId);
    $('.orderItems').each(function () {
        var value = $(this).data("id");
        console.log(value);
        if (value == order_id) {
            $(this).removeAttr('hidden');
        } else {
            $(this).prop('hidden', true);
        }
    });
}







