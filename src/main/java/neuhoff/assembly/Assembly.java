package neuhoff.assembly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assembly {

	private StringBuffer memory;
	private int index;

	public Assembly(String fileName) {
		memory = new StringBuffer();
		index = 0;
		try {
			readInCode(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readInCode(String file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		while ((line = br.readLine()) != null) {
			translateCode(line);
		}
		br.close();
		System.out.println(memory.toString());
		System.exit(0);
	}

	private void translateCode(String line) {
		String[] split = line.split(" ");
		switch (split[0]) {
		case "LD":
			memory.append("0");
			convertDecToHex(split[1]);
			break;
		case "ST":
			memory.append("1");
			convertDecToHex(split[1]);
			break;
		case "SWP":
			memory.append("2");
			break;
		case "ADD":
			memory.append("3");
			break;
		case "INC":
			memory.append("4");
			break;
		case "DEC":
			memory.append("5");
			break;
		case "BZ":
			memory.append("6");
			convertDecToHex(split[1]);
			break;
		case "BR":
			memory.append("7");
			convertDecToHex(split[1]);
			break;
		case "STP":
			memory.append("8");
			break;
		case "DATA":
			memory.append(split[1]);
		}
	}

	private void convertDecToHex(String value) {
		Integer dec = Integer.parseInt(value);
		int temp = dec / 16;
		memory.append(convertDecToHex(temp));
		memory.append(convertDecToHex(dec - (temp * 16)));
	}

	private String convertDecToHex(int val) {

		if (val >= 0 && val < 10) {
			return String.valueOf(val);
		} else {
			switch (val) {
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Assembly assembly = new Assembly("assemblyLanguage.txt");
	}
}
