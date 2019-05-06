import java.util.ArrayList;

class HashNode<k,v>{
	private k key;
	private v value;
	private HashNode<k,v> next;
	public HashNode(k key, v value){
		this.key = key;
		this.value = value;
		next = null;
	}

	public k getKey(){
		return this.key;
	}

	public v getValue(){
		return this.value;
	}

	public HashNode getNext(){
		return this.next;
	}

	public void setKey(k key){
		this.key = key;
	}

	public void setValue(v value){
		this.value = value;
	}

	public void setNext(HashNode next){
		this.next = next;
	}
}

public class Hashtable<k,v>{
	private ArrayList<HashNode<k,v>> slots;
	private int size;
	private double lambda = 0.6;

	private int getSlot(k key){
		int slot = key.hashCode();
		return slot % slots.size();
	}

	public Hashtable(){
		size = 0;
		slots = new ArrayList();
		for(int i = 0; i < 10; i ++)
			slots.add(null);
	}

	public Object get(k key){
		int slot = getSlot(key);
		HashNode node = slots.get(slot);
		while(node != null && node.getKey()!=key)
			node.getNext();
		if(node == null)
			return null;
		return node.getValue();
	}

	public void put(k key, v value){
		//if(size/slots.size() >= lambda)
			//growArray();
		int slot = getSlot(key);
		HashNode node = slots.get(slot);
		HashNode newNode = new HashNode(key,value);
		newNode.setNext(node);
		slots.set(slot, newNode);
		size++;
	}

	public boolean containsKey(k key){
		int slot = getSlot(key);
		HashNode node = slots.get(slot);
		while(node != null && node.getKey()!=key)
			node.getNext();
		if(node == null)
			return false;
		return true;
	}

	// Method to remove a given key 
	public String remove(k key) 
	{ 
		// Apply hash function to find index for given key 
		int slot = getSlot(key); 

		// Get head of chain 
		HashNode head = slots.get(slot); 

		// Search for key in its chain 
		HashNode prev = null; 
		while (head != null) 
		{ 
			// If Key found 
			if (head.getKey().equals(key)) 
				break; 

			// Else keep moving in chain 
			prev = head; 
			head.getNext(); 
		} 

		// If key was not there 
		if (head == null) 
			return null; 

		// Reduce size 
		size--; 

		// Remove key 
		if (prev != null) 
			head.getNext(); 
		else
			slots.set(slot, head.getNext()); 

		return head.getValue().toString(); 
	}

}




