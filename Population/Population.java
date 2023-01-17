import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *	Population - <description goes here>
 *	NOTES: 
 *	1: selection and insertion sorts are already sorted so the values printed come at the first 50
 *	2: make sure merge sort sorts so the printed values are at the front(index 0) of cities
 *	
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Justin Chen
 *	@since	1/9/2023
 */
public class Population {
	
	// List of cities
	private List<City> cities = new ArrayList<City>();

	private List<City> sortedCities = new ArrayList<City>();
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	private SortMethods sorts = new SortMethods();

	public Population(){

	}
	
	/**
	 * cities is sorted in order from the smallest population 
	 * to the greatest population. 
	 */
	private void SelectionSortAscending(){
		sorts.selectionSort(cities);
	}

	/**
	 * cities are sorted from most populous to least populous
	 */
	private void MergeSortDescending(List<City> city){
		sorts.mergeSortDescending(city, 0, city.size() - 1);
	}

	/**
	 * cities are sorted by name, from largest first char(z)
	 * to smallest first char(a)
	 */
	private void MergeSortAscending(List<City> city){
		sorts.mergeSortAscending(city, 0, city.size() - 1);
	}

	/**
	 * cities are sorted by name, from smallest first char(a)
	 * to largest first char(z)
	 */
	private void InsertionSortAscending(){
		sorts.insertionSort(cities);
	}

	/**
	 * cities are not sorted but held in sortedCities
	 */
	private void PopulationInState(){
		sortedCities.clear();
		boolean stateMatches = false;
		String stateName = "";
		System.out.println();
		while(!stateMatches){
			int i = 0;
			stateName = Prompt.getString("Enter state name (ie. Alabama)");
			while(i < cities.size()){					
				if(stateName.equals(cities.get(i).getStateName())){
					stateMatches = true;
					sortedCities.add(cities.get(i));
				}
				i++;
			}
			if(!stateMatches){
				System.out.println("ERROR: " + stateName + " is not valid");
			}
		}
		//call method which sorts the cities by population descending(mergesort)
		MergeSortDescending(sortedCities);
		System.out.println("\nFifty most populous cities in " + stateName);
		//last line of code^
	}

	/**
	 * cities are not sorted but held in sortedCities
	 */
	private void PopulationOfCityName(){
		sortedCities.clear();
		boolean cityMatches = false;
		String cityName = "";
		System.out.println();
		while(!cityMatches){
			int i = 0;
			cityName = Prompt.getString("Enter city name");
			while(i < cities.size()){					
				if(cityName.equals(cities.get(i).getCityName())){
					cityMatches = true;
					sortedCities.add(cities.get(i));
				}
				i++;
			}
			if(!cityMatches){
				System.out.println("ERROR: " + cityName + " is not valid");
			}
		}
		//call method which sorts the cities by population descending(mergesort)
		MergeSortAscending(sortedCities);
		MergeSortDescending(sortedCities);
		System.out.println("\nCity " + cityName + " by population");
	}

	//used to read all the cities and info, as well as load it
	//into separate city objects in the cities List
	public void loadCities(){
		Scanner sc = FileUtils.openToRead(DATA_FILE).useDelimiter("[\t\n]");
		String state, city, type;
		int pop;
		while(sc.hasNext()){	
			state = sc.next();
			city = sc.next();
			type = sc.next();
			pop = sc.nextInt();
			cities.add(new City(state, city, type, pop));
		}
	}
	public void run(){
		printIntroduction();
		loadCities();
		System.out.println(cities.size() + " cities in database\n");
		while(true){
			System.out.println();
			printMenu();
			int choiceNum  = 0;
			while((choiceNum > 6 && choiceNum != 9) || choiceNum == 0){
				choiceNum = Prompt.getInt("Enter Selection");
			}
			long startMillisec = System.currentTimeMillis();
			if(choiceNum == 1){
				System.out.println("\nFifty least populous cities");
				SelectionSortAscending();
			}else if(choiceNum == 2){
				System.out.println("\nFifty most populous cities");
				MergeSortDescending(cities);
			}else if(choiceNum == 3){
				System.out.println("\nFifty cities sorted by name");
				InsertionSortAscending();
			}else if(choiceNum == 4){
				System.out.println("\nFifty cities sorted by name descending");
				MergeSortAscending(cities);
			}else if(choiceNum == 5){
				PopulationInState();
			}else if(choiceNum == 6){
				PopulationOfCityName();
			}else if(choiceNum == 9){
				System.out.println("\nThanks for using Population!");
				return;
			}
			long endMillisec = System.currentTimeMillis();
			//distance between end of one column and start of next: 
			//each one allotted 20 slots
			System.out.printf("%10s%24s%25s%26s\n", "State", "City", "Type", "Population");
			if(choiceNum < 5 && choiceNum > 0){
				for(int i = 0; i < 50; i++){
					String cityName = cities.get(i).getCityName();
					String stateName = cities.get(i).getStateName();
					int pop = cities.get(i).getPopulation();
					String type = cities.get(i).getCityType();
					System.out.printf("%3d: %-25s%-25s%-15s%,15d\n", i + 1, stateName, cityName, type, pop);
				}
			}else if(choiceNum == 5 || choiceNum == 6){
				for(int i = 0; i < 50 && i < sortedCities.size(); i++){
					String cityName = sortedCities.get(i).getCityName();
					String stateName = sortedCities.get(i).getStateName();
					int pop = sortedCities.get(i).getPopulation();
					String type = sortedCities.get(i).getCityType();
					System.out.printf("%3d: %-25s%-25s%-15s%,15d\n", i + 1, stateName, cityName, type, pop);
				}
			}
			System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds");
			//everything formatted to left except first line and pop
			//always takes the first 50 at the beginning of the List for easy access
		}	
		
	}
			
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	public static void main(String[] argv){
		Population c = new Population();
		c.run();
	}
}