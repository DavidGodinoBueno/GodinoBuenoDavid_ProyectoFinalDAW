/**
 * 
 */

window.addEventListener("load", function() {
	
	var inputs = document.querySelectorAll("input");
	
	const expReg = {
		Dni: /^\d{8}[A-Z]{1}$/,
		Nombre: /^[À-ÿa-zA-Z\s]{4,20}$/,
		Email: /^([a-zA-Z0-9_\.\-]){3,12}\@(([a-zA-Z0-9\-]){4,13}\.)+(com|org|es|edu)$/,
		Clave: /^(?=.*\d)(?=.*[\u0021-\u002b\u003c-\u0040])(?=.*[A-Z])(?=.*[a-z])\S{8,16}$/,
		Direccion: /^.{8,30}$/
	}
	
	var validarCampos = (v) => {
		switch(v.target.id) {
			case "textoDni":
			   comprobarCampos(v.target, "Dni", expReg.Dni, "Dni");
			break;
			case "textoNombre":
			   comprobarCampos(v.target, "Nombre", expReg.Nombre, "Nombre");
			break;
			case "textoEmail":
			   comprobarCampos(v.target, "Email", expReg.Email, "Email");
			break;
			case "textoClave":
			   comprobarCampos(v.target, "Clave", expReg.Clave, "Clave");
			break;
			case "textoDireccion":
			   comprobarCampos(v.target, "Direccion", expReg.Direccion, "Direccion");
			break;
		}
	}
	
	var comprobarCampos = (elid, campo, exp, error) => {
		 if(!elid.value.match(exp)) {
			 document.getElementById(`texto${campo}`).classList.add("is-invalid");
		     document.getElementById(`texto${campo}`).classList.remove("is-valid");
			 document.getElementById(`error${error}`).classList.add("mostrarErrores");
		} else {
			document.getElementById(`texto${campo}`).classList.add("is-valid");
		     document.getElementById(`texto${campo}`).classList.remove("is-invalid");
			document.getElementById(`error${error}`).classList.remove("mostrarErrores");
		}
	}
	
	inputs.forEach((i) => {
		i.addEventListener("keyup", validarCampos);
		i.addEventListener("blur", validarCampos);
	})
	
	var loscampos = {
		Eldni: document.getElementById("textoDni"),
		Elnombre: document.getElementById("textoNombre"),
		Elemail: document.getElementById("textoEmail"),
		Laclave: document.getElementById("textoClave"),
		Ladireccion: document.getElementById("textoDireccion")
	}
	
	var btnRegistrar = document.getElementById("btnRegistrar");
	btnRegistrar.addEventListener("click", (r) => {
		if(!(loscampos.Eldni.value.match(expReg.Dni)
		&& loscampos.Elnombre.value.match(expReg.Nombre)
		&& loscampos.Elemail.value.match(expReg.Email)
		&& loscampos.Laclave.value.match(expReg.Clave)
		&& loscampos.Ladireccion.value.match(expReg.Direccion))) {
			r.preventDefault();
		}
	})
})