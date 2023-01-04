/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.*;

/**
 *
 * @author ELAINE
 */
public class Minesweeper {
    private Random r ;

    public Minesweeper(long seed) {
        r = new Random();
        r.setSeed(seed);
    }
    
    /**
     * @param args the command line arguments
     */
    //method untuk hardcode soal 
    //angka disesuaikan dengan contoh mozaic puzzle
    private static final char[][] target = { { ' ', '4', '3', ' ', ' ' },
            { ' ', ' ', ' ', '6', ' ' },
            { '5', ' ', '5', ' ', '5' },
            { ' ', ' ', '2', ' ', ' ' },
            { '1', ' ', '0', '2', ' ' } };
    //asumsi terdapat 100 populasi 
     private static final int POPULATION_SIZE = 100;
    //random isi kromosom 
     //kromoson = seluruh puzzle
     //alel = isi sell (kotak yang dipencet/tidak)
    private boolean mutated_genes() {
        //untuk mengeluarkan true/false secara random
        return r.nextBoolean();
    }
    //method untuk men-generate 1 kromosom 
    private boolean[][] create_genome() {
        //inisialisasi matriks kromosom 
        boolean[][] res = new boolean[target.length][target[0].length];
        //looping sebanyak allel 
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                    // isi alel 
                res[i][j] = mutated_genes();
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Minesweeper ms= new Minesweeper(0);
       // inisialisasi counter generasi
        int generation = 0;
        // inisialisasi populasi
        List<Individual> population = new ArrayList<Individual>();
        // inisialisasi flag solusi blm ditemukan
        boolean found = false;
        // inisialisasi populasi awal
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new Individual( ms.create_genome(),target,ms.r));
        }
        // selama blm ditemukan
        while (!found) {
            // melakukan sorting sesuai fitnes
            Collections.sort(population);
            // jika individu awal mempunyai fitnes lebih kecil atau sama dengan 0 maka
            // keluar dari loop karna sudah mendapatkan solusi
            if (population.get(0).getFitnes() <= 0) {
                found = true;
                break;
            }
            // jika tidak membuat generasi baru
            List<Individual> new_generation = new ArrayList<>();
            // memasukan 10% (yang bagus) dari populasi lama dengan fitnes terendah kedalam generasi baru
            int s = (10 * POPULATION_SIZE) / 100;
            for (int i = 0; i < s; i++) {
                new_generation.add(population.get(i));
            }
            // melakukan persilangan dan mutasi pada 50 individu dengan fitnes yang kecil
            // untuk sisa populasi generasi baru
            s = POPULATION_SIZE - s;
            for (int i = 0; i < s; i++) {
//                Random rand = new Random();
//          
//                rand.setSeed(2000);
                // melakukan random angka untuk persilangan antara 50 individu terbaik
                int randomInt = ms.r.nextInt(50);
                // mengambil orang tua pertama
                Individual parent1 = population.get(randomInt);
                randomInt = ms.r.nextInt(50);
                Individual parent2 = population.get(randomInt);
                // melakukan persilangan untuk menghasilkan generasi baru
                new_generation.add(parent1.mate(parent2));
            }
            // memasukan generasi baru kedalam populasi
            population = new_generation;
            generation++;
            // melakukan print generasi
            System.out.println("Generasi: " + generation);
            System.out.println(population.get(0).toString());
            System.out.println("Fitnes: " + population.get(0).getFitnes());
        }
        generation++;
        // melakukan print generasi
        System.out.println("Generasi: " + generation);
        System.out.println(population.get(0).toString());
        System.out.println("Fitnes: " + population.get(0).getFitnes());
    }
    
}
