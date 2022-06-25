import java.util.ArrayList;


public class Heap<T extends Comparable<T>>{

	private final int numberOfChildren;

	private final ArrayList<T> heapArray;




	public Heap(int numberOfChildren){

		this.numberOfChildren = numberOfChildren;

		this.isValidPowerOfTwo(numberOfChildren);

		//This is the general way of the d-ary heap data structure where a parent node can have at least 2 child nodes i.e binary heap.

		/*if (numberOfChildren < 2){

			throw new IllegalArgumentException("Number of chalid node must be 2++");
		}*/

		this.heapArray = new ArrayList<T>();

	}


	// To make a different/particular d-ary heap(the 2^n d-ary heap), you can uncomment the following helper function wich checks if the number
	// of children is a power of 2
	

	private void isValidPowerOfTwo(int numberOfChildren){

		if (numberOfChildren <=0){

			throw new IllegalArgumentException("childCount must be greater than zero");

		}

		double logChildCount = Math.log(numberOfChildren)/Math.log(2);

		if (Math.ceil(logChildCount) != Math.floor(logChildCount)){
			
			throw new IllegalArgumentException("childPower must be a power of two");

		}

	}

	/*
	public void heapifyMax(ArrayList<Integer> sampleArray){

		int arraySize = sampleArray.size();

		for (int i = ((arraySize - 1)/numberOfChildren); i >= 0; i--){

			this.restoreDown(i);

		}


	}*/

	private int restoreUp(int lastItemIndex){

		int parentIndex = (int) Math.floor((float)(lastItemIndex - 1)/numberOfChildren);

		T childValue = heapArray.get(lastItemIndex);


		if(parentIndex >= 0){

			T parentValue = heapArray.get(parentIndex);


			if(childValue.compareTo(parentValue) > 0){

				heapArray.set(lastItemIndex, parentValue);

				heapArray.set(parentIndex, childValue);

				return parentIndex;

			}

		}

		return -1;

	}


	private int restoreDown(int itemIndex){

		T parentValue = heapArray.get(itemIndex);

		T biggerChildValue = null;

		int biggerChildIndex = 0;

		for(int i = 0; i < numberOfChildren; i++){

			int childIndex = numberOfChildren * itemIndex + i + 1;

			if(childIndex < heapArray.size() - 1){

				T childValue = heapArray.get(childIndex);

				if (biggerChildValue == null || childValue.compareTo(biggerChildValue) > 0 ){

					biggerChildIndex = childIndex;

					biggerChildValue = childValue;

				}
			}

		}

		if (biggerChildValue != null && parentValue.compareTo(biggerChildValue) < 0){

			heapArray.set(itemIndex, biggerChildValue);

			heapArray.set(biggerChildIndex, parentValue);

		}

		return -1;

	}

	public void push(T itemToPush){

		heapArray.add(itemToPush);

		int arraySize = heapArray.size();

		int itemIndex = arraySize-1;

		while (itemIndex > 0){

			itemIndex = this.restoreUp(itemIndex);

		}


	}


	public T popMax(){

		if(heapArray.size() == 0){

			return null;

		}else if (heapArray.size() < 2){

			T maxValue = heapArray.get(0);

			heapArray.remove(0);

			return maxValue;

		}else{


			T maxValue = heapArray.get(0);



			T toReplaceMax = heapArray.get(heapArray.size() - 1);

			heapArray.remove(heapArray.size() - 1);

			heapArray.set(0, toReplaceMax);

			int itemIndex = 0;

			while(itemIndex >= 0 ){

				itemIndex = this.restoreDown(itemIndex);

			}


			return maxValue;
		}

	}

    
    
    public static void main(String[] args){

        System.out.println("Testing d-ary heap dataStructure");

	int[] initialArray = {1,2,3,4,5,6,7,8,9};

	Heap heap = new Heap(4);

	for(int i = 0; i < initialArray.length; i++){

		heap.push(initialArray[i]);

	}

	System.out.println(heap.heapArray);

	heap.popMax();

	System.out.println("");

	System.out.println(heap.heapArray);
		



		

	}
    
}
