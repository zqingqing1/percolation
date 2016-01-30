import java.io.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class PercolationStats {
	private Percolation per;
	private int expers;
	private int s;
	private double[] pro;
	
	public PercolationStats(int N,int T){
		s=N;
		expers=T;
		if(s<=0||expers<=0) throw new IllegalArgumentException();
		pro=new double[expers];
		for(int times=0;times<expers;times++){
			per=new Percolation(s);
			int opensites=0;
			while(!per.percolates()){
				int i=StdRandom.uniform(1,N+1);
				int j=StdRandom.uniform(1,N+1);
				if(!per.isOpen(i, j)){
					per.open(i, j);
					opensites++;
				}
			}
			pro[times]=(double)opensites/(s*s);
		}		
	}
	
	public double mean(){
		return StdStats.mean(pro);
	}
	public double stddev(){
		return StdStats.stddev(pro);
	}
	public double confidenceLo(){
		return mean()-(1.96*stddev())/Math.sqrt(expers);
	}
	public double confidenceHi(){
		return mean()+(1.96*stddev())/Math.sqrt(expers);
	}
	
	public static void main(String[] args){

		int N=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);
		Stopwatch watch=new Stopwatch();
		PercolationStats perstas=new PercolationStats(N,T);
		
		
		System.out.println("mean                    ="+perstas.mean());
		System.out.println("stddev                  ="+perstas.stddev());
		System.out.println("95% confidence interval ="+perstas.confidenceLo()+","+perstas.confidenceHi());
		System.out.println("Running time            ="+watch.elapsedTime());
	}
}
