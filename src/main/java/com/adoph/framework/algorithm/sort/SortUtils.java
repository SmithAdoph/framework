package com.adoph.framework.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序工具类:
 * 1.冒泡排序效率最低，快排效率最高，插入相对稳定且效率不错
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/2/12
 */
public class SortUtils {

    /**
     * 冒泡排序:
     * 数组长度n，外循环变量i，内循环变量j
     * 由小到大，相邻两个元素比较，如果第一个比第二个大，交换位置
     * 每次外循环，将最大的元素排到最后
     * 内循环依次减少i次
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort(int[] arr) {
        long start = System.currentTimeMillis();
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("【冒泡排序】耗时(毫秒)：" + (System.currentTimeMillis() - start));
    }

    /**
     * 默认以数组第一个元素为中轴，获取他所在的数组中的位置
     *
     * @param arr  待排序的数组
     * @param low  开始位置
     * @param high 结束位置
     * @return 中轴所在位置
     */
    public static int getMiddle(int[] arr, int low, int high) {
        int temp = arr[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];//比中轴小的记录移到低端
//            System.out.println("1.比中轴小的记录移到低端:" + Arrays.toString(arr));
            while (low < high && arr[low] < temp) {
                low++;
            }
            arr[high] = arr[low]; //比中轴大的记录移到高端
//            System.out.println("2.比中轴大的记录移到高端:" + Arrays.toString(arr));
        }
        arr[low] = temp; //中轴记录到尾
//        System.out.println(low + ":" + high);
//        System.out.println(Arrays.toString(arr));
        return low; // 返回中轴的位置
    }

    /**
     * 快速排序：
     * 把第零个位置看做中轴，和最后一个比，如果比它小交换，比它大不做任何处理；
     * 交换了以后再和小的那端比，比它小不交换，比他大交换。
     * 这样循环往复，一趟排序完成，左边就是比中轴小的，右边就是比中轴大的，
     * 然后再用分治法，分别对这两个独立的数组进行排序
     *
     * @param arr 需要排序的数组
     */
    public static void quickSort(int[] arr) {
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        System.out.println("【快速排序】耗时(毫秒)：" + (System.currentTimeMillis() - start));
    }

    /**
     * 快速排序
     *
     * @param arr  需要排序的数组
     * @param low  低位
     * @param high 高位
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int middle = getMiddle(arr, low, high);
            quickSort(arr, low, middle - 1);
            quickSort(arr, middle + 1, high);
        }
    }

    /**
     * 选择排序：
     * 1.默认第一个元素为最小值
     * 2.如果从剩余的元素中找出更小的值，则和第一个位置的元素交换位置
     * 3.下次从第二个元素开始，重复步骤2
     *
     * @param arr 待排序数组
     */
    public static void selectSort(int[] arr) {
        long start = System.currentTimeMillis();
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int k = i;//最小值下标
            //每次选出一个最小值
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        System.out.println("【选择排序】耗时(毫秒)：" + (System.currentTimeMillis() - start));
    }

    /**
     * 插入排序:
     * 1.从第一个元素开始，该元素可以认为已经被排序
     * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5.将新元素插入到该位置后
     * 6.重复步骤2~5
     *
     * @param arr 待排序数组
     */
    public static void insertSort(int[] arr) {
        long start = System.currentTimeMillis();
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];//元素大的后移
            }
            if (j + 1 != i) {
                arr[j + 1] = temp;//元素小的前移
            }
        }
        System.out.println("【插入排序】耗时(毫秒)：" + (System.currentTimeMillis() - start));
    }

    /**
     * 希尔排序：
     * 1.插入排序的优化版本
     */
    public static void shellSort(int[] arr) {
        long start = System.currentTimeMillis();
        int gap = 1, i, j, len = arr.length;
        int temp;
        while (gap < len / 3) {
            gap = gap * 3 + 1; // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
        }
        for (; gap > 0; gap /= 3) {
            for (i = gap; i < len; i++) {
                temp = arr[i];
                for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap)
                    arr[j + gap] = arr[j];
                arr[j + gap] = temp;
            }
        }
        System.out.println("【希尔排序】耗时(毫秒)：" + (System.currentTimeMillis() - start));
    }

    private static void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        merge_sort_recursive(arr, result, start1, end1);
        merge_sort_recursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
    }

    /**
     * 归并排序
     *
     * @param arr 待排序数组
     */
    public static void mergeSort(int[] arr) {
        long start = System.currentTimeMillis();
        int len = arr.length;
        int[] result = new int[len];
        merge_sort_recursive(arr, result, 0, len - 1);
        System.out.println("【归并排序】耗时(毫秒)：" + (System.currentTimeMillis() - start));
    }

    /**
     * 随机整数数组
     *
     * @param length 自定义数组长度
     * @return int[]
     */
    public static int[] randomArray(int length) {
        int[] arr = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(1000000);
        }
        return arr;
    }

    /**
     * 二分查找:
     * 优点：每一次比较都使搜索范围缩小一半
     * 要求：数组必须是按照指定大小顺序排序
     * 1.将数组一分为二，中间位置的数跟待查找的数比较，得到下次待查找的范围
     * 2.重复步骤1，直至只剩最后一个数，如果没有找到返回-1，满足则返回该值的下标
     *
     * @param low  低位
     * @param high 高位
     * @param arr  待查找的数组
     * @param key  待查找的数
     * @return 返回被查找值索引位置
     */
    public static int binarySearch(int low, int high, int[] arr, int key) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (arr[mid] < key) {
            return binarySearch(mid + 1, high, arr, key);
        }
        if (arr[mid] > key) {
            return binarySearch(low, mid - 1, arr, key);
        }
        return mid;
    }

    public static void main(String[] args) {
        int len = 10000;//数组长度
        System.out.println("数组长度：" + len);
        int arr1[] = SortUtils.randomArray(len);
        int arr2[] = Arrays.copyOfRange(arr1, 0, arr1.length);
        int arr3[] = Arrays.copyOfRange(arr1, 0, arr1.length);
        int arr4[] = Arrays.copyOfRange(arr1, 0, arr1.length);
        int arr5[] = Arrays.copyOfRange(arr1, 0, arr1.length);
        int arr6[] = Arrays.copyOfRange(arr1, 0, arr1.length);
        System.out.println("--排序前：");
        System.out.println(Arrays.toString(arr6));
        SortUtils.bubbleSort(arr1);
        System.out.println("-----------------------");
        SortUtils.quickSort(arr2);
        System.out.println("-----------------------");
        SortUtils.selectSort(arr3);
        System.out.println("-----------------------");
        SortUtils.insertSort(arr4);
        System.out.println("-----------------------");
        SortUtils.shellSort(arr5);
        System.out.println("-----------------------");
        SortUtils.mergeSort(arr6);
        System.out.println("-----------------------");
        System.out.println("--排序后：");
        System.out.println(Arrays.toString(arr6));

//        int[] arr = {1, 3, 5, 7, 9, 100, 1023, 1178};
//        int key = 7;
//        long start = System.currentTimeMillis();
//        System.out.println("二分查找>>被查找数的位于数组下标为：" + binarySearch(0, arr.length - 1, arr, key));
//        System.out.println("查找耗时：" + (System.currentTimeMillis() - start));
    }

}
