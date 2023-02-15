package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoComentarios;
import dao.DaoCompras;
import dao.DaoDetallepeliculas;
import dao.DaoGenerospeliculas;
import dao.DaoPago;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import carrocompras.Carrito;
import dao.DaoClientes;
import dao.DaoPeliculas;
import entidades.Clientes;
import entidades.Comentarios;
import entidades.Compras;
import entidades.Detallepeliculas;
import entidades.Generospeliculas;
import entidades.Pago;
import entidades.Peliculas;
import excepciones.LoginException;
import excepciones.PeliculaException;
import excepciones.RegistroException;
/**
 * Servlet implementation class Controller
 */
@MultipartConfig
@WebServlet("/ControllerCliente")
public class ControllerCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerCliente() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void procesarErrorLogin(HttpServletRequest request, HttpServletResponse response, LoginException le, String url)
    		throws ServletException, IOException {
    	   String mensajeerror = le.getMessage();
    	   request.setAttribute("errorLogin", mensajeerror);
    	   request.getRequestDispatcher(url).forward(request, response);
    }
    
    protected void procesarErrorRegistro(HttpServletRequest request, HttpServletResponse response, RegistroException re, String url) 
    		throws ServletException, IOException {
    	String mensajeerror = re.getMessage();
 	    request.setAttribute("errorRegistro", mensajeerror);
 	   request.getRequestDispatcher(url).forward(request, response);
    }
    
    protected void procesarErrorPelicula(HttpServletRequest request, HttpServletResponse response, PeliculaException pe, String url) 
    		throws ServletException, IOException{
    	    String mensajeError = pe.getMessage();
    	    request.setAttribute("error", mensajeError);
    	    request.getRequestDispatcher(url).forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    List<Carrito> listacarrito = new ArrayList<>();
	int item;
	double importe = 0.0;
	int cantidad = 1;
	
	int rowsByPage = 5;
    int firstRow = 0;
    int lastRow;
	
	DaoComentarios daocomentario = new DaoComentarios();
	DaoGenerospeliculas daogenero = new DaoGenerospeliculas();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String keyword = request.getParameter("keyword");
		DaoPeliculas daopeliculas = new DaoPeliculas();
		DaoCompras daocomprar = new DaoCompras();
		String txtNombre = request.getParameter("txtNombre");
		String txtClave = request.getParameter("txtClave");
        HttpSession sesion = request.getSession(true);
        DaoClientes daoclientes = new DaoClientes();
        
        
        int idpelicula;
        Carrito carrito;
		switch(keyword) {
		   case "Ingresar":
			   try {
				   List<Peliculas> listapeliculas = daopeliculas.listarPeliculas(txtNombre, txtClave);
				   List<Generospeliculas> listageneros = daogenero.listarGeneros();
				   Clientes rolcliente = daoclientes.findClientesByName(txtNombre);
				   request.setAttribute("listapeliculas", listapeliculas);
				   request.setAttribute("listageneros", listageneros);
				   sesion.setAttribute("rolcliente", rolcliente);
				   sesion.setAttribute("nombrecliente", txtNombre);
				   request.getRequestDispatcher("cliente/listadopeliculas.jsp").forward(request, response);
			   } catch(LoginException le) {
				   request.setAttribute("elnombre", txtNombre);
				   request.setAttribute("laclave", txtClave);
				   procesarErrorLogin(request, response, le, "index.jsp");
			   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   break;
		   case "mostrarcaratulas":
			   int idfilm = Integer.parseInt(request.getParameter("id"));
			   daopeliculas.listarIMGpeliculas(idfilm, response);
		   break;
		   case "mostrarperfil":
			   String namesclient = request.getParameter("elnombre");
			   daoclientes.listarIMGperfilCliente(namesclient, response);
		   break;
		   case "Buscar":
			      String criteriogenero = request.getParameter("elgenero");
				  String iniciales = request.getParameter("iniciales");
				  try {
					List<Peliculas> listadopeliculas;
					List<Generospeliculas> listageneros = daogenero.listarGeneros();
					if(iniciales == null) {
						listadopeliculas = daopeliculas.buscarPeliculasByGeneroYTitulo("%", "%");
					} else {
						listadopeliculas = daopeliculas.buscarPeliculasByGeneroYTitulo(criteriogenero, iniciales);
					}
					sesion.setAttribute("lasiniciales", iniciales);
					sesion.setAttribute("criteriogenero", criteriogenero);
					request.setAttribute("nombrecliente", txtNombre);
					request.setAttribute("listapeliculas", listadopeliculas);
					request.setAttribute("listageneros", listageneros);
					request.setAttribute("contador", listacarrito.size());
					request.getRequestDispatcher("cliente/listadopeliculas.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			break;
		    case "addCarrito":
		    	int posicion = 0;
		    	cantidad = 1;
		    	String generopeli = (String) sesion.getAttribute("criteriogenero");
		    	String inicialestitulo = (String) sesion.getAttribute("lasiniciales");
		    	idpelicula = Integer.parseInt(request.getParameter("idpelicula"));
			try {
				Peliculas pelicula = daopeliculas.buscarPeliculasPorId(idpelicula);
                if(listacarrito.size() > 0) {
		    		for(int i=0;i<listacarrito.size();i++) {
		    			if(idpelicula == listacarrito.get(i).getIdpelicula()) {
		    				posicion = i;
		    			}
		    		}
		    		if(idpelicula ==listacarrito.get(posicion).getIdpelicula()) {
		    			  cantidad = listacarrito.get(posicion).getCantidad()+cantidad;
		    			  double subtotal = listacarrito.get(posicion).getPreciocompra()*cantidad;
		    			  listacarrito.get(posicion).setCantidad(cantidad);
		    			  listacarrito.get(posicion).setSubtotal(subtotal);
		    		} else {
		    			item = item+1;
						carrito = new Carrito();
						carrito.setItem(item);
						carrito.setIdpelicula(pelicula.getIdpelicula());
						carrito.setTitulo(pelicula.getTitulo());
						carrito.setDescripcion(pelicula.getDescripcion());
						carrito.setPreciocompra(pelicula.getPrecio());
						carrito.setCantidad(cantidad);
						carrito.setSubtotal(cantidad * pelicula.getPrecio());
						listacarrito.add(carrito);
		    		}
		    	} else {
		    		item = item+1;
					carrito = new Carrito();
					carrito.setItem(item);
					carrito.setIdpelicula(pelicula.getIdpelicula());
					carrito.setTitulo(pelicula.getTitulo());
					carrito.setDescripcion(pelicula.getDescripcion());
					carrito.setPreciocompra(pelicula.getPrecio());
					carrito.setCantidad(cantidad);
					carrito.setSubtotal(cantidad * pelicula.getPrecio());
					listacarrito.add(carrito);
		    	}
				request.setAttribute("contador", listacarrito.size());
				List<Peliculas> listadopeliculas;
				if(inicialestitulo == null) {
					listadopeliculas = daopeliculas.buscarPeliculasByGeneroYTitulo("%", "%");
				} else {
					listadopeliculas = daopeliculas.buscarPeliculasByGeneroYTitulo(generopeli, inicialestitulo);
				}
				List<Generospeliculas> listageneros = daogenero.listarGeneros();
				request.setAttribute("listapeliculas", listadopeliculas);
				request.setAttribute("listageneros", listageneros);
				request.getRequestDispatcher("cliente/listadopeliculas.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    break;
		    case "Carrito":
		    	importe = 0.0;
		    	request.setAttribute("carrito", listacarrito);
		    	for(int i=0; i<listacarrito.size(); i++) {
		    		importe = importe + listacarrito.get(i).getSubtotal();
		    	}
		    	request.setAttribute("totalpagar", importe);
		    	request.getRequestDispatcher("cliente/carrito.jsp").forward(request, response);
		    break;
		    case "EliminarPeliCarro":
		    	int elidpelicula = Integer.parseInt(request.getParameter("idpeli"));
		    	 for(int i=0; i<listacarrito.size(); i++) {
		    		  if(listacarrito.get(i).getIdpelicula() == elidpelicula) {
		    			  listacarrito.remove(i);
		    		  }
		    	 }
		    	 request.getRequestDispatcher("ControllerCliente?keyword=Carrito").forward(request, response);
		    break;
		    case "AumentarCantidad":
		    	int idpe = Integer.parseInt(request.getParameter("idpe"));
		    	int lacantidad = Integer.parseInt(request.getParameter("cantidad"));
		    	for(int i=0;i<listacarrito.size();i++) {
		         	 if(listacarrito.get(i).getIdpelicula() == idpe) {
		    			 listacarrito.get(i).setCantidad(lacantidad);
		    			 double st = listacarrito.get(i).getPreciocompra() * lacantidad;
		    			 listacarrito.get(i).setSubtotal(st);
		    		 }
		    	}
		    break;
		    case "Comprar":
		    	Pago pago = new Pago();
		    	DaoCompras daocompra = new DaoCompras();
		    	DaoPago daopago = new DaoPago();
		    	Compras compra = new Compras();
		    	String namecliente = request.getParameter("namecliente");
		    	String numtarjeta = request.getParameter("numtarjeta");
			try {
				Clientes elcliente = daoclientes.findClientesByName(namecliente);
				double importeredondeo = Math.round(importe*100.0)/100.0;
				pago.setImporte(importeredondeo);
				pago.setTarjetapago(numtarjeta);
				daopago.agregarPago(pago);
				int ultimopago = daopago.buscarPago();
		    	compra.setIdcliente(elcliente.getIdcliente());
		    	compra.setIdpago(ultimopago);
		    	compra.setImporte(importeredondeo);
		    	compra.setEstado("En estado de envío");
		    	compra.setDetallecompra(listacarrito);
				daocompra.realizarCompra(compra);
				request.setAttribute("compraconfirmada", "Compra finalizada.");
		    	item = 0;
		    	listacarrito = new ArrayList<>();
		    	request.getRequestDispatcher("ControllerCliente?keyword=Carrito").forward(request, response);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    break;
		    case "VerCompras":
		       String elnombrecliente = (String) sesion.getAttribute("nombrecliente");
		       lastRow = firstRow + rowsByPage;
		       try {
		    	   ArrayList<Compras> listacompras = daocomprar.listarComprasByCliente(elnombrecliente, rowsByPage, firstRow);
		    	   int totalCompras = daocomprar.totalComprasCliente(elnombrecliente);
		    	   request.setAttribute("firstRow", firstRow);
		    	   request.setAttribute("rowsByPage", rowsByPage);
		    	   request.setAttribute("lastRow", lastRow);
		    	   request.setAttribute("totalCompras", totalCompras);
		    	   request.setAttribute("listacompras", listacompras);
		    	   request.getRequestDispatcher("cliente/detallecompras.jsp").forward(request, response);
		       } catch(Exception e) {
		    	   e.printStackTrace();
		       }
		    break;
		    case "PaginaAnteriorCompras":
		    	String nameclient = (String) sesion.getAttribute("nombrecliente");
			       int thfirstRow = Integer.parseInt(request.getParameter("thefirstRow"));
			       if(thfirstRow < 0) {
			    	   thfirstRow = 0;
			       }
			       lastRow = thfirstRow + rowsByPage;
			       try {
			    	   ArrayList<Compras> listacompras = daocomprar.listarComprasByCliente(nameclient, rowsByPage, thfirstRow);
			    	   int totalCompras = daocomprar.totalComprasCliente(nameclient);
			    	   request.setAttribute("firstRow", thfirstRow);
			    	   request.setAttribute("rowsByPage", rowsByPage);
			    	   request.setAttribute("lastRow", lastRow);
			    	   request.setAttribute("totalCompras", totalCompras);
			    	   request.setAttribute("listacompras", listacompras);
			    	   request.getRequestDispatcher("cliente/detallecompras.jsp").forward(request, response);
			       } catch(Exception e) {
			    	   e.printStackTrace();
			       }
		    break;
		    case "PaginaSiguienteCompras":
		    	String nombrecliente = (String) sesion.getAttribute("nombrecliente");
			       int thefirstRow = Integer.parseInt(request.getParameter("firstRow"));
			       lastRow = thefirstRow + rowsByPage;
			       try {
			    	   int totalCompras = daocomprar.totalComprasCliente(nombrecliente);
			    	   if(thefirstRow > totalCompras) {
			    		   thefirstRow = thefirstRow - rowsByPage;
			    	   }
			    	   if(lastRow > totalCompras) {
			    		   lastRow = totalCompras;
			    	   }
			    	   ArrayList<Compras> listacompras = daocomprar.listarComprasByCliente(nombrecliente, rowsByPage, thefirstRow);
			    	   request.setAttribute("firstRow", thefirstRow);
			    	   request.setAttribute("rowsByPage", rowsByPage);
			    	   request.setAttribute("lastRow", lastRow);
			    	   request.setAttribute("totalCompras", totalCompras);
			    	   request.setAttribute("listacompras", listacompras);
			    	   request.getRequestDispatcher("cliente/detallecompras.jsp").forward(request, response);
			       } catch(Exception e) {
			    	   e.printStackTrace();
			       }
			break;
		    case "VerPelisCompradas":
		    	DaoDetallepeliculas daodetalle = new DaoDetallepeliculas();
		       int elidcompra = Integer.parseInt(request.getParameter("elidcompra"));
		       try {
		    	   ArrayList<Detallepeliculas> listapeliscompra = daodetalle.peliculasByCompra(elidcompra);
		    	   request.setAttribute("elidcompra", elidcompra);
		    	   request.setAttribute("listapeliscompra", listapeliscompra);
		    	   request.getRequestDispatcher("cliente/pelisporcompra.jsp").forward(request, response);
		       } catch(Exception e) {
		    	   e.printStackTrace();
		       }
		    break;
		    case "Registrarse":
		    	String eldni = request.getParameter("eldni");
		    	String elnombre = request.getParameter("elnombre");
		    	String elemail = request.getParameter("elemail");
		    	String laclave = request.getParameter("laclave");
		    	String ladireccion = request.getParameter("ladireccion");
		    	Part parte = request.getPart("fotoperfil");
		    	InputStream streamfotoperfil = parte.getInputStream();
		    	Clientes nuevocliente = new Clientes();
		    	try {
		    		nuevocliente.setDni(eldni);
		    		nuevocliente.setNombre(elnombre);
		    		nuevocliente.setCorreo(elemail);
		    		nuevocliente.setClave(laclave);
		    		nuevocliente.setDireccion(ladireccion);
		    		nuevocliente.setFotoperfil(streamfotoperfil);
		    		daoclientes.registrarCliente(nuevocliente);
		    		request.getRequestDispatcher("index.jsp").forward(request, response);
		    	} catch(RegistroException e) {
		    		 procesarErrorRegistro(request, response, e, "index.jsp");
		    	} catch(Exception ex) {
		    		ex.printStackTrace();
		    	}
		    break;
		    case "IrANotas":
			try {
				List<Comentarios> listacomentarios = daocomentario.listarComentarios();
				request.setAttribute("listacomentarios", listacomentarios);
				request.getRequestDispatcher("cliente/notas.jsp").forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    break;
		    case "Comentar":
		       String ladescripcion = request.getParameter("ladescripcion");
		       Comentarios comentario = new Comentarios();
		       try {
		    	   String nombrecitocliente = (String) sesion.getAttribute("nombrecliente");
		    	   comentario.setDescripcion(ladescripcion);
		    	   comentario.setNombrecliente(nombrecitocliente);
		    	   daocomentario.escribirComentarios(comentario);
		    	   List<Comentarios> listacomentarios = daocomentario.listarComentarios();
				   request.setAttribute("listacomentarios", listacomentarios);
		    	   request.getRequestDispatcher("ControllerCliente?keyword=IrANotas").forward(request, response);
                } catch(Exception e) {
		    	   e.printStackTrace();
		       }
		    break;
		    case "EliminarComentario":
		      int elid = Integer.parseInt(request.getParameter("elid"));
			  try {
				daocomentario.deleteComentario(elid);
				List<Comentarios> listacomentarios = daocomentario.listarComentarios();
				request.setAttribute("listacomentarios", listacomentarios);
		    	request.getRequestDispatcher("cliente/notas.jsp").forward(request, response);
			  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
		    break;
		    case "VerInformacionPelicula":
		    	idpelicula = Integer.parseInt(request.getParameter("idpelicula"));
			try {
				Peliculas peliculadetalle = daopeliculas.buscarPeliculasPorId(idpelicula);
				request.setAttribute("peliculadetalle", peliculadetalle);
				request.getRequestDispatcher("cliente/informacionPelicula.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    break;
		    case "Logout":
		    	sesion.invalidate();
		    	item=0;
		    	listacarrito=new ArrayList<>();
		    	request.getRequestDispatcher("index.jsp").forward(request, response);
		    break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
