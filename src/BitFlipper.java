import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import argparser.ArgParser;
import argparser.IntHolder;
import argparser.StringHolder;

public class BitFlipper {

	public static void main(String[] args) {

		// create holder objects for storing results ...
		StringHolder fileName = new StringHolder();

		// Borders of the flipping area
		IntHolder x1 = new IntHolder();
		IntHolder x2 = new IntHolder();
		IntHolder y1 = new IntHolder();
		IntHolder y2 = new IntHolder();

		// create the parser and specify the allowed options ...
		ArgParser parser = new ArgParser(null);
		parser.addOption("-x1 %d #x1", x1);
		parser.addOption("-x2 %d #x2", x2);
		parser.addOption("-y1 %d #y1", y1);
		parser.addOption("-y2 %d #y2", y2);
		parser.addOption("-i %s #name of the input file", fileName);

		// match the arguments ...
		parser.matchAllArgs(args);

		System.out.println("borders x: (" + x1.value + ", " + x2.value + ")");
		System.out.println("borders y: (" + y1.value + ", " + y2.value + ")");
		System.out.println("intput filename: " + fileName.value);

		List<String> rows = getFileContent(fileName.value);
		System.out.println("input:");
		printRows(rows);

		if (x2.value - x1.value < rows.size() || y2.value - y1.value < rows.size()) {
			if (x2.value - x1.value >= 0 && y2.value - y1.value >= 0) {
				List<String> flippedRows = flipping(x1.value * 2, x2.value * 2, y1.value, y2.value, rows);
				System.out.println("flipped:");
				printRows(flippedRows);
				rowsToFile(flippedRows, fileName.value);
			} else {
				System.out.println("ranges to small, should be: x2-x1>=0 and y2-y1>=0");
			}
		} else {
			System.out.println("input matrix to small, for these ranges");
		}

	}

	private static void rowsToFile(List<String> flippedRows, final String fileName) {
		final String OUTPUT_FILE_NAME = new String(fileName + "_flipped");
		final File OUTPUT_FILE = new File(OUTPUT_FILE_NAME);

		try {
			OUTPUT_FILE.createNewFile();
			FileWriter fw = new FileWriter(OUTPUT_FILE.getPath(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String row : flippedRows) {
				bw.write(row + "\n");
			}
			bw.close();
			System.out.println("File: " + OUTPUT_FILE_NAME + " written.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void printRows(List<String> rows) {
		System.out.println("[");
		for (String row : rows) {
			System.out.println(row);
		}
		System.out.println("]");

	}

	private static List<String> flipping(int x1, int x2, int y1, int y2, List<String> rows) {
		List<String> flippedRows = new ArrayList<String>();

		// add all untouched
		for (int row = 0; row < y1; row++) {
			flippedRows.add(rows.get(row));
		}

		// flipping part
		for (int row = y1; row <= y2; row++) {
			char[] tempRow = rows.get(row).toCharArray();
			for (int column = x1; column <= x2; column += 2) {
				if (tempRow[column] == '1') {
					tempRow[column] = '0';
				} else if(tempRow[column] == '0') {
					tempRow[column] = '1';
				}
			}
			flippedRows.add(new String(tempRow));
		}

		// add all untouched
		for (int row = y2 + 1; row < rows.size(); row++) {
			flippedRows.add(rows.get(row));
		}

		return flippedRows;
	}

	private static List<String> getFileContent(final String fileName) {

		List<String> rows = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				rows.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
	}

}
