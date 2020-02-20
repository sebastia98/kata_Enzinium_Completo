package edu.elsmancs.domain.EnZinIum;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {
	
	private Address owner = null;
	private double ownerBalance = 0d;
	private String name = null;
	private String symbol = null;
	private Integer totalSupply = 0;
	private double tokenPrice = 0d;
	private Integer tokenSold = 0; 
	private Map<PublicKey, Integer> balances = new HashMap<>();
	
	
	public String toString() {
		return "Nombre = " + this.name() + "\n" +
				"Símbolo = " + this.symbol() + "\n" +
				"Total entradas: " + this.totalSupply();
	} 
	
	public TokenContract(Address owner) {
		this.owner= owner;
	}
	public void setBalanceOwner(double dineroPagador) {
		this.ownerBalance = this.ownerBalance + dineroPagador;
	}
	
	public void setName(String name) {
		this.name = name;	
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;	
	}

	public void setTotalSupply(Integer totalSupply) {
		this.totalSupply = totalSupply;	
	}

	public void setTokenPrice(double tokenPrice) {
		this.tokenPrice = tokenPrice;	
	}
	
	public String name() {
		return this.name;
	}
	
	public String symbol() {
		return this.symbol;
	}
	
	public Integer totalSupply() {
		return this.totalSupply;
	}

	public void addOwner(PublicKey pk, Integer tokens) {
		this.balances.putIfAbsent(pk, tokens);	
	}

	public int numOwners() {
		return this.balances.size();
	}

	public Integer balanceOf(PublicKey pk) {
		return this.balances.getOrDefault(pk, 0);
	}

	public void transfer(PublicKey pkComprador, Integer units) {
		
		/*Método require que comprueba que units no es mayor al totalSupply*/
		if (units < this.balanceOf(this.owner.getPK())){
			this.tokenSold = this.tokenSold + units;
			this.balances.put(this.owner.getPK(), (this.balanceOf(this.owner.getPK()) - units));
			this.balances.put(pkComprador, this.balanceOf(pkComprador) + units);
		}
	}
	
	/*private void require(Boolean holds) {
		if (holds == false) {
			throws new 
		}
	}*/
	
	public void transfer(PublicKey pkSender, PublicKey pkRecipient, Integer tokens) {
		
		/*Método require*/
		if (tokens < this.balanceOf(pkSender)) {
			this.balances.put(pkSender, this.balanceOf(pkSender) - tokens);
			this.balances.put(pkRecipient, this.balanceOf(pkRecipient) + tokens);
		}
	}

	public void owners() {
		/*Falta que no imprima el ID de rick (owner)*/
		this.balances.forEach((PK, tokens) -> System.out.println("Address: " + PK.hashCode() + "\nTokens: " + tokens +
																"\n---------------"));
    	}

	public Integer totalTokensSold() {
		return this.tokenSold;
	}
	
	public boolean payable(PublicKey address, Double enziniums) {
		if (enziniums > this.tokenPrice) {
			int tokensPagables = (int)(enziniums/this.tokenPrice);
			this.transfer(address, tokensPagables);
			return true;
		}
		return false;
	}
	
	
	
	
}


