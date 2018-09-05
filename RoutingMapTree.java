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

	public Exchange getRoot()
	{
		return this.root;
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
		// if (a.status()) {
		// 	System.out.println("Moible is already on");
		// 	return;
		// }
		// else
		// {
			
		Exchange ptr = b;// *** check if b is base station and not any other exchange
	
			a.switchOn();
			a.setBase(b);				
			// System.out.println("Moible is turned on and base is set.");
			do
			{
			ptr.getMobileList().Insert(a);
			// System.out.println("Moible is inserted in Exchange of id : " +  ptr.getid());
				ptr = ptr.parent();
			}
			while(ptr!=null);
				
			// }
		
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

	public Exchange searchExchange(int id)
	{

			// System.out.println("befor if statement that compares " + this.root.getid() + id);

		if (this.root.getid() == id) {
			// System.out.println("print statement inside searchExchange root");
			return root;
		}
		for (int i = 0 ; i<root.numChildren() ; i++ ) {
			// System.out.println("print statement inside searchExchange for loop when the wanted exchange is not the current one");
			// System.out.println("going to check the " + i + "th subtree");
			try
			{
				Exchange check = this.root.subtree(i).searchExchange(id);
				if (check != null) {
					return check;
				}
	
			}
			catch(NullPointerException e){
			// System.out.println("didn't get exchange in subtree with root of this index" + this.root.subtree(i).getRoot().getid());

			}

			// if (check.getRoot().getid() == id) 
			// {
			// System.out.println("root one");

			// 		return check.getRoot();
			// }
			// else
			// {
			// System.out.println("inside else one");

			// 	Exchange temp = check.getRoot().subtree(i).searchExchange(id);
			// 	if(temp!=null)
			// 	{
			// 		return temp;
			// 	}

			// }

		}
			// System.out.println("need to return null");

		return null; //if not in the current subtree of tree.
	}

	public void performAction(String actionMessage) 
	{
		int n = actionMessage.length();
		String[] inputWords = actionMessage.split(" ");

		for ( String ss : inputWords ) {

		}

		

			if (inputWords[0].equals("addExchange")) {
				// Search Exchange with id parseInt(inputWords[1] and then 
				Exchange temp = this.searchExchange(Integer.parseInt(inputWords[1]));
				// System.out.println(temp.getid());
				// add parseInt(inputWords[2] exchange inside childList
				Exchange nodeToAdd = new Exchange();
				nodeToAdd.setid(Integer.parseInt(inputWords[2]));
				// System.out.println("i have set id of exchange as " + Integer.parseInt(inputWords[2]) + " is it equal to " + nodeToAdd.getid());
				nodeToAdd.setparent(temp);
				// System.out.println("size of the childList before adding the exchange:" + temp.getListChildren().size());
				temp.getListChildren().Insert(nodeToAdd);
				// System.out.println("size of the childList :" + temp.getListChildren().size());
			}


			else if (inputWords[0].equals("switchOnMobile")) {   //*** special case if obile is already there and is in any other base station then put in other base station.
				// Search parseInt(inputWords[2] in all tree and return basestaion b
				Exchange b = this.searchExchange(Integer.parseInt(inputWords[2])); // *** also make sure that this base station is actually a base station and not any other exchange....
				MobilePhone a = new MobilePhone(Integer.parseInt(inputWords[1]));
				// return the MobilePhone a which has the id parseInt(inputWords[1] in basestaion b
				this.switchOn(a,b);
			}

			
			else if (inputWords[0].equals("switchOffMobile")) {
				// Search mobile id parseInt(inputWords[1]) in Mobileset of root Exchange and then return (MobilePhomne a)
				LinkedList<MobilePhone> list = this.root.getMobileList().getList();
				// now this list has nodes of which nodes.data are of type MobilePhone
				MobilePhone a = new MobilePhone();
				int size = list.size();	
				// System.out.println("size befor deleting is :" + size);
				int i = 0;
				for (i = 0; i < size ;i++ ) {	
					if (list.getChildat(i).number() ==	 Integer.parseInt(inputWords[1])) 
					{					
						a = list.getChildat(i);
						break;
					}
				}
				if (i==size) 
				{
					System.out.println("The mobile phone is alreday off"); // precisely didn't find mobile number in mobilephoneset. 
				}
				//**** check if size == i


				this.switchOff(a);
				// System.out.println("size after deleting is :" + list.size());

				
			}

			else if (inputWords[0].equals("queryNthChild")) {
				// funtiond exchange a which is the ( parseInt(inputWords[2] )th child of Exchange with id parseInt(inputWords[1]
				Exchange b = this.searchExchange(Integer.parseInt(inputWords[1]));
				// System.out.println("id of the parent is :" + b.getid());
				// System.out.println("Now I want to print the id of the " + Integer.parseInt(inputWords[2]) + "th " + "child of the Exchange with id " + b.getid());

				Exchange child = b.child(Integer.parseInt(inputWords[2]));
				if(child.getid() >= 0)   // ******  
				{
					System.out.println(actionMessage + ": "+ child.getid());
				}
				else
					System.out.println(actionMessage + ": "+"null");
			}


			else if (inputWords[0].equals("queryMobilePhoneSet")) {
				// find exchange a with id parseInt(inputWords[1]
				Exchange b = this.searchExchange(Integer.parseInt(inputWords[1]));
				String ans = b.getMobileList().printSet();
				// print ids of all elements in MobilePhoneSet of a ..... there is a funtion most probably
				System.out.println(inputWords[0]+ " " + inputWords[1] + ": " +ans);
			}

					
	}
}
	