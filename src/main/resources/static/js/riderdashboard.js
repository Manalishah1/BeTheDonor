$(function() {
    $('#city').autocomplete({
        source : function(request, response) {
            $.ajax({
                url : "/api/v1/riderDashboard/keyword",
                dataType : "json",
                data : {
                    q : request.term
                },
                success : function(data) {
                    //alert(data);
                    console.log(data);
                    response(data);
                }
            });
        },
        minLength : 1,
        cache: false
    });

});



$(document).on("click",".close",function (evt){
    evt.preventDefault();
    console.log("hELLO");
    $(this).parent().parent().parent().prop('hidden',true);
});


function load(orderId)
{

    var order_id = orderId;
    console.log(orderId);
     $('.orderItems').each(function (){
        var value = $(this).data("id");
        console.log(value);
        if (value == order_id)
        {
            $(this).removeAttr('hidden');
        }
        else
        {
            $(this).prop('hidden',true);
        }
     });
}







