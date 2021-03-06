$(document).ready(function () {
    $('.carousel').carousel({
        interval: 2000
    });
});

$(document).ready(function () {
    $('a.buy').click(function (event) {
        event.preventDefault();
        $(".modal-info").empty();
        var url = $(this).attr("href");
        var goodId = $(this).data("id");

        $.ajax({
            url: url,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(goodId),
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $(".modal-info").append(data);
            },
            error: function () {
                $(".modal-info").append("Please login, before adding a product to the cart");
            }
        });
    });

    $('button.good-calc').click(function (event) {
        event.preventDefault();
        var itemId = $(this).data("id");
        var isPlus = $(this).hasClass('good-calc-up');

        $.ajax({
            url: "/cart/calculate",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({id: itemId, isPlus: isPlus}),
            dataType: 'json',
            type: 'post',
            context: this,
            success: function (data) {
                $(this).parent().children('.good-count').text(data.count);
                $("h3>span").text(data.sum);
            },
            error: function () {
                console.log('Error! ' + $(this));
            }
        });
    });

});
