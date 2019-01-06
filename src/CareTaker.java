import java.util.ArrayList;

public class CareTaker {
	protected ArrayList<Memento> savedPages = new ArrayList<Memento>();
	

	protected void addMemento(Memento m){
			savedPages.add(m);	
	}

	protected Memento getMemento(int index){
		return savedPages.get(index);
	}

	
}
