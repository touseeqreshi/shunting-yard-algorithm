
public class LinkedList 
{
	private Node head;
	private int countList;

	public LinkedList() 
	{
		this.head = null;
		this.countList = 0;
	}
	
	public LinkedList(String string)
	{
		this.head = null;
		this.countList = 0;
		for (int i = 0; i < string.length(); i++)
		{
			if (string.charAt(i) != ' ')
			{
				this.addEnd("" + string.charAt(i));
			}			
		}
	}
	
	public void addFront(String value) 
	{
		this.countList++;
		Node n = new Node(value);
		n.setNextNode(this.head);
		this.head = n;
	}
	
	public String removeFront() throws Exception
	{
		if(this.head == null)
		{
			throw new Exception("List is Empty Can't remove");
		}
		else
		{
			Node nodeToReturn = head;
			head = head.getNextNode();
			nodeToReturn.setNextNode(null);
			this.countList--;
			return nodeToReturn.getPayload();
		}
	}
	
	public void addEnd(String value) 
	{
		if (head == null) {
			this.addFront(value);
		} else {
			Node n = new Node(value);
			Node currNode = head;
			while(currNode.getNextNode() != null) 
			{
				currNode = currNode.getNextNode();
			}
			currNode.setNextNode(n);
			this.countList++;
		}
	}
	
	public String removeEnd() throws Exception
	{
		if(head == null)
		{
			return this.removeFront();
		}
		else
		{
			this.countList--;
			Node currNode = head;
			while(currNode.getNextNode() != null && currNode.getNextNode().getNextNode() != null)
			{
				currNode = currNode.getNextNode();
			}
			if(currNode == head && currNode.getNextNode() == null)
			{
				head = null;
				return currNode.getPayload();
			}
			else
			{
				Node nodeToReturn = currNode.getNextNode();
				currNode.setNextNode(null);
				return nodeToReturn.getPayload();
			}
		}
	}
	
	public String getHead()
	{
		if (this.head == null)
		{
			return "";
		}
		return this.head.getPayload();
	}
	
	public void display()
	{
		if(head == null)
		{
			System.out.println("Empty List");
		}
		else
		{
			System.out.println("Count: " + this.countList);
			this.head.display();
		}
	}
	
	public int getCount()
	{
		return this.countList;
	}
}
