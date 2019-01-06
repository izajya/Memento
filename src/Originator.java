
public class Originator {

	private String about;
	private String web;
	private String statement;
	private String hex;
	private int ver;
	
	protected void setState(String newAbout, String newWeb, 
			String newStatement, String newHex, int version){
		
		about = newAbout;
		web = newWeb;
		statement = newStatement;
		hex = newHex;
		ver = version;
	}
	
	protected Memento storeInMemento(){
		return new Memento(about, web, statement, hex, ver);
	}
	
	protected String restoreAbout(Memento m){
		return m.getAbout();
	}
	
	protected String restoreWeb(Memento m){
		return m.getWeb();
	}
	
	protected String restoreStatement(Memento m){
		return m.getStatement();
	}
	
	protected String restoreHex(Memento m){
		return m.getHex();
	}
}
