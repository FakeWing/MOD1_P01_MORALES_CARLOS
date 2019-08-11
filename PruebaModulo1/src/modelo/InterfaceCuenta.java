package modelo;

import java.util.ArrayList;

public interface InterfaceCuenta {
	
	public Cuenta crearCuenta(ArrayList<Cuenta> cuentas,Cliente cliente);
	public ArrayList<Cuenta> eliminarCuenta(ArrayList<Cuenta> cuentas, Cuenta cuenta, Cliente cliente, int id);
	public ArrayList<Cuenta>  realizarGiro(ArrayList<Cuenta> cuentas,Cuenta cuenta);
	public ArrayList<Cuenta>  realizarAbono(ArrayList<Cuenta> cuentas,Cuenta cuenta);
	

}
