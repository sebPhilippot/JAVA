import java.util.Scanner;

public class Calc
{
	public static void main(String[] args)
	{
		Double operand1, operand2;
		String operator;

		System.out.println("Java Calculator");
		System.out.println("Type Ctrl-C to quit.");
		System.out.print("> ");
		
		Scanner stdin = new Scanner(System.in);
		operand1 = stdin.nextDouble();
		operator = stdin.next();
		operand2 = stdin.nextDouble();
	
		String result = perform(operator, operand1, operand2);
		System.out.println(result);	
	}

	private static String perform(String operation, Double o1, Double o2)
	{
		String result;
		switch (operation)
		{
			case "+":
				result =  (new Double(o1 + o2)).toString();
			break;
			
			case "-":
				result =  (new Double(o1 - o2)).toString();
			break;

			case "*":
				result =  (new Double(o1 * o2)).toString();
			break;

			case "/":
				if (o2 != 0)
				{
					result =  (new Double(o1 / o2)).toString();
				}
				else
				{
					result =  "Division by 0 is undefined.";
				}
			break;

			default:
				result =  "Operation " + operation + " unknown.";
		}
		
		return result;
	}
}
