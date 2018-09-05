// Create a class MobilePhoneSet which stores MobilePhone objects in a Myset .
public class MobilePhoneSet extends Myset<MobilePhone>{
	// public void printL(){
 		// System.out.println("Size of the list is "+ this.size);
 		// Node<MobilePhone> ptr = tail.getPrev();
 		// for (int i = 0;i< this.size() ; i++ ) {
 		// System.out.println(ptr.data.getNumber());
 		// ptr = ptr.getPrev();
 		// }
 		
 	// }

 	public void printSet()
	{
			System.out.println("number of mobiles is :" + this.size());

		for(int i=0;i<this.size() ;i++){
			int p  = this.getList().getChildat(i).number();
			System.out.println(p);
		}
	}
}