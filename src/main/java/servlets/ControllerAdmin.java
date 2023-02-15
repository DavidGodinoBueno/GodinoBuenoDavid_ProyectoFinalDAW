package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.DaoGenerospeliculas;
import dao.DaoPeliculas;
import entidades.Generospeliculas;
import entidades.Peliculas;
import excepciones.PeliculaException;

/**
 * Servlet implementation class ControllerAdmin
 */
@MultipartConfig
@WebServlet("/ControllerAdmin")
public class ControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void procesarErrorPelicula(HttpServletRequest request, HttpServletResponse response, PeliculaException pe, String url) 
    		throws ServletException, IOException{
    	    String mensajeError = pe.getMessage();
    	    request.setAttribute("error", mensajeError);
    	    request.getRequestDispatcher(url).forward(request, response);
    }
    
    DaoGenerospeliculas daogenero = new DaoGenerospeliculas();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String keyword = request.getParameter("keyword");
		DaoPeliculas daopeliculas = new DaoPeliculas();
		switch(keyword) {
		  case "IrAltaPelicula":
			try {
				ArrayList<Generospeliculas> listageneros = daogenero.listarGeneros();
				request.setAttribute("listageneros", listageneros);
				request.getRequestDispatcher("administrador/altapelicula.jsp").forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    break;
		    case "AgregarPelicula":
		    	String titulo = request.getParameter("titulo");
		    	float precio = Float.parseFloat(request.getParameter("precio"));
		    	String descripcion = request.getParameter("descripcion");
		    	int genero = Integer.parseInt(request.getParameter("genero"));
		    	int stock = Integer.parseInt(request.getParameter("stock"));
		    	Part fotitopeli = request.getPart("fotopelicula");
		    	InputStream fotopeli = fotitopeli.getInputStream();
		    	Peliculas nuevapelicula = new Peliculas();
		    	try {
		    		nuevapelicula.setTitulo(titulo);
		    		nuevapelicula.setPrecio(precio);
		    		nuevapelicula.setDescripcion(descripcion);
		    		nuevapelicula.setFotoportada(fotopeli);
		    		nuevapelicula.setIdgenero(genero);
		    		nuevapelicula.setStock(stock);
		    		daopeliculas.agregarPelicula(nuevapelicula);
		    		ArrayList<Generospeliculas> listageneros = daogenero.listarGeneros();
		    		request.setAttribute("listageneros", listageneros);
		    		request.setAttribute("confirmacion", "Pelicula agregada: "+nuevapelicula.getTitulo());
		    		request.getRequestDispatcher("administrador/altapelicula.jsp").forward(request, response);
		    	} catch(PeliculaException pe) {
		    		request.setAttribute("eltitulo", titulo);
		    		request.setAttribute("elprecio", precio);
		    		request.setAttribute("ladescripcion", descripcion);
		    		request.setAttribute("elgenero", genero);
		    		request.setAttribute("elstock", stock);
					try {
						ArrayList<Generospeliculas> listageneros = daogenero.listarGeneros();
						request.setAttribute("listageneros", listageneros);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		procesarErrorPelicula(request, response, pe, "administrador/altapelicula.jsp");
		    	}
		    	catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    break;
		    case "IrAModificarEliminar":
		    	request.getRequestDispatcher("administrador/buscarPelisModificarEliminar.jsp").forward(request, response);		
		    break;
		    case "BuscarPelisEditDelete":
		    	String trozotitulo = request.getParameter("trozotitulo");
		    	try {
		    		List<Peliculas> estaspeliculas = daopeliculas.buscarPeliculasPorTitulo(trozotitulo);
		    		request.setAttribute("patrontitulo", trozotitulo);
		    		request.setAttribute("estaspeliculas", estaspeliculas);
		    		request.getRequestDispatcher("administrador/buscarPelisModificarEliminar.jsp").forward(request, response);	
		    	} catch(Exception e) {
		    		e.printStackTrace();
		    	}	
		    break;
		    case "PeliculaAEditar":
		    	int elidpeli = Integer.parseInt(request.getParameter("elidpeli"));
		    	try {
		    		Peliculas pelieditar = daopeliculas.buscarPeliculasPorId(elidpeli);
		    		ArrayList<Generospeliculas> listageneros = daogenero.listarGeneros();
					request.setAttribute("listageneros", listageneros);
		    		request.setAttribute("pelieditar", pelieditar);
		    		request.getRequestDispatcher("administrador/modificarPelicula.jsp").forward(request, response);
		    	} catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    break;
		    case "ModificarLaPeli":
		    	int estapelimodificada = Integer.parseInt(request.getParameter("estapelimodificada"));
                String nuevotitulo = request.getParameter("nuevotitulo");
                float nuevoprecio = Float.parseFloat(request.getParameter("nuevoprecio"));
                String nuevadescripcion = request.getParameter("nuevadescripcion");
                int nuevogenero = Integer.parseInt(request.getParameter("nuevogenero"));
                int nuevostock = Integer.parseInt(request.getParameter("nuevostock"));
                Part nuevacaratula = request.getPart("nuevacaratula");
                InputStream nuevafoto = nuevacaratula.getInputStream();
		    	try {
		    		Peliculas peliculamodificada = daopeliculas.buscarPeliculasPorId(estapelimodificada);
		    		peliculamodificada.setTitulo(nuevotitulo);
		    		peliculamodificada.setPrecio(nuevoprecio);
		    		peliculamodificada.setDescripcion(nuevadescripcion);
		    		peliculamodificada.setIdgenero(nuevogenero);
		    		peliculamodificada.setFotoportada(nuevafoto);
		    		peliculamodificada.setStock(nuevostock);
		    		daopeliculas.modificarPelicula(peliculamodificada);
		    		ArrayList<Generospeliculas> listageneros = daogenero.listarGeneros();
					request.setAttribute("listageneros", listageneros);
		    		request.setAttribute("pelieditar", peliculamodificada);
		    		request.setAttribute("confirmar", "Pelicula modificada");
		    		request.getRequestDispatcher("administrador/modificarPelicula.jsp").forward(request, response);
		    	} catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    break;
		    case "eliminarPelicula":
		    	String patrontitulo = request.getParameter("patrontitulo");
		    	int idpelidelete = Integer.parseInt(request.getParameter("idpelidelete"));
		    	try {
		    		Peliculas pelieliminada = daopeliculas.buscarPeliculasPorId(idpelidelete);
		    		daopeliculas.eliminarPelicula(idpelidelete);
		    		List<Peliculas> estaspeliculas = daopeliculas.buscarPeliculasPorTitulo(patrontitulo);
		    		request.setAttribute("confirmEliminar", "Película eliminada: "+pelieliminada.getTitulo());
		    		request.setAttribute("patrontitulo", patrontitulo);
		    		request.setAttribute("estaspeliculas", estaspeliculas);
		    		request.getRequestDispatcher("administrador/buscarPelisModificarEliminar.jsp").forward(request, response);
		    	} catch(Exception e) {
		    		e.printStackTrace();
		    	}
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
