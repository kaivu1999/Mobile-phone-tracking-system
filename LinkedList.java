public class LinkedList<E>{
	// Here I have made a doubly linked list.
	private static class Node<E>{
		private E data;
		private Node<E> prev;
		private Node<E> next;
		public Node(E data , Node<E> prev , Node<E> next)
		{
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		public E getData()
		{
			return this.data;
		}

		public Node<E> getNext()
		{
			return this.next;
		}

		public Node<E> getPrev()
		{
			return this.prev;
		}

		public void setNext(Node<E> next)
		{
			this.next = next;
		}

		public void setPrev(Node<E> prev)
		{
			this.prev = prev;
		}
		
	}

	private int size = 0;
	private Node<E> head;
	private Node<E> tail;

	public LinkedList(){
		this.head = new Node<>(null , null , null);
		this.tail = new Node<>(null, head, null);
		this.head.setNext(this.tail);

 	}

 	public int size()
 	{
 		return this.size;
 	}

 	public boolean isEmpty()
 	{
 		if (this.size == 0) {
 			return true;
 		}
 		else
 			return false;
 	}
 	public boolean isMember(E data){
 		// System.out.println("Searching for " + data + " we have size " + size);
 		Node<E> ptr = head.getNext();
 		int s = this.size;
 		for (int i = 0;i<s ;i++ ) {
 			// System.out.println(ptr.data);
 			if(ptr.data == data)
 				return true;
 			ptr = ptr.getNext();
 		}
 		return false;
 	}

 	// ***public Node<E> getMember(E data){
 	// 	// System.out.println("Searching for " + data + " we have size " + size);
 	// 	Node<E> ptr = head.getNext();
 	// 	int s = this.size;
 	// 	for (int i = 0;i<s ;i++ ) {
 	// 		// System.out.println(ptr.data);
 	// 		if(ptr.data == data)
 	// 			return ptr;
 	// 		ptr = ptr.getNext();
 	// 	}
 	// 	return null;
 	// }

 	public E getFirst()
 	{
 		return head.getNext().data;
 	}

 	public  E getLast()
 	{
 		return tail.getPrev().data;
 	}

 	public void insertFirst(E data)
 	{
 		Node<E> noden = new Node(data, head , head.getNext());
 		noden.getNext().setPrev(noden);
 		head.setNext(noden);
 		size++;
 		// System.out.println("Inserted " + data); 
 	}

 	public void insertLast(E data)
 	{
 		Node<E> noden = new Node(data, tail.getPrev() , tail.getNext());
 		tail.setPrev(noden);
 		noden.getPrev().setNext(noden);
 		size++;
 		// System.out.println("Inserted " + data); 
 	}

 	public E removeFirst()
 	{
 		if (this.isEmpty()) {
 			return null;
 		}
 		else 
 		{
 			return removeN(head.getNext());

 		}
 	}

 	public E removeLast()
 	{
 		if (this.isEmpty()) {
 			return null;
 		}
 		else
 		{
 			return removeN(tail.getPrev());
 		}
 	}

 	public E removeN(Node<E> node)
 	{	
 		Node<E> previousnode = node.getPrev();
 		Node<E> nextnode = node.getNext();
 		previousnode.setNext(nextnode);
 		nextnode.setPrev(previousnode);
 		return node.data;

 	}

 	public E removeData(E data)
 	{	
 		try{
	 		Node<E> nodeD = getNode(data);
	 		// System.out.println(nodeD.getData() + "previous data :" + nodeD.getPrev().getData() + "Next data :" + nodeD.getNext().getData());
	 		Node<E> previousnode = nodeD.getPrev();
	 		Node<E> nextnode = nodeD.getNext();
	 		// System.out.println(previousnode.getData());
	 		// System.out.println(nodeD.getData());
	 		previousnode.setNext(nextnode);
	 		nextnode.setPrev(previousnode);
	 		size--;
	 		return data;
	 	}
	 	catch (Exception e)
	 	{
	 		System.out.println("Data not found");
	 		return null;
	 	}
 	}

 	public Node<E> getNode(E data)
 	{
 		Node<E> ptr = head.getNext();
 		int s = this.size;
 		for (int i = 0;i<s ;i++ ) {
 			// System.out.println(ptr.data);
 		// System.out.println("check if data : "+data+" = "+ptr.data);
 			if (ptr.data == data)
 				return ptr;
 			ptr = ptr.getNext();
 		}
 		// System.out.println("the node with the asked data" + data + " doesn't exist.");
 		return null;
 	}

 	public String printL(){
 		String ans = "";
 		// System.out.println("Size of the list is "+ size);
 		Node<E> ptr = tail.getPrev();
 		for (int i = 0;i< size ; i++ ) {
 			ans +=ptr.data;
 			ptr = ptr.getPrev();
 		}
 		return ans;
 		
 	}
 	
 	public LinkedList<E> unionof2l(LinkedList<E> b)
 	{
 		LinkedList<E> c = new LinkedList<E>() ;
 		int s1 = size;
 		int s2 = b.size;
 		Node<E> ptr1 = head.getNext();
 		Node<E> ptr2 = b.head.getNext();

 		for (int i = 0; i < s1 ;i++ ) {
 			c.insertFirst(ptr1.getData());
 			ptr1 = ptr1.getNext();

 		}

 		for ( int i = 0 ; i<s2 ; i++ ) {
 			if (!c.isMember(ptr2.getData())) {
 				c.insertFirst(ptr2.getData());
 			}
 			ptr2 = ptr2.getNext();
 		}


 		return c;

 	}

 	public LinkedList<E> intersectionof2l(LinkedList<E> a )
 	{
 		LinkedList<E> c = new LinkedList<E>() ;
 		int s1 = size;
 		int s2 = a.size;
 		Node<E> ptr1 = head.getNext();
 		Node<E> ptr2 = a.head.getNext();

 		for (int i = 0; i < s1 ;i++ ) {
 			if (a.isMember(ptr1.getData())) {
 			c.insertFirst(ptr1.getData());
  			}
 			ptr1 = ptr1.getNext();

 		}

 		return c;

 	}

 	public E getChildat(int i)
 	{
 		Node<E> ptr  = head;
 		for (int j =0 ; j <= i ; j++ ) {
 			ptr = ptr.next;
 		}
 		return ptr.data;
 	}
 	
 	


}	
	