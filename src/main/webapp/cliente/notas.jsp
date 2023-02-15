<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
src="${pageContext.request.contextPath}/resources/js/validarEscribirComentarios.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleComentarios.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody" style="background-image: url('${pageContext.request.contextPath}/resources/img/fucsia.jpg');
background-position: center center;">
  <c:if test="${nombrecliente == null}">
      <% response.sendRedirect("ControllerCliente?keyword=Logout"); %>
   </c:if>
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
 <br>
 <div class="container mt-3">
   <div class="row g-3">
    <div class="col-lg-6 col-md-5 col-sm-12 col-12">
       <form action="ControllerCliente" class="bg-light p-3" 
       method="post" style="border-radius:10px;">
         <h2>¿Que piensas de esta tienda online?</h2>
         <label>Escribe un comentario:</label>
         <textarea rows="6" cols="4" name="ladescripcion" id="textoDescripcion"
         class="form-control w-75" placeholder="Escribe algo..."></textarea>
         <p id="errorDescripcion" class="text-danger w-75 elerror">Debe tener entre 1 y 100 carácteres de cualquier tipo excepto saltos de línea (se permiten espacios en blanco y acentos).</p>
         <input type="submit" class="btn btn-primary mt-2" id="btnComentar"
         name="keyword" value="Comentar"/>
      </form>
    </div>
    <div class="col-lg-6 col-md-7 col-sm-12 col-12">
       <c:if test="${not empty listacomentarios}">
       <div class="contenedor bg-light p-3">
        <c:forEach var="comentario" items="${listacomentarios}">
         <div class="row justify-content-start">
           <div class="col-lg-2 col-md-3 col-sm-3 col-3">
             <img src="${pageContext.request.contextPath}/ControllerCliente?elnombre=${comentario.nombrecliente}&keyword=mostrarperfil" 
            width="80px" height="80px" style="border-radius:100px;">
           </div>
           <div class="col-lg-2 col-md-3 col-sm-3 col-3 mt-4">
             <strong>&nbsp;&nbsp;${comentario.nombrecliente}</strong>
           </div>
         </div>
         <p style="font-size:20px;">${comentario.descripcion}</p>
         <div class="row justify-content-between">
            <div class="col-lg-4 col-md-4 col-sm-4 col-4">
               <c:if test="${comentario.nombrecliente == nombrecliente}">
                <a href="${pageContext.request.contextPath}/ControllerCliente?elid=${comentario.idcomentario}&keyword=EliminarComentario" 
                class="text-danger me-2"><strong>Eliminar</strong></a>
               </c:if>
            </div>
            <div class="col-lg-4 col-md-5 col-sm-4 col-4">
                <strong class="text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${comentario.fechacomentario}"/></strong>
            </div>
         </div>
         <hr/>
        </c:forEach>
      </div>
      </c:if>
      <c:if test="${listacomentarios != null && listacomentarios.isEmpty()}">
          <h1 class="text-light">
             <c:out value="No hay comentarios disponibles"></c:out>
          </h1>
      </c:if>
    </div>
  </div>
 </div>
  <%@include file="../WEB-INF/includes/piepagina.jspf" %>
 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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