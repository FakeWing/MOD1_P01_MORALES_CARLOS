package modelo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cuenta implements InterfaceCuenta {
	private int cuentaID;
	private String tipoCuenta;
	private int saldo;
	Cliente cliente;
	private int cargo;

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCuentaID() {
		return cuentaID;
	}

	public void setCuentaID(int cuentaID) {
		this.cuentaID = cuentaID;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Cuenta() {

	}

	public Cuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Cuenta(String tipoCuenta, int saldo) {
		this.tipoCuenta = tipoCuenta;
		this.saldo = saldo;
	}

	public Cuenta(int cuentaID, String tipoCuenta, int saldo) {
		this.cuentaID = cuentaID;
		this.tipoCuenta = tipoCuenta;
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cuenta [cuentaID=" + cuentaID + ", tipoCuenta=" + tipoCuenta + ", saldo=" + saldo + "]";
	}

	public int seleccionarID() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Cual es el ID de la cuenta? :");
		int id = scan.nextInt();

		return id;

	}

	public ArrayList<Cuenta> añadirCuenta(ArrayList<Cuenta> cuentas, Cuenta cuenta) {
		cuentas.add(cuenta);
		return cuentas;
	}

	@Override
	public ArrayList<Cuenta> eliminarCuenta(ArrayList<Cuenta> cuentas, Cuenta cuenta, Cliente cliente, int id) {
		boolean opcion = false;
		Scanner scan = new Scanner(System.in);

		if (cuenta.getSaldo() > 0) {
			System.out.println("no se puede eliminar la cuenta: " + cuenta.getCuentaID() + ", ya que tiene saldo $"
					+ cuenta.getSaldo());
			opcion = true;
		}
		System.out.println(cuenta.getSaldo() + "----" + cuenta);

		while (!opcion) {

			System.out.print("Desea eliminar la cuenta ID: " + id + "\nEscriba SI para confirmar.");
			String n = scan.next();
			if (n.equalsIgnoreCase("si")) {
				cuentas.remove(cuenta);
				System.out.println("Cuenta Removida");
				opcion = true;

				return cuentas;
			}

		}

		return cuentas;

	}

	@Override
	public ArrayList<Cuenta> realizarGiro(ArrayList<Cuenta> cuentas, Cuenta cuenta) {
		int saldo = cuenta.getSaldo();
		int res = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Realizar un Giro por?");
		int giro = scan.nextInt();
		if (saldo > giro) {
			System.out.println("Su saldo actual es: $" + saldo);
			System.out.println("El giro que realizara es de: $" + giro + ", con un cargo de: " + cuenta.getCargo());
			res = saldo - giro + cuenta.getCargo();
			cuenta.setSaldo(res);

		} else {
			System.out.println("Su saldo actual es: $" + saldo
					+ ", por lo que no puede realizar un giro por la cantidad de " + giro);
		}

		System.out.print(cuenta);
		return cuentas;

	}

	@Override
	public ArrayList<Cuenta> realizarAbono(ArrayList<Cuenta> cuentas, Cuenta cuenta) {
		int saldo = cuenta.getSaldo();

		Scanner scan = new Scanner(System.in);
		int abono = 0;
		int res = 0;
		boolean max = false;
		if (cuenta.getTipoCuenta().equalsIgnoreCase("vista")) {
			System.out.println("su cuenta soporta un maximo es de $2000000 y usted tiene " + cuenta.getSaldo());
			while (!max) {
				System.out.println("Cuanto quiere abonar?");
				abono = scan.nextInt();
				if ((res = saldo + abono) > 2000000) {
					System.out.println("sobre paso el limite de su cuenta.");
					continue;

				} else {
					res = saldo + abono;
					max = true;

				}

			}
			cuenta.setSaldo(res);
			return cuentas;
		} else {
			System.out.println("Cuanto quiere abonar?");
			abono = scan.nextInt();
			res = saldo + abono;
			cuenta.setSaldo(res);
			return cuentas;
		}

	}

	public int revisarSaldo(ArrayList<Cuenta> cuentas) {
		int money = 0;

		for (Cuenta c : cuentas) {
			money = money + c.getSaldo();
		}

		return money;
	}

	@Override
	public Cuenta crearCuenta(ArrayList<Cuenta> cuentas, Cliente cliente) {
		int n = 0;
		Cuenta c;
		Scanner scan = new Scanner(System.in);
		String tipo = cliente.getTipoCliente();
		Random rand = new Random();

		switch (tipo.toLowerCase()) {

		case "galaxy":
			c = new Cuenta();
			System.out.println("Cuanto es el monto inicial?");
			n = scan.nextInt();
			if (n >= 5000000) {
				System.out.println("su monto inicial es de $" + n );
				String tipo1 = "";
				boolean exit = false;
				while (!exit) {
					System.out.println("Usted puede optar a cuenta diamante o corriente");
					System.out.println("Cual desea?");
					tipo1 = scan.next();
					if (tipo1.equalsIgnoreCase("diamante")) {

						tipo1 = "diamante";
						exit = true;
					}
					if (tipo1.equalsIgnoreCase("corriente")) {
						tipo1 = "corriente";
						exit = true;
					}
				}
				c.setCuentaID(rand.nextInt(999999));
				c.setTipoCuenta(tipo1);
				c.setSaldo(n);
				cuentas.add(c);
				mostrar(c);
				return c;

			}
			if (n > 2000000 && n < 5000000) {
				System.out.println(
						"su monto inicial es de $" + n + " por lo que a usted se le asignara una cuenta corriente.");
				c.setCuentaID(rand.nextInt(999999));
				c.setSaldo(n);
				c.setTipoCuenta("corriente");
				cuentas.add(c);
				mostrar(c);
				return c;
			}

			if (n > 0 && n <= 2000000) {
				System.out.println("Su monto inicial es de $" + n + "");
				String tipo1 = "";
				boolean exit = false;
				while (!exit) {
					System.out.println("Usted puede optar a cuenta vista o corriente");
					System.out.println("Cual desea?");
					tipo1 = scan.next();
					if (tipo1.equalsIgnoreCase("vista")) {

						tipo1 = "vista";
						exit = true;
					}
					if (tipo1.equalsIgnoreCase("corriente")) {
						tipo1 = "corriente";
						exit = true;
					}
				}
				c.setCuentaID(rand.nextInt(999999));
				c.setTipoCuenta(tipo1);
				c.setSaldo(n);
				cuentas.add(c);
				mostrar(c);
				return c;
			}
			if (n <= 0) {
				System.out.print(
						"Monto insuficiente para abrir una cuenta en nuestro banco, por favor vaya a otro banco");
				this.saldo = n;
			} else {
				System.out.println("el cliente premium solo puede tener 3 cuentas");
				mostrarCuentas(cliente);
				break;
			}
			break;

		case "premium":
			if (cliente.getCuentas().size() < 3) {

				c = new Cuenta();
				System.out.println("Cuanto es el monto inicial?");
				n = scan.nextInt();

				if (n > 2000000) {
					System.out.println("su monto inicial es de $" + n
							+ " por lo que a usted se le asignara una cuenta corriente.");
					c.setCuentaID(rand.nextInt(999999));
					c.setSaldo(n);
					c.setTipoCuenta("corriente");
					cuentas.add(c);
					mostrar(c);
					return c;

				}

				if (n > 0 && n <= 2000000) {
					System.out.println("Su monto inicial es de $" + n + "");
					String tipo1 = "";
					boolean exit = false;
					while (!exit) {
						System.out.println("Usted puede optar a cuenta vista o corriente");
						System.out.println("Cual desea?");
						tipo1 = scan.next();
						if (tipo1.equalsIgnoreCase("vista")) {

							tipo1 = "vista";
							exit = true;
						}
						if (tipo1.equalsIgnoreCase("corriente")) {
							tipo1 = "corriente";
							exit = true;
						}
					}
					c.setCuentaID(rand.nextInt(999999));
					c.setTipoCuenta(tipo1);
					c.setSaldo(n);
					cuentas.add(c);
					mostrar(c);
					return c;
				}
				if (n <= 0) {
					System.out.print(
							"Monto insuficiente para abrir una cuenta en nuestro banco, por favor vaya a otro banco");
					this.saldo = n;
				}
			} else {
				System.out.println("el cliente premium solo puede tener 3 cuentas");
				mostrarCuentas(cliente);
				break;
			}
			break;
		case "normal":

			if (cliente.getCuentas().size() < 1) {
				c = new CuentaVista();
				System.out.println("Cuanto es el monto inicial?");
				n = scan.nextInt();
				if (cliente.getTipoCliente().equalsIgnoreCase("Normal")) {
					System.out.println("Se le asignara una cuenta Vista");
					c.setCuentaID(rand.nextInt(999999));
					c.setTipoCuenta("Vista");
					c.setSaldo(n);
					cuentas.add(c);
					mostrar(c);
					return c;

				}
				break;

			} else {
				System.out.println("el cliente normal solo puede tener 1 cuenta vista");
				mostrarCuentas(cliente);
			}

		}
		return c = null;

	}

	public void mostrarCuentas(Cliente cliente) {

		ArrayList<Cuenta> cuentas = cliente.getCuentas();
		System.out.println("LISTA DE CUENTAS DEL CLIENTE RUT " + cliente.getRut());

		for (Cuenta c : cuentas) {
			System.out.println("*******************************************");

			System.out.println("Cuenta ID: " + c.getCuentaID());
			System.out.println("Cuenta: " + c.getTipoCuenta());
			System.out.println("Saldo: " + c.getSaldo());

		}

	}

	public void mostrar(Cuenta cuenta) {

		System.out.println("Cuenta ID: " + cuenta.getCuentaID());
		System.out.println("Tipo: " + cuenta.getTipoCuenta());
		System.out.println("Saldo: $" + cuenta.getSaldo());

	}

	public Cuenta seleccionarCuenta(ArrayList<Cuenta> cuentas, int idcuenta) {

		Cuenta cuenta = null;
		int id = idcuenta;

		for (Cuenta c : cuentas) {
			if (c.getCuentaID() == id) {
				cuenta = c;
			}
		}
		if (cuentas.contains(cuenta)) {
			System.out.println("Cuenta encontrada");
			mostrar(cuenta);
			return cuenta;
		} else {
			System.out.println("Cuenta NO encontrada");
		}

		return cuenta;

	}

}
