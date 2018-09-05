public class Myset<E> {
	private LinkedList<E> l = new LinkedList(); 
	public Boolean IsEmpty(){
		return l.isEmpty();
	}

	public Boolean IsMember(Object o){
		return l.isMember((E) o);
	}

	public void Insert(Object o){
		if (!this.IsMember((E) o)) 
		{
			this.l.insertLast((E) o);
		}
	}

	public int size()
	{
		return this.l.size();
	}

	public E Delete(Object o){
		return (this.l.removeData((E)o));
	}

	public void printSet()
	{
		this.l.printL();
	}


	public Myset<E> Union(Myset<E> a){
		Myset<E> c = new Myset<E>();
		c.l = this.l.unionof2l(a.getList());
		return c;
	}

	public Myset<E> Intersection(Myset<E> a)
	{
		Myset<E> c = new Myset<E>();
		c.l = this.l.intersectionof2l(a.getList());
		return c;		
	}

	public LinkedList<E> getList()
	{
		return this.l;
	}

}