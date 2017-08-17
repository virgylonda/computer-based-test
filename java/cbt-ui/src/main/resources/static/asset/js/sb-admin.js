$(function() {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
$(function() {
    $(window).bind("load resize", function() {
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.sidebar-collapse').addClass('collapse')
        } else {
            $('div.sidebar-collapse').removeClass('collapse')
        }
    })
})

function getCheckBoxValues() {
	var checkbox;
	$('[name="checkname"]').each(function() {
		if ($(this).prop('checked') == true) {
			alert(this.value);
			window.open("" + this.value);
		}
	});

	console.log(checkbox);
}