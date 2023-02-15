/**
 * 
 */

window.addEventListener("load", function() {
	var inputs = document.querySelectorAll("input");
	var textoarea = document.querySelector("textarea");
	
	const expReg = {
		Titulo: /^[À-ÿa-zA-Z\d\s\u003A\u0022\u005F\u002E\u002D]{4,60}$/,
		Precio: /^[0-9]{1,2}([.][0-9]{1,3})?$/,
		Descripcion: /^.{10,500}$/,
		Stock: /^([1-9]|[1-2][0-9]|30)$/
	}
	
	var validarCampos = (v) => {
		switch(v.target.id) {
			case "textoTitulo":
			  comprobarCampos(v.target, "Titulo", expReg.Titulo, "Titulo");
			break;
			case "textoPrecio":
			  comprobarCampos(v.target, "Precio", expReg.Precio, "Precio");
			break;
			case "textoDescripcion":
			   comprobarCampos(v.target, "Descripcion", expReg.Descripcion, "Descripcion");
			break;
			case "textoStock":
			   comprobarCampos(v.target, "Stock", expReg.Stock, "Stock");
			break;
		}
	}
	
	var comprobarCampos = (elid, campo, exp, error) => {
		if(!elid.value.match(exp)) {
			 document.getElementById(`texto${campo}`).classList.add("is-invalid");
		     document.getElementById(`texto${campo}`).classList.remove("is-valid");
             document.getElementById(`error${error}`).classList.add("mostrarErroresInputs");
		} else {
			document.getElementById(`texto${campo}`).classList.remove("is-invalid");
		     document.getElementById(`texto${campo}`).classList.add("is-valid");
             document.getElementById(`error${error}`).classList.remove("mostrarErroresInputs");
		}
	}
	
	inputs.forEach((i) => {
		i.addEventListener("keyup", validarCampos);
		i.addEventListener("blur", validarCampos);
	})
	
	textoarea.addEventListener("keyup", validarCampos);
	textoarea.addEventListener("blur", validarCampos);
	
	var loscampos = {
		 Eltitulo: document.getElementById("textoTitulo"),
	     Elprecio: document.getElementById("textoPrecio"),
         Ladescripcion: document.getElementById("textoDescripcion"),
         Elstock: document.getElementById("textoStock")
	}
	
	var elcontador = loscampos.Ladescripcion.value.length;
	document.getElementById("contador").innerHTML = elcontador+"/500";
	
	textoarea.addEventListener("keyup", function() {
		elcontador = loscampos.Ladescripcion.value.length;
		document.getElementById("contador").innerHTML = elcontador+"/500";
	})
	
	var btnNuevaPeli = document.getElementById("btnNuevaPeli");
	btnNuevaPeli.addEventListener("click", function(b) {
		if(!(loscampos.Eltitulo.value.match(expReg.Titulo) 
		&& loscampos.Elprecio.value.match(expReg.Precio)
		&& loscampos.Ladescripcion.value.match(expReg.Descripcion)
		&& loscampos.Elstock.value.match(expReg.Stock))) {
			b.preventDefault();
		}
	})
})