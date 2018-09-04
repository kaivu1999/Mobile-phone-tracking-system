// implements a linked list of exchanges.

public class ExchangeList extends Myset<Exchange>
{


	public Exchange getChild(int i)
	{
		return (getList().getChildat(i));
	} 
}
