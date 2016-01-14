
import hero.HeroMovement;
import hero.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author Miguel Ferr�o Ferreira n� 41983 e Tiago dos Reis Morais da Silva n� 42056
 *
 */
public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		String str = buffer.readLine();
		StringTokenizer token = new StringTokenizer(str);
		int lines = Integer.parseInt(token.nextToken()); //Le o numero de linhas do mapa
		int columns = Integer.parseInt(token.nextToken()); //Le o numero de colunas do mapa
		
		int [][] map = new int[lines][columns]; //Array com listas ligadas onde vao ser guardados os sucessores
		boolean [][] select = new boolean[lines][columns];
				
		for(int i = 0; i < lines; i++){ // Enquanto existirem linhas
			String str1 = buffer.readLine();
			StringTokenizer token1 = new StringTokenizer(str1); // Leitura dos valores de uma linha
			for(int j = 0; j < columns; j++){
				int cell = Integer.parseInt(token1.nextToken());  
				map[i][j] = cell;  // ...adiciona o valor a celula correspondente
				select[i][j] = false;
			}
		}
				
		String str2 = buffer.readLine();
		StringTokenizer token2 = new StringTokenizer(str2);
		int xStart = Integer.parseInt(token2.nextToken()); //Le a coordenada x da posi�ao inicial do heroi
		int yStart = Integer.parseInt(token2.nextToken()); //Le a coordenada y da posi�ao inicial do heroi
		
		String str3 = buffer.readLine();
		StringTokenizer token3 = new StringTokenizer(str3);
		int xEnd = Integer.parseInt(token3.nextToken()); //Le a coordenada x da posi�ao objetivo
		int yEnd = Integer.parseInt(token3.nextToken()); //Le a coordenada y da posi�ao objetivo
		
		Vertex v = null;
		if(map[xStart][yStart] == 1){
			 v = new Vertex(xStart, yStart, 0 , 1);
		}
		else if(map[xStart][yStart] > 1){
				int m = map[xStart][yStart]/10;
				int n = map[xStart][yStart]%10;
				v = new Vertex(xStart, yStart, m, n , xStart, yStart);
		}
	
		Vertex vFinal = new Vertex(xEnd, yEnd, 0, 1);
						
		int out = jumpingHero(select, v, vFinal, lines, columns, map);
				
		if( out == -1)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(out);
					
	}
	
	private static int jumpingHero(boolean[][] select, Vertex v, Vertex vFinal, int lines, int columns, int[][] map) {
			
		Queue<Vertex> current = new LinkedList<Vertex>(); //Lista currente que conter� os vertices ja encontrados e nao tratados
		Queue<Vertex> aux = new LinkedList<Vertex>();
		int height = 0;
		int impossible = -1;
		int xFinal = vFinal.xCoord();
		int yFinal = vFinal.yCoord();
		
		if(map[xFinal][yFinal] == 0){
			return impossible;
		}
		
		current.add(v);
		select[v.xCoord()][v.yCoord()] = true;
				
		while(select[xFinal][yFinal] == false){
			Vertex vertex = current.remove();
			//System.out.println("("+ vertex.xCoord() +"," + vertex.yCoord()+")" + " " +vertex.mValue() + " "+vertex.nValue() +" ("
					//+ vertex.xFont() + "," +vertex.yFont() + ")" );
			
			if(vertex.xCoord() == xFinal && vertex.yCoord() == yFinal){
				select[xFinal][yFinal] = true;
				height--;
			}
			else{
				HeroMovement move = new HeroMovement(select, map, xFinal, yFinal);
				move.moveRight(vertex, columns);
				move.moveDown(vertex, lines);
				move.moveLeft(vertex);
				move.moveUp(vertex);
				select = move.getSelected();
				map = move.getMap();
				/**for(int i = 0; i < lines; i++){
					for(int j = 0; j < columns; j++){
						System.out.print(select[i][j] +" ");
					}
					System.out.println();
				}*/
				if(move.getSuccessors().isEmpty())
					select[vertex.xCoord()][vertex.yCoord()] = true;
				else
					aux.addAll(move.getSuccessors());
		
				if(current.isEmpty()){
					current.addAll(aux);
					if(current.isEmpty()){
						return impossible;
					}
					aux = new LinkedList<Vertex>();
					if(select[xFinal][yFinal] == false)
						height++;
				}
			}
		}
		return height + 1;		
	}
}
		
		
		