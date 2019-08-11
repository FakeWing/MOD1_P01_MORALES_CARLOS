package modelo;

import java.util.ArrayList;

public interface InterfaceCliente {
	
	
	public Cliente crearCliente(ArrayList<Cliente> lista);
	public void listarCliente(ArrayList<Cliente> lista);
	public ArrayList<Cliente> eliminarCliente(ArrayList<Cliente> lista, Cliente cliente,String rut);
	public ArrayList<Cliente> modificarCliente(ArrayList<Cliente> lista,Cliente cliente,String rut);

}
