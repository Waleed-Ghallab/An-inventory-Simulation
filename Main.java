package com.company;
import java.util.Random;
import java.util.Scanner;



public class Main {
    private static double getRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return  r.nextInt(((max - min) + 1)) + min;
    }

    public static void main(String[] args) {
	Scanner scanner= new Scanner(System.in);
	int order=1; //number of orders either 1 or 2
	int sold=0; //amount sold
	int AvPCs=0; //Available PCs
 	float notSatCust=0; //not satisfied customers
	float[] profit=new float[500];
	double[] r1=new double[500]; //Array of generated random numbers for each week
	int[] demand= new int[500]; //Amount of Demand in each week

	for (;order<=2;order++){
	    float sum=0; //sum of weekly profit
	    float avg=0; //average weekly profit

	    for(int wk=1;wk<=499;wk++){
            r1 [wk]= (double) getRandomNumber(0, 1);
            if (r1[wk]>0 && r1[wk]<=0.2){
                demand[wk]=0;
            }
            else if (r1[wk]>0.2 && r1[wk]<=0.6){
                demand[wk]=1;
            }
            else if (r1[wk]>0.6 && r1[wk]<=0.8){
                demand[wk]=2;
            }
            else if (r1[wk]>0.8 && r1[wk]<=0.9){
                demand[wk]=3;
            }
            else if (r1[wk]>0.9 && r1[wk]<=1.0){
                demand[wk]=4;
            }
            AvPCs+=order; // available PCs are the already available ones from previous weeks plus the new ordered ones
            sold= Math.min(demand[wk],AvPCs); //sold PCs are the minimum of both demand and available PCs
            profit[wk]=sold*450; //450$ profit for each sold PC
            notSatCust=demand[wk]-sold; //not satisfied customers are the lack between demand and sold
            profit[wk]=profit[wk]-notSatCust; //update profit if there is any penalties
            AvPCs-=sold; //update available PCs after selling
            profit[wk]-=AvPCs*50; //update profit after storing the remaining PCs if any
            sum+=profit[wk];
	    }
	    avg=sum/profit.length;
	    System.out.println("Average weekly profit if Order "+order+" PC = "+avg);
        }
    }
}