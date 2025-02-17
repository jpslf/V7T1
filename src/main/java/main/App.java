package main;

import java.util.Scanner;

class Impl {
	Scanner in;

	Impl(String[] args) {
		in = new Scanner(System.in);
	}

	String nextString() {
		return in.nextLine();
	}

	String promtString(String promt) {
		System.out.println(promt);

		return nextString();
	}

	int nextInt() {
		return Integer.parseInt(nextString());
	}

	int promtInt(String promt) {
		System.out.println(promt);

		return nextInt();
	}

	void close() {
		in.close();

		System.out.println("Kiitos ohjelman käytöstä.");
	}

	void LisaaKirja() {
		System.out.println("Lisää Kirja");
	}

	void ListaaKirjat() {
		System.out.println("Listaa Kirjat");
	}

	void LainaaFiktiokirja() {
		System.out.println("Lainaa fiktiokirja");
	}

	void PalautaFiktiokirja() {
		System.out.println("Palauta fiktiokirja");
	}


	boolean run() {
		System.out.println("1) Lisää Kirja");
		System.out.println("2) Listaa Kirjat");
		System.out.println("3) Lainaa fiktiokirja");
		System.out.println("4) Palauta fiktiokirja");

		System.out.println("0) Lopeta ohjelma");

		int selection = nextInt();

		if (selection == 0) {
			return false;

		} else if (selection == 1) {
			LisaaKirja();

		} else if (selection == 2) {
			ListaaKirjat();

		} else if (selection == 3) {
			LainaaFiktiokirja();

		} else if (selection == 4) {
			PalautaFiktiokirja();

		} else {
			System.out.println("Tuntematon valinta, yritä uudestaan.");
		}

		return true;
	}
}

public class App {
	public static void main(String[] args) {
		Impl impl = new Impl(args);

		while (impl.run()) {}

		impl.close();
	}
}
