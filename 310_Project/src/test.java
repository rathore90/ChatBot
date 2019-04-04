
public class test {

	public static void main(String[] args) {
		POSandNER nlp = new POSandNER();
		
		//String s = "I do not like Donald Trump";
		//String ret = nlp.returnPOS(s);
		//String name = nlp.returnNER(s);
		//System.out.println(ret);
		String word = null;
		//if(ret.equals("JJ")) {
			//word = nlp.returnWord(s);
			System.out.println("you are " + word);
		//}else if(name.equals("PERSON")) {
			//word = nlp.returnWord(s);
			//System.out.println("I love this place " + word  + " .");
	//	}
	
			String before = "     .,,,;'/???  Your              string with     spaces.?....!!!!            ";
			String after = before.trim().replaceAll(" +", " ");
			String finalString =  after.replaceAll("[\\p{Punct}&&[^?]]+", "");
			//str = str.replace(" " , "");
			System.out.println(finalString);
			
		String name = ":;                            "
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ " _-               John                     Smith          ?.....,.";
		name = name.replaceAll("[\\p{Punct}&&[^?]]+", "");
		System.out.println(name.trim());
	}

}
