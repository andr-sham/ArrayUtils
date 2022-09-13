import java.util.Arrays;

/**
 * Утилиты для работы с массивами
 */
public class ArrayUtils {

    /**
     * Метод print принимает на вход двумерный массив любого размера,
     * заполненного целыми числами и выводит его в консоль в виде таблицы
     *
     * @param arrayToPrint передаваемый массив для вывода в консоль
     */
    public void print(int[][] arrayToPrint) {
        for (int[] ints : arrayToPrint) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Метод sortOneDimensionalArray сортирует одномерный массив по возрастанию слиянием
     * с помощью рекурсии
     *
     * @param nums исходный массив
     * @return отсортированный массив
     */
    private static int[] sortOneDimensionalArray(int[] nums) {
        if (nums.length != 1) {   // выход из рекурсии если размер массива равен 1

            /*
            разбиваем массив на 2 массива примерно пополам
             */
            int[] leftArray;
            int[] rightArray;
            leftArray = Arrays.copyOfRange(nums, 0, nums.length / 2);
            rightArray = Arrays.copyOfRange(nums, nums.length / 2, nums.length);

            /*
            рекурсивно сортируем оба массива
             */
            leftArray = sortOneDimensionalArray(leftArray);
            rightArray = sortOneDimensionalArray(rightArray);

            /*
            выполняем слияние отсортированных массивов обратно в один
             */
            nums = merge(leftArray, rightArray);
        }
        return nums;
    }

    /**
     * Метод sort сортирует 2-х мерный массив
     *
     * @param nums исходный массив для сортировки
     * @return отсортированный массив
     */
    public int[][] sort(int[][] nums) {

        /*
         * превращаем 2-х мерный массив в одномерный и сортируем его по возрастанию
         */

        int[] oneLineArray;
        oneLineArray = sortOneDimensionalArray(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            nums[i] = sortOneDimensionalArray(nums[i]);  // сортировка построчно
            oneLineArray = merge(oneLineArray, nums[i]); // слияние в отсортированных строк в одну
        }

        /*
        разделение одномерного массива обратно по строкам
         */

        int countOfFinishedElem = 0;
        for (int[] num : nums) {
            System.arraycopy(oneLineArray, countOfFinishedElem, num, 0, num.length);
            countOfFinishedElem += num.length;
        }

        return nums;
    }

    /**
     * метод слияния отсортированных по возрастанию массивов
     *
     * @param leftArray  левая часть массива
     * @param rightArray правая часть массива
     * @return возврат единого отсортированного массива
     */

    private static int[] merge(int[] leftArray, int[] rightArray) {

        /*
        счётчики для подмассивов
         */
        int countLeft = 0;
        int countRight = 0;

        /*
        выполняем слияние правого и левого массивов поэлементно, пока один из них не закончится
         */

        int[] mergedArray = new int[rightArray.length + leftArray.length];  //объявляем итоговый массив
        do {
            if (leftArray[countLeft] <= rightArray[countRight]) {               // если элемент левого массива <= правому
                mergedArray[countLeft + countRight] = leftArray[countLeft++];  // дописываем его в итоговый массив
            } else {
                mergedArray[countLeft + countRight] = rightArray[countRight++]; // иначе дописываем в итоговый массив элемент правого массива
            }
        } while (countLeft < leftArray.length && countRight < rightArray.length);  // выполняем до тех пор, пока один из массивов не закончится

        /*
        дописываем в конец итогового массива остаток одного из подмассивов
         */

        if (rightArray.length - countRight > leftArray.length - countLeft) {    // если закончился левый массив, а в правом ещё остались элементы
            System.arraycopy(rightArray, countRight, mergedArray, countLeft + countRight, rightArray.length - countRight); // дописываем оставшиеся элементы правого массива в итоговый массив
        } else {
            System.arraycopy(leftArray, countLeft, mergedArray, countLeft + countRight, leftArray.length - countLeft);  // и наоборот
        }
        return mergedArray;
    }


    /**
     * Метод rotateRight принимает на вход двумерный массив любого размера и
     * возвращает на выход массив, который повернут один раз по часовой стрелке на 90 градусов.
     *
     * @param arrayToRotate массив для поворота
     * @return возврат повернутого массива
     */
    public int[][] rotateRight(int[][] arrayToRotate) {
        int[][] rotatedArray = new int[arrayToRotate[0].length][arrayToRotate.length];
        for (int i = 0; i < rotatedArray.length; i++) {
            for (int j = 0; j < rotatedArray[i].length; j++) {
                rotatedArray[i][j] = arrayToRotate[arrayToRotate.length - j - 1][i];
            }
        }
        return rotatedArray;
    }



    /**
     * Метод flipHorizontally возвращает на выход массив, значения которого зеркально отражены по горизонтали.
     * @param arrayToFlip массив для отражения по горизонтали
     * @return зеркально отраженный массив по горизонтали
     */
    public int[][] flipHorizontally(int[][] arrayToFlip) {
        for (int i = 0; i < arrayToFlip.length / 2; i++) {
            for (int j = 0; j < arrayToFlip[i].length; j++) {
                swap(arrayToFlip, i, j, arrayToFlip.length - i - 1, j);
            }
        }
        return arrayToFlip;
    }

    /**
     * Метод flipVertically возвращает на выход массив, значения которого зеркально отражены по вертикали.
     * @param arrayToFlip массив для отражения по вертикали
     * @return зеркально отраженный массив по вертикали
     */

    public int[][] flipVertically(int[][] arrayToFlip) {
        for (int i = 0; i < arrayToFlip.length; i++) {
            for (int j = 0; j < arrayToFlip[i].length / 2; j++) {
                swap(arrayToFlip, i, j, i, arrayToFlip[i].length - j - 1);
            }
        }
        return arrayToFlip;
    }

    /**
     * метод swap меняет местами 2 элемента в массиве
     * @param arrayToSwap исходный массив, содержащий 2 элемента, которые необходимо поменять местами
     * @param i номер строки 1-ого элемента
     * @param j номер столбца 1-ого элемента
     * @param i1 номер строки 2-ого элемента
     * @param j1 номер стобца 2-ого элемента
     */
    private static int[][] swap(int[][] arrayToSwap, int i, int j, int i1, int j1) {
        arrayToSwap[i][j] += arrayToSwap[i1][j1];
        arrayToSwap[i1][j1] = arrayToSwap[i][j] - arrayToSwap[i1][j1];
        arrayToSwap[i][j] -= arrayToSwap[i1][j1];
        return arrayToSwap;
    }

}




