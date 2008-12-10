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
		st_World worldInfo(void)=10;
		st_Status pacManInfo(st_Actor)=20;
		st_ActorAndNext ghostInfo(short)=30;
	}=1;
	
}=1;
