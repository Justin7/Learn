package my.andr.calc;

public class Calc {
    private double su = 0.0;
    private char   op = '=';

    public String calc(String ssu, String sop){
        double su = Double.parseDouble(ssu);
        switch(this.op){
            case '=': this.su = su;           break;
            case '+': this.su = this.su + su; break;
            case '-': this.su = this.su - su; break;
            case 'X':
			case 'x':
            case '*': this.su = this.su * su; break;
            case '/': this.su = this.su / su; break;
        }
        this.op = sop.charAt(0);
        return ""+this.su;
    }
}