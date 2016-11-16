public class Stack 
{
	public LinkedList stack;
	
	public Stack()
	{
		this.stack = new LinkedList();
	}
	
	public Stack(String input)
	{
		this.stack = new LinkedList(input);
	}
	
	public Stack push(String value)
	{
		this.stack.addFront(value);
		return this;
	}
	
	public String pop() throws Exception
	{
		return this.stack.removeFront();
	}
	
	public String peek()
	{
		if (this.stack.getCount() == 0)
		{
			return null;
		}
		return this.stack.getHead();
	}
	
	public void display()
	{
		this.stack.display();
	}
}