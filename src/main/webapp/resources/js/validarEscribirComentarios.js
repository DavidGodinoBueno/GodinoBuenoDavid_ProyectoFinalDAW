/**
 * 
 */

window.addEventListener("load", function() {
	var cuadrotexto = document.querySelector("textarea");
	
	var expReg = /^.{1,100}$/
	
	var validarCuadro = (v) => {
		if(v.target.id) {
			comprobarCuadro(v.target, expReg);
		}
	}
	
	var comprobarCuadro = (elid, exp) => {
		 if(!elid.value.match(exp)) {
			  document.getElementById("textoDescripcion").classList.add("is-invalid");
	          document.getElementById("textoDescripcion").classList.remove("is-valid");
	          document.getElementById("errorDescripcion").classList.add("mostrarErroresInputs");
		} else {
			document.getElementById("textoDescripcion").classList.add("is-valid");
			document.getElementById("textoDescripcion").classList.remove("is-invalid");
			document.getElementById("errorDescripcion").classList.remove("mostrarErroresInputs");
		}
	};
	
	
	cuadrotexto.addEventListener("keyup", validarCuadro);
	cuadrotexto.addEventListener("blur", validarCuadro);
	
	var txtCuadrito = document.getElementById("textoDescripcion");
	
	var btnComentar = document.getElementById("btnComentar");
	btnComentar.addEventListener("click", (c) => {
		if(!txtCuadrito.value.match(expReg)) {
			c.preventDefault();
		}
	});
})