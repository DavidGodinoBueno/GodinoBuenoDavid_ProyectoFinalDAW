<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleCarrito.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/operacionesCarro.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ValidarPago.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="contenedor" style="background-image: url('${pageContext.request.contextPath}/resources/img/fucsia.jpg');
background-position: center center;">
   <c:if test="${nombrecliente == null}">
      <% response.sendRedirect("ControllerCliente?keyword=Logout"); %>
   </c:if>
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
 <br>
 <div class="container mt-5">
   <div class="row">
     <div class="col-lg-9 col-md-8 col-sm-7 col-7">
		<c:if test="${not empty carrito}">
		  <div class="table-responsive">
		   <table class="table table-striped table-hover table-bordered bg-light">
		     <tr>
               <th colspan="7" class="bg-warning text-center">
                <h2><img alt="" src="${pageContext.request.contextPath}/resources/img/carrito.png"
                     width="50px" height="50px"/>
                    Carrito Compras</h2></th>
              </tr>
		      <tr>
		        <th>ITEM</th>
		        <th>TITULO</th>
		        <th>CARATULA</th>
		        <th>PRECIO</th>
		        <th>CANTIDAD</th>
		        <th>SUBTOTAL</th>
		        <th>ACCIÓN</th>
		      </tr>
		      <c:forEach var="carro" items="${carrito}">
		        <c:set var = "elprecio" value = "${carro.preciocompra}" />
		         <c:set var = "elsubtotal" value = "${carro.subtotal}" />
		         <fmt:setLocale value = "es_ES"/>
		         <tr>
		            <td>${carro.item}</td>
		            <td>${carro.titulo}</td>
		            <td><img alt="" src="${pageContext.request.contextPath}/ControllerCliente?id=${carro.idpelicula}&keyword=mostrarcaratulas" width="160px" height="170px"></td>
		            <td><fmt:formatNumber value = "${elprecio}" type = "currency"/></td>
		            <td><input type="hidden" id="idpe" value="${carro.idpelicula}"/>
		            <input type="number" id="cantidad" class="form-control w-75" value="${carro.cantidad}" min="1"/></td>
		            <td><fmt:formatNumber value = "${elsubtotal}" type = "currency"/></td>
		            <td> 
		              <input type="hidden" id="idpeli" value="${carro.idpelicula}"/>        
		              <a href="${pageContext.request.contextPath}/ControllerCliente?idpeli=${carro.idpelicula}&keyword=EliminarPeliCarro" id="botonEliminar">
		              <img alt="" src="${pageContext.request.contextPath}/resources/img/cubodelete.png"
		              width="60px" height="50px"></a>
		            </td>
		         </tr>
		      </c:forEach>
		   </table>
		  </div>
		</c:if>
		<c:if test="${carrito != null && carrito.isEmpty()}">
		 <div id="carritovacio" class="p-3 text-center text-light w-75">
	        <div class="mt-2"></div>
	         <img alt="" src="${pageContext.request.contextPath}/resources/img/carrovacio.png" 
	     width="150px" height="150px">
	     <h1 class="text-center">El carro de compras esta vacío</h1>
	         <div class="mt-2"></div>
	     </div>
		</c:if>
		</div>
		<div class="col-lg-3 col-md-4 col-sm-5 col-5">
		<c:if test="${compraconfirmada != null}">
           <h2 id="mensaje" class="p-2 text-center text-white">${compraconfirmada}</h2>
         </c:if>
		 <form action="ControllerCliente" method="post">
		   <div class="card bg-warning">
		     <div class="card-body">
		        <h3>Total Compra</h3>
		        <fmt:formatNumber value = "${totalpagar}" type = "currency" var="elimporte"/>
		        <div class="form-group mt-2">
		          <label>Nombre cliente: </label>
                  <input type="text" class="form-control" name="namecliente" value="${nombrecliente}" readonly/>
		        </div>
		        <div class="form-group mt-2">
		          <label>Importe:</label>
		          <input type="text"
		          value="${elimporte}" readonly class="form-control"/>
		        </div>
		        <div class="form-group mt-2">
		           <label>Número tarjeta VISA: <span class="rojo">*</span></label>
                   <input type="text" class="form-control" id="textoTarjeta"
                   name="numtarjeta" placeholder="4XXX-XXXX-XXXX-XXXX" required/>
                   <p class="text-danger" id="errorTarjeta">Debe tener 16 dígitos numéricos (el 1º dígito es el numero 4), los dígitos pueden estar juntos o ir separados en grupos de 4 en 4 por un guión medio.</p>
		       </div> 
		       <div class="form-group row g-2 mt-3">
		          <input type="submit" name="keyword" id="btnComprar"
                  class="btn btn-success" value="Comprar" 
                  <c:if test="${carrito != null && carrito.isEmpty()}">
		            <c:out value=" disabled"></c:out>
		          </c:if>/>
		           <a class="btn btn-danger" href="${pageContext.request.contextPath}/ControllerCliente?keyword=Buscar">
		          Continuar comprando peliculas</a>
		        </div>
		     </div>
		   </div>
		  </form>
		</div>
	 </div>
	</div>
<%@include file="../WEB-INF/includes/piepagina.jspf" %>
 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

   <div class="offcanvas offcanvas-end bg-dark text-light" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header">
        <h3 class="offcanvas-title" id="offcanvasNavbarLabel">Contactar con la tienda</h3>
        <button type="button" class="btn-close btn-close-white text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
          <li class="nav-item">
            <a class="nav-link active text-light" aria-current="page" href="#"><strong>Municipio: </strong> Boadilla del Monte</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-light" href="#"><strong>Teléfono:</strong> 938-34-24-64</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-light" href="#"><strong>Correo:</strong> pelisdavidg@hotmail.es</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-light" href="#"><strong>Dirección: </strong> C/ Duque nº 23</a>
          </li>
        </ul>
        <hr/>
        <h2 class="text-light">Mapa: </h2>
        <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d3612.8166546215616!2d-3.8831197501874692!3d40.4087494601882!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses!2ses!4v1652069005813!5m2!1ses!2ses" 
width="370" height="250" style="border:0;border-radius:10px;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        <hr/>
        <h3 class="text-light">Redes sociales: </h3><br>
        <ul class="row justify-content-around" style="list-style:none;">
           <li class="col-3">
            <a href="https://es-es.facebook.com/">
              <img class="redsocial" alt="" src="${pageContext.request.contextPath}/resources/img/facebook.png"
            width="50px" height="50px"/>
             </a></li>
            <li class="col-3"><a href="https://twitter.com/?lang=es">
               <img class="redsocial" alt="" src="${pageContext.request.contextPath}/resources/img/twitter.png"
            width="50px" height="50px"/>
            </a></li>
            <li class="col-3">
              <a href="https://www.instagram.com/">
                <img class="redsocial" alt="" src="${pageContext.request.contextPath}/resources/img/instagram.png"
            width="50px" height="50px"/>
              </a>
            </li>
        </ul>
      </div>
    </div>
</body>
</html>