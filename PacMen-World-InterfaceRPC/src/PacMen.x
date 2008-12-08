const C_MAX = 1000;

struct st_World {
	short id;
	string map<32768>;
	short width;
	short height;
};

struct st_Actor {
	short id;
	short x;
	short y;
};

struct st_ActorAndNext {
	st_Actor actor;
	short next;
};

struct st_Status {
	short id;
	string msg<256>;
};

program PacMen {

	version PacMen_v1 {
		st_World worldInfo(void)=1;
		st_Status pacManInfo(st_Actor)=2;
		st_ActorAndNext ghostInfo(short)=3;
	}=1;
	
}=1;
