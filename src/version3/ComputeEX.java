package version3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputeEX {

	public ComputeEX () {

	};
	private static int is_op(String op) {
		if (op.equals("+")) {
        	return 1;
        }
		else if (op.equals("-")) {
			return 2;
		}
		else if (op.equals("×")) {
			return 3;
		}
		else if (op.equals("/")) {
			return 4;
		}
        else {
        	return -1;
        }
	}
	static String result (String a,String b,String op) {
		int flag=is_op(op);
		if (flag==-1) {
			throw new ArithmeticException();
		}
		else {
			double num_a=Double.parseDouble(a);
			double num_b=Double.parseDouble(b);
			double outcome=0;
			if (Math.abs(num_a)>1e10 || Math.abs(num_b)>1e10) {
				throw new RuntimeException();
			}
			if (flag==1) {
				outcome=ArithEX.add(num_a, num_b);
			}
			if (flag==2) {
				outcome=ArithEX.sub(num_a, num_b);
			}
			if (flag==3) {
				outcome=ArithEX.mul(num_a, num_b);
				if (Math.abs(outcome)<1e-8) {
					outcome=0;
				}
			}
			if (flag==4) {
				outcome=ArithEX.div(num_a, num_b);
			}
			String tempoutput=Double.toString(outcome);
			String sample="^[1-9].[0-9]+E([0-9]+)$|^[1-9].[0-9]+E(-[0-9]+)$";
			Pattern p;
			Matcher m;
			p=Pattern.compile(sample);
			m=p.matcher(tempoutput);
			
			String output="";
			
			if (m.find()) {
				String base=tempoutput.split("E")[0];
				String index=tempoutput.split("E")[1];
				int e=Integer.parseInt(index);
				if (e>10) {
					throw new RuntimeException();
				}
				else if (e<-10) {
					return "0";
				}
				else {
					if (e>0) {
						String t1=base.split("\\.")[0]+base.split("\\.")[1];
						if (t1.length()>e+1) {
							output=t1.substring(0, e+1)+"."+t1.substring(e+1, t1.length());
						}
						else {
							int loop=e+1-t1.length();
							output=t1;
							for (int i=0;i<loop;i++) {
								output=output.concat("0");
							}
						}
						if (output.length()>10) {
							throw new RuntimeException();
						}
					}
					else {
						e=-e;
						String t1=base.split("\\.")[0]+base.split("\\.")[1];
						for (int i=0;i<e;i++) {
							output=output.concat("0");
						}
						output=output.concat(t1);
						output=output.substring(0, 1)+"."+output.substring(1, output.length());
						while (output.lastIndexOf("0")==output.length()-1) {
							output=output.substring(0, output.length()-1);
						}
						if (output.length()>10) {
							output="0";
						}
					}
				}	
			}
			else {
				output=tempoutput;
				while (output.lastIndexOf("0")==output.length()-1) {
					output=output.substring(0, output.length()-1);
				}
				if (output.lastIndexOf(".")==output.length()-1) {
					output=output.substring(0, output.length()-1);
				}
			}
			return output;
		}
	}
	
}
