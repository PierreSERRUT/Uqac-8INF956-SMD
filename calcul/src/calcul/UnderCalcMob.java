package calcul;

import java.util.ArrayList;

public class UnderCalcMob extends UnderCalc {

	// private ClientMobile client;
	private String messRecu;

	public UnderCalcMob() {
		super();
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
			if (this.messRecu.charAt(i) == '&')
				indiceLimit = i;
		}
		System.out.println("indice Limite: " + indiceLimit);
		for (int i = 0; i < indiceLimit; i++) {
			if (((this.messRecu.charAt(i) > 47) && (this.messRecu.charAt(i) < 58)) || (this.messRecu.charAt(i) == '.'))
					j++;
			if (this.messRecu.charAt(i) == '#') {
				tmp = this.messRecu.substring(i - j, i);
				this.val.add(Double.parseDouble(tmp));
				j = 0;
			}
		}
		for (int i = indiceLimit + 1; i < this.messRecu.length(); i++) {
			if (this.messRecu.charAt(i) != '#')
				this.ope.add(this.messRecu.charAt(i));
		}
	}
	
	public UnderCalcMob(int id, ArrayList<Double> data, ArrayList<Character> ope) {
		super(id, data, ope);
		this.messRecu = new String();
	}

	public double CalcUnderCalc() {
		this.res = this.val.get(0);
		int m = this.val.size();

		for (int i = 1; i < m; i++) {
			if (this.val.get(i) != 0) {
				switch (this.ope.get(i - 1)) {
				case '+':
					this.res += this.val.get(i);
					break;
				case '-':
					this.res -= this.val.get(i);
					break;
				case '*':
					this.res *= this.val.get(i);
					break;
				case '/':
					if (this.ope.get(i) == 0)
						System.out.println("Division par zero: division non effectué");
					else
						this.res *= this.val.get(i);
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
		for (int i = 0; i < this.val.size(); i++)
			System.out.print(this.val.get(i) + " ");
		System.out.println();

	}

	public void AffOpe() {
		System.out.println("Liste des operateurs: ");
		for (int i = 0; i < this.ope.size(); i++)
			System.out.print(this.ope.get(i) + " ");
		System.out.println();
	}
}
