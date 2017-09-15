package com.theslof;

public class MenuItem {
    String menuString;
    MenuOption ex;

    public MenuItem(String menuString, MenuOption ex){
        this.menuString = menuString;
        this.ex = ex;
    }

    public void execute(){
        ex.execute();
    }
}
