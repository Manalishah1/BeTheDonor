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
        minLength : 2,
        cache: false
    });
});


$(document).ready(function () {

   $('#btn').bind("click",function () {
       var cityName = document.getElementById("city").value;
       $('#heading').text("You have selected " + cityName);

   });
});

