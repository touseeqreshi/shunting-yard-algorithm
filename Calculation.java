
public class Calculation 
{
	private Stack queue;
	private Stack oprand;
	private Stack inputstring;
	
	public Calculation(String input)
	{
		this.queue = new Stack();
		this.oprand = new Stack();
		this.inputstring = parseString2Stack(input);
	}
	
	public void displayInput() 
	{
		this.inputstring.display();
	}
	
	//InputString gets converted into a stack of numbers and operators
	public Stack parseString2Stack(String input)
	{
		Stack result = new Stack();
		String temp = "";
		int n = 0;
		if (input.charAt(0) == '-')
		{
			temp += '-';
			n++;
		}
		for (int i = n; i < input.length(); i++)
		{
			if (input.charAt(i) == ' ')
			{
				
			}
			else if (isNumber(input.charAt(i)))
			{
				temp += input.charAt(i);
			}
			else if(input.charAt(i) == '-' && !isNumber(result.peek()))
			{
				temp += input.charAt(i);
			}
			else
			{
				if (temp != "")
				{
					result.stack.addEnd(temp);
				}
				result.stack.addEnd(""+input.charAt(i));
				temp = "";
			}
		}
		if (temp != "")
		{
			result.stack.addEnd(temp);
		}
		return result;
	}
	
	// priority of an operator are defined
	private int op_preced(char op)
	{
		int op_priority = 0;
		switch(op)
		{
			case '^':
				op_priority = 3;
				break;
			case '*':
				op_priority = 2;
				break;
			case '/':
				op_priority = 2;
				break;
			case '+':
				op_priority = 1;
				break;
			case '-':
				op_priority = 1;
				break;
			default:
				op_priority = 0;
				break;
		}
		return op_priority;
	}
	
	//check char is a number.
	//period (".") is considered as OK as it may be a float.
	private Boolean isNumber(char num)
	{
		String numericString = "0123456789.";
		if (numericString.indexOf(num) < 0)
		{
			return false;
		}
		return true; 
	}
	
	//Check InputString is a number including negative value
	private Boolean isNumber(String num)
	{
		if (num.charAt(0) == '-')
		{
			if (num.length() <= 2)
			{
				return false;
			}
			for (int i = 1; i < num.length(); i++)
			{
				if (!isNumber(num.charAt(i)))
				{
					return false;
				}
			}
		} else
		{
			for (int i = 0; i < num.length(); i++)
			{
				if (!isNumber(num.charAt(i)))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	//getResult
	public String getResult() throws Exception
	{
		String currNumber = "";
		while (this.inputstring.peek() != null)
		{
			String temp = this.inputstring.pop();
			if (temp.charAt(0) == '(')
				// "(" start a new calculating operation
			{
				parseLeftParenthesis();
			}
			else if (isNumber(temp))
				//number
			{
				currNumber = temp;
			}
			else	//operator
			{
				if (temp.charAt(0) != '^')
				{
					while ((oprand.peek() != null) && (op_preced(temp.charAt(0)) <= op_preced(oprand.peek().charAt(0))))
					{
						String rightNum = currNumber;
						String leftNum = queue.pop();
						char op = oprand.pop().charAt(0);
						currNumber = goOperation(leftNum, rightNum, op);
					}
				}
				queue.push(currNumber);
				oprand.push(temp);
				currNumber = "";
			}
		}
		while (oprand.peek() != null)
		{
			String rightNum = currNumber;
			String leftNum = queue.pop();
			char op = oprand.pop().charAt(0);
			currNumber = goOperation(leftNum, rightNum, op);
		}
		return currNumber;
	}

	//left parenthesis
	public void parseLeftParenthesis() throws Exception
	{
		String currNumber = "";
		Stack queue = new Stack();
		Stack oprand = new Stack();
		while (this.inputstring.peek().charAt(0) != ')')
		{
			String temp = this.inputstring.pop();
			if (temp.charAt(0) == '(')
			
			{
				parseLeftParenthesis();
			}
			else if (isNumber(temp))
				//digit char
			{
				currNumber = temp;
			}
			else	
				//arithmetic operator
			{
				if (temp.charAt(0) != '^')
				{
					while ((oprand.peek() != null) && (op_preced(temp.charAt(0)) <= op_preced(oprand.peek().charAt(0))))
					{
						String rightNum = currNumber;
						String leftNum = queue.pop();
						char op = oprand.pop().charAt(0);
						currNumber = goOperation(leftNum, rightNum, op);
					}
				}
				queue.push(currNumber);
				oprand.push(temp);
				currNumber = "";
			}
		}
		while (oprand.peek() != null)
		{
			String rightNum = currNumber;
			String leftNum = queue.pop();
			char op = oprand.pop().charAt(0);
			currNumber = goOperation(leftNum, rightNum, op);
		}
		this.inputstring.pop();
		this.inputstring.push(currNumber);
	}
	
	//calculate and return
	private String goOperation(String leftNum, String rightNum, char op)
	{
		double leftNumber = Double.parseDouble(leftNum);
		double rightNumber = Double.parseDouble(rightNum);
		String result = "";
		switch(op)
		{
			case '^':
				result += Math.pow(leftNumber, rightNumber);
				break;
			case '*':
				result += leftNumber * rightNumber;
				break;
			case '/':
				result += leftNumber / rightNumber;
				break;
			case '+':
				result += leftNumber + rightNumber;
				break;
			case '-':
				result += leftNumber - rightNumber;
				break;
			default:
				break;
		}
		return result;
	}
	}
	
