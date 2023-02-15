/**
 * 
 */

window.addEventListener("load", function() {
	
	var btnEditPeli = document.getElementById("btnEditPeli");
	btnEditPeli.addEventListener("click", function(b) {
		var elinputfile = document.getElementById("elinputfile").files[0].size;
		if(elinputfile > 1048576) {
			b.preventDefault();
			document.getElementById("errorCaratula").classList.add("mostrarErroresInputs");
		} else {
			document.getElementById("errorCaratula").classList.remove("mostrarErroresInputs");
		}
	});
});