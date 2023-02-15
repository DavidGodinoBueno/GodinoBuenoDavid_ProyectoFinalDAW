/**
 * 
 */

window.addEventListener("load", function() {
	var textoTarjeta = document.getElementById("textoTarjeta");
	
	var expReg = /^4\d{3}-?\d{4}-?\d{4}-?\d{4}$/;
	
	var validarCampo = (v) => {
		 if(v.target.id) {
			comprobarCampo(v.target, expReg);
		}
	}
	
	var comprobarCampo = (elid, exp) => {
		 if(!elid.value.match(exp)) {
			 document.getElementById("textoTarjeta").classList.add("is-invalid");
		     document.getElementById("textoTarjeta").classList.remove("is-valid");
             document.getElementById("errorTarjeta").classList.add("mostrarErrorTexto");
		 } else {
			document.getElementById("textoTarjeta").classList.add("is-valid");
		    document.getElementById("textoTarjeta").classList.remove("is-invalid");
            document.getElementById("errorTarjeta").classList.remove("mostrarErrorTexto");
		}
	}
	
	textoTarjeta.addEventListener("keyup", validarCampo);
	textoTarjeta.addEventListener("blur", validarCampo);
	
	var btnComprar = document.getElementById("btnComprar");
	btnComprar.addEventListener("click", (c) => {
		if(!textoTarjeta.value.match(expReg)) {
			c.preventDefault();
		} 
	});
});