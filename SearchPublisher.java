
public class SearchPublisher implements DocumentSearch {
	
	public SearchPublisher() {
	}
	
	public void printMenu() {
		System.out.println("Enter Publisher(no spaces) and press enter: ");
	}
	
	public void start() {
		String publisher;
		boolean printed = false;
		publisher = sc.next();
		
		for(int i = 0; i < AllItems.total_items; ++i) {
			if(AllItems.loadedItems[i].type == 0 || AllItems.loadedItems[i].type == 1) 
				if(AllItems.loadedItems[i].publisher.equals(publisher)) {
					AllItems.displayItemInfo(AllItems.loadedItems[i]);
					printed = true;
			}
		}							
	}
}
