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

		Exchange ptr = b;
	
			a.switchOn();
			a.setBase(b);				
			do
			{
			ptr.getMobileList().Insert(a);
				ptr = ptr.parent();
			}
			while(ptr!=null);
						
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
		if (this.root.getid() == id) {
			return root;
		}
		for (int i = 0 ; i<root.numChildren() ; i++ ) {
			try
			{
				Exchange check = this.root.subtree(i).searchExchange(id);
				if (check != null) {
					return check;
				}
	
			}
			catch(NullPointerException e){
			System.out.println("didn't get exchange in subtree with root of this index" + this.root.subtree(i).getRoot().getid());

			}
		}

		return null; //if not in the current subtree of tree. 
		// then where ever searchExchange is used to get say.. Exchange temp and if you are using temp later use try catch as temp can be null
	}

	public String performAction(String actionMessage) 
	{
		int n = actionMessage.length();
		String[] inputWords = actionMessage.split(" ");
		String  answer = new String();
		for ( String ss : inputWords ) {

		}

		

		if (inputWords[0].equals("addExchange")) {
			// Search Exchange with id parseInt(inputWords[1] and then 
			try
			{
				Exchange temp = this.searchExchange(Integer.parseInt(inputWords[1]));
				// add parseInt(inputWords[2] exchange inside childList
				Exchange nodeToAdd = new Exchange();
				nodeToAdd.setid(Integer.parseInt(inputWords[2]));
				nodeToAdd.setparent(temp);
				temp.getListChildren().Insert(nodeToAdd);
			}
			catch(NullPointerException e)
			{
				System.out.println("The Exchange(id =" +Integer.parseInt(inputWords[1]) + ") to which another exchange was to be added itself doesnot exists." );
			}
			answer = "";
		}


		else if (inputWords[0].equals("switchOnMobile")) {   //*** special case if obile is already there and is in any other base station then put in other base station.
			// Search parseInt(inputWords[2] in all tree and return basestaion b
			Exchange b = this.searchExchange(Integer.parseInt(inputWords[2])); // *** also make sure that this base station is actually a base station and not any other exchange....
			// check if the mobile number exists.
			Exchange ourRoot = this.getRoot();
			LinkedList<MobilePhone> ourMobiles = ourRoot.getMobileList().getList();
			int s = ourMobiles.size();
			int temp = 0;
			for (int i = 0; i<s ; i ++ ) {
				if (Integer.parseInt(inputWords[1]) == ourMobiles.getChildat(i).getNumber()) {
					System.out.println("MobilePhone already exists and is switched on.");			
					temp = 1;	
				}		
			}	

			MobilePhone a = new MobilePhone(Integer.parseInt(inputWords[1]));
			// return the MobilePhone a which has the id parseInt(inputWords[1] in basestaion b
			
			if (temp == 0){ // this is when the mobile phone doeno't exist before.
				this.switchOn(a,b);
			}
			answer = "";

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

			this.switchOff(a);
			answer = "";

			
		}

		else if (inputWords[0].equals("queryNthChild")) {
			// funtiond exchange a which is the ( parseInt(inputWords[2] )th child of Exchange with id parseInt(inputWords[1]
			Exchange b = this.searchExchange(Integer.parseInt(inputWords[1]));
			try
			{
				Exchange child = b.child(Integer.parseInt(inputWords[2]));
				if(child.getid() >= 0)   
				{
					answer = actionMessage + ": "+ child.getid();
				}
				else
					answer = actionMessage + ": "+"null";
			}
			catch(NullPointerException e)
			{
				System.out.println("The "+ Integer.parseInt(inputWords[2]) + "th child doesnot exist for asked Exchange (id =" + Integer.parseInt(inputWords[1]) + ")" );
			}
		}


		else if (inputWords[0].equals("queryMobilePhoneSet")) {
			// find exchange b with id parseInt(inputWords[1]
			// print ids of all elements in MobilePhoneSet of a ..... there is a funtion most probably				
			try
			{
				Exchange b = this.searchExchange(Integer.parseInt(inputWords[1]));
				String ans = b.getMobileList().printSet();
				answer = inputWords[0]+ " " + inputWords[1] + ans;
			}
			catch(NullPointerException e)
			{
				System.out.println("The Exchange with id = " + Integer.parseInt(inputWords[1]) + " doesnot exist.");
			}
		}					
		return answer;
	}
}
	