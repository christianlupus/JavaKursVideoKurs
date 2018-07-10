package klassen;

import java.util.Arrays;
import java.util.stream.Stream;

public class DynGenArray<T> {

	private int nextFreeSlot = 0;
	private T[] data;

	public T get(int position) {
		if (position >= data.length)
			return null;
		else
			return data[position];
	}

	public void put(int position, T value) {
		if (position >= data.length) {
			int newSize = 2 * data.length;
			if (position >= newSize)
				newSize = 2 * position;
			T[] newData = (T[]) new Object[newSize];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData;
		}
		data[position] = value;
		nextFreeSlot++;
	}

	public void edit(int position, T value) {
		data[position] = value;
	}

	public DynGenArray(int capacity) {
		data = (T[]) new Object[capacity];
	}

	public int getElementCount() {
		return nextFreeSlot;
	}

}