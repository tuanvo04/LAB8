package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

import org.w3c.dom.Text;

public class TestReadFile {
	public static void main(String[] args) throws IOException {
		MyWordCountApp wordCountApp = new MyWordCountApp();
		wordCountApp.loadData();
		System.out.println("countUnique: "+wordCountApp.countUnique());
		wordCountApp.printWordCounts();
		wordCountApp.printWordCountsAlphabet();
		System.out.println();

		TextAnalyzer text = new TextAnalyzer();
		try {
			text.load("Data/short");
		} catch (IOException e) {
			e.printStackTrace();
		}

		text.add("IT", 2);
		text.displayWords();
		text.displayText();
		System.out.println(text.mostFrequentWord());
	}
}
