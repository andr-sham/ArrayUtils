

public class Main {
    public static void main(String[] args) {

/*        int numberOfLines = 1 + (int) (Math.random() * 10);
        int numberOfColumns = 1 + (int) (Math.random() * 10);
        int[][] arrayWithData = new int[numberOfLines][numberOfColumns];
        for (int i = 0; i < numberOfLines; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                arrayWithData[i][j] = - 100 + (int) (Math.random() * 200);
            }
        }*/

        int[][] arrayWithData = new int[][]{{33, 5, 0}, {1, -9, -13}, {6, -72, -9}, {3, -21, 0}, {3, 16, 5}, {9, -24, 5}};

        ArrayUtils arrayUtils = new ArrayUtils();

        arrayUtils.print(arrayWithData);

        /*
        Сортировка по возрастанию
         */

        int[][] arraySorted = arrayUtils.sort(arrayWithData);
        System.out.println();
        System.out.println("Отсортированный по возрастанию массив");
        arrayUtils.print(arraySorted);

        /*
        разворот массива на 90 градусов
         */


        int[][] arrayRotatedRight = arrayUtils.rotateRight(arrayWithData);
        System.out.println();
        System.out.println("Массив, развёрнутый на 90 градусов направо");
        arrayUtils.print(arrayRotatedRight);

        /*
        зеркальное отображение массива по вертикали
         */

        int[][] arrayFlippedVertically = arrayUtils.flipVertically(arrayWithData);
        System.out.println();
        System.out.println("Массив, зеркально отображённый по вертикали");
        arrayUtils.print(arrayFlippedVertically);

        /*
        зеркальное отображение массива по горизонтали
         */

        int[][] arrayFlippedHorizontally = arrayUtils.flipHorizontally(arrayWithData);
        System.out.println();
        System.out.println("Массив, зеркально отображённый по горизонтали");
        arrayUtils.print(arrayFlippedHorizontally);

    }
}
