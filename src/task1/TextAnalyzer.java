package task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TextAnalyzer {
	// <word, its positions>
	private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

	// load words in the text file given by fileName and store into map by using add
	// method in Task 2.1.
	// Using BufferedReader reffered in file TextFileUtils.java
	public void load(String fileName) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line = null;
			int index = 1;

			while ((line = reader.readLine()) != null) {
				StringTokenizer tokens = new StringTokenizer(line, " ");
				while (tokens.hasMoreTokens()) {
					String word = tokens.nextToken();
					int position = tokens.hasMoreTokens() ? index : -index;
					add(word, position);
					index++;
					
				}
				System.out.println("newMap: " + map);
			}
		}
	}

	// In the following method, if the word is not in the map, then adding that word
	// to the map containing the position of the word in the file. If the word is
	// already in the map, then its word position is added to the list of word
	// positions for this word.
	// Remember to negate the word position if the word is at the end of a line in
	// the text file

	public void add(String word, int position) {
		// Check if the word is already in the map
		if (map.containsKey(word)) {
			// Word is already in the map, so retrieve the list of positions
			ArrayList<Integer> positions = map.get(word);
			// Add the word position to the list
			positions.add(position);
		} else {
			// Word is not in the map, so create a new list with the word position
			ArrayList<Integer> positions = new ArrayList<>();
			positions.add(position);
			// Add the word and its position to the map
			map.put(word, positions);
		}

		// Check if the word is at the end of a line
		// If yes, negate the word position
		if (word.endsWith("\n")) {
			ArrayList<Integer> positions = map.get(word);
			int lastIndex = positions.size() - 1;
			positions.set(lastIndex, -positions.get(lastIndex));
			System.out.println("add: " + map);

		}

		
	}

	// Other methods for text analysis can be added here

	// This method should display the words of the text file along with the
	// positions of each word, one word per line, in alphabetical order
	public void displayWords() {

		try {
			Files.lines(Paths.get("Data/short")).forEach(line -> {
				String[] words = line.split("\\s+");
				for (int lineNumber = 0; lineNumber < words.length; lineNumber++) {
					map.computeIfAbsent(words[lineNumber], k -> new ArrayList<>()).add(lineNumber);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Display words in alphabetical order
		map.forEach((word, positions) -> System.out.println(word + ": " + positions));
		System.out.println("displayWords: " + map);
	}

	// This method will display the content of the text file stored in the map
	public void displayText() {
		System.out.println("displayText: ");
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {

			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	// This method will return the word that occurs most frequently in the text file
	public String mostFrequentWord() {
		if (map.isEmpty()) {
			return null;
		}
		String mostFrequentWord = null;
		int maxFrequency = 0;

		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			int key = entry.getValue().size();
			if (key > maxFrequency) {
				maxFrequency = key;
				mostFrequentWord = entry.getKey();
			}
		}
		return mostFrequentWord;
	}

}
