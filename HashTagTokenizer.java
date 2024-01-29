

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		in.readString();
		for(int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}
		in.close();
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		// Iterate through the dictionary array
		for (String dictWord : dictionary) {
			// Check if the current word in the array is equal to the given word
			if (dictWord.equals(word)) {
				return true; // Word found in the dictionary
			}
		}
		// If the loop completes without finding the word, return false
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Convert the hashtag to lowercase for consistent comparison
		hashtag = hashtag.toLowerCase();

		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}
		int N = hashtag.length();
		for (int i = 1; i <= N; i++) {
			// Extract the prefix of length i from the hashtag
			String prefix = hashtag.substring(0, i);

			// Check if the prefix is a valid word in the dictionary
			if (existInDictionary(prefix, dictionary)) {
				// If it is, print the word and recursively call with the remaining part of the hashtag
				System.out.println(prefix);
				breakHashTag(hashtag.substring(i), dictionary);
			}
		
        }
    }
}
