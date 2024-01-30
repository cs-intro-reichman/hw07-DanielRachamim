
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// If the length of the input string is 1, return an empty string
		if (str.length() == 1) {
			return "";
		}

		// Otherwise, return the substring starting from the second character to the end
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// If either of the words is empty, return the length of the other word
		if (word1.isEmpty()) {
			return word2.length();
		}
		if (word2.isEmpty()) {
			return word1.length();
		}

		// Convert the first characters to lowercase for case-insensitive comparison
		char char1 = Character.toLowerCase(word1.charAt(0));
		char char2 = Character.toLowerCase(word2.charAt(0));

		// If the first characters are the same, no operation is needed, continue with the rest of the strings
		if (char1 == char2) {
			return levenshtein(word1.substring(1), word2.substring(1));
		}

		// If the first characters are different, consider three possible operations: insert, delete, replace
		int insert = levenshtein(word1, word2.substring(1)) + 1;
		int delete = levenshtein(word1.substring(1), word2) + 1;
		int replace = levenshtein(word1.substring(1), word2.substring(1)) + 1;

		// Return the minimum of the three operations
		return Math.min(Math.min(insert, delete), replace);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for(int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}
		in.close();
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minDistance = Integer.MAX_VALUE;
		String closestMatch = word.toLowerCase(); // Convert to lowercase for case-insensitive comparison

		for (String dictWord : dictionary) {
			int distance = levenshtein(word.toLowerCase(), dictWord.toLowerCase());

			if (distance < minDistance) {
				minDistance = distance;
				closestMatch = dictWord;
			}
		}

		if (minDistance > threshold) {
			return word; // No sufficiently similar word found, return the original word
		} else {
			return closestMatch;
		}
	}

}
