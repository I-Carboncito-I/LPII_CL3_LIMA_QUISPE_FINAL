package Controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ClassProductoLmp;
import model.TblProductocl3;

@WebServlet("/ControladorProducto")
public class ControladorProducto extends HttpServlet {

    private final ClassProductoLmp productoDao = new ClassProductoLmp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action != null) {
            switch (action) {
                case "registrar":
                    registrarProducto(request, response);
                    break;
                case "eliminar":
                    eliminarProducto(request, response);
                    break;
                case "editar":
                    editarProducto(request, response);
                    break;
                case "actualizar":
                    actualizarProducto(request, response);
                    break;
                default:
                    listarProductos(request, response);
                    break;
            }
        } else {
            listarProductos(request, response);
        }
    }
    
    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar parámetros del formulario
        String nombre = request.getParameter("nombre");
        String precioCompraStr = request.getParameter("preciocompra");
        String precioVentaStr = request.getParameter("precioventa");
        String estado = request.getParameter("estado");
        String descripcion = request.getParameter("descripcion");
        
        
        try {
            // Validar y convertir parámetros
            double precioCompra = Double.parseDouble(precioCompraStr);
            double precioVenta = Double.parseDouble(precioVentaStr);
            
            // Crear el objeto TblProductocl2 con los datos del formulario
            TblProductocl3 producto = new TblProductocl3();
            producto.setNombrecl3(nombre);
            producto.setPreciocompcl3(precioCompra);
            producto.setPrecioventacl3(precioVenta);
            producto.setEstadocl3(estado);
            producto.setDescripcl3(descripcion);
            
            // Guardar el producto en la base de datos
            productoDao.RegistrarProducto(producto);
            
            // Redirigir a la misma página para mostrar el listado actualizado
            listarProductos(request, response);
            
        } catch (NumberFormatException e) {
            // Manejar error de conversión de número
            listarProductos(request, response);
        }
    }
    
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("id"));
        TblProductocl3 producto = productoDao.BuscarProducto(idProducto);
        if (producto != null) {
            productoDao.EliminarProducto(producto);
        }
        listarProductos(request, response);
    }
    
    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("id"));
        TblProductocl3 producto = productoDao.BuscarProducto(idProducto);
        if (producto != null) {
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("actualizarproducto.jsp").forward(request, response);
        } else {
            listarProductos(request, response);
        }
    }
    
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar parámetros del formulario de edición
        int idProducto = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String precioCompraStr = request.getParameter("preciocompra");
        String precioVentaStr = request.getParameter("precioventa");
        String estado = request.getParameter("estado");
        String descripcion = request.getParameter("descripcion");

        try {
            // Validar y convertir parámetros
            double precioCompra = Double.parseDouble(precioCompraStr);
            double precioVenta = Double.parseDouble(precioVentaStr);
            
            // Crear el objeto TblProductocl3 con los datos del formulario
            TblProductocl3 producto = new TblProductocl3();
            producto.setIdproductoscl3(idProducto);
            producto.setNombrecl3(nombre);
            producto.setPreciocompcl3(precioCompra);
            producto.setPrecioventacl3(precioVenta);
            producto.setEstadocl3(estado);
            producto.setDescripcl3(descripcion);
            
            // Actualizar el producto en la base de datos
            productoDao.ActualizarProducto(producto);
            
            // Redirigir a la misma página para mostrar el listado actualizado
            listarProductos(request, response);
            
        } catch (NumberFormatException e) {
            // Manejar error de conversión de número
            listarProductos(request, response);
        }
    }

    
    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener lista de productos desde el DAO
            List<TblProductocl3> productos = productoDao.ListadoProductos();

            // Setear la lista de productos como atributo para ser mostrada en el JSP
            request.setAttribute("productos", productos);

            // Redirigir al JSP para mostrar el listado
            request.getRequestDispatcher("registrarproducto.jsp").forward(request, response);

        } catch (Exception e) {
            // Manejar cualquier otra excepción
            e.printStackTrace(); // Imprime la excepción en la consola del servidor
            request.setAttribute("error", "Error al obtener la lista de productos.");
            request.getRequestDispatcher("registrarproducto.jsp").forward(request, response);
        }
    }
}