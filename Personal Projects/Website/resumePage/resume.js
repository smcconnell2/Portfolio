$(window).scroll(function () {
    if ($(window).scrollTop() + $(window).height() >= ($(document).height() / 1.1)) {

        $('#popUp').addClass('show')
    } else {
        $('#popUp').removeClass('show')
    }
});