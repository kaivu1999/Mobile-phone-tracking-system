public class check{
	public static void main(String[] args) {
		Myset<Integer> a = new Myset<>();
		a.Insert(1);
		a.Insert(2);
		a.Insert(3);
		a.Insert(4);
		a.Insert(5);
		a.Insert(5);
		a.Insert(100);
		a.Insert(6);
		a.Insert(6);
		a.Insert(6);
		a.Insert(6);
		a.Insert(6);
		Myset<Integer> b = new Myset<>();
		Myset<Integer> d;
		b.Insert(6);
		b.Insert(10);
		b.Insert(3);
		b.Insert(12);
		b.Insert(13);
		Myset<Integer> c;
		c = a.Union(b);
		d = a.Intersection(b);

		a.printSet();
		b.printSet();
		c.printSet();
		d.printSet();
		System.out.println(a.IsMember(7));
		System.out.println(a.IsMember(4));
		a.Delete(3);
		a.printSet();
		System.out.println(a.IsMember(1));
		System.out.println(a.IsMember(2));
		System.out.println(a.IsMember(5));
	}
}