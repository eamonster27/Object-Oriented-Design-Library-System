
public class SearchTitle implements DocumentSearch {
	
	public SearchTitle() {
	}
	
	public void printMenu() {
		System.out.println("Enter Title(no spaces) and press enter: ");
	}
	
	public void start() {
		String title;
		boolean printed = false;
		title = sc.next();
		
		for(int i = 0; i < AllItems.total_items; ++i) {
			if(AllItems.loadedItems[i].title.equals(title)) {
				AllItems.displayItemInfo(AllItems.loadedItems[i]);
				printed = true;
			}
		}							
	}
}
