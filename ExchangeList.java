// implements a linked list of exchanges.

public class ExchangeList extends Myset<Exchange>
{


	public Exchange getChild(int i)
	{
		return (getList().getChildat(i));
	} 
	public String printSet()
	{
		String ans = ": ";
		for(int i=0;i<this.size() ;i++ )
		{
			if (i!=0) 
			{
				ans += ", ";
			}
			int p  = this.getList().getChildat(i).getid();
			ans = ans + p;	

		}
		return ans;
	}

	public ExchangeList Union(ExchangeList a)
	{
		ExchangeList c = new ExchangeList();
		c.setList(this.getList().unionof2l(a.getList()));
		return c;
	}

	public void setList(ExchangeList a)
	{
		this.setList(a.getList());// refers to Myset setList.....
	}
}
