<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleDetalleCompras.css"/>
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
    <c:choose>
      <c:when test="${not empty listacompras}">
        <table id="tablacompras" class="table table-striped table-hover table-bordered bg-light w-100">
          <thead>
           <tr>
             <th colspan="7" class="bg-warning text-center">
             <h2><img alt="" src="${pageContext.request.contextPath}/resources/img/bolsito.png"
            width="50px" height="50px"/>
             Mis Compras</h2></th>
           </tr>
           <tr class="bg-dark text-light">
             <th>ID COMPRA</th>
             <th>NOMBRE CLIENTE</th>
             <th>FECHA COMPRA</th>
             <th>HORA</th>
             <th>IMPORTE</th>
             <th>ESTADO</th>
             <th>VER DETALLE</th>
           </tr>
           </thead>
           <tbody>
           <c:forEach items="${listacompras}" var="compra">
               <tr>
                 <td><strong>${compra.id}</strong></td>
                 <td>${nombrecliente}</td>
                 <td><fmt:formatDate pattern="dd-MM-yyyy" value="${compra.fechacompra}"/></td>
                 <td>${compra.fechacompra.toString().substring(11, 19)}</td>
                 <c:set var = "precioimporte" value = "${compra.importe}" />
		         <fmt:setLocale value = "es_ES"/>
                 <td><fmt:formatNumber value="${precioimporte}" type="currency"/></td>
                 <td>${compra.estado}</td>
                 <td><a href="${pageContext.request.contextPath}/ControllerCliente?elidcompra=${compra.id}&keyword=VerPelisCompradas" class="btn btn-info">
                  <img alt="" src="${pageContext.request.contextPath}/resources/img/ojo.png"
            width="30px" height="30px"/>&nbsp;&nbsp;<strong>Ver películas compradas</strong></a></td>
              </tr>
           </c:forEach>
         <tbody>
        </table>
        <h2 class="text-light">
           <c:out value="Mostrando de ${firstRow+1} a ${lastRow} sobre ${totalCompras} compras"/>
        </h2>
        <br>
        <div class="row g-2 col-6">
           <div class="col-lg-4 col-md-4">
             <a href="${pageContext.request.contextPath}/ControllerCliente?thefirstRow=${firstRow-rowsByPage}&keyword=PaginaAnteriorCompras" 
             class="btn btn-primary col-sm-12 col-12">Anterior</a>
           </div>
           <div class="col-lg-4 col-md-4">
            <a href="${pageContext.request.contextPath}/ControllerCliente?firstRow=${firstRow=firstRow+rowsByPage}&keyword=PaginaSiguienteCompras" 
            class="btn btn-primary col-sm-12 col-12">Siguiente</a>
           </div>
        </div>
     </c:when>
      <c:when test="${listacompras != null && listacompras.isEmpty()}">
	     <div id="comprasvacio" class="p-3 text-center text-light w-75">
	        <div class="mt-2"></div>
	         <img alt="" src="${pageContext.request.contextPath}/resources/img/bolsa.png" 
	     width="150px" height="150px">
	     <h1 class="text-center">No hay ninguna compra</h1>
	         <div class="mt-2"></div>
	     </div>
	 </c:when>
    </c:choose>
 </div>
<%@include file="../WEB-INF/includes/piepagina.jspf" %>
 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
    
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