/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minesweeper;

/**
 *
 * @author ELAINE
 */
// program to demonstrate the
// function java.util.Random.setSeed()

import java.util.*;
public class GFG {
	public static void main(String[] args)
	{

		// create random object
		Random r = new Random();

		// return the next pseudorandom integer value
		System.out.println("Random Integer value : "
						+ r.nextInt());

		// setting seed
		long s = 24;

		r.setSeed(s);

		// value after setting seed
		System.out.println("Random Integer value : " + r.nextInt());
	}
}
