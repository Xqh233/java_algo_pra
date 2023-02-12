package a_28_headsort;

import java.util.Arrays;


public class HeapSort {
    /*
     * BuildHeap
     * Heapify
     * InsertNode
     * RemoveHeadHead
     * Sort
     */
    // empty node can be null
    private Integer[] arr;
    // arr size
    private int n;
    /*
     * BuildHeap : withInpArr, createEmpty
     * > default to build maxHeap
     */
    public HeapSort() {
        this(10);
    }
    public HeapSort(int capacity) {
        arr = new Integer[capacity];
    }
    // default arr save data range is [1, n-1]
    public HeapSort(Integer[] arr) {
        // inplace : set value from ref
        this.arr = arr;
        for (Integer i : arr) {
            if (i != null) {
                n++;
            }
        }
        buildHeap();
    }
    private void buildHeap() {
        buildHeap(n);
    }
    private void buildHeap(int len) {
        for (int i = len / 2; i >= 1; i--) {
            // The last non-leaf node goes to the top of the heap
            heapify(i, len);
        }
    }

    // search the child node of cur node and find maxPos to swap
    private void heapify(int i, int n) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && arr[i] < arr[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && arr[maxPos] < arr[i * 2 + 1]) maxPos = i * 2 + 1;
            if (i == maxPos) break;
            swap(i, maxPos);
            i = maxPos;
        }
    }

    public boolean insert(Integer data) {
        if (n == arr.length) {
            return false;
        }

        arr[n] = data;
        n--;
        int i = n;
        // heapify to up
        while (i / 2 > 0 && arr[i/2] > arr[i]) {
            swap(i, i / 2);
            i = i / 2;
        }

        return true;
    }

    // heapify by up to down 
    public Integer removeHeapHead() {
        if (n == 0) {
            return null;
        }

        arr[1] = arr[n];
        n--;
        heapify(1, n);
        return null;
    }

    // sort as like remove, default asc
    public void sort() {
        sort(n);
    }
    public void sort(int len) {
        buildHeap(len);
        int k = len;
        // from last node, swap to heapHead, heapify
        while (k > 1) {
            swap(1, k);
            k--;
            heapify(1, k);
        }
    }

    private void swap(int i, int j) {
        Integer tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // test
    public static void main(String[] args) {
        Integer[] arr = {null, 7, 5, 19, 8, 4, 1, 20, 13, 16};
        HeapSort heap = new HeapSort(arr);

        // check inplace and maxHeap build result
        System.out.println(Arrays.toString(arr));

        arr = new Integer[] {null, 3, 6, 9, 1 ,5};
        heap = new HeapSort(arr);
        
        System.out.println(Arrays.toString(arr));
        // check range sort result, arr be an BST
        heap.sort(arr.length / 2 - 1);
        System.out.println(Arrays.toString(arr));
        
        heap.sort();
        System.out.println(Arrays.toString(arr));
    }


}
