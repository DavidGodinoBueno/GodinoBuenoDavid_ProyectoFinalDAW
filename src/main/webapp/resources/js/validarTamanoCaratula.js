/**
 * 
 */

window.addEventListener("load", function() {
	
	var btnNuevaPeli = document.getElementById("btnNuevaPeli");
	btnNuevaPeli.addEventListener("click", function(b) {
		var elfile = document.getElementById("elfile").files[0].size;
		if(elfile > 1048576) {
			b.preventDefault();
			document.getElementById("errorCaratula").classList.add("mostrarErroresInputs");
		} else {
			document.getElementById("errorCaratula").classList.remove("mostrarErroresInputs");
		}
	});
});