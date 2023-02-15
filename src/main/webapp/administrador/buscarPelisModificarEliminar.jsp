<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleBuscarModificarEliminar.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/capturarPeliEliminar.js"></script>
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
    <c:if test="${confirmEliminar != null}">
       <h2 id="confDelete" class="text-white p-2 col-lg-6 col-md-8 col-sm-12 col-12">${confirmEliminar}</h2>
    </c:if>
    <form action="ControllerAdmin" class="bg-dark p-2 mt-5 col-lg-6 col-md-6 col-sm-12 col-12" 
    method="post" style="border-radius:5px;border:solid white 2px;">
       <h2 class="text-light">
         <img alt="" src="${pageContext.request.contextPath}/resources/img/lupita.png"
            width="50px" height="50px"/>
        Buscar películas modificar/eliminar</h2>
        <div class="row justify-content-between g-2">
           <div class="col-lg-8 col-md-8 col-sm-12 col-12">
              <input type="text" name="trozotitulo" 
              class="form-control" value="${patrontitulo}"
              placeholder="Títulos películas"/>
           </div> 
           <div class="col-lg-3 col-md-3">
               <input type="hidden" name="keyword" value="BuscarPelisEditDelete"/>
              <input type="submit" class="btn btn-primary col-sm-12 col-12" value="Buscar"/>
           </div>
        </div>
    </form>
    <br>
    <c:choose>
       <c:when test="${not empty estaspeliculas}">
        <div class="table-responsive">
         <table class="table table-striped table-hover table-bordered w-75 bg-light">
          <tr>
             <th colspan="6" class="bg-warning text-center">
             <h2><img alt="" src="${pageContext.request.contextPath}/resources/img/iconopeli.png"
            width="50px" height="50px"/>
             Películas</h2></th>
           </tr>
          <tr>
            <th class="col-2">ID_PELICULA</th>
            <th class="col-2">TITULO</th>
            <th class="col-0">CARATULA</th>
            <th class="col-2">PRECIO</th>
            <th class="col-2">EDITAR</th>
            <th class="col-2">ELIMINAR</th>
          </tr>
          <c:forEach items="${estaspeliculas}" var="pelicula">
             <tr>
               <td>${pelicula.idpelicula}<input type="hidden" value="${pelicula.idpelicula}" class="idpeli"/></td>
               <td>${pelicula.titulo}<input type="hidden" value="${pelicula.titulo}" class="titulopeli"/></td>
               <td><img alt="" src="${pageContext.request.contextPath}/ControllerCliente?id=${pelicula.idpelicula}&keyword=mostrarcaratulas"
               width="120px" height="100px"></td>
               <td>${pelicula.precio} €</td>
               <td><a class="btn btn-warning" href="${pageContext.request.contextPath}/ControllerAdmin?elidpeli=${pelicula.idpelicula}&keyword=PeliculaAEditar">
               <img alt="" src="${pageContext.request.contextPath}/resources/img/editar.png"
            width="30px" height="30px"/>
               </a></td>
                 <td>
                  <button type="button" class="btnEliminar btn btn-danger"
                  data-bs-toggle="modal" data-bs-target="#elmodaldelete">
                     <img alt="" src="${pageContext.request.contextPath}/resources/img/cubobasura.png"
                      width="30px" height="30px"/>
                  </button>
               </td>
           </tr>
          </c:forEach>
        </table>
        </div>
       </c:when>
       <c:when test="${estaspeliculas != null && estaspeliculas.isEmpty()}">
          <br><br><br><br>
	     <div id="notFound" class="mt-5 p-3 text-center w-75">
	      <div class="mt-2"></div>
	       <img alt="" src="${pageContext.request.contextPath}/resources/img/iconopeli.png" 
	       width="120px" height="120px">
	       <h1 class="text-center">No hay peliculas coincidentes</h1>
	        <div class="mt-2"></div>
	     </div>
       </c:when>
    </c:choose>    
  
  <div class="modal fade" id="elmodaldelete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="max-width:650px!important;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">¿Estas seguro que quieres eliminar la película: ?</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="ControllerAdmin" method="post">
      <div class="modal-body">
         <input type="hidden" name="patrontitulo" value="${patrontitulo}"/>
         <input type="hidden" name="idpelidelete" id="elid"/>
         <h2 id="titulopelidelete"></h2>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cancelar</button>
        <input type="hidden" name="keyword" value="eliminarPelicula"/>
        <input type="submit" class="btn btn-danger" value="Eliminar"/>
        </div></form>
        </div>
      </div>
    </div>
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