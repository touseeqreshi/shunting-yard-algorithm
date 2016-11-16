public class Driver 
{

	public static void main(String[] args) throws Exception 
	{
		String input = "3+4*2/(1-5)^2^3";
		Calculation First = new Calculation(input);
		First.displayInput();
		System.out.print(First.getResult());
			
		String input1 = "5*9^3/(1+2+(2*4))";
		Calculation Second = new Calculation(input1);
		Second.displayInput();
		System.out.println(Second.getResult());
	
		String input2 = "2*6^3/(7+2+(8*4))";
		Calculation Third = new Calculation(input2);
		Third.displayInput();
		System.out.println(Third.getResult());
	}
}
