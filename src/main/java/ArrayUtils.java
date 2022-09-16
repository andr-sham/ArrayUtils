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
            print(ints);
            System.out.println();
        }
    }

    /**
     * Метод print принимает на вход одномерный массив любого размера,
     * заполненного целыми числами и выводит его в консоль в виде таблицы
     *
     * @param arrayToPrint передаваемый массив для вывода в консоль
     */

    public void print(int[] arrayToPrint) {
        for (int ints : arrayToPrint) {
            System.out.print(ints + "\t");
        }
    }

    /**
     * Метод sortOneDimensionalArray сортирует одномерный массив по возрастанию слиянием
     * с помощью рекурсии
     *
     * @param nums исходный массив
     * @return отсортированный массив
     */
    public int[] sortOneDimensionalArray(int[] nums) {
        if (nums.length != 1) {   // выход из рекурсии если размер массива равен 1

            /*
            разбиваем массив на 2 массива примерно пополам
             */

            int[] leftArray = arrayCopyOfRange(nums, 0, nums.length / 2);
            int[] rightArray = arrayCopyOfRange(nums, nums.length / 2, nums.length);

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

        int[][] sortedArray = arrayCopy(nums);
        int[] oneLineArray = sortOneDimensionalArray(sortedArray[0]);
        for (int i = 1; i < sortedArray.length; i++) {
            sortedArray[i] = sortOneDimensionalArray(sortedArray[i]);  // сортировка построчно
            oneLineArray = merge(oneLineArray, sortedArray[i]); // слияние в отсортированных строк в одну
        }

        /*
        разделение одномерного массива обратно по строкам
         */

        int countOfFinishedElem = 0;
        for (int[] num : sortedArray) {
            arrayCopyOfRangeFromTo(oneLineArray, countOfFinishedElem, num, 0, num.length);
            countOfFinishedElem += num.length;
        }

        return sortedArray;
    }

    /**
     * метод слияния отсортированных по возрастанию массивов
     *
     * @param leftArray  левая часть массива
     * @param rightArray правая часть массива
     * @return возврат единого отсортированного массива
     */

    private int[] merge(int[] leftArray, int[] rightArray) {

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
            arrayCopyOfRangeFromTo(rightArray, countRight, mergedArray, countLeft + countRight, rightArray.length - countRight); // дописываем оставшиеся элементы правого массива в итоговый массив
        } else {
            arrayCopyOfRangeFromTo(leftArray, countLeft, mergedArray, countLeft + countRight, leftArray.length - countLeft);  // и наоборот
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
     *
     * @param arrayToFlip массив для отражения по горизонтали
     * @return зеркально отраженный массив по горизонтали
     */
    public int[][] flipHorizontally(int[][] arrayToFlip) {
        int[][] flippedArray = arrayCopy(arrayToFlip);
        for (int i = 0; i < flippedArray.length / 2; i++) {
            for (int j = 0; j < flippedArray[i].length; j++) {
                swap(flippedArray, i, j, flippedArray.length - i - 1, j);
            }
        }
        return flippedArray;
    }

    /**
     * Метод flipVertically возвращает на выход массив, значения которого зеркально отражены по вертикали.
     *
     * @param arrayToFlip массив для отражения по вертикали
     * @return зеркально отраженный массив по вертикали
     */

    public int[][] flipVertically(int[][] arrayToFlip) {
        int[][] flippedArray = arrayCopy(arrayToFlip);
        for (int i = 0; i < flippedArray.length; i++) {
            for (int j = 0; j < flippedArray[i].length / 2; j++) {
                swap(flippedArray, i, j, i, flippedArray[i].length - j - 1);
            }
        }
        return flippedArray;
    }

    /**
     * метод swap меняет местами 2 элемента в массиве
     *
     * @param arrayToSwap исходный массив, содержащий 2 элемента, которые необходимо поменять местами
     * @param i           номер строки 1-ого элемента
     * @param j           номер столбца 1-ого элемента
     * @param i1          номер строки 2-ого элемента
     * @param j1          номер стобца 2-ого элемента
     */
    private void swap(int[][] arrayToSwap, int i, int j, int i1, int j1) {
        arrayToSwap[i][j] += arrayToSwap[i1][j1];
        arrayToSwap[i1][j1] = arrayToSwap[i][j] - arrayToSwap[i1][j1];
        arrayToSwap[i][j] -= arrayToSwap[i1][j1];
    }

    /**
     * Метод arrayCopy копирует массив
     *
     * @param arrayForCopy копируемый массив
     * @return скопированный массив
     */
    private int[][] arrayCopy(int[][] arrayForCopy) {
        int[][] copiedArray = new int[arrayForCopy.length][arrayForCopy[0].length];
        for (int i = 0; i < arrayForCopy.length; i++) {
            for (int j = 0; j < arrayForCopy[i].length; j++) {
                copiedArray[i][j] = arrayForCopy[i][j];
            }
        }
        return copiedArray;
    }

    /**
     * Метод arrayCopyOfRange копирует часть массива в новый массив
     *
     * @param originalArray исходный массив
     * @param fromIndex     с какого элемента копируем (включительно)
     * @param toIndex       по какой элемент копируем (исключительно)
     * @return скипированная часть массива
     */

    private int[] arrayCopyOfRange(int[] originalArray, int fromIndex, int toIndex) {
        int[] copiedArray = new int[toIndex - fromIndex];
        for (int i = fromIndex, j = 0; i < toIndex; i++, j++) {
            copiedArray[j] = originalArray[i];
        }
        return copiedArray;
    }

    /**
     * Метод arrayCopyOfRangeFromTo копирует часть массива в другой массив начиная с заданной позиции
     *
     * @param originalArray исходный массив для копирования
     * @param srcStartIndex начальная позиция в исходном массиве originalArray, которой производим копирование
     * @param copiedArray   целевой массив, в который копируем данные
     * @param destIndex     начальная позиция целевого массива copiedArray
     * @param length        количество символов для копирования
     */

    private void arrayCopyOfRangeFromTo(int[] originalArray, int srcStartIndex, int[] copiedArray, int destIndex, int length) {
        for (int i = srcStartIndex, j = destIndex; i < srcStartIndex + length; i++, j++) {
            copiedArray[j] = originalArray[i];
        }
    }

}
