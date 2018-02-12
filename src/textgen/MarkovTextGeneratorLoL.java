package textgen;

import java.util.*;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator){
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText){
		String[] lstSentences = sourceText.split("[.?!]+");
		for (int j = 0; j < lstSentences.length; j++) {
			String[] lstWords = lstSentences[j].trim().split("[^a-zA-Z0-9']+");
			for (int i = 0; i < lstWords.length; i++) {
				int index = searchListNode(lstWords[i]);
				//System.out.println(index);
				if (i + 1 == lstWords.length && index == 0) {
					wordList.add(new ListNode(lstWords[i]));
				} else {
					if (index >= 0) {
						wordList.get(index).addNextWord(lstWords[i + 1]);
					} else {
						ListNode tmpListNode = new ListNode(lstWords[i]);
						tmpListNode.addNextWord(lstWords[i + 1]);
						wordList.add(tmpListNode);
					}
				}
			}
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords){
		ListNode tmpNode = getRandomNode();
		String generatedText = tmpNode.getWord();
		for (int i = 0; i < numWords; i++){
			String tmpWord = tmpNode.getRandomNextWord(rnGenerator);
			System.out.println(tmpWord);
			if(tmpWord == null){
				tmpNode = getRandomNode();
				generatedText += generatedText + ".";
			}
			else
				tmpNode = wordList.get(searchListNode(tmpWord));
			generatedText += generatedText
					+ " " + tmpNode.getWord();
		}
		return generatedText;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString(){
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText){
		wordList.clear();
		train(sourceText);
	}
	
	private int searchListNode (String word) {
		if (wordList.isEmpty())
			return -1;
		else
			for (ListNode tmpNode : wordList)
				if (tmpNode.getWord().equals(word.trim()))
					return wordList.indexOf(tmpNode);
		return -1;
	}
	private ListNode getRandomNode (){
		System.out.println(Math.abs(rnGenerator.nextInt())
				% wordList.size());
		System.out.println(Math.abs(rnGenerator.nextInt())
				% wordList.size());
		return wordList.get(Math.abs(rnGenerator.nextInt())
				% wordList.size());
	}


	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		//System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		//dSystem.out.println(textString2);
		//gen.retrain(textString2);
		//System.out.println(gen);
		//System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word){
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator){
		return nextWords.get(generator.nextInt()%nextWords.size());
	}
	@Override
	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


