import java.lang.Comparable;
/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Justin Chen	
 *	@since	1/9/2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String stateName, cityName, cityType;
	private int population;
	// constructor
	public City(String state, String city, String type, int pop){
		stateName = state;
		cityName = city;
		cityType = type;
		population = pop;
	}


	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other){
		if(this.getPopulation() != other.getPopulation()){
			return this.getPopulation() - other.getPopulation();
		}else if(this.getStateName().compareTo(other.getStateName()) != 0){
			return this.getStateName().compareTo(other.getStateName());
		}else{
			return this.getCityName().compareTo(other.getCityName());
		}
	}

	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(Object other){
		if(this.cityName.equals(((City)other).getCityName()) && this.stateName.equals(((City)other).getStateName())){
			return true;
		}
		return false;
	}


	/**	Accessor methods */
	public String getStateName(){
		return stateName;
	}
	public String getCityName(){
		return cityName;
	}
	public String getCityType(){
		return cityType;
	}
	public int getPopulation(){
		return population;
	}

	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, cityType,
						population);
	}
}