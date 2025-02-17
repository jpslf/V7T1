package main;

import java.util.Scanner;
import java.util.ArrayList;

abstract class Book {
	String title;
	String author;

	int pages;
	int copies;

	void displayinfo() {
		System.out.println(String.format("Nimi: %s, Kirjailija: %s, Sivut: %d, Määrä: %d", title, author, pages, copies));
	}

	abstract String categorize();
}

class FictionBook extends Book implements Borrowable {
	int borrows;

	FictionBook(String title_, String author_, int pages_, int copies_) {
		title = title_;
		author = author_;

		pages = pages_;
		copies = copies_;
		borrows = 0;
	}

	@Override
	String categorize() {
		return "Fiktiokirja";
	}

	@Override
	public void borrow() {
		if(copies == 0) {
			System.out.println(String.format("Kirjaa %s ei enään ole lainattavissa!", title));
		} else {
			copies -= 1;
			borrows += 1;

			System.out.println(String.format("Kirja %s on nyt lainattu.", title));
		}
	}

	@Override
	public void returnBook() {
		if (borrows == 0) {
			System.out.println(String.format("Kirja %s ei ole lainassa.", title));
		} else {
			copies += 1;
			borrows -= 1;

			System.out.println(String.format("Kirja %s on palautettu.", title));
		}
	}
}

class NonFictionBook extends Book {
	NonFictionBook(String title_, String author_, int pages_, int copies_) {
		title = title_;
		author = author_;

		pages = pages_;
		copies = copies_;
	}

	@Override
	String categorize() {
		return "Tietokirja";
	}
}

interface Borrowable {
	void borrow();
	void returnBook();
}

class Library {
	ArrayList<Book> books;

	Library() {
		books = new ArrayList<Book>();
	}

	void addBook(Book book_) {
		books.add(book_);
	}

	ArrayList<Book> getBooks() {
		return books;
	}

	void listBooks() {
		int count = 0;

		for (Book book : books) {
			book.displayinfo();

			count += book.copies;

			System.out.println(String.format("Kirjan kategoria: %s", book.categorize()));
		}

		System.out.println(String.format("Kirjojen määrä kirjastossa on: %d", count));
	}

	void borrow(String title) {
		for (Book book : books) {
			if (book.title.equals(title)) {
				if (book instanceof Borrowable) {
					((Borrowable)book).borrow();

					return;
				} else {
					System.out.println("Kirjaa ei löytynyt tai sitä ei voi lainata.");
				}
			}
		}

		System.out.println("Kirjaa ei löytynyt tai sitä ei voi lainata.");
	}

	void returnBook(String title) {
		for (Book book : books) {
			if (book.title.equals(title)) {
				if (book instanceof Borrowable) {
					((Borrowable)book).returnBook();

					return;
				} else {
					System.out.println("Kirjaa ei löytynyt tai sitä ei voi palauttaa.");
				}
			}
		}

		System.out.println("Kirjaa ei löytynyt tai sitä ei voi palauttaa.");
	}
}

class Impl {
	Scanner in;
	Library library;

	Impl(String[] args) {
		in = new Scanner(System.in);
		library = new Library();
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
		int kind = promtInt("Minkä kirjan haluat lisätä kirjastoon? 1) Fiktiokirja, 2) Tietokirja");

		String title = promtString("Anna kirjan nimi:");

		String author = promtString("Anna kirjailijan nimi:");

		int pages = promtInt("Anna sivumäärä:");

		int copies = promtInt("Anna kirjojen määrä:");

		switch (kind) {
			case 1:
				library.addBook(new FictionBook(title, author, pages, copies));

				break;

			case 2:
				library.addBook(new NonFictionBook(title, author, pages, copies));

				break;

			default:
				System.out.println("Tuntematon valinta");

				break;
		}

		System.out.println("Kirja lisätty kirjastoon!");
	}

	void ListaaKirjat() {
		library.listBooks();
	}

	void LainaaFiktiokirja() {
		String title = promtString("Anna lainattavan kirjan nimi:");

		library.borrow(title);
	}

	void PalautaFiktiokirja() {
		String title = promtString("Anna palautettavan kirjan nimi:");

		library.returnBook(title);
	}

	boolean run() {
		System.out.println("1) Lisää kirja");
		System.out.println("2) Listaa kirjat");
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
