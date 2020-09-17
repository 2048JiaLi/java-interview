package com.utils.heapq;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Li W
 * @version 1.0
 * @date 2020/6/9 14:04
 *
 * python heapq
 *
 * Heaps are arrays for which a[k] <= a[2*k+1] and a[k] <= a[2*k+2] for
 * all k, counting elements from 0.  For the sake of comparison,
 * non-existing elements are considered to be infinite.  The interesting
 * property of a heap is that a[0] is always its smallest element.
 *
 * Usage:
 *
 * List<T> heap = new LinkedList<>()            # creates an empty heap
 * heappush(heap, item)                         # pushes a new item on the heap
 * item = heappop(heap)                         # pops the smallest item from the heap
 * item = heap.get(0)                           # smallest item on the heap without popping it
 * heapify(x)                                   # transforms stack into a heap, in-place, in linear time
 * item = heapreplace(heap, item)               # pops and returns smallest item, and adds
 *                                              # new item; the heap size is unchanged
 */
public class heapq {
    public static<T extends Comparable> void heappush(List<T> heap, T item){
        heap.add(item);
        siftdown(heap,0,heap.size()-1);
    }

    public static<T extends Comparable> T heappop(List<T> heap){
        T lastElement = heap.get(heap.size()-1);
        heap.remove(heap.size()-1);
        if (!heap.isEmpty()){
            T returnItem = heap.get(0);
            heap.set(0,lastElement);
            siftup(heap,0);
            return returnItem;
        }
        return lastElement;
    }

    public static<T extends Comparable> T heapreplace(List<T> heap, T item){
        /**Pop and return the current smallest value, and add the new item.
         *
         * This is more efficient than heappop() followed by heappush(), and can be
         * more appropriate when using a fixed-size heap.  Note that the value
         * returned may be larger than item!  That constrains reasonable uses of
         * this routine unless written as part of a conditional replacement:
         *
         *     if item > heap[0]:
         *         item = heapreplace(heap, item)
         */
        T returnItem = heap.get(0);
        heap.set(0,item);
        siftup(heap,0);
        return returnItem;
    }

    public static<T extends Comparable> T heappushpop(List<T> heap, T item){
        // Fast version of a heappush followed by a heappop.
        if (!heap.isEmpty() && heap.get(0).compareTo(item)<0){
            T tmp = item;
            item = heap.get(0);
            heap.set(0,tmp);
            siftup(heap,0);
        }
        return item;
    }

    public static<T extends Comparable> void heapify(List<T> heap){
        /**Transform stack into a heap, in-place, in O(len(x)) time.
         *
         * Transform bottom-up.  The largest index there's any point to looking at
         * is the largest with a child index in-range, so must have 2*i + 1 < n,
         * or i < (n-1)/2.  If n is even = 2*j, this is (2*j-1)/2 = j-1/2 so
         * j-1 is the largest, which is n//2 - 1.  If n is odd = 2*j+1, this is
         * (2*j+1-1)/2 = j so j-1 is the largest, and that's again n//2-1.
         */
        int i = heap.size() / 2;
        while (i>=0){
            siftup(heap,i--);
        }
    }

    /**
     * @param heap is a heap at all indices >= startpos, except possibly for pos.
     * @param startpos
     * @param pos is the index of a leaf with a possibly out-of-order value.
     */
    private static<T extends Comparable> void siftdown(List<T> heap, int startpos, int pos){
        T newitem = heap.get(pos);

        // Follow the path to the root, moving parents down until finding a place
        // newitem fits.
        while (pos > startpos){
            int parentpos = (pos-1) >> 1;
            T parent = heap.get(parentpos);
            // newitem < parent
            if (newitem.compareTo(parent) < 0){
                heap.set(pos,parent);
                pos = parentpos;
                continue;
            }
            break;
        }
        heap.set(pos,newitem);
    }

    private static<T extends Comparable> void siftup(List<T> heap, int pos){
        int endpos = heap.size();
        int startpos = pos;
        T newItem = heap.get(pos);
        // Bubble up the smaller child until hitting a leaf.
        int childpos = 2*pos + 1;
        while (childpos < endpos){
            //  Set childpos to index of smaller child.
            int rightpos = childpos + 1;
            // heap.get(childpos) >= heap.get(rightpos)
            if (rightpos < endpos && heap.get(childpos).compareTo(heap.get(rightpos)) >= 0){
                childpos = rightpos;
            }
            // # Move the smaller child up.
            heap.set(pos,heap.get(childpos));
            pos = childpos;
            childpos = 2*pos + 1;
        }
        // The leaf at pos is empty now.  Put newitem there, and bubble it up
        // to its final resting place (by sifting its parents down).
        heap.set(pos,newItem);
        siftdown(heap,startpos,pos);
    }
}
