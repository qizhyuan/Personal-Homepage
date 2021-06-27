$(function(){
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			$('.scroll-up').fadeIn();
		} else {
			$('.scroll-up').fadeOut();
		}
	});
	$(".screen-height").height($(window).height());

    $(window).resize(function(){
    	$(".screen-height").height($(window).height());
    });
});

