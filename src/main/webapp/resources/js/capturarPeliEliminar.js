/**
 * 
 */

$(document).ready(function() {
	$('.btnEliminar').click(function() {
		var idpeli = $(this).parent().siblings('td').find('.idpeli').val();
		var titulopeli = $(this).parent().siblings('td').find('.titulopeli').val();
		var titulopelidelete = $('#titulopelidelete');
		var elid = $('#elid');
		elid.val(idpeli);
		titulopelidelete.html(titulopeli);
	});
});