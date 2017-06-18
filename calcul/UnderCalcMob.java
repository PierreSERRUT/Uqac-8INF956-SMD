package calc;

import java.util.ArrayList;

public class UnderCalcMob extends UnderCalc {

	// private ClientMobile client;

	public UnderCalcMob() {
		super();
	}

	public UnderCalcMob(int id, ArrayList<Double> data, ArrayList<Character> ope) {
		super(id, data, ope);
	}

	public double CalcUnderCalc() {
		this.res = this.val.get(0);
		int m = this.val.size();

		for (int i = 1; i < m; i++) {
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
		return this.res;
	}
}
