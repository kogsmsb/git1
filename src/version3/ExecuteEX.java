package version3;

public class ExecuteEX {

	private ExecuteEX () {

	};
	static String result (String origin) throws ArithmeticException {
		
		String a="";
		String b="";
		String op="";
		String result=origin;
		while (result.contains("×") || result.contains("/")) {
			int mul=result.indexOf("×");
			int div=result.indexOf("/");
			int index;
			if (mul==-1 && !(div==-1)) {
				op="/";
				index=div;
			}
			else if (!(mul==-1) && div==-1) {
				op="×";
				index=mul;
			}
			else {
				if (mul<div) {
					op="×";
					index=mul;
				}
				else {
					op="/";
					index=div;
				}
			}
			a="";
			char temp;
			int up=1;
			while (index+up<result.length()) {
				temp=result.charAt(index+up);
				if (temp=='+' || temp=='-' ||  temp=='×' ||  temp=='/' ) {
					break;
				}
				else {
					a=a+temp;
					up++;
				}
			}
			int down=1;
			while (index>=down) {
				temp=result.charAt(index-down);
				if (temp=='+' || temp=='-' ||  temp=='×' ||  temp=='/' ) {
					break;
				}
				else {
					down++;
				}
			}
			down=index-down+1;
			b=result.substring(down, index);
			String rep=ComputeEX.result(b, a, op);
			int rlength=result.length();
			result=result.substring(0, down)+rep+result.substring(index+up, rlength);
		}
		
		//System.out.println("\n"+result+"\n");
		
		while (result.contains("+") || result.contains("-")) {
			int add=result.indexOf("+");
			int sub=result.indexOf("-");
			int index;
			int flag=0;
			a="";
			if (sub==0) {
				flag=1;
				result=result.substring(1, result.length());
				sub=result.indexOf("-");
				add=result.indexOf("+");
				System.out.println("\n"+result+"\n");
				System.out.println("sub="+sub+"\n");
			}
			if (add==-1 && !(sub==-1)) {
				op="-";
				index=sub;
			}
			else if (!(add==-1) && sub==-1) {
				op="+";
				index=add;
			}
			else if (add==-1 && sub==-1) {
				result="-"+result;
				break;
			}
			else {
				if (add<sub) {
					op="+";
					index=add;
				}
				else {
					op="-";
					index=sub;
				}
			}
			System.out.println("index="+index+"\n");
			System.out.println("op="+op+"\n");
			char temp;
			int up=1;
			System.out.println("up="+up+"\n");
			System.out.println("r="+result.charAt(index+up)+"\n");
			while (index+up<result.length()) {
				temp=result.charAt(index+up);
				System.out.println("temp="+temp+"\n");
				if (temp=='+' || temp=='-' ||  temp=='×' ||  temp=='/' ) {
					break;
				}
				else {
					a=a+temp;
					up++;
				}
			}
			System.out.println("a="+a+"\n");
			int down=1;
			while (index>=down) {
				temp=result.charAt(index-down);
				if (temp=='+' || temp=='-' ||  temp=='×' ||  temp=='/' ) {
					break;
				}
				else {
					down++;
				}
			}
			down=index-down+1;
			b=result.substring(down, index);
			if (flag==1) {
				b="-"+b;
			}
			if (b.equals("")) {
				break;
			}
			System.out.println("b="+b+"\n");
			String rep=ComputeEX.result(b, a, op);
			int rlength=result.length();
			result=result.substring(0, down)+rep+result.substring(index+up, rlength);
			System.out.println("\n"+result+"\n");
		}
		return result;
	}
}
