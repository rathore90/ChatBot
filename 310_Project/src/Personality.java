import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Personality {
	/*
	 * An instance of 'Personality' can be called from within the terminal class. 
	 * This will create a randomized personality object that contains arrays
	 * of likes and dislikes. As well as basic chat-bot information
	 */

	private String gender;
	private String name;
	private String zodiacSign;
	private String occupation;

	
	private ArrayList<String> likes = new ArrayList<String>();
	private ArrayList<String> dislikes = new ArrayList<String>();

	private ArrayList<String> likesSports = new ArrayList<String>();
	private ArrayList<String> likesMusic = new ArrayList<String>();
	private ArrayList<String> likesMovies = new ArrayList<String>();
	private ArrayList<String> likesAnimals = new ArrayList<String>();
	private ArrayList<String> likesCountries = new ArrayList<String>();
	private ArrayList<String> likesHobbies = new ArrayList<String>();
	private ArrayList<String> likesFoods = new ArrayList<String>();
	private ArrayList<String> dislikesSports = new ArrayList<String>();
	private ArrayList<String> dislikesMusic = new ArrayList<String>();
	private ArrayList<String> dislikesMovies = new ArrayList<String>();
	private ArrayList<String> dislikesAnimals = new ArrayList<String>();
	private ArrayList<String> dislikesCountries = new ArrayList<String>();
	private ArrayList<String> dislikesHobbies = new ArrayList<String>();
	private ArrayList<String> dislikesFoods = new ArrayList<String>();
	
	/**
	 * Personality Constructor that has male as the default gender setting
	 */
	public Personality() {
		this("man");
	}
	
	/**
	 * Personality Constructor that randomly generates all variables for a new personality instance.
	 * @param gender
	 */
	public Personality(String gender) {
		
		setGender(gender);
		setZodiacSign(setString(txtToArray("zodiac_signs.txt")));
		setOccupation(setString(txtToArray("occupations.txt")));
		setLikesSports(setArray(txtToArray("sports.txt"), random(5)+3));
		setLikesMusic(setArray(txtToArray("music.txt"), random(5)+3));
		setLikesMovies(setArray(txtToArray("movies.txt"), random(5)+3));
		setLikesAnimals(setArray(txtToArray("animals.txt"), random(5)+3));
		setLikesCountries(setArray(txtToArray("countries.txt"), random(5)+3));
		setLikesHobbies(setArray(txtToArray("hobbies.txt"), random(5)+3));
		setLikesFoods(setArray(txtToArray("food.txt"), random(5)+3));
		setDislikesFoods(setArray(txtToArray("food.txt"),this.getLikesFoods(), 3));
		setDislikesSports(setArray(txtToArray("sports.txt"),this.getLikesSports(), 3));
		setDislikesMusic(setArray(txtToArray("music.txt"),this.getLikesMusic(), 3));
		setDislikesMovies(setArray(txtToArray("movies.txt"),this.getLikesMovies(), 3));
		setDislikesAnimals(setArray(txtToArray("animals.txt"),this.getLikesAnimals(), 3));
		setDislikesCountries(setArray(txtToArray("countries.txt"),this.getLikesCountries(), 3));
		setDislikesHobbies(setArray(txtToArray("hobbies.txt"),this.getLikesHobbies(), 3));
		
		
		if(gender.equals("man"))
			setName(setString(txtToArray("names_boys.txt")));
		else
			setName(setString(txtToArray("names_girls.txt")));
		
		setLikes();
		setDislikes();
	}




	/**
	 * Method that converts the contents of a given .txt file to an ArrayList<String>
	 * @param filename
	 * @return ArrayList<String>
	 */
	public ArrayList<String> txtToArray(String filename) {
		ArrayList<String> result = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			while (br.ready()) {
				result.add(br.readLine());
			}
				
			br.close();	
			return result;
			
		} catch (IOException e) {
			System.out.println("Error File Not Found");
			result.add("Error File Not Found");
			return result;
		}
	}

	/**
	 * Method that returns a random string selected from the given arraylist<String>
	 * @param arraylist 
	 * @return String
	 */
	public static String setString(ArrayList<String> arraylist) {
		int random = random(arraylist.size()-1);
		return arraylist.get(random);
	}

	/**
	 * Method that return an array of randomized strings from the given list. Avoiding duplicates
	 * @param array to choose from
	 * @param choose number of strings in the returned arraylist
	 * @return ArrayList<String>
	 */
	private ArrayList<String> setArray(ArrayList<String> array, int choose) {

		ArrayList<String> result = new ArrayList<>();

		for (int i = 0; i < choose; i++) {
			int random = random(array.size() - 1);
			if (i > 0 && result.contains(array.get(random))) {
				System.out.print("");
			} else {
				result.add(array.get(random));
			}
		}
		return result;
	}
	
	/**
	 * Method that return an array of randomized strings from the given list, also avoids adding
	 * strings that are already in the comparatorArray. Use this method to ensure dislikes are
	 * set randomly but are not the same as likes.
	 * @param textFileArray array to choose from
	 * @param comparatorArray array to check for duplicates in
	 * @param choose number of strings in the returned arraylist
	 * @return
	 */
private ArrayList<String> setArray(ArrayList<String> textFileArray, ArrayList<String> comparatorArray, int choose) {

		
		ArrayList<String> result = new ArrayList<>();

		for (int i = 0; i < choose;) {
			int random = random(textFileArray.size() - 1);
			if (i > 0 && (result.contains(textFileArray.get(random)))) {
			System.out.print("");
			} else if (comparatorArray.contains(textFileArray.get(random))) {
				System.out.print("");
			} else {
				result.add(textFileArray.get(random));
				i++;
			}
		}
		return result;
	}

	/**
	 * Checks an array to see if it contains an item
	 * @param arr
	 * @param item
	 * @return boolean
	 */
	public static boolean contains(ArrayList<String> array, String item) {
		for (String n : array) {
			if (n.equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks two arrays to see if it contains an item
	 * @param array
	 * @param comparatorArray
	 * @param item
	 * @return
	 */
	public static boolean contains(ArrayList<String> array, ArrayList<String> comparatorArray, String item) {
		for (String n : array) {
			if (n.equals(item)) 
				return true;
			}
		for (String n : comparatorArray) {
			if (n.equals(item)) 
				return true;
			}
		return false;
	}

	/**
	 * A random number generator
	 * 
	 * @param upperBound
	 * @return A number between 0 and upperBound
	 */
	static int random(int upperBound) {
		return (int) (Math.random() * upperBound + 1);
	}
	
	/**
	 * Combines all the arrays of likes into a single array
	 */
	public void setLikes() {
		ArrayList<String> likes = new ArrayList<String>();
		likes.addAll(this.getLikesAnimals());
		likes.addAll(this.getLikesCountries());
		likes.addAll(this.getLikesFoods());
		likes.addAll(this.getLikesHobbies());
		likes.addAll(this.getLikesMovies());
		likes.addAll(this.getLikesMusic());
		likes.addAll(this.getLikesSports());
		
		this.likes = likes;
	}

	public ArrayList<String> getDislikes() {
		return dislikes;
	}

	/**
	 * Combines all the arrays of dislikes into a single array
	 */
	public void setDislikes() {
		ArrayList<String> dislikes = new ArrayList<String>();
		dislikes.addAll(this.getDislikesAnimals());
		dislikes.addAll(this.getDislikesCountries());
		dislikes.addAll(this.getDislikesFoods());
		dislikes.addAll(this.getDislikesHobbies());
		dislikes.addAll(this.getDislikesMovies());
		dislikes.addAll(this.getDislikesMusic());
		dislikes.addAll(this.getDislikesSports());
		
		this.dislikes = dislikes;
	}
	
	public ArrayList<String> getLikes() {
		return likes;
	}

	public String getGender() {
		return gender;
	}

	private void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZodiacSign() {
		return zodiacSign;
	}

	private void setZodiacSign(String zodiacSign) {
		this.zodiacSign = zodiacSign;
	}

	public String getOccupation() {
		return occupation;
	}

	private void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public ArrayList<String> getLikesSports() {
		return likesSports;
	}

	private void setLikesSports(ArrayList<String> likesSports) {
		this.likesSports = likesSports;
	}

	public ArrayList<String> getLikesMusic() {
		return likesMusic;
	}

	public void setLikesMusic(ArrayList<String> likesMusic) {
		this.likesMusic = likesMusic;
	}

	public ArrayList<String> getLikesMovies() {
		return likesMovies;
	}

	public void setLikesMovies(ArrayList<String> likesMovies) {
		this.likesMovies = likesMovies;
	}

	public ArrayList<String> getLikesAnimals() {
		return likesAnimals;
	}

	public void setLikesAnimals(ArrayList<String> likesAnimals) {
		this.likesAnimals = likesAnimals;
	}

	public ArrayList<String> getLikesCountries() {
		return likesCountries;
	}

	public void setLikesCountries(ArrayList<String> likesCountries) {
		this.likesCountries = likesCountries;
	}

	public ArrayList<String> getLikesHobbies() {
		return likesHobbies;
	}

	public void setLikesHobbies(ArrayList<String> likesHobbies) {
		this.likesHobbies = likesHobbies;
	}
	
	public ArrayList<String> getLikesFoods() {
		return likesFoods;
	}
	
	public void setLikesFoods(ArrayList<String> likesFoods) {
		this.likesFoods = likesFoods;
	}
	
	public ArrayList<String> getDislikesFoods() {
		return dislikesFoods;
	}
	
	public void setDislikesFoods(ArrayList<String> dislikesFoods) {
		this.dislikesFoods = dislikesFoods;
	}
	
	public ArrayList<String> getDislikesSports() {
		return dislikesSports;
	}

	public void setDislikesSports(ArrayList<String> dislikesSports) {
		this.dislikesSports = dislikesSports;
	}

	public ArrayList<String> getDislikesMusic() {
		return dislikesMusic;
	}

	public void setDislikesMusic(ArrayList<String> dislikesMusic) {
		this.dislikesMusic = dislikesMusic;
	}

	public ArrayList<String> getDislikesMovies() {
		return dislikesMovies;
	}

	public void setDislikesMovies(ArrayList<String> dislikesMovies) {
		this.dislikesMovies = dislikesMovies;
	}

	public ArrayList<String> getDislikesAnimals() {
		return dislikesAnimals;
	}

	public void setDislikesAnimals(ArrayList<String> dislikesAnimals) {
		this.dislikesAnimals = dislikesAnimals;
	}

	public ArrayList<String> getDislikesCountries() {
		return dislikesCountries;
	}

	public void setDislikesCountries(ArrayList<String> dislikesCountries) {
		this.dislikesCountries = dislikesCountries;
	}

	public ArrayList<String> getDislikesHobbies() {
		return dislikesHobbies;
	}

	public void setDislikesHobbies(ArrayList<String> dislikesHobbies) {
		this.dislikesHobbies = dislikesHobbies;
	}

	

}
