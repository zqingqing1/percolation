import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.*;
import java.io.*;

public class Percolation {
	private boolean[][] arr;
	private int top=0;
	private int bottom;
	private WeightedQuickUnionUF que;
	private int s;
	
	
	public Percolation(int N){
		s=N;
		bottom=s*s+1;
		arr=new boolean[s][s];
		que=new WeightedQuickUnionUF(s*s+2); //include 2 points
	}
	public int getIndex(int i,int j){
		return i*s-(s-j);
	}
	public void open(int i, int j){
		arr[i-1][j-1]=true;
		if(i==1) que.union(top,getIndex(i,j));
		if(i==s) que.union(bottom,getIndex(i,j));
		
		if(j>1&&isOpen(i,j-1)) que.union(getIndex(i,j),getIndex(i,j-1));
		if(j<s&&isOpen(i,j+1)) que.union(getIndex(i,j),getIndex(i,j+1));
		if(i>1&&isOpen(i-1,j)) que.union(getIndex(i,j),getIndex(i-1,j));
		if(i<s&&isOpen(i+1,j)) que.union(getIndex(i,j),getIndex(i+1,j));

	}
	public boolean isOpen(int i, int j){
		return arr[i-1][j-1];
	}
	public boolean isFull(int i, int j){
		if(i>0&&i<=s&&j>0&&j<=s){
			return que.connected(top,getIndex(i,j));
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}
	public boolean percolates(){
		return que.connected(top,bottom);
	}	
}
