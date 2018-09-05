public class Exchange{
	private Exchange parent;
	private ExchangeList listChildren = new ExchangeList();
	private int id; 
	private MobilePhoneSet mobiles = new MobilePhoneSet();

	Exchange(int number){
		// contructor to create adn exchange Unique idntifier for an exchange is an integer.
		this.id = number;
	}

	Exchange()
	{
		
	}
	
	public int getid()
	{
		// System.out.println("hi inside of getid");
		// System.out.println("the id is :" + this.id);
		return this.id;
	}
	
	public void setid(int id)
	{
		// System.out.println("hi " + id);
		this.id = id;
	}
	
	public MobilePhoneSet getMobileList()
	{
			// System.out.println("a");

		return this.mobiles;
	}
	
	public ExchangeList getListChildren()
	{
		return this.listChildren;
	}
	public Exchange parent()
	{
		return this.parent;
	}
	
	public void setparent(Exchange parent)
	{
		this.parent = parent;
	}

	public int numChildren(){
		// for number of children
		int n = this.listChildren.getList().size();
		return n;
	}

	public Exchange child(int i){
		// returns ith child
		return this.listChildren.getChild(i);
	}	

	public Boolean isRoot(){
		if (this.parent == null) {
			return true;
		}
		else 
			return false;	
	}

	public RoutingMapTree subtree(int i){
		// returns ith subtree
		
		RoutingMapTree a = new RoutingMapTree();
		a.setRoot(this.child(i));
		return a;

	}

}