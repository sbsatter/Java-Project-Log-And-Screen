package TickTackToe;

public class gameVarSetter {
	static int easiness = -1;
	static int player = -1;
	void setE(int e){
		easiness = e;
	}
	void setP(int p){
		player = p;
	}
	int getE(){
		return easiness;
	}
	int getP(){
		return player;
	}
}
