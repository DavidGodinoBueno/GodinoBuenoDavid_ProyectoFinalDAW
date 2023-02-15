<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleModificarPeli.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarEdicionPeli.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoCaratulaEdit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/verFotoPeliInputFile.js"></script>
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
    <c:if test="${confirmar != null}">
       <h2 id="mensajeEdicion" class="p-2 text-light col-lg-6 col-md-8 col-sm-12 col-12">${confirmar}</h2>
    </c:if>
    <form action="ControllerAdmin" class="bg-light p-3 col-lg-9 col-md-11 col-sm-12 col-12" 
    method="post" style="border-radius:10px;" enctype="multipart/form-data">
       <input type="hidden" name="estapelimodificada" 
       value="${pelieditar.idpelicula}"/>
       <h2 class="bg-warning text-center p-2"><img alt="" 
      src="${pageContext.request.contextPath}/resources/img/editar.png" width="80px" height="80px">
      Modificar pelicula</h2>
     <div class="row justify-content-between mt-3 g-3">
       <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
          <div class="col">
            <label>Título: </label>
          </div>
          <div class="col">
          <input type="text" id="textoTitulo" name="nuevotitulo" 
          class="form-control me-5" value="${pelieditar.titulo}" required/>
           </div>
           <div class="col">
           <p id="errorTitulo" class="text-danger">Debe tener entre 4 y 60 carácteres albéticos y opcionalmente numéricos, se permiten espacios en blanco, acentos y los carácteres <b>:</b> <b>.</b> <b>_</b> <b>"</b> <b>-</b></p>
            </div>
          <div class="mt-4">
            <label>Descripción: </label>
           <textarea rows="5" cols="2" id="textoDescripcion" name="nuevadescripcion"
           class="form-control" placeholder="Ej: Es una pelicula interesante..."
           required>${pelieditar.descripcion}</textarea>
           <h6 id="contador"></h6>
           <p id="errorDescripcion" class="text-danger">Debe tener entre 10 y 500 carácteres de cualquier tipo excepto saltos de línea (se permiten espacios en blanco y acentos).</p>
          </div>
          </div>
        <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
           <div class="input-group row mt-3">
           <div class="input-group-prepend">
               <div class="input-group-text justify-content-center">CARATULA ACTUAL</div>
               <div class="border p-2 text-center" style="border-radius:10px;">
                 <img id="laimagen" src="${pageContext.request.contextPath}/ControllerCliente?id=${pelieditar.idpelicula}&keyword=mostrarcaratulas" 
                width="170px" height="150px">
               </div>           
             </div>
           <input type="file" class="file-select mt-2" id="elinputfile"
             name="nuevacaratula" accept="image/gif,image/jpeg,image/jpg,image/png"
            required/>
            <p id="errorCaratula" class="text-danger mt-2">El tamaño de la imagen supera 1MB, adjunte otra menos pesada para cambiarla.</p>
        </div>
        </div>
        </div>
     <div class="row justify-content-between mt-2 g-3">
        <div class="form-group col-lg-3 col-md-3 col-sm-12 col-12">
          <label>Stock: </label>
           <input type="number" id="textoStock" name="nuevostock"
           class="form-control col-lg-5 col-md-5 col-sm-12 col-12" min="1" max="30" value="${pelieditar.stock}"/>
           <p id="errorStock" class="text-danger">Solo dígitos numéricos en un rango del 1 al 30.</p>
        </div>
        <div class="form-group col-lg-3 col-md-3 col-sm-12 col-12">
          <label>Precio: </label>
           <div class="input-group col-lg-6 col-md-6 col-sm-12 col-12">
              <input type="text" class="form-control" id="textoPrecio" 
              name="nuevoprecio" value="${pelieditar.precio}"/>
              <div class="input-group-prepend col-lg-3 col-md-3 col-sm-3 col-3">
               <div class="input-group-text justify-content-center">€</div>
             </div>
           </div>
           <p id="errorPrecio" class="text-danger">Solo dígitos numéricos, entre 1 y 2 carácteres la parte entera, separado por un <strong>punto</strong> de la parte decimal, y entre 1 y 3 caracteres la parte decimal (parte decimal es opcional de escribir).</p>
        </div>
        <div class="form-group col-lg-3 col-md-3 col-sm-12 col-12">
          <label>Elige el género: </label>
          <select class="form-select" name="nuevogenero">
             <c:forEach items="${listageneros}" var="genero">
                  <option value="${genero.idgenero}"
                    <c:if test="${pelieditar.idgenero == genero.idgenero}">
                       <c:out value=" selected"></c:out>
                    </c:if>
                  >${genero.descripcion}</option>
             </c:forEach>
          </select>
        </div>    
     </div>
     <div class="row justify-content-center mt-5">
        <input type="hidden" name="keyword" value="ModificarLaPeli"/>
        <input type="submit" id="btnEditPeli" 
       class="btn btn-lg btn-warning w-50" value="Modificar">
     </div> 
   </form>
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