package edu.elsmancs.domain.EnZinIum;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;
import edu.elsmancs.domain.EnZinIum.GenSig;

public class Address {
	
	private PublicKey PK = null;
	private PrivateKey SK = null;
	KeyPair pair = null;
	private double balanceTransferido = 0.0;
	private double balance = 0.0;
	final private String simbolo = "EZI";
	
	
	public Address() {
		
	}

	public void generateKeyPair() {
		pair = GenSig.generateKeyPair();
		this.PK = pair.getPublic();
		this.SK = pair.getPrivate();
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public String getSimbolo() {
		return this.simbolo;
	}
	
	public String toString() {
		return "Clave pública: " + this.PK.hashCode() + "\n" +
			   "Número de EnZinIums: " + this.getBalance() + " " + this.getSimbolo() +
			   "\nRecarga de " + this.balanceTransferido + " Enziniums en " + this.getPK().hashCode();
	}

	public PublicKey getPK() {
		return this.PK;
	}

	public void transferEZI(double enziniums) {
		this.balanceTransferido = enziniums;
		this.balance = this.balance + this.balanceTransferido;
	}
	
	/*Falta transferir EZIS rick*/
	public void send(TokenContract rickinillos, double dinero) {
		if (rickinillos.payable(this.getPK(), dinero) == true) {
			this.transferEZI(dinero);
		}
	}
	
	
}
