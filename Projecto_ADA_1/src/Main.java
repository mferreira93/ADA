
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Comparator;


/**
 * @author Tiago
 *
 */
public class Main {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		String str = buffer.readLine();
		StringTokenizer token = new StringTokenizer(str);
		int vertices = Integer.parseInt(token.nextToken()); //Le o numero de vertices a atribuir
		int arcs = Integer.parseInt(token.nextToken()); //Le o numero de arcos existentes
		
		List<Integer>[] successors = new LinkedList[vertices];
		List<Integer>[] predecessors = new LinkedList[vertices];
		boolean[] found= new boolean[vertices];
		
		for(int i = 0; i < vertices; i++){
			successors[i] = new LinkedList<Integer>();
			predecessors[i] = new LinkedList<Integer>();
			found[i] = false;
		}
				
		for(int i = 0; i < arcs; i++){ // Enquanto existirem regras
			String str1 = buffer.readLine();
			StringTokenizer token1 = new StringTokenizer(str1); // Leitura das regras
			int a1 = Integer.parseInt(token1.nextToken());  
			int a2 = Integer.parseInt(token1.nextToken());
			successors[a1].add(new Integer(a2)); // Cria as listas dos varios sucessores 
			predecessors[a2].add(new Integer(a1));	//Cria as lista do varios antecessores	
		}
		
		friendlyManuals(successors, predecessors, found);
		
		//teste sucessores e antecessores
		/*for(int i = 0; i < vertices; i++){
			for(int i = 0; i < predecessors.length; i++){
				System.out.println(i + " " + predecessors[i].get(j));
			}
		}*/
	}
		
	
	
	

	private static void friendlyManuals(List<Integer>[] successors, List<Integer>[] predecessors, boolean found[]){
		List<Integer> current = new LinkedList<Integer>();
		Queue<Integer> aux = new PriorityQueue(succ, new Comp());
		
		int succ;
		int vertex = -1; 
				
		for( int i = 0; i < predecessors.length; i++){
			if(predecessors[i].size() == 0){
				current.add(i);
				found[i] = true;
			}
		}
		
		while(!current.isEmpty()){
			vertex = current.remove(0);
			System.out.println("Vertex: " + vertex );
						
			if(successors[vertex].size() > 0){
				for(int i = 0; i < successors[vertex].size(); i++){
						succ = successors[vertex].get(i);
						//System.out.println("Succ: " + succ );
						if(found[succ] == false){
							if(aux.size() == 0){
							aux.add(succ);
							found[succ] = true;
							}
							else if(succ > aux.get(0)){
									
							}
								
						}
				}
			}
		}
		
			/**if(remove == true){
				int min = Integer.MAX_VALUE;
			
				for(int i = 0; i < current.size(); i++){
					if(min > i){
						min = i;
					}
				}
				current.remove(min);
				vertex = min;
			}
			*/
			
			
		}	
		
	}
	
	public List<Integer> search(aux){
		int inicio = 0; 
	}


/*
	   		toWin(successors, predecessors, height, chanceToWin, vertices, arcs);
	   	}
		
			
	}
	
	private static void toWin(List<Integer>[] successors, List<Integer>[] predecessors,List<Integer>[] height, int chanceToWin, int prizes, int aces){
		Queue<Integer> current = new LinkedList<Integer>();
		int vertex;
						
		for(int i = 0; i < predecessors.length; i++){
			if(predecessors[i].size() == 0){ //Se o vertice nao tiver antecessor...
				current.add(i);
			}
		}
		
		while(!current.isEmpty()){
			//System.out.println("SIZE: " + current.size());
			int level = 0;
			vertex = current.remove();
			
			if(successors[vertex].size() > 0){ //Se o vertice tiver sucessores...
				for(int i = 0; i < successors[vertex].size(); i++){
					int succ;
					succ = successors[vertex].get(i);
					//System.out.println("SUCC: " + succ);
					current.add(succ); //... adiciona no final da lista currente de vertices a tratar
				}
			}
			
			if(predecessors[vertex].size() == 0)
				height[vertex].add(new Integer(level));
			else{
				//System.out.println("Vertex: " + vertex);
				//A lista height guarda o numero de antecessores de um vertice. Para um novo vertice basta consultar o
				// numero de antecessores de um antecessor seu, e somar lhe um para se obter o nivel e o numero de antecessores 
				//desse mesmo vertice
				for(int i = 0; i < predecessors[vertex].size(); i++){
					int pred = 0;
					 pred = predecessors[vertex].get(i); // Obtem os predecessores do vertice
					 //System.out.println(pred);
					 level += height[pred].get(0); // Obtem o numero de antecessores de um nivel anterior ao vertice, e soma lhe um
				}
				level++;
				height[vertex].add(new Integer(level));
				}
				
			
		}
		
		/**for(int j = 0; j < aces; j++){
			int su = 0;
			if(height[j].get(0) == 0)
				su = successors[j].get(0);
				if(height[su].get(0) > 1)
					height[j].add(0, height[su].get(0) - 1);				
		}
		for(int j = 0; j < aces; j++){
		System.out.println(j+ " " +height[j].get(0));
	}
		
		
		if(height[chanceToWin].get(0) >= prizes){
			System.out.println("Sorry");
		}
		else{
			int targets = 0;
			for(int i = 0; i < aces; i++){
				if(height[i].get(0) < prizes)
					targets++;
		}
		
		if(targets <= prizes)
			System.out.println("Congratulations!");
		else
			System.out.println("You have chances");
		
		//Testar vetor height
		/**for(int j = 0; j < aces; j++){
			System.out.println(j+ " " +height[j].get(0));
		}
				
		}
	}}*/
