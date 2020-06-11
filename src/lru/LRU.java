package lru;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Savvas Theofilou
 */
class LRU 
{ 
	// Method to find page faults using indexes 
	static int pageFaults(int pages[], int capacity) 
	{
            int totalPages=pages.length;
            int pageFaults=0;
            HashSet<Integer> setOfPages=new HashSet<>();
            HashMap<Integer, Integer> indexesOfPages=new HashMap<>();
            
            for (int i=0;i<totalPages;i++){
                if (setOfPages.size()<capacity){
                    if (setOfPages.contains(pages[i])){
                        //do nothing -> hit
                    }
                    else{
                        setOfPages.add(pages[i]);
                        pageFaults++;
                    }
                    indexesOfPages.put(pages[i], i);
                }
                else{
                    if (setOfPages.contains(pages[i])){
                        //do nothing -> hit
                    }
                    else{
                        Iterator<Integer> itr=setOfPages.iterator();
                        int minIndex=Integer.MAX_VALUE;
                        int minIndexPage=-1;
                        
                        while (itr.hasNext()){
                            int tempPage=itr.next();
                            if (indexesOfPages.get(tempPage)<minIndex){
                                minIndex=indexesOfPages.get(tempPage);
                                minIndexPage=tempPage;
                            }
                        }
                        setOfPages.remove(minIndexPage);
                        setOfPages.add(pages[i]);
                        pageFaults++;
                    }
                    indexesOfPages.put(pages[i], i);
                }
            }
            return pageFaults;
	} 
	
	// Driver Method to test your algorithm with a simple example
	public static void main(String args[]) 
	{
		/*
		 * This is an array that holds the reference string for all
		 * page requests.
		 */
		int pages[] = {5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2}; 
		
		// This is the number of available page frames
		int memoryCapacity = 3; 
		
		int faults = pageFaults(pages, memoryCapacity);
		System.out.println(faults);
	} 
} 
