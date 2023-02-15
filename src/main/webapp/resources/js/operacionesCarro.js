/**
 * 
 */

$(document).ready(function() {	
	$("tr #cantidad").click(function() {
		var idpeli = $(this).parent().find("#idpe").val();
		var cantidad = $(this).parent().find("#cantidad").val();
		var url = "ControllerCliente?keyword=AumentarCantidad";
		$.ajax({
			type: 'POST', 
			url: url, 
			data: "idpe="+idpeli+"&cantidad="+cantidad,
			success:function(data, textStatus, jqXHR) {
				location.href="ControllerCliente?keyword=Carrito";
			}
		});
	});	
	
});
