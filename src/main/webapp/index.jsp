<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarRegistro.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarFotoPerfil.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="contenedor" style="background-image: url('${pageContext.request.contextPath}/resources/img/fucsia.jpg');
background-position: center center;">
   <div class="container">
     <div class="col-lg-4 col-md-6 col-sm-10 col-11">
       <c:if test="${errorLogin!=null}">
        <h2 id="errorlog" class="p-2 text-light">
            ${errorLogin}
        </h2>
     </c:if>
      <c:if test="${errorRegistro != null}">
          <h2 id="errorNewUser" class="p-2 text-light">
               ${errorRegistro}
          </h2>
       </c:if>
      <form action="ControllerCliente" class="bg-light p-3 text-center"
      method="post">
         <img src="resources/img/filmicono.png" width="120px" height="120px"/>
         <h2>Formulario de Login</h2>
         <hr>
         <div class="row justify-content-between g-2">
           <div class="form-group d-flex">
             <label class="me-5 mt-1">Nombre: </label>
             <input type="text" name="txtNombre" 
             class="form-control" value="${elnombre}" required/>
           </div>
         </div>
         <div class="row justify-content-between mt-3 g-2">
           <div class="form-group d-flex">
             <label class="me-4 mt-1">Contraseña: </label>
             <input type="password" name="txtClave" 
             class="form-control" value="${laclave}" required/>
           </div>
         </div>
          
          <div class="row mt-4">
             <input type="submit" class="btn btn-dark" name="keyword" value="Ingresar">
             <button type="button" class="btn btn-success mt-2" data-bs-toggle="modal"
           data-bs-target="#modalregistro">Registrarse</button>
          </div>
      </form> 
      
     <div class="modal fade" id="modalregistro" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" style="max-width:650px!important;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Registrese con sus datos</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
       <form action="ControllerCliente" method="post" enctype="multipart/form-data">
      <div class="modal-body">
        <div class="row justify-content-between g-2">
          <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
            <label class="col-form-label">Dni: <span class="rojo">*</span></label>
            <input type="text" id="textoDni" name="eldni"
             class="form-control" placeholder="00000000T" required/>
            <p id="errorDni" class="text-danger">Los 8 primeros carácteres son números y después una letra mayúscula (deben ir juntos).</p>
          </div>
          <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
            <label class="col-form-label">Nombre: <span class="rojo">*</span></label>
            <input type="text" id="textoNombre" name="elnombre"
             class="form-control" required/>
            <p id="errorNombre" class="text-danger">Solo carácteres alfabéticos entre 4 y 20 carácteres (se permiten acentos y espacios en blanco).</p>
          </div>
       </div>
       <div class="row mt-3">
          <div class="form-group col">
            <label class="col-form-label">Email: <span class="rojo">*</span></label>
            <div class="input-group">
             <div class="input-group-prepend col-2">
                <div class="input-group-text justify-content-center">@</div>
              </div>
            <input type="email" id="textoEmail" name="elemail"
             class="form-control" placeholder="...@gmail.com" required/>
            </div>
            <p id="errorEmail" class="text-danger">Debe tener entre 10 y 30 caracteres alfabéticos y opcionalmente numéricos, incluyendo el @ y cualquiera de estos tipos de dominios: .com .org .es .edu</p>
          </div>
       </div>
       <div class="row justify-content-between mt-2 g-2">
          <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
            <label for="message-text" class="col-form-label">Clave: <span class="rojo">*</span></label>
            <input type="password" id="textoClave" name="laclave" 
            class="form-control" required/>
            <p id="errorClave" class="text-danger">La contraseña debe tener entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula, al menos una mayúscula y al menos un caracter no alfanumérico.</p>
          </div>
          <div class="form-group col-lg-7 col-md-7 col-sm-12 col-12">
            <label for="message-text" class="col-form-label">Dirección: <span class="rojo">*</span></label>
            <input type="text" id="textoDireccion" name="ladireccion" 
            class="form-control" placeholder="C. Ejemplo n3" required/>
            <p id="errorDireccion" class="text-danger">Debe tener una longitud entre 8 y 30 carácteres de cualquier tipo.</p>
          </div>
       </div>
       <div class="row justify-content-between mt-2 g-2">
       <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
            <label for="message-text" class="col-form-label">Foto perfil:</label>
            <input type="file" class="file-select" id="elfile"
             name="fotoperfil" accept="image/gif,image/jpeg,image/jpg,image/png"/>
             <p id="errorPerfil" class="text-danger mt-2">El tamaño de la imagen supera 1MB, adjunte otra menos pesada, y vuelva a registrarse.</p>
        </div>
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
        <input type="submit" id="btnRegistrar" name="keyword" 
        class="btn btn-primary" value="Registrarse"/>
      </div></form>
    </div>
  </div>
</div>
 </div>
     </div>
 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
</body>
</html>