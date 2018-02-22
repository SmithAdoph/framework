package com.adoph.framework.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序工具类
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/2/12
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
     * @return int[] 排序后数组
     */
    public static int[] bubbleSort(int[] arr) {
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
        return arr;
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
     * @return int[] 排序后的数组
     */
    public static int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     *
     * @param arr  需要排序的数组
     * @param low  低位
     * @param high 高位
     * @return int[] 排序后的数组
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int middle = getMiddle(arr, low, high);
            quickSort(arr, low, middle - 1);
            quickSort(arr, middle + 1, high);
        }
        return arr;
    }

    /**
     * 选择排序：
     * 默认数组第一个元素为最小值，依次与后面其他元素比较，如果比其他元素大就交换位置
     * 下次循环从第二个元素开始比较，如此循环到倒数第二个数和最后一个数比较为止
     *
     * @param arr 待排序数组
     * @return int[] 排序后的数组
     */
    public static int[] selectSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i <= len - 2; i++) {
            for (int j = i + 1; j <= len - 1; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
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
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }

    public static void main(String[] args) {
//        int arr[] = {5, 19, 8, 3, 16};
        int arr[] = SortUtils.randomArray(1000);
        System.out.println("--排序前：");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序开始时间：" + System.currentTimeMillis());
//        SortUtils.bubbleSort(arr);
//        SortUtils.quickSort(arr);
        SortUtils.selectSort(arr);
        System.out.println("排序结束时间：" + System.currentTimeMillis());
        System.out.println("--排序后：");
        System.out.println(Arrays.toString(arr));
    }

}
