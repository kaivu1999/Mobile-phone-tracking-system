// Create a class MobilePhoneSet which stores MobilePhone objects in a Myset .
public class MobilePhoneSet extends Myset<MobilePhone>{

 	public String printSet()
	{
		String ans = ": ";
		for(int i=0;i<this.size() ;i++ )
		{
			if (i!=0) 
			{
				ans += ", ";
			}
			int p  = this.getList().getChildat(i).number();
			ans = ans + p;	

		}
		return ans;
	}
}