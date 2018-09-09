public class RoutingMapTree{
	private Exchange root = new Exchange();
	private MobilePhoneSet MobilePhones = new MobilePhoneSet();
	public RoutingMapTree() 
	{
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
		// System.out.println(performAction("queryMobilePhoneSet 0"));
		
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

	public Exchange findPhone(MobilePhone m)
	{
		return m.location();
	}

	public Exchange lowestRouter(Exchange a, Exchange b)
	{
		Exchange aParents[] = a.parents();
		Exchange bParents[] = b.parents();
		int aSize=aParents.length;
		int bSize=bParents.length;
		int i = aSize-1;
		int j = bSize-1;
		for(; i>=0 && j>=0 ;)
		{			
			if(aParents[i].getid() != bParents[j].getid())
			{
				return aParents[i+1];
			}
			else if(aParents[i].getid() == bParents[j].getid() && i==0 && j==0)
			{
				return aParents[i];
			}				
			i=i-1;
			j=j-1;
		}
		return null;
	}
	public ExchangeList routeCall(MobilePhone a, MobilePhone b)
	{
		Exchange aBase = a.location();
		Exchange bBase = b.location();
		Exchange lR = lowestRouter(aBase, bBase);
		ExchangeList routeList = new ExchangeList();
		Exchange ptra = aBase;
		while(ptra!=lR)
		{
			routeList.Insert(ptra);
			ptra=ptra.parent();
		}
		routeList.Insert(lR);
		ExchangeList temp = new ExchangeList();
		Exchange ptrb = bBase;
		while(ptrb!=lR)
		{
			temp.InsertAtFront(ptrb);
			ptrb = ptrb.parent();
		}	
		ExchangeList ans = routeList.Union(temp);
		return ans;
	}

	
	public void movePhone(MobilePhone a, Exchange b)
	{
		if(a.status() && b.numChildren() == 0)
		{
			Exchange currentBase = a.location();
			switchOff(a);
			switchOn(a, b);
		}	
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
			LinkedList<MobilePhone> MobilePhones = this.MobilePhones.getList();
			int sizeOfAll = MobilePhones.size();
			LinkedList<MobilePhone> ourMobiles = ourRoot.getMobileList().getList();
			int s = ourMobiles.size();
			int temp = 0;
			MobilePhone a = new MobilePhone();
			for(int i = 0 ; i < sizeOfAll; i++){
				if (Integer.parseInt(inputWords[1]) == MobilePhones.getChildat(i).getNumber()) {			
					a = MobilePhones.getChildat(i);
					temp = 1;
					break;	
				}
			}
			for (int i = 0; i<s && temp > 0; i ++ ) {
				if (Integer.parseInt(inputWords[1]) == ourMobiles.getChildat(i).getNumber()) {
					System.out.println("MobilePhone already exists and is switched on.");			
					temp = 2;	
				}		
			}	

			
			// return the MobilePhone a which has the id parseInt(inputWords[1] in basestaion b
			if(temp == 1)
			{
				this.switchOn(a,b);	
			}
			else if (temp == 0){ // this is when the mobile phone doeno't exist before.
				a = new MobilePhone(Integer.parseInt(inputWords[1]));
				this.MobilePhones.Insert(a);
				this.switchOn(a,b);
			}
			answer = "";

		}

		
		else if (inputWords[0].equals("switchOffMobile")) {
			// Search mobile id parseInt(inputWords[1]) in Mobileset of root Exchange and then return (MobilePhomne a)
			LinkedList<MobilePhone> list = this.root.getMobileList().getList();
			LinkedList<MobilePhone> listOffAllMobiles = this.MobilePhones.getList();
			// now this list has nodes of which nodes.data are of type MobilePhone
			MobilePhone a = new MobilePhone();
			int size = list.size();	
			int allsize = listOffAllMobiles.size();
			int i = 0;
			int temp = 0; // for checking difference between exist and off
			for ( i = 0 ; i<allsize ; i++)
			{
				if(listOffAllMobiles.getChildat(i).number() == Integer.parseInt((inputWords[1])))
				{
					a = listOffAllMobiles.getChildat(i);
					temp = 1;
					break;
				}
			}
			for (i = 0; i < size ;i++ ) {	
				if (list.getChildat(i).number() ==	 Integer.parseInt(inputWords[1])) 
				{					
					a = list.getChildat(i);
					break;
				}
			}
			if (i==size) 
			{
				if (temp == 0) {
					System.out.println("The mobile doesnot exist");
				}
				else 
					System.out.println("The mobile phone is alreday off"); // precisely didn't find mobile number in mobilephoneset and the mobile phone exist. 
			}
			else
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

		else if(inputWords[0].equals("queryFindPhone"))
		{
			LinkedList<MobilePhone> list = this.root.getMobileList().getList();
			MobilePhone a = new MobilePhone();
			int size = list.size();
			int i;
			for (i = 0; i < size ;i++ ) {	
				if (list.getChildat(i).number() ==	 Integer.parseInt(inputWords[1])) 
				{					
					a = list.getChildat(i);
					break;
				}
			}
			if (i == size) {
				System.out.println("The phone is not registered to any BaseStation");

			}
			else
			{
				Exchange findP = findPhone(a);
				answer = inputWords[0] + ": " + findP.getid();
			}
		}

		else if(inputWords[0].equals("queryLowestRouter"))
		{
			Exchange a = this.searchExchange(Integer.parseInt(inputWords[1]));
			Exchange b = this.searchExchange(Integer.parseInt(inputWords[2]));
			Exchange ans = lowestRouter(a, b);
			answer = inputWords[0] + ": " + ans.getid();
		}
		else if(inputWords[0].equals("queryFindCallPath"))
		{
			LinkedList<MobilePhone> list = this.root.getMobileList().getList();
			MobilePhone a = new MobilePhone();
			MobilePhone b = new MobilePhone();
			int size = list.size();	
			int i = 0;
			int j = 0;
			for (i = 0; i < size ;i++ ) {	
				if (list.getChildat(i).number() ==	 Integer.parseInt(inputWords[1])) 
				{					
					a = list.getChildat(i);
					break;
				}
			}
			for (j = 0; j < size ;j++ ) {

				if (list.getChildat(j).number() ==	 Integer.parseInt(inputWords[2])) 
				{					
					b = list.getChildat(j);
					break;
				}
			}
			if (i==size || j==size) {
				System.out.println("Mobiles are not currently registered to any base station.");
				answer="";
			}
			else
			{
				ExchangeList ans = routeCall(a, b);
				answer = inputWords[0] + ans.printSet();
			}
			
		}
		else if(inputWords[0].equals("movePhone"))
		{
			// first int is mobilephone and next is exchange
			LinkedList<MobilePhone> list = this.root.getMobileList().getList();
			MobilePhone a = new MobilePhone();
			int size = list.size();
			int i = 0;
			for (i = 0; i < size ;i++ ) {	
				if (list.getChildat(i).number() ==	 Integer.parseInt(inputWords[1])) 
				{					
					a = list.getChildat(i);
					break;
				}
			}
			Exchange b = this.searchExchange(Integer.parseInt(inputWords[2]));
			movePhone(a, b);
			answer = "";
			

		}
		// String all = this.MobilePhones.printSet() ;
		// System.out.println("All phones" + all );
		return answer;
	}
}
	