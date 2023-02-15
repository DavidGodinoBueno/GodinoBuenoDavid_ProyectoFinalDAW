<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleListadoPeliculas.css">
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
    <form action="ControllerCliente" method="post" 
    class="bg-dark p-2 mt-5 col-lg-6 col-md-8 col-sm-10 col-10"
    style="border-radius:5px;border:solid white 2px;">
        <h2 class="text-light">
         <img alt="" src="${pageContext.request.contextPath}/resources/img/lupita.png"
            width="50px" height="50px"/>
        Películas a buscar</h2>
        <div class="form-group row g-2">
          <div class="col-lg-6 col-md-6">
             <input type="text" class="form-control"
             placeholder="Buscar titulo peliculas" name="iniciales"
             value="${lasiniciales}"/>
          </div>
          <div class="col-lg-4 col-md-6">
             <select class="form-select" name="elgenero">
               <option value="%">Cualquier género</option>
               <c:forEach items="${listageneros}" var="genero">
                 <option value="${genero.descripcion}"
                    <c:if test="${criteriogenero == genero.descripcion}">
                       <c:out value=" selected"></c:out>
                    </c:if>
                 >
                 ${genero.descripcion}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-lg-2"> 
              <input type="submit" name="keyword" class="btn btn-primary col-md-12 col-sm-12 col-12"
              value="Buscar">
          </div>
        </div>
    </form>
	<c:if test="${not empty listapeliculas}">
	<div class="cuadropelis p-2">
	 <div class="row mt-1">
	  <c:forEach items="${listapeliculas}" var="peliculas">
	   <div class="col-lg-3 col-md-5 col-sm-6 col-9 mt-4">
		<div class="card bg-light p-1">
		    <img class="card-img-top" src="${pageContext.request.contextPath}/ControllerCliente?id=${peliculas.idpelicula}&keyword=mostrarcaratulas" width="180px" height="200px">
		   <div class="card-body">
		      <h3 class="card-title">${peliculas.titulo} </h3>
		      <p class="text-muted">${peliculas.nombregenero}</p> 
		      <c:set var = "preciopeli" value = "${peliculas.precio}" />
		      <fmt:setLocale value = "es_ES"/>
		       <h3 class="text-danger">
		            <fmt:formatNumber value = "${preciopeli}" type = "currency"/>
		       </h3>
		      <div class="row g-1">
		        <a href="ControllerCliente?idpelicula=${peliculas.idpelicula}&keyword=addCarrito" class="btn btn-danger">
		      <img alt="" width="30px" height="30px" 
		      src="${pageContext.request.contextPath}/resources/img/carritocompra.png"/>&nbsp;Añadir al carrito</a>
		      <a href="${pageContext.request.contextPath}/ControllerCliente?idpelicula=${peliculas.idpelicula}&keyword=VerInformacionPelicula" 
		      class="btn btn-info text-dark"><img alt="" src="${pageContext.request.contextPath}/resources/img/info.png"
              width="20px" height="20px"/>&nbsp;Más Información</a>
		      </div>
		    </div>
		   </div>
		</div>
	  </c:forEach>
	 </div>
	 </div>
	</c:if>
	<c:if test="${listapeliculas != null && listapeliculas.isEmpty()}">
	   <br><br><br><br>
	   <div id="notFound" class="mt-5 p-3 text-center w-75">
	     <div class="mt-2"></div>
	     <img alt="" src="${pageContext.request.contextPath}/resources/img/iconopeli.png" 
	     width="120px" height="120px">
	     <h1 class="text-center">No hay peliculas coincidentes</h1>
	      <div class="mt-2"></div>
	   </div>
	</c:if>
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
</body>
</html>