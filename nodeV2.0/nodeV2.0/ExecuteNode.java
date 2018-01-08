package node;

public class ExecuteNode extends Thread  {

	Node node;
	
	ExecuteNode(Node node){
		this.node=node;
		
		
	}
	
	
	public void run() {
		try {
			this.node.startNode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
