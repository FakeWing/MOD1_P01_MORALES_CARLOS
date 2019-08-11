package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Cliente;
import modelo.Cuenta;
import modelo.CuentaCorriente;
import modelo.CuentaDiamante;
import modelo.CuentaVista;

public class Controlador {
	Cliente cliente = new Cliente();
	Cuenta cuenta = new Cuenta();
	ArrayList<Cliente> lista = new ArrayList<Cliente>();
	ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

	public ArrayList<Cliente> crearClientesIniciales(ArrayList<Cliente> lista) {

		Cuenta cuenta = new Cuenta();

		Cliente c1 = new Cliente(1, "11111111", "pepe", 20, 600000, 1, "normal");
		c1.getCuentas().add(new CuentaVista(101099, "vista", 1900000));

		Cliente c2 = new Cliente(2, "22222222", "juan", 26, 2100000, 2, "premium");
		c2.getCuentas().add(new CuentaCorriente(111111, "corriente", 5900000));
		c2.getCuentas().add(new CuentaCorriente(112233, "corriente", 2000000));

		Cliente c3 = new Cliente(3, "33333333", "maria", 19, 800000, 2, "normal");
		c3.getCuentas().add(new CuentaVista(110011, "vista", 1000000));

		Cliente c4 = new Cliente(4, "44444444", "oscar", 32, 2100000, 3, "premium");
		c4.getCuentas().add(new CuentaVista(991199, "vista", 800000));
		c4.getCuentas().add(new CuentaCorriente(888888, "corriente", 1500000));
		c4.getCuentas().add(new CuentaCorriente(123456, "corriente", 3000000));

		Cliente c5 = new Cliente(5, "55555555", "camila", 36, 4500000, 6, "galaxy");
		c5.getCuentas().add(new CuentaDiamante(123321, "diamante", 50000000));
		c5.getCuentas().add(new CuentaCorriente(113344, "corriente", 800000));

		Cliente c6 = new Cliente(6, "66666666", "tester", 26, 3500000, 5, "galaxy");
		c6.getCuentas().add(new CuentaDiamante(999991, "diamante", 55000000));
		c6.getCuentas().add(new CuentaCorriente(333442, "corriente", 1800000));

		Cliente c7 = new Cliente(7, "11110000", "carlos", 24, 700000, 2, "normal");
		c7.getCuentas().add(new CuentaVista(101091, "vista", 0));

		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		lista.add(c4);
		lista.add(c5);
		lista.add(c6);
		lista.add(c7);
		return lista;
	}

	public void menu(ArrayList<Cliente> lista) {
		boolean exit = false;
		String rut = "";
		String bye = "Escriba SI para continuar (Volver al menu principal)";
		System.out.println("Bienvenido al BANCO RUPTO");
		while (!exit) {
			Cliente cliente = new Cliente();
			Scanner scan = new Scanner(System.in);
			System.out.println("****************************");
			System.out.println("En que te ayudamos?");
			System.out.println("1.- Crear un cliente nuevo.");
			System.out.println("2.- Modificar datos de un cliente.");
			System.out.println("3.- Eliminar un cliente.");
			System.out.println("4.- Listar todos los clientes.");
			System.out.println("5.- Cuentas.(Crear cuentas y realizar movimientos)");
			System.out.println("6.- Salir.");
			System.out.println("****************************");

			int opcion = scan.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Ingresar datos del cliente.");
				cliente = cliente.crearCliente(lista);
				if(!lista.contains(cliente)) {
					
					Cuenta cuenta= new Cuenta();
					cuenta.crearCuenta(cliente.getCuentas(), cliente);
					lista.add(cliente);
					break;
					
				}else {
					break;
				
				
				}
				
				
			case 2:
				rut = cliente.seleccionarRut();
				cliente = cliente.seleccionarCliente(lista, rut);
				if (lista.contains(cliente)) {
					cliente.modificarCliente(lista, cliente, rut);
				} else {
					System.out.println("el cliente no se encuentra en los registros.");
				}

				break;
			case 3:
				rut = cliente.seleccionarRut();
				cliente = cliente.seleccionarCliente(lista, rut);
				cliente.eliminarCliente(lista, cliente, rut);

				break;
			case 4:
				System.out.println("Listando clientes");

				cliente.listarCliente(lista);

				break;
			case 5:

				System.out.println("MENU DE CUENTAS");
				rut = cliente.seleccionarRut();
				cliente = cliente.seleccionarCliente(lista, rut);
				if (lista.contains(cliente)) {

					menuCuentas(cliente);

				}

				break;
			case 6:
				bye = "Gracias por utilizar nuestros servicios.";

				exit = true;

				break;

			}

			System.out.println(bye);

			if (scan.next().equalsIgnoreCase("SI")) {

				continue;
			} else {
				exit = true;
			}

		}
	}

	public void menuCuentas(Cliente cliente) {

		boolean salida = false;
		int id = 0;
		while (!salida) {
			Scanner scan = new Scanner(System.in);
			System.out.println("************************************");
			System.out.println("1.- Ver cuentas del cliente.");
			System.out.println("2.- Crear cuenta.");
			System.out.println("3.- Eliminar cuenta.");
			System.out.println("4.- Realizar Giro.");
			System.out.println("5.- Realizar Abono.");
			System.out.println("6.- Volver al menu principal");
			int opcion = scan.nextInt();

			switch (opcion) {
			case 1:
				cuenta.mostrarCuentas(cliente);
				salida = false;
				break;
			case 2:

				cuentas = cliente.getCuentas();

				cuenta.crearCuenta(cuentas, cliente);
				break;
			case 3:
				id = cuenta.seleccionarID();
				cuentas = cliente.getCuentas();
				cuenta = cuenta.seleccionarCuenta(cuentas, id);
				cuenta.eliminarCuenta(cuentas, cuenta, cliente, id);
				System.out.println(cuentas.size());
				if(cuentas.size()==0) {
					cliente.eliminarCliente(lista, cliente, cliente.getRut());
				}
				break;
			case 4:
				id = cuenta.seleccionarID();
				cuentas = cliente.getCuentas();
				cuenta = cuenta.seleccionarCuenta(cuentas, id);
				if (cuentas.contains(cuenta)) {
					cuenta.realizarGiro(cuentas, cuenta);
					cuenta.mostrar(cuenta);
				}else {
					salida=false;
				}

				
				break;
			case 5:
				id = cuenta.seleccionarID();
				cuentas = cliente.getCuentas();
				cuenta = cuenta.seleccionarCuenta(cuentas, id);
				if (cuentas.contains(cuenta)) {
					cuenta.realizarAbono(cuentas, cuenta);
					cuenta.mostrar(cuenta);
				}else {
					salida=false;
					break;
				}

				break;

			case 6:
				salida = true;
				break;
			}
			if (salida = true) {
				break;
			}
			System.out.println("Escriba SI para continuar");
			if (scan.next().equalsIgnoreCase("SI")) {
				continue;
			} else {
				salida = true;
			}
		}

	}

}
