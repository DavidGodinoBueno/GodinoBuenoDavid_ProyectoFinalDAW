/**
 * 
 */

window.addEventListener("load", function() {
	
	var btnRegistrar = document.getElementById("btnRegistrar");
	btnRegistrar.addEventListener("click", function(b) {
		var elfile = document.getElementById("elfile").files[0].size;
		if(elfile > 1048576) {
			b.preventDefault();
			document.getElementById("errorPerfil").classList.add("mostrarErrores");
		} else {
			document.getElementById("errorPerfil").classList.remove("mostrarErrores");
		}
	});
});