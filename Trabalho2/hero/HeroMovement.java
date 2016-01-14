package hero;

import java.util.LinkedList;
import java.util.Queue;

public class HeroMovement {
	
	public static final int emptyCell = 1;
	public static final int wall = 0;
	
		boolean [][] select;
		int[][] map;
		Queue<Vertex> succ;
		int xFinal;
		int yFinal;
				
		public HeroMovement(boolean [][] select, int[][] map, int xFinal, int yFinal){
			this.select = select;
			succ = new LinkedList<Vertex>();
			this.map = map;
			this.xFinal = xFinal;
			this.yFinal = yFinal;
		}
		
		public void moveRight(Vertex vertex, int columns){
			int x = vertex.xCoord();
			int y = vertex.yCoord() + vertex.nValue();
			
			if(y < columns){
				int cellValue = map[x][y];
				if(cellValue != wall){
					if(select[x][y] == false){
						if(vertex.mValue() == 0){ //Sem super poderes...
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								succ.add(new Vertex(x, vertex.yCoord() + 1, 0, 1));
								select[x][vertex.yCoord() + 1] = true;
							}
							else{ //... o heroi salta para um fonte de super poderes
								succ.add(new Vertex(x, y, mValue(x, y), nValue(x, y), x, y));
							}
						}
						else{ //Com super poderes...
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								if(x == xFinal && y == yFinal){
									select[x][y] = true;
								}
								if(vertex.mValue() == 1){//e executa o ultimo salto com super poderes
									succ.add(new Vertex(x, y, 0, 1));
									select[x][y] = true;
								}
								else{//.. o heroi ainda possui super poderes
									succ.add(new Vertex(x, y, vertex.mValue() -1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
								}
							}
							else{//... o heroi salta para um fonte de super poderes
								//Se a fonte for a fonte onde ganhou os super poderes atuais
								if(x == vertex.xFont() && y == vertex.yFont() && vertex.mValue() < mValue(x, y)){
									if(vertex.mValue() == 1){ //e executa o ultimo salto com super poderes 
										succ.add(new Vertex(x, y, vertex.mValue() - 1, 1, vertex.xFont(), vertex.yFont()));
										select[x][y] = true;
										map[x][y] = 1;
									}
									else{ //.. o heroi ainda possui super poderes
										succ.add(new Vertex(x, y, vertex.mValue() - 1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
									}
								}
								else{//..a fonte é diferente da fonte inicial, onde o heroi ganhou os super poderes atuais
									succ.add(new Vertex(x, y, mValue(x,y), nValue(x,y), x, y));
								}
							}
						}
					}
				} 
			}
		}
		
		public void moveDown(Vertex vertex, int lines){
			int x = vertex.xCoord() + vertex.nValue();
			int y = vertex.yCoord();
			
			if(x < lines){
				int cellValue = map[x][y];
				if(cellValue != wall){
					if(select[x][y] == false){
						if(vertex.mValue() == 0){ //Sem super poderes..
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								succ.add(new Vertex(vertex.xCoord() + 1, y, 0, 1));
								select[vertex.xCoord() + 1][y] = true;
							}
							else{ //... o heroi salta para um fonte de super poderes
								succ.add(new Vertex(x, y, mValue(x, y), nValue(x, y), x, y));
							}
			    	    }
						else{ //Com super poderes...
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								if(x == xFinal && y == yFinal){
									select[x][y] = true;
								}
								if(vertex.mValue() == 1){//e executa o ultimo salto com super poderes
									succ.add(new Vertex(x, y, 0, 1));
									select[x][y] = true;
								}
								else{//.. o heroi ainda possui super poderes
									succ.add(new Vertex(x, y, vertex.mValue() -1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
								}
							}
							else{//... o heroi salta para um fonte de super poderes
								//Se a fonte for a fonte onde ganhou os super poderes atuais
								if(x == vertex.xFont() && y == vertex.yFont() && vertex.mValue() < mValue(x, y)){
									if(vertex.mValue() == 1){ //e executa o ultimo salto com super poderes 
										succ.add(new Vertex(x, y, vertex.mValue() - 1, 1, vertex.xFont(), vertex.yFont()));
										select[x][y] = true;
										map[x][y] = 1;
									}
									else{ //.. o heroi ainda possui super poderes
										succ.add(new Vertex(x, y, vertex.mValue() - 1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
									}
								}
								else{//..a fonte é diferente da fonte inicial, onde o heroi ganhou os super poderes atuais
									succ.add(new Vertex(x, y, mValue(x,y), nValue(x,y), x, y));
								}
							}
						}
					}
				}
			}
		}

		public void moveLeft(Vertex vertex){
			int x = vertex.xCoord();
			int y = vertex.yCoord() - vertex.nValue();
			
			if(y  >= 0){
				int cellValue = map[x][y];
				if(cellValue != wall){
					if(select[x][y] == false){
						if(vertex.mValue() == 0){ //Sem super poderes..
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								succ.add(new Vertex(x, vertex.yCoord() - 1, 0, 1));
								select[x][ vertex.yCoord() - 1] = true;
							}
							else{ //... o heroi salta para um fonte de super poderes
								succ.add(new Vertex(x, y, mValue(x, y), nValue(x, y), x, y));
							}
			    	    }
						else{ //Com super poderes...
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								if(x == xFinal && y == yFinal){
									select[x][y] = true;
								}
								if(vertex.mValue() == 1){//e executa o ultimo salto com super poderes
									succ.add(new Vertex(x, y, 0, 1));
									select[x][y] = true;
								}
								else{//.. o heroi ainda possui super poderes
									succ.add(new Vertex(x, y, vertex.mValue() -1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
								}
							}
							else{//... o heroi salta para um fonte de super poderes
								//Se a fonte for a fonte onde ganhou os super poderes atuais
								if(x == vertex.xFont() && y == vertex.yFont() && vertex.mValue() < mValue(x, y)){
									if(vertex.mValue() == 1){ //e executa o ultimo salto com super poderes 
										succ.add(new Vertex(x, y, vertex.mValue() - 1, 1, vertex.xFont(), vertex.yFont()));
										select[x][y] = true;
										map[x][y] = 1;
									}
									else{ //.. o heroi ainda possui super poderes
										succ.add(new Vertex(x, y, vertex.mValue() - 1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
									}
								}

								else{//..a fonte é diferente da fonte inicial, onde o heroi ganhou os super poderes atuais
									succ.add(new Vertex(x, y, mValue(x,y), nValue(x,y), x, y));
								}
							}
						}
					}
				}
			}
		}
		
		public void moveUp(Vertex vertex){
			int x = vertex.xCoord() - vertex.nValue();
			int y = vertex.yCoord();
			
			if(x >= 0){
				int cellValue = map[x][y];
				if(cellValue != wall){
					if(select[x][y] == false){
						if(vertex.mValue() == 0){ //Sem super poderes..
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								succ.add(new Vertex(vertex.xCoord() - 1, y, 0, 1));
								select[vertex.xCoord() - 1][y] = true;
							}
							else{ //... o heroi salta para um fonte de super poderes
								succ.add(new Vertex(x, y, mValue(x, y), nValue(x, y), x, y));
							}
			    	    }
						else{ //Com super poderes...
							if(cellValue == emptyCell){ //... o heroi salta para uma celula vazia
								if(x == xFinal && y == yFinal){
									select[x][y] = true;
								}
								if(vertex.mValue() == 1){//e executa o ultimo salto com super poderes
									succ.add(new Vertex(x, y, 0, 1));
									select[x][y] = true;
								}
								else{//.. o heroi ainda possui super poderes
									succ.add(new Vertex(x, y, vertex.mValue() -1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
								}
							}
							else{//... o heroi salta para um fonte de super poderes
								//Se a fonte for a fonte onde ganhou os super poderes atuais
								if(x == vertex.xFont() && y == vertex.yFont() && vertex.mValue() < mValue(x, y)){
									if(vertex.mValue() == 1){ //e executa o ultimo salto com super poderes 
										succ.add(new Vertex(x, y, vertex.mValue() - 1, 1, vertex.xFont(), vertex.yFont()));
										select[x][y] = true;
										map[x][y] = 1;
									}
									else{ //.. o heroi ainda possui super poderes
										succ.add(new Vertex(x, y, vertex.mValue() - 1, vertex.nValue(), vertex.xFont(), vertex.yFont()));
									}
								}
								else{//..a fonte é diferente da fonte inicial, onde o heroi ganhou os super poderes atuais
									succ.add(new Vertex(x, y, mValue(x,y), nValue(x,y), x, y));
								}
							}
						}
					}
				}
			}
		}
		
		public boolean[][] getSelected(){
			return select;
		}
		
		public int[][] getMap(){
			return map;
		}
		
		public Queue<Vertex> getSuccessors(){
			return succ;
		}

		public int mValue( int mX, int mY){
			int m = (map[mX][mY])/10;
			return m;
		}
		
		public int nValue( int nX, int nY){
			int n = (map[nX][nY])%10;
			return n;
		}
}
