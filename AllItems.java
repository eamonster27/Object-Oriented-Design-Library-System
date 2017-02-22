import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AllItems {
	
	public static int total_items = 0;
	public static int MAX_ITEMS = 100;
	
	public static Item[] loadedItems = null;
	
	private DocumentSearch ds;
	public AllItems(DocumentSearch ds){
		this.ds = ds;
	}
	public DocumentSearch getDocSearch(){
		return ds;
	}
	public void executeDocSearch(){
		getDocSearch().printMenu();
		getDocSearch().start();
	}

	public static void displayItemInfo(Item item) {
		
		System.out.println("\nDocument ID: " + item.doc_id);
		System.out.println("Title: " + item.title);
		
		System.out.println("Author: ");
		for(int i = 0; i < item.num_authors; ++i)
			System.out.println(item.author[i]);
		
		String t;
		if(item.type == 0) {
			t = "Book";
			System.out.println("Publisher: " + item.publisher);
			System.out.println("Publication Date: " + item.publication_date);
			System.out.println("ISBN: " + item.isbn);
		}
		else if(item.type == 1) {
			t = "Journal";
			System.out.println("Publisher: " + item.publisher);
			System.out.println("Publication Date: " + item.publication_date);
			System.out.println("Number: " + item.number);
			System.out.println("Volume: " + item.volume);
		}
		else if(item.type == 2) {
			t = "Audio Tape";
		}
		else if(item.type == 3) {
			t = "Video";
		}
		else if(item.type == 4) {
			t = "Conference Proceedings";
			System.out.println("Location: " + item.location);
			System.out.println("Date: " + item.date);
		}
		else
			t = "Error";
		
		System.out.println("Type: " + t);
		System.out.println("Copies In Stock: " + item.in_stock);
		System.out.println("Total Copies: " + item.total);
		if(item.in_stock < item.total)
			System.out.println("Oldest Checkout: " + item.dateRecord[0]);
		System.out.println("Wait List: " + item.num_waiting);
		
		
	}
}
