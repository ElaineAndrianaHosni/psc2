/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;

/**
 *
 * @author ELAINE
 */
//class untuk mutasi dan mencari fitness dari kromosom
public class Individual implements Comparable<Individual>{
    private boolean[][] chromosome;
    private int fitnes;
    private char[][] target;
    
    //setter dan getter dan constructor
    public Individual(boolean[][] chromosome, char[][] target) {
        this.chromosome = chromosome;
        this.target = target;
        //buat langsung itung fitness per individu
        this.fitnes=callFitnes();
    }
    //method persilangan antara 2 individu menjadi individu baru
    public Individual mate(Individual parent2) {
        //bikin chromosome baru, kromosom ini dibuat dari persilangan individu ini sama individu kedua 
        boolean[][] child_chromosome = new boolean[this.chromosome.length][this.chromosome[0].length];
        for (int i = 0; i < child_chromosome.length; i++) {
            for (int j = 0; j < child_chromosome[i].length; j++) {
                Random r = new Random();
                //random 100 angka spy itungannya 100%
                int rand = r.nextInt(100);
                // jika hasil random kurang dari 45 mewarisi chromosome parent1
                if (rand < 45)
                    child_chromosome[i][j] = this.chromosome[i][j];
                // jika hasil random berada antara 45 dan 90 mewaeisi chromosome parent 2
                else if (rand < 90)
                    child_chromosome[i][j] = parent2.getChromosome()[i][j];
                else
                    // jika tidak kedanya chromosome bermutasi
                    child_chromosome[i][j] = r.nextBoolean();

            }
        }
        return new Individual( child_chromosome,this.target);
    }

    public boolean[][] getChromosome() {
        return chromosome;
    }

    public void setChromosome(boolean[][] chromosome) {
        this.chromosome = chromosome;
    }

    public int getFitnes() {
        return fitnes;
    }

    public void setFitnes(int fitnes) {
        this.fitnes = fitnes;
    }

    public char[][] getTarget() {
        return target;
    }

    public void setTarget(char[][] target) {
        this.target = target;
    }
    private boolean cekFitnes(int i, int j) {
        // ambil target parse ke int
        int target_hasil = Integer.parseInt(this.target[i][j] + "");
        // inisialisasi cromosome yang sedang di cek
        int real_hasil = 0;
        // cek cromosome
        // cek titik cromosome
        if (this.chromosome[i][j]) {
            // jika chromosome ditekan maka menambahkan hasil cromosome satu
            real_hasil++;
        }
        // cek atas jika j>0
        if (j > 0) {
            if (this.chromosome[i][j - 1]) {
                // jika chromosome ditekan maka menambahkan hasil cromosome satu
                real_hasil++;
            }
        }
        // cek bawah jika j<target.length-1
        if (j < this.target[i].length - 1) {
            if (this.chromosome[i][j + 1]) {
                // jika chromosome ditekan maka menambahkan hasil cromosome satu
                real_hasil++;
            }
        }
        // cek kanan kiri
        if (i > 0) {
            // cek cromosome kiri jika kromosome tidak berada di i>0
            if (this.chromosome[i - 1][j]) {
                // jika chromosome ditekan maka menambahkan hasil cromosome satu
                real_hasil++;
            }
            // cek diagonal kiri atas jika j>0
            if (j > 0) {
                if (this.chromosome[i - 1][j - 1]) {
                    // jika chromosome ditekan maka menambahkan hasil cromosome satu
                    real_hasil++;
                }
            }
            // cek diagonal kiri bawah jika j<target.length-1
            if (j < this.target[i].length - 1) {
                if (this.chromosome[i - 1][j + 1]) {
                    // jika chromosome ditekan maka menambahkan hasil cromosome satu
                    real_hasil++;
                }
            }
        }
        if (i < this.target.length - 1) {
            // cek cromosome kanan jika kromosome tidak berada di i<this.target.length -1
            if (this.chromosome[i + 1][j]) {
                // jika chromosome ditekan maka menambahkan hasil cromosome satu
                real_hasil++;
            }
            // cek diagonal kanan atas jika j>0
            if (j > 0) {
                if (this.chromosome[i + 1][j - 1]) {
                    // jika chromosome ditekan maka menambahkan hasil cromosome satu
                    real_hasil++;
                }
            }
            // cek diagonal kanan bawah jika j<target.length-1
            if (j < this.target[i].length - 1) {
                if (this.chromosome[i + 1][j + 1]) {
                    // jika chromosome ditekan maka menambahkan hasil cromosome satu
                    real_hasil++;
                }
            }
        }
        return real_hasil == target_hasil;
    }
    private int callFitnes() {
        //inisialisasi fitness
        int fitnes = 0;
        // looping sebanyak target
        for (int i = 0; i < this.target.length; i++) {
            for (int j = 0; j < this.target[i].length; j++) {
                // kalau isi char ada isinya
                if (this.target[i][j] != ' ') {
                    //cek fitness kalau cell yang ditekan tidak sesuai dengan target maka fitness akan ditambahkan
                    if (!this.cekFitnes(i, j))
                        fitnes++;

                }
            }

        }
        return fitnes;
    }
    
    //method comapreTo untuk sorting fitness populasi
    @Override
    public int compareTo(Individual o) {
        //individu akan dianggap lebih besar jika fitness lebih besar
        return this.getFitnes()-o.getFitnes();
    }
    //menampilkan objek individu 
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < chromosome.length; i++) {
            for (int j = 0; j < chromosome[i].length; j++) {
                //jika di klik
                if (chromosome[i][j])
                    //tampilan akan memiliki _ di depan angka
                    res += '_';
                else
                    //jika tidak diklik 
                    res += ' ';
                //nampilin soal
                res += this.target[i][j];
                //cek target kosong ga
                if (this.target[i][j] != ' ') {
                    //cek fitness, kalau ga kosong
                    if (!this.cekFitnes(i, j))
                      // kalau salah tampilin * di belakang angka
                        res += '*';
                    else
                        // kalau benar tampilkan spasi
                        res += ' ';
                } else {
                    //kalau target kosong tampilin spasi
                    res += ' ';
                }
                //tab antar cell 
                res += '\t';
            }
            //pindah baris
            res += '\n';
        }
        return res;
    }
    

}
