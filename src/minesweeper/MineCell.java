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
//Kelas Minecell adalah kelas per kotak di minesweeper
public class MineCell {
    //var boolean dipencet tidak 
    private boolean isPencet;
    // angka pada minesweeper
    private int angka; 
    //cell-cell bertetanggaan
    private MineCell[][] tetangga;
    
    public MineCell() {
        //blom di pencet cellnya 
        this.isPencet = false;
        //yang tidak pny angka di set -1
        this.angka = -1;
    }

    public MineCell(int angka) {
        this.angka = angka;
         //blom di pencet cellnya 
        this.isPencet = false;
        
    }
    
    //getter setter 
    public boolean isIsPencet() {
        return isPencet;
    }

    public void setIsPencet(boolean isPencet) {
        this.isPencet = isPencet;
    }

    public int getAngka() {
        return angka;
    }

    public void setAngka(int angka) {
        this.angka = angka;
    }

    public MineCell[][] getTetangga() {
        return tetangga;
    }

    public void setTetangga(MineCell[][] tetangga) {
        this.tetangga = tetangga;
    }
    public void print(){
        if(this.isPencet){
            System.out.print("`");
        }
        String tampil="-";
        if(this.angka>-1)tampil=this.angka+"";
        System.out.print(tampil);
    }
    
}
