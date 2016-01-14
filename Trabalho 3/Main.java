import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import dataStructures.UnionFindInArray;

/**
 * @author Miguel Ferrão Ferreira nº 41983 e Tiago dos Reis Morais da Silva nº 42056
 *
 */
public class Main {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		String str = buffer.readLine();
		StringTokenizer token = new StringTokenizer(str);
		int lineSegments = Integer.parseInt(token.nextToken()); //Le o numero de linhas
		
		//Cria um HashMap em que a chave e uma String(coordenadas) e o valor e um Integer(0, 1, 2,...)
		HashMap<String, Integer> points = new HashMap<String, Integer>();
		Queue<Integer> p = new LinkedList<Integer>();
		int counter = 0; //Variavel que atribuira os inteiros a cada ponto
						
		for(int i = 0; i < lineSegments; i++){ // Enquanto existirem linhas
			String str1 = buffer.readLine();
			StringTokenizer token1 = new StringTokenizer(str1); // Leitura das coordenadas dos extremos do segmento
			int x1 = Integer.parseInt(token1.nextToken());  
			int y1 = Integer.parseInt(token1.nextToken());	
			int x2 = Integer.parseInt(token1.nextToken());
			int y2 = Integer.parseInt(token1.nextToken());
			String coord1 = String.valueOf(x1) + " " + String.valueOf(y1);//Constroi a string que contem as coordenadas do primeiro ponto
			String coord2 = String.valueOf(x2) + " " + String.valueOf(y2);//Constroi a string que contem as coordenadas do segundo ponto
			 
			if(!points.containsKey(coord1)){ //Se o primeiro ponto nao existe no HashMap...
				points.put(coord1, counter); //...insere o ponto no HashMap e atribui lhe um valor que ainda nao existe (depois incrementa)
				p.add(counter);
				counter++;
			}
			else{
				p.add(points.get(coord1));
			}
				
			if(!points.containsKey(coord2)){ //Se o segundo ponto nao existe no HashMap...
				points.put(coord2, counter);//...insere o ponto no HashMap e atribui lhe um valor que ainda nao existe (depois incrementa)
				p.add(counter);
				counter++;
			}
			else{
				p.add(points.get(coord2));
			}
		}
		
		int out = PolygonPhobia(p, lineSegments);

		System.out.println(out); //Imprime o resultado
	}

	private static int PolygonPhobia(Queue<Integer> p, int lineSegments) {
		
		UnionFindInArray union = new UnionFindInArray(lineSegments + 1); //Referencia a classe onde ocorre a uniao
		int result = 0; //Variavel que guarda o resultado
		
		while(!p.isEmpty()){ 
			int union1 = union.find(p.remove());//Encontra o representante do primeiro ponto
			
			int union2 = union.find(p.remove());//Encontra o representante do segundo ponto
				
			if(union1!= union2){ //Caso em que os representantes sao diferentes
				union.union(union1, union2); //Executa a uniao dos representantes 
				result++;
			}
		}
		
		return result;
	}

}
	