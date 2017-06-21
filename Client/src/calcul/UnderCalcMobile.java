package calcul;

import java.util.ArrayList;

public class UnderCalcMobile extends UnderCalc {

	// private ClientMobile client;
	private String messRecu;

	public UnderCalcMobile() {
		super();
		//this.messRecu = new String();
	}
	
	public UnderCalcMobile(int id, ArrayList<Double> data, ArrayList<Character> ope) {
		super(id, data, ope);
		this.messRecu = new String();	
	}

	public void SetMessRecu(String str)  {
	    this.messRecu = str;
	}
	
	public void RecupUCalc() {
		int j = 0;
		int indiceLimit = 0;
		String tmp;
		for (int i = 0; i < this.messRecu.length(); i++) {
			if (this.messRecu.charAt(i) == '&') {
				indiceLimit = i;
				break;
			}
		}
		System.out.println("indice Limite: " + indiceLimit);
		for (int i = 0; i < indiceLimit; i++) {
			if (((this.messRecu.charAt(i) > 47) && (this.messRecu.charAt(i) < 58)) || (this.messRecu.charAt(i) == '.'))
					j++;
			if (this.messRecu.charAt(i) == '#') {
				tmp = this.messRecu.substring(i - j, i);
				this.value.add(Double.parseDouble(tmp));
				j = 0;
			}
		}
		for (int i = indiceLimit + 1; i < this.messRecu.length(); i++) {
			if (this.messRecu.charAt(i) != '#')
				this.operator.add(this.messRecu.charAt(i));
		}
	}
	
	public Double CalcUnderCalc() {
		this.res = this.value.get(0);
		int m = this.value.size();

		for (int i = 1; i < m; i++) {
			if (this.value.get(i) != 0) {
				switch (this.operator.get(i - 1)) {
				case '+':
					this.res += this.value.get(i);
					break;
				case '-':
					this.res -= this.value.get(i);
					break;
				case '*':
					this.res *= this.value.get(i);
					break;
				case '/':
					if (this.operator.get(i) == 0)
						System.out.println("Division par zero: division non effectué");
					else
						this.res *= this.value.get(i);
					break;
				default:
					break;
				}
			}
		}
		return this.res;
	}

	public void AffMess() {
		System.out.println("Message re�u :" + messRecu);
	}

	public void AffVal() {
		System.out.println("Liste des valeurs: ");
		for (int i = 0; i < this.value.size(); i++)
			System.out.print(this.value.get(i) + " ");
		System.out.println();
	}

	public void AffOpe() {
		System.out.println("Liste des operateurs: ");
		for (int i = 0; i < this.operator.size(); i++)
			System.out.print(this.operator.get(i) + " ");
		System.out.println();
	}
}