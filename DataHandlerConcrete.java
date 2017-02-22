import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DataHandlerConcrete implements DataHandler {

	public void loadDataFromFile(String fileName, LibrarySystem libs) throws IOException {
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		String data[] = null;
		String line;
		AllItems.loadedItems = new Item[AllItems.MAX_ITEMS];
		try {
			while(!(line = in.readLine()).equals("endItems")){
				data = line.split("	");
			
				AllItems.loadedItems[AllItems.total_items] = new Item();
			
				AllItems.loadedItems[AllItems.total_items].doc_id = Integer.valueOf(data[0]);
				AllItems.loadedItems[AllItems.total_items].type = Integer.valueOf(data[1]);
				AllItems.loadedItems[AllItems.total_items].title = data[2];
				AllItems.loadedItems[AllItems.total_items].num_authors = 
					Integer.valueOf(data[3]);
			
				AllItems.loadedItems[AllItems.total_items].author = 
					new String[AllItems.loadedItems[AllItems.total_items].num_authors];
			
				for(int i = 0; i < AllItems.loadedItems[AllItems.total_items].num_authors; ++i)
					AllItems.loadedItems[AllItems.total_items].author[i] = data[4+i];
			
				int index = AllItems.loadedItems[AllItems.total_items].num_authors + 4;
				
				if(AllItems.loadedItems[AllItems.total_items].type == 0){
					AllItems.loadedItems[AllItems.total_items].publisher = data[index++];
					AllItems.loadedItems[AllItems.total_items].publication_date = data[index++];
					AllItems.loadedItems[AllItems.total_items].isbn = data[index++];
				}
				else if(AllItems.loadedItems[AllItems.total_items].type == 1){
					AllItems.loadedItems[AllItems.total_items].publisher = data[index++];
					AllItems.loadedItems[AllItems.total_items].publication_date = data[index++];
					AllItems.loadedItems[AllItems.total_items].number = data[index++];
					AllItems.loadedItems[AllItems.total_items].volume = data[index++];
				}
				else if(AllItems.loadedItems[AllItems.total_items].type == 4){
					AllItems.loadedItems[AllItems.total_items].location = data[index++];
					AllItems.loadedItems[AllItems.total_items].date = data[index++];
				}
				else if(AllItems.loadedItems[AllItems.total_items].type != 2 && 
						AllItems.loadedItems[AllItems.total_items].type != 3)
					System.out.println("Error loading item type.");

				AllItems.loadedItems[AllItems.total_items].num_waiting = Integer.valueOf(data[index++]);
				int num_w = AllItems.loadedItems[AllItems.total_items].num_waiting;
				
				AllItems.loadedItems[AllItems.total_items].waitList = new int[num_w];
				
				for(int i = 0; i < num_w; ++i)
					AllItems.loadedItems[AllItems.total_items].waitList[i] = Integer.valueOf(data[index + i]);
				
				index = index + num_w;

				AllItems.loadedItems[AllItems.total_items].in_stock = 
					Integer.valueOf(data[index++]);
				AllItems.loadedItems[AllItems.total_items].total = 
					Integer.valueOf(data[index++]);

				int size = AllItems.loadedItems[AllItems.total_items].total - 
				AllItems.loadedItems[AllItems.total_items].in_stock;

				AllItems.loadedItems[AllItems.total_items].docIDRecord = new int[size];
				AllItems.loadedItems[AllItems.total_items].userIDRecord = new int[size];
				AllItems.loadedItems[AllItems.total_items].dateRecord = new String[size];
				AllItems.loadedItems[AllItems.total_items].dueDate = new int[size];
				for(int i = 0; i < size; ++i) {
					AllItems.loadedItems[AllItems.total_items].docIDRecord[i] = 
						Integer.valueOf(data[index++]);
					AllItems.loadedItems[AllItems.total_items].userIDRecord[i] = 
						Integer.valueOf(data[index++]);
					AllItems.loadedItems[AllItems.total_items].dateRecord[i] = 
						data[index++];
					AllItems.loadedItems[AllItems.total_items].dueDate[i] = 
						Integer.valueOf(data[index++]);
				}
				AllItems.total_items++;
			}
			
			data = null;
			AllUsers.loadedUsers = new User[AllUsers.MAX_USERS];
			
			while(!(line = in.readLine()).equals("endUsers")){
				
				data = line.split("	");
				
				AllUsers.loadedUsers[AllUsers.total_IDs] = new User();

				AllUsers.loadedUsers[AllUsers.total_IDs].ID = Integer.valueOf(data[0]);
				AllUsers.loadedUsers[AllUsers.total_IDs].type = Integer.valueOf(data[1]);
				AllUsers.loadedUsers[AllUsers.total_IDs].name = data[2];
				AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed = 
					Integer.valueOf(data[3]);
				
				int rem = Integer.valueOf(data[4]);
				if(rem == 0)
					AllUsers.loadedUsers[AllUsers.total_IDs].removed = false;
				else if(rem == 1)
					AllUsers.loadedUsers[AllUsers.total_IDs].removed = true;
				else
					System.out.println("Problem with input value.\n");

				AllUsers.loadedUsers[AllUsers.total_IDs].borrowedItems = 
					new Item[AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed];
				AllUsers.loadedUsers[AllUsers.total_IDs].borrowedDates = 
					new String[AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed];
				
				for(int i = 0; i < AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed;++i){
					int x = 2*i; //i=0 x=0, i=1 x=2, etc
					int id = 5 + x;
					int dr = 6 + x;
					int doc_id = Integer.valueOf(data[id]);
					AllUsers.loadedUsers[AllUsers.total_IDs].borrowedItems[i] = new Item();
					AllUsers.loadedUsers[AllUsers.total_IDs].borrowedItems[i] = 
						AllItems.loadedItems[doc_id];
					AllUsers.loadedUsers[AllUsers.total_IDs].borrowedDates[i] = data[dr];
				}
				
				AllUsers.loadedUsers[AllUsers.total_IDs].J_MAX_DAYS = 3;
				AllUsers.loadedUsers[AllUsers.total_IDs].hold = false;

				if(AllUsers.loadedUsers[AllUsers.total_IDs].type == 0){
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_DAYS = 180;
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_RENTALS = 6;
				}
				else if(AllUsers.loadedUsers[AllUsers.total_IDs].type == 1){
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_DAYS = 365;
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_RENTALS = 12;
				}
				else if(AllUsers.loadedUsers[AllUsers.total_IDs].type == 2){
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_DAYS = 365;
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_RENTALS = 12;
				}
				else {
					if(AllUsers.loadedUsers[AllUsers.total_IDs].type != 9)
						System.out.println("Error loading user type.");
				}

				//If user removed do not increment total_users.			
				AllUsers.total_users++;
				AllUsers.total_IDs++;
			}
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
	}

	public void saveDataToFile(String fileName, LibrarySystem libs) {
		try {
			File file = new File(fileName);
			if(!file.exists())
				file.createNewFile();
		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < AllItems.total_items; ++i){
				bw.write(AllItems.loadedItems[i].doc_id+"\t");
				bw.write(AllItems.loadedItems[i].type+"\t");
				bw.write(AllItems.loadedItems[i].title+"\t");
				bw.write(AllItems.loadedItems[i].num_authors+"\t");
				for(int j = 0; j < AllItems.loadedItems[i].num_authors; ++j)
					bw.write(AllItems.loadedItems[i].author[j]+"\t");
				//Book
				if(AllItems.loadedItems[i].type == 0) {
					bw.write(AllItems.loadedItems[i].publisher+"\t");
					bw.write(AllItems.loadedItems[i].publication_date+"\t");
					bw.write(AllItems.loadedItems[i].isbn+"\t");
				}//Journal
				else if(AllItems.loadedItems[i].type == 1) {
					bw.write(AllItems.loadedItems[i].publisher+"\t");
					bw.write(AllItems.loadedItems[i].publication_date+"\t");
					bw.write(AllItems.loadedItems[i].number+"\t");
					bw.write(AllItems.loadedItems[i].volume+"\t");
				}//Conference Proceeding
				else if(AllItems.loadedItems[i].type == 4) {
					bw.write(AllItems.loadedItems[i].location+"\t");
					bw.write(AllItems.loadedItems[i].date+"\t");
				}
				
				bw.write(AllItems.loadedItems[i].num_waiting+"\t");
				for(int l = 0; l < AllItems.loadedItems[i].num_waiting; ++l)
					bw.write(AllItems.loadedItems[i].waitList[l]+"t");
				
				bw.write(AllItems.loadedItems[i].in_stock+"\t");
				bw.write(AllItems.loadedItems[i].total+"\t");
				
				int size = AllItems.loadedItems[i].total - AllItems.loadedItems[i].in_stock;
				for(int k = 0; k < size; ++k) {
					bw.write(AllItems.loadedItems[i].docIDRecord[k]+"\t");
					bw.write(AllItems.loadedItems[i].userIDRecord[k]+"\t");
					bw.write(AllItems.loadedItems[i].dateRecord[k]+"\t");
					bw.write(AllItems.loadedItems[i].dueDate[k]+"\t");
				}
				bw.newLine();
			}
			bw.write("endItems");
			bw.newLine();
			
			for(int i = 0; i < AllUsers.total_IDs; ++i){
				bw.write(AllUsers.loadedUsers[i].ID+"\t");
				bw.write(AllUsers.loadedUsers[i].type+"\t");
				bw.write(AllUsers.loadedUsers[i].name+"\t");
				bw.write(AllUsers.loadedUsers[i].num_borrowed+"\t");
		
				int rem = 0;
				if(AllUsers.loadedUsers[i].removed == true)
					rem = 1;
				bw.write(rem+"\t");
				
				for(int j = 0; j < AllUsers.loadedUsers[i].num_borrowed; ++j) {
					bw.write(AllUsers.loadedUsers[i].borrowedItems[j].doc_id+"\t");
					bw.write(AllUsers.loadedUsers[i].borrowedDates[j]);
					if(!(j == (AllUsers.loadedUsers[i].num_borrowed - 1)))
						bw.write("\t");
				}
				bw.newLine();
			}
			bw.write("endUsers\n");
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
