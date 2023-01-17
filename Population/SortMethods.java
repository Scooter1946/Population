import java.util.List;

import javax.swing.Icon;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Justin Chen
 *	@since	1/9/23
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<City> arr) {
        for(int i = arr.size() - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr.get(j).getPopulation() > arr.get(j + 1).getPopulation()){
                    swap(arr, j, j + 1);
                }
            }
        }
    }
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
        City temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);
    }
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City> arr) {
        for(int i = arr.size() - 1; i > 0; i--){
            int greatestIndex = 0;
            for(int j = 0; j <= i - 1; j++){
                if(arr.get(j).compareTo(arr.get(greatestIndex)) > 0){
                    greatestIndex = j;
                }
            }
            swap(arr, greatestIndex, i);
        }
    }
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<City> arr) {
        for(int i = 1; i < arr.size(); i++){
            for(int j = 0; j < i; j++){
                if(arr.get(i).getCityName().compareTo(arr.get(j).getCityName()) < 0){
                    City temp = arr.get(i);
                    arr.remove(i);
                    arr.add(j, temp);
                    break;
                }else if(arr.get(i).getCityName().compareTo(arr.get(j).getCityName()) == 0){
                    if(arr.get(i).getPopulation() > arr.get(j).getPopulation()){
                        City temp = arr.get(i);
                        arr.remove(i);
                        arr.add(j, temp);
                        break;
                    }
                }
            }
        }
    }
	
    /**
	 *	Merge Sort algorithm - in ascending order (you implement)
     *  population, largest at front to smallest at back
     * @param arr       array to sort
     * @param start     starting index of each sort
     * @param end       ending index of each sort
     */
	public void mergeSortDescending(List<City> arr, int start, int end) {
        if(end - start < 2){
            if(end > start && arr.get(end).compareTo(arr.get(start)) > 0){
                swap(arr, start, end);
            }
        }else{
            int middle = (start + end) / 2;
            mergeSortDescending(arr, start, middle);
            mergeSortDescending(arr, middle + 1, end);
            descendingMerge(arr, start, middle, end);
        }
    }
    /**
     * Complementary class to the mergeSortAscending method, merges the already
     * sorted halves of the List.
     * @param arr       List of cities to merge
     * @param start     Starting index in cities
     * @param middle    Midpoint between the two sorted groups
     * @param end       Where to sort up to
     */
    private void descendingMerge(List<City> arr, int start, int middle, int end){
        int i = start, j = middle + 1;
        int iOverCount = 0;
        while(i <= middle && j <= end){
            if(arr.get(i + iOverCount).compareTo(arr.get(j)) < 0){
                City temp = arr.get(j);
                arr.remove(j);
                arr.add(i + iOverCount, temp);
                iOverCount++;
                j++;
            }else{
                i++;
            }
        }
    }

    /**
	 *	Merge Sort algorithm - in ascending order (you implement)
     *  smallest word at top, looks for 50 smallest words
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSortAscending(List<City> arr, int start, int end) {
        if(end - start < 2){
            if(end > start && arr.get(end).getCityName().compareTo(arr.get(start).getCityName()) > 0){
                swap(arr, start, end);
            }
        }else{
            int middle = (start + end) / 2;
            mergeSortAscending(arr, start, middle);
            mergeSortAscending(arr, middle + 1, end);
            ascendingMerge(arr, start, middle, end);
        }
    }

    /**
     * complementary method
     * @param arr       array to work with
     * @param start     start index
     * @param middle    halfway point
     * @param end       end index
     */
    private void ascendingMerge(List<City> arr, int start, int middle, int end){
        int i = start, j = middle + 1;
        int iOverCount = 0;
        while(i <= middle && j <= end){
            if(arr.get(i + iOverCount).getCityName().compareTo(arr.get(j).getCityName()) < 0){
                City temp = arr.get(j);
                arr.remove(j);
                arr.add(i + iOverCount, temp);
                iOverCount++;
                j++;
            }else if(arr.get(i + iOverCount).getCityName().equals(arr.get(j).getCityName())){
                if(arr.get(i + iOverCount).compareTo(arr.get(j)) > 0){
                    City temp = arr.get(j);
                    arr.remove(j);
                    arr.add(i + iOverCount, temp);
                    iOverCount++;
                    j++;
                }else{
                   i++; 
                }
            }else{
                i++;
            }
        }
    }
	
    public void reverseOrder(List<City> arr){
        for(int i = 0; i < arr.size()/2; i++){
            swap(arr, i, arr.size() - i);
        }
    }
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		//bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
/*		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
*/
/*		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
*/
/*		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
*/
	}
}