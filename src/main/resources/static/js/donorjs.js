$(document).ready(function () {
    function fetchProducts() {
        $.ajax({
            type: "GET",
            url: "/api/v1/getOrders",
            success: function (result) {


                resultStr = "{\"result\":" + JSON.stringify(result) + "}";
                resultJSON = JSON.parse(resultStr);

                for (var i = 0; i < resultJSON.result.length; i++) {
                    // console.log(resultJSON.result.length);
                    //console.log(resultJSON.result[i]);
                    $('#product'+i).clone().insertAfter('#product'+i).attr('id','product'+(i+1)).css('display','block');
                    $('#product'+(i+1)).find('.product-details').children(".product-title").html(resultJSON.result[i].firstName).attr('id','product-title'+(i+1));
                    $('#product'+(i+1)).find('.product-details').children(".product-description").html(resultJSON.result[i].productName.join()).attr('id','product-description'+(i+1));
                    $('#product'+(i+1)).find('.product-price').html(resultJSON.result[i].totalAmount).attr('id','product-price'+(i+1));
                    $('#product'+(i+1)).find('.product-quantity').children().attr('id','product-quantity'+(i+1));
                    $('#product'+(i+1)).find('.product-line-price').attr('id','product-line-price'+(i+1));
                }

            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
        });
    }

    fetchProducts();
});

/* Set rates + misc */
var taxRate = 0.05;
var shippingRate = 15.00;
var fadeTime = 300;


function callf(ele){
    let orderNumber = $(ele).attr('id').split('product-quantity')[1];
    if($('#product-quantity'+orderNumber).is(':checked')){
        $('#product-line-price'+orderNumber).html($('#product-price'+orderNumber).html());
    }else{
        $('#product-line-price'+orderNumber).html('0');
    }
    updateTotal();

}
function updateTotal()
{

    var sum = 0;
    $('div.product-line-price').each(function(){
        sum += parseFloat($(this).text());
        $('#cart-subtotal').text(sum);// Or this.innerHTML, this.innerText
        recalculateCart();
    });
}


/* Recalculate cart */
function recalculateCart()
{
    var subtotal = 0;
    /* Sum up row totals */
    $('.product').each(function () {
        subtotal += parseFloat($(this).children('.product-line-price').text());
    });

    /* Calculate totals */
    var tax = subtotal * taxRate;
    var shipping = (subtotal > 0 ? shippingRate : 0);
    var total = subtotal + tax + shipping;

    /* Update totals display */
    $('.totals-value').fadeOut(fadeTime, function() {
        $('#cart-subtotal').html(subtotal.toFixed(2));
        $('#cart-tax').html(tax.toFixed(2));
        $('#cart-shipping').html(shipping.toFixed(2));
        $('#cart-total').html(total.toFixed(2));
        if(total == 0){
            $('.checkout').fadeOut(fadeTime);
        }else{
            $('.checkout').fadeIn(fadeTime);
        }
        $('.totals-value').fadeIn(fadeTime);
    });
}



/* Update quantity */
function updateQuantity(quantityInput)
{
    /* Calculate line price */
    var productRow = $(quantityInput).parent().parent();
    var price = parseFloat(productRow.children('.product-price').text());
    var quantity = $(quantityInput).val();
    var linePrice = price * quantity;

    /* Update line price display and recalc cart totals */
    productRow.children('.product-line-price').each(function () {
        $(this).fadeOut(fadeTime, function() {
            $(this).text(linePrice.toFixed(2));
            recalculateCart();
            $(this).fadeIn(fadeTime);
        });
    });
}


/* Remove item from cart */
function removeItem(removeButton)
{
    /* Remove row from DOM and recalc cart total */
    var productRow = $(removeButton).parent().parent();
    productRow.slideUp(fadeTime, function() {
        productRow.remove();
        recalculateCart();
    });
}