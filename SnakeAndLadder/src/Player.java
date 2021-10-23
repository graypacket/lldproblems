/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own personal use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
public class Player {
    private int id;
    private int currPos;

    Player(int id) {
        this.id = id;
        this.currPos = 0;
    }

    public int getId() {
        return this.id;
    }

    public int getPos() {
        return this.currPos;
    }

    public void setPos(int pos) {
        this.currPos = pos;
    }
}
