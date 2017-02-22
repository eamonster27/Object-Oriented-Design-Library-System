import java.util.Observable;
import java.util.Observer;


public class DocObserver implements Observer{
	private int doc_id;
	
	public DocObserver(){
		doc_id = -1;
	}
	
	
	public void update(Observable obj, Object arg) {
		if(arg instanceof Integer){
			int user_id;
			doc_id = (Integer) arg;
			for(int i = 0; i < AllItems.loadedItems[doc_id].num_waiting; ++i){
				//Selecting user
				user_id = AllItems.loadedItems[doc_id].waitList[i];
				//Telling user there is a new notification
				AllUsers.loadedUsers[user_id].notification = true;
				//Creating index for number of messages
				int index = AllUsers.loadedUsers[user_id].num_messages;
				//Initializing temporary message storage
				String tempM[] = new String[index];
				//Copying messages into tempM
				for(int j = 0; j < index; ++j)
					tempM[j] = AllUsers.loadedUsers[user_id].message[j];
				//Reinitializing messages to array size num_messages + 1
				AllUsers.loadedUsers[user_id].message = new String[index+1];
				//Copying contents from tempM back to messages
				for(int j = 0; j < index; ++j)
					AllUsers.loadedUsers[user_id].message[j] = tempM[j];
				//Copying new message into message array and incrementing num_messages
				AllUsers.loadedUsers[user_id].message[index] = 
					AllItems.loadedItems[doc_id].title + " is available.";
				AllUsers.loadedUsers[user_id].num_messages++;
				
			}
			AllItems.loadedItems[doc_id].waitList = new int[0];
			AllItems.loadedItems[doc_id].num_waiting = 0;
		}
		
	}
	

}
