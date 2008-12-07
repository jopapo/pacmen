package utl;

import java.util.AbstractList;
import java.util.Iterator;

public class ClassIterator implements Iterator {
	
	private Class c;
	private AbstractList al;
	private int actual, next;

	public ClassIterator(AbstractList al, Class c) {
		this.al = al;
		this.c = c;
		this.next = findNext(0);
	}

	private int findNext(int from) {
		for (int i = from; i < al.size(); i++) {
			Object o = al.get(i);
			if (c.isInstance(o))
				return i;
		}
		return -1;
	}

	@Override
	public boolean hasNext() {
		return next >= 0;
	}

	@Override
	public Object next() {
		if (next < 0)
			return null;
		actual = next;
		next = findNext(actual + 1);
		return al.get(actual);
	}

	@Override
	public void remove() {}

}
