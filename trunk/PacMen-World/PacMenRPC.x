struct PacMen{
	int posX;
	int posY;
};

struct World{
	string mapa<5000>;
};

program PacMenRPC{
	version	PacMenRPC01 {
		PacMen getPacMan(void)=1;
		void setPacMan(PacMen)=2;
		World getWorld(void)=3;
	}=1;
}=1;