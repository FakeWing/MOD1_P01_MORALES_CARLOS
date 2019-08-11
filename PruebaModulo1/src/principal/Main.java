package principal;

import java.util.ArrayList;
import java.util.Scanner;

import controlador.Controlador;
import modelo.Cliente;
import modelo.Cuenta;

public class Main {

	public static void main(String[] args) throws Throwable {
		Scanner scan = new Scanner(System.in);
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

		Controlador controlador = new Controlador();
		controlador.crearClientesIniciales(lista);

		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		controlador.menu(lista);

	}

}
