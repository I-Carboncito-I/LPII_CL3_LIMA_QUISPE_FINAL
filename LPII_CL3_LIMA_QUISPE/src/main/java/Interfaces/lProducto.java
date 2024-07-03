package Interfaces;

import java.util.List;
import model.TblProductocl3;

public interface lProducto {
    void RegistrarProducto(TblProductocl3 producto);
    void ActualizarProducto(TblProductocl3 producto);
    void EliminarProducto(TblProductocl3 producto);
    TblProductocl3 BuscarProducto(int idProducto);
    List<TblProductocl3> ListadoProductos();
}