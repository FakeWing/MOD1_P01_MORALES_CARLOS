package modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class CuentaDiamante extends Cuenta {
	
	private int cargo;
	
	

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	public CuentaDiamante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CuentaDiamante(int cuentaID, String tipoCuenta, int saldo) {
		super(cuentaID, tipoCuenta, saldo);
		// TODO Auto-generated constructor stub
	}

	public CuentaDiamante(String tipoCuenta, int saldo) {
		super(tipoCuenta, saldo);
		// TODO Auto-generated constructor stub
	}

	public CuentaDiamante(String tipoCuenta) {
		super(tipoCuenta);
		// TODO Auto-generated constructor stub
	}
	public CuentaDiamante(int cuentaID,  int saldo, int cargo) {
		super(cuentaID,"diamante",saldo);
		this.cargo=cargo;
	}

	@Override
	public ArrayList<Cuenta> realizarGiro(ArrayList<Cuenta> cuentas, Cuenta cuenta) {
		
		int saldo=cuenta.getSaldo();
		cuenta.setCargo(300);
		int res=0;
		Scanner scan= new Scanner(System.in);
		System.out.println("Realizar un Giro por?");int giro=scan.nextInt();
		if(saldo>giro) {
			System.out.println("Su saldo actual es: $"+saldo);
			System.out.println("El giro que realizara es de: $"+giro+", con un cargo de: "+cuenta.getCargo());
			res=saldo-giro+cuenta.getCargo();
			cuenta.setSaldo(res);
			
		}else {
			System.out.println("Su saldo actual es: $"+saldo+", por lo que no puede realizar un giro por la cantidad de "+giro);
		}
		
		System.out.print(cuenta);
		return cuentas;
	}
}


