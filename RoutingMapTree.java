public class RoutingMapTree{
	private Exchange root = new Exchange();
	public RoutingMapTree() {
		this.root.setid(0);
		this.root.setparent(null);
	}
	
	public void setRoot(Exchange r)
	{
		this.root = r;
	}
	
	public RoutingMapTree(Exchange r)
	{
		this.root = r;
	}

	public boolean containsNode(Exchange a)
	{
		boolean ans = false;
		
		
		// checking root is a itself
		if(this.root == a)
		{
			ans = true;
			return ans;
		}
		
		// checking Exchange a in every subtree(i) for all children in ExchangeList of Exchange ROOT
		int s = a.numChildren();
		if(s>0)
		{
			for(int i = 0 ; i< s ; i++ )
			{
				if(a.subtree(i).containsNode( a ))
				{
					ans = true;
				}
			}
		}
		
		return ans;
		
	}
	



	public void switchOn(MobilePhone a , Exchange b)
	{
		if (a.status()) {
			return;
		}
		else
		{
			
			Exchange ptr = b;
			if (this.containsNode(b)) {
				a.switchOn();
				a.setBase(b);
				do
				{
					ptr.getMobileList().Insert(a);
					ptr = ptr.parent();
				}
				while(ptr!=null);
				
			}
		}
	}

	public void switchOff(MobilePhone a)
	{
		if(a.status())
		{
			Exchange ptr = a.location();
			a.switchOff();
			do
			{
				ptr.getMobileList().Delete(a);
				ptr = ptr.parent();
			}while(ptr != null);
			
		}
		
	}

	public void performAction(String actionMessage) 
	{
		int n = actionMessage.length();
		String[] inputWords = actionMessage.split(" ");

		for ( String ss : inputWords ) {

			System.out.println(ss);

		}

		if (inputWords[0].isEqual("switchOnMobile")) {
			// Search inputWords[2] in all tree and return basestaion b
			// return the MobilePhone a which has the id inputWords[1] in basestaion b
			switchOn(a,b);
		}

		if (inputWords[0].isEqual("addExchange")) {
			// Search Exchange with id inputWords[2] and then 
			// add inputWords[1] exchange inside childList
			
			
		}
			
	}
}







// Write a java class
// RoutingMapTree
// which is a tree class whose nodes
// are from the
// Exchange
// class.  The class should contain the following:
// –
// RoutingMapTree()
// :   constructor  method.   This  should  create  a
// RoutingMapTree  with  one  Exchange  node,
// the  root  node  which
// has  an  identifier  of  0.
// Create other constructors that you deem
// necessary.
// –
// All general tree methods like
// public Boolean containsNode(Exchange
// a)
// but with
// Exchange
// as the node class.
// –
// public void switchOn(MobilePhone a, Exchange b)
// : This method
// only works on mobile phones that are currently switched off.  It
// switches the phone
// a
// on and registers it with base station
// b
// .  The
// entire routing map tree will be updated accordingly.
// –
// public void switchOff(MobilePhone a)
// : This method only works
// on mobile phones that are currently switched on.  It switches the
// phone
// a
// off.  The entire routing map tree has to be updated ac-
// cordingly.
// –
// public void performAction(String actionMessage)
// : This the
// main stub method that you have to implement.  It takes an action
// as a string.  The list of actions, and their format will be described
// next.