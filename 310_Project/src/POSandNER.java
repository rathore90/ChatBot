import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import java.util.Properties;

public class POSandNER {

	public void corNLP(){}
	
	/**
	 * The following method implement the POS nlp and return noun, adjective or pronoun as per the given string.
	 * @param s
	 * @return noun, pronoun or adjective.
	 */
	public String returnPOS(String s){
	        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
	        Properties props = new Properties();
	        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
	        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

	        // create an empty Annotation just with the given text
	        Annotation document = new Annotation(s);

	        // run all Annotators on this text
	        pipeline.annotate(document);

	        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
	        String pos = null;
	        for (CoreMap sentence : sentences) {
	            // traversing the words in the current sentence
	            // a CoreLabel is a CoreMap with additional token-specific methods
	            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
	                // this is the text of the token
	                String word = token.get(CoreAnnotations.TextAnnotation.class);
	                // this is the POS tag of the token
	                pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
	                // this is the NER label of the token
	                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

	              // System.out.println(String.format("Print: word: [%s] pos: [%s] ne: [%s]", word, pos, ne));
	            }
	        }
			return pos;
		}
	/**
	 * This method implement the POS and Name Entity Recognition Nlps and return the word with respect to 
	 * adjective, noun, or location. 
	 * @param s
	 * @return word
	 */
	
	public String returnWord(String s){
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(s);

        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        String word = null;
        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                 word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

              // System.out.println(String.format("Print: word: [%s] pos: [%s] ne: [%s]", word, pos, ne));
            }
        }
		return word;
	}
	/**
	 * This method Implement the Name Entity Recognition. It takes the input as a string 
	 * and return the name entity if there is any name entity.
	 * @param s
	 * @return Name entity
	 */
	
	public String returnNER(String s){
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(s);

        // run all Annotators on this text
        //pipeline.annotate(document);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        String ne = null;
        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                 String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                 ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

               //System.out.println(String.format("Print: word: [%s] pos: [%s] ne: [%s]", word, pos, ne));
            }
        }
		return ne;
	}
	
/**
 * I am able to run POS and NER(Name entity recognition) nlps. I have tested them in a main class. I just 
 * need to sync it to my chatbot. 
 * 
 * Please run the main class
 */
    public static void main(String[] args) {
	
	
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
       // String text = "What is the Weather in Kelowna right now?";
        //String text = "I am a computer science student at UBC Okanagan";
        String text = "I do not like Donald Trumph";
        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);
        //Annotation document = new Annotation(s);

        // run all Annotators on this text
        //pipeline.annotate(document);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        String pos = null;
        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

               System.out.println(String.format("Print: word: [%s] pos: [%s] ne: [%s]", word, pos, ne));
            }
        }
		//return pos;
	}
   // main braket 
}