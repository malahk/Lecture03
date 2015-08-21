package ua.org.oracle.academy.malahk;

import java.security.InvalidParameterException;
import java.util.Comparator;

// @TODO проверить/переделать prelast method на коректную работу в случае вхождения fe (1, 2, 3, 4, 4);
// переделал

public class GenericStorage<Gen>
{
	private Gen[] myData;
	int size = 0;

	public GenericStorage() {
		this(10);
	}

	@SuppressWarnings("unchecked")
	public GenericStorage(int size) {
		this.myData = (Gen[]) new Object[size];
		this.size = 0;
	}

	public void add(Gen obj) throws NullPointerException
	{
		if (size >= this.myData.length) {
			throw new NullPointerException("Array is full");
		}

		this.myData[size++] = obj;
	}

	public Gen get(int index) throws InvalidParameterException
	{
		if (!this.indexCheck(index)) {
			throw new InvalidParameterException("Index is incorrect");
		}

		return this.myData[index];
	}

	public Gen[] getAll() {
		return this.myData;
	}

	public Gen update(int index, Gen obj) throws NullPointerException, InvalidParameterException
	{
		if (!this.indexCheck(index)) {
			throw new InvalidParameterException("Index is incorrect");
		}

		while (index < this.myData.length) {
			if (!(myData[index] == null)) {
				myData[index] = obj;
			} else {
				throw new NullPointerException("The cluster is empty");
			}
		}

		return obj;

	}

	@SuppressWarnings("unused")
	private Gen delete(Gen obj) throws InvalidParameterException
	{
		int index = this.findByElement(obj);

		if (-1 == index) {
			throw new InvalidParameterException("No such element");
		}

		return this.delete(index);
	}

	public Gen delete(int index) throws InvalidParameterException
	{
		if (!this.indexCheck(index)) {
			throw new InvalidParameterException("Index is incorrect");
		}

		Gen deletedObject = this.myData[index];

		while (index < size) {
			this.myData[index] = this.myData[++index];
		}
		this.myData[size--] = null;

		return deletedObject;
	}

	public int getFilledLength()
	{
		return this.size;
	}

	// круто
	// @TODO проверить/переделать prelast method на корректную работу в случае вхождения (1, 2, 3, 4, 4);
	// переделал

	public Gen getPreLast(Comparator<Gen> comparator)
	{
		SameIndexSet last = new SameIndexSet();
		SameIndexSet preLast = new SameIndexSet();
		SameIndexSet temp = new SameIndexSet();

		for (int index = 0; index < this.size; ++index) {
			if (last.isEmpty()) {
				last.add(index);
				continue;
			}

			if (0 == comparator.compare(this.myData[index], this.myData[last.get()])) {
				last.add(index);
				continue;
			}

			if (1 == comparator.compare(this.myData[index], this.myData[last.get()])) {
				temp = last;
				last = new SameIndexSet(index);
				preLast = temp;
			}

			if (preLast.isEmpty()) {
				preLast.add(index);
				continue;
			}

			if (0 == comparator.compare(this.myData[index], this.myData[preLast.get()])) {
				preLast.add(index);
				continue;
			}

			if (1 == comparator.compare(this.myData[index], this.myData[last.get()])) {
				preLast = new SameIndexSet(index);
			}
		}

		return this.myData[preLast.get()];
	}


	private boolean indexCheck (int index) {
		return index >= 0 && index < this.size;
	}

	/**
	 *
	 * @param  Gen element	element to find
	 * @return int			index of element or -1 of didn't found
	 */
	private int findByElement(Gen element)
	{
		int result = -1;
		for (int index = 0; index < this.size; ++index) {
			if (this.myData[index].equals(element)) {
				result = index;
			}
		}

		return result;
	}

// saved this "raw" method for future

//	public Gen last(Comparator<Gen> comparator)
//	{
//		SameIndexSet set = new SameIndexSet();
//
//		for (int index = 0; index < this.size; ++index) {
//			if (set.isEmpty()) {
//				set.add(index);
//				continue;
//			}
//
//			if (0 == comparator.compare(this.myData[index], this.myData[set.get()])) {
//				set.add(index);
//			} else if (1 == comparator.compare(this.myData[index], this.myData[set.get()])) {
//				set = new SameIndexSet(index);
//			}
//		}
//
//		return this.myData[set.get()];
//	}
}
