/**
 * 
 */

$(document).ready(function() {
    var mensaje = $("#mensaje");
	  if(mensaje.text() == "Compra finalizada.") {
		setTimeout(function() {
          $(mensaje).fadeOut(1500);
        },3000);
	}
	
	var confirmarAlta = $("#confirmarAlta");
	if(confirmarAlta.text().substring(0, 17) == "Pelicula agregada") {
		setTimeout(function() {
			$(confirmarAlta).fadeOut(1500);
		}, 3000);
	}
	
	var confirmarEdicion = $("#mensajeEdicion");
	if(confirmarEdicion.text() == "Pelicula modificada") {
		setTimeout(function() {
			$(confirmarEdicion).fadeOut(1500);
		}, 3000);
	}
	
	var confDelete = $("#confDelete");
	if(confDelete.text().substring(0, 18) == "Película eliminada") {
		setTimeout(function() {
			$(confDelete).fadeOut(1500);
		}, 3000);
	}	
});