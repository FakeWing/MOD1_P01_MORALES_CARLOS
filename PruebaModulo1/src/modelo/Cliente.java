package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import controlador.Controlador;

public class Cliente implements InterfaceCliente {

	private int clienteID;
	private String rut;
	private String nombre;
	private int edad;
	private int renta;
	private int antiguedadLaboral;
	private String tipoCliente;
	private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getAntiguedadLaboral() {
		return antiguedadLaboral;
	}

	public void setAntiguedadLaboral(int antiguedadLaboral) {
		this.antiguedadLaboral = antiguedadLaboral;
	}

	public int getRenta() {
		return renta;
	}

	public void setRenta(int renta) {
		this.renta = renta;
	}

	public String getTipoCliente() {

		return tipoCliente;
	}

	public void setTipoCliente() {

		if ((this.getEdad() > 35) && (this.getRenta() >= 3000000) && (this.getAntiguedadLaboral() >= 3)) {

			this.tipoCliente = "Galaxy";
		} else if ((this.getEdad() > 25) && (this.getRenta() >= 2000000)) {
			this.tipoCliente = "Premium";
		} else {
			this.tipoCliente = "Normal";
		}

	}

	public int getClienteID() {
		return clienteID;
	}

	public void setClienteID(int clienteID) {
		this.clienteID = clienteID;
	}

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cliente() {

	}

	public Cliente(int clienteID, String rut, String nombre, int edad, int renta, int antiguedadLaboral,
			String tipoCliente) {
		super();
		this.clienteID = clienteID;
		this.rut = rut;
		this.nombre = nombre;
		this.edad = edad;
		this.renta = renta;
		this.antiguedadLaboral = antiguedadLaboral;
		this.tipoCliente = tipoCliente;
	}

	public Cliente(int clienteID, String rut, String nombre, int edad, int renta, int antiguedadLaboral,
			String tipoCliente, ArrayList<Cuenta> cuentas) {
		this.clienteID = clienteID;
		this.rut = rut;
		this.nombre = nombre;
		this.edad = edad;
		this.renta = renta;
		this.antiguedadLaboral = antiguedadLaboral;
		this.tipoCliente = tipoCliente;
		this.setCuentas(cuentas);
	}

	@Override
	public String toString() {
		return "Cliente [clienteID=" + clienteID + ", rut=" + rut + ", nombre=" + nombre + ", edad=" + edad + ", renta="
				+ renta + ", antiguedadLaboral=" + antiguedadLaboral + ", tipoCliente=" + tipoCliente + ", cuentas="
				+ cuentas + "]";
	}

	public String seleccionarRut() {
		Scanner scan = new Scanner(System.in);
		boolean rutb = false;
		System.out.println("Ingrese el rut del cliente: ");
		String rut = scan.next();
		
		return rut;
	}

	@Override
	public Cliente crearCliente(ArrayList<Cliente> lista) {

		Scanner scan = new Scanner(System.in);

		this.setClienteID((lista.size() + 2));
		System.out.println("last id: " + this.getClienteID());
		boolean ruta = false;
		System.out.println("Ingrese el rut del cliente: ");
		String rut = scan.next();
		
		
		ArrayList<String> listaRuts = new ArrayList<String>();

		for (Cliente c : lista) {
			listaRuts.add(c.getRut());

		}
		while (!ruta) {
			if (listaRuts.contains(rut)) {
				System.out.println("Este rut ya se encuentra en nuestros registros.");
				System.out.println("Ingresa un nuevo rut:");
				rut = scan.next();

			} else {
				ruta = true;

			}

		}

		this.setRut(rut);
		System.out.print("Nombre del cliente: ");
		this.setNombre(scan.next());
		System.out.print("Edad del cliente: ");
		this.setEdad(scan.nextInt());
		System.out.print("Renta del cliente: ");
		this.setRenta(scan.nextInt());
		System.out.print("Antiguedad del cliente: ");
		this.setAntiguedadLaboral(scan.nextInt());
		this.setTipoCliente();

		return this;

	}

	public ArrayList<Cliente> añadirCliente(ArrayList<Cliente> lista, Cliente cliente) {
		lista.add(cliente);
		return lista;
	}

	@Override
	public void listarCliente(ArrayList<Cliente> lista) {
		Comparator<Cliente> byRUT = (Cliente o1, Cliente o2) -> Integer.valueOf(o1.getRut())
				.compareTo(Integer.valueOf(o2.getRut()));
		lista.sort(byRUT);
		;
		System.out.println("LISTA DE CLIENTES DEL BANCO RUPTO");
		System.out.println("*******************************************");
		for (Cliente c : lista) {

			c.mostrar(c);
		}

	}

	public ArrayList<Cliente> eliminarCliente(ArrayList<Cliente> lista, Cliente cliente, String rut) {
		boolean opcion = false;
		Scanner scan = new Scanner(System.in);
		ArrayList<Cuenta> cuentas = cliente.getCuentas();
		Cuenta c = new Cuenta();
		int saldo = c.revisarSaldo(cuentas);
		if (saldo > 0) {
			System.out.println("no se puede eliminar el cliente rut : " + cliente.getRut() + ", debido a que tiene $"
					+ saldo + " disponible en sus cuentas");
			opcion = true;
		}

		while (!opcion) {
			if (cliente != null) {
				System.out.println("Desea eliminar el cliente rut " + cliente.getRut() + " , nombre "
						+ cliente.getNombre() + " ? \nEscriba SI para confirmar.");
				String n = scan.next();
				if (n.equalsIgnoreCase("si")) {
					lista.remove(cliente);
					System.out.println("Cliente eliminada con exito.");
					opcion = true;
					return lista;

				} else {
					opcion = false;
				}

			} else {
				opcion = false;
			}

		}
		return lista;

	}

	@Override
	public ArrayList<Cliente> modificarCliente(ArrayList<Cliente> lista, Cliente cliente, String rut) {
		boolean modificar = false;
		Scanner scan = new Scanner(System.in);
		while (!modificar) {
			System.out.println("Desea modificar el cliente : " + rut);
			String tipo = cliente.getTipoCliente().toLowerCase();
			int opcion = 0;
			switch (tipo) {
			case "galaxy":

				do {
					System.out.println("Cliente Galaxy, puede modificar: \n1.-Edad\n2.-Renta\n3.-Antiguedad\n4.-Salir");
					opcion = scan.nextInt();
					if (opcion == 1) {
						System.out.println("Cual es la edad actual del cliente?");
						cliente.setEdad(scan.nextInt());
					}
					if (opcion == 2) {
						System.out.println("Cual es la renta actual del cliente?");
						cliente.setRenta(scan.nextInt());
					}
					if (opcion == 3) {
						System.out.println("Cual es la antiguedad laboral actual del cliente?");
						cliente.setAntiguedadLaboral(scan.nextInt());
					}
					if (opcion == 4) {
						break;
					}
					System.out.println("Desea modificar algun otro dato? Escriba SI para confirmar");
					String si = scan.next();

					if (si.equalsIgnoreCase("si")) {
						continue;
					} else {
						break;
					}

				} while (opcion > 0 || opcion < 4);

				break;

			case "premium":

				do {
					System.out.println("Cliente Premium, puede modificar: \n1.-Edad\n2.-Renta\n3.-Salir");
					opcion = scan.nextInt();
					if (opcion == 1) {
						System.out.println("Cual es la edad actual del cliente?");
						cliente.setEdad(scan.nextInt());
						opcion = 1;
					}
					if (opcion == 2) {
						System.out.println("Cual es la renta actual del cliente?");
						cliente.setRenta(scan.nextInt());
						opcion = 2;
					}
					if (opcion == 3) {
						break;
					}

					System.out.println("Desea modificar algun otro dato? Escriba SI para confirmar");
					String si = scan.next();

					if (si.equalsIgnoreCase("si")) {
						continue;
					} else {
						break;
					}
				} while (opcion > 0 || opcion < 3);
				break;

			case "normal":
				System.out.println("Lamentablemente el cliente Normal no puede modificar sus datos");

				break;

			}
			System.out.println("Asi quedaron los datos del cliente");
			mostrar(cliente);
			if (cliente.getTipoCliente().equalsIgnoreCase("normal")) {
				break;
			}
			System.out.println("Escriba SI para volver a modificar o cualquier input para salir");
			if (scan.next().equalsIgnoreCase("SI")) {
				continue;
			} else {
				modificar = true;
			}

		}
		return lista;

	}

	public void mostrar(Cliente cliente) {

		System.out.println("id:" + cliente.getClienteID());
		System.out.println("rut:" + cliente.getRut());
		System.out.println("nombre:" + cliente.getNombre());
		System.out.println("edad:" + cliente.getEdad());
		System.out.println("renta: " + cliente.getRenta());
		System.out.println("antiguedad(años): " + cliente.getAntiguedadLaboral());
		System.out.println("tipo: " + cliente.getTipoCliente());

		ArrayList<Cuenta> listaCuentas = cliente.getCuentas();

		for (int i = 0; i < listaCuentas.size(); i++) {

			System.out.println("Cuenta " + listaCuentas.get(i).getCuentaID() + " - Tipo: "
					+ listaCuentas.get(i).getTipoCuenta() + " - Saldo: $ " + listaCuentas.get(i).getSaldo());

		}

		System.out.println("********************************************");
	}

	public Cliente seleccionarCliente(ArrayList<Cliente> lista, String rut) {
		Cliente cliente = null;
		String n = rut;

		for (Cliente c : lista) {
			if (c.getRut().equalsIgnoreCase(n)) {
				cliente = c;

			}
		}

		if (lista.contains(cliente)) {
			System.out.println("Cliente encontrado");
			mostrar(cliente);
			Cliente c = cliente;
			return c;

		} else {
			System.out.println("el cliente con rut " + n + " no fue encontrado en nuestros registros");

		}

		return cliente;

	}
}
