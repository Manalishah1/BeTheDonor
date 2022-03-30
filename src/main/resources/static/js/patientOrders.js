$(document).ready(function() {
    function fetchOrders() {
            $.ajax({
                type: "GET",
                url: "/api/v1/getOrders",
                success: function(result) {
                    resultStr = "{\"result\":" + JSON.stringify(result) + "}";
                    resultJSON = JSON.parse(resultStr);
                    console.log(resultJSON);
                    orderCount = resultJSON["result"].length;
                    var str="";
                    for(var i=0;i<orderCount;i++)
                    {
                    str+="<div class='container'> <table class='table table-striped'> <p>Order Id: "+ resultJSON["result"][i]["orderId"]+"</p> <p>Order Total: "+resultJSON["result"][i]["totalAmount"]+"</p> <thead> <tr> <th>Product</th> <th>Price</th> <th>Quantity</th> </tr> </thead>";

                    for(var j=0;j<resultJSON["result"][i]["productName"].length;j++)
                    {
                    str+="<tbody> <tr><td>"+resultJSON["result"][i]["productName"][j]+"</td><td>"+resultJSON["result"][i]["price"][j]+"</td><td>"+resultJSON["result"][i]["quantity"][j]+"</td></tr></tbody> ";
                    }
                    str+="</table> </div>";
                    }
                    str+="<br>";
                    $(".patientOrdersList").append(str);
                },
                error: function(e) {
                    console.log("ERROR: ", e);
                },
            });
        }
        fetchOrders();
});