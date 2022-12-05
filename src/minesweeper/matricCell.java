/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author ELAINE
 */
public class matricCell {
    private final int width=5;
    private final int height=5;
    private MineCell[][] cell;

    public matricCell() {
        this.cell=new MineCell[this.width][this.height];
        int jmlAngka=(int) Math.round(Math.random()*this.width*this.height/2);
        this.initCell();
    }
    public void initCell(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.cell[i][j]=new MineCell();
            }
        }
    }
    public void randomCell(){
        
    }
    public void printCell(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.cell[i][j].print();
            }
            System.out.println("");
        }
    }
    
}
