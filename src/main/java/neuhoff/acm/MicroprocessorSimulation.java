package neuhoff.acm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

public class MicroprocessorSimulation {

	private int[] memory;
	private int accA;
	private int accB;

	public MicroprocessorSimulation(String fileName/* , int lineNum */) {
		memory = new int[256];

		try {
			readInCode(fileName/* , lineNum */);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readInCode(String file/* , int lineNum */)
			throws IOException {
		
			 //BufferedReader br = new BufferedReader(
			 //new FileReader(file));

			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			
			while (memory[0] != 8) {
				String val = br.readLine();
				String[] temp = val.split("");
				for (int i = 0; i < 256; i++) {
					memory[i] = convertHexToDec(temp[i]);
				}
				translateCode();
			}
			br.close();
			System.exit(0);
	}

	private void translateCode() {
		for (int i = 0; i < 256; i++) {
			int val = memory[i];
			switch (val) {
			case 0:
				accA = memory[memory[++i] * 16 + memory[++i]];
				break;
			case 1:
				memory[memory[++i] * 16 + memory[++i]] = accA;
				break;
			case 2:
				int temp = accA;
				accA = accB;
				accB = temp;
				break;
			case 3:
				int add = accA + accB;
				accB = add / 16;
				accA = add - (accB * 16);
				break;
			case 4:
				if (accA <= 14) {
					accA++;
				} else
					accA = 0;
				break;
			case 5:
				if (accA > 0) {
					accA--;
				} else
					accA = 15;
				break;
			case 6:
				if (accA == 0) {
					i = memory[i + 1] * 16 + memory[i + 2];
					i--;
				} else {
					i += 2;
				}
				break;
			case 7:
				i = memory[i + 1] * 16 + memory[i + 2];
				i--;
				break;
			case 8:
				getOutput();
				return;
			}
		}
	}

	public void getOutput() {
		String[] print = new String[256];
		for (int i = 0; i < 256; i++) {
			print[i] = convertDecToHex(memory[i]);
			System.out.print(print[i]);
		}
		System.out.println();
	}

	private int convertHexToDec(String val) {

		if (NumberUtils.isNumber(val)) {
			return Integer.parseInt(val);
		} else {
			switch (val) {
			case "A":
				return 10;
			case "B":
				return 11;
			case "C":
				return 12;
			case "D":
				return 13;
			case "E":
				return 14;
			case "F":
				return 15;
			}
		}
		return -1;
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
		String inputFile = "mach.in.txt";
		// int lineNum = 4;
		MicroprocessorSimulation ms = new MicroprocessorSimulation(inputFile/*
																			 * ,
																			 * lineNum
																			 */);
	}
}
