//Variant 3. LAB 1
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    //Ex 1 (3) Створіть метод, який дозволяє вставляти в будь яку позицію масиву будь яке число. Метод повинен повертати новий масив.
    public static  int[] addElem(int[] array, int index, int value){
        if(index > array.length || index < 0){
            throw new IndexOutOfBoundsException("Index out of range!");
        }
        int[] new_array = new int[0];
        if(array != null) {
            new_array = new int[array.length + 1];
            for(int i = 0; i < new_array.length; ++i){
                if(i > index){
                    new_array[i] = array[i - 1];
                }
                else if(i == index) {
                    new_array[i] = value;
                }
                else{
                    new_array[i] = array[i];
                }
            }
        }
        return new_array;
    }
    //Ex 2 (6) Створіть метод, який дозволяє порівняти 2 будь яких масиви int[] по складу. Масиви можуть відрізнятися по порядку елементів ,
    // но бути однаковими зі складу. Приклад- [1,4,7]==[4,7,1- еквівалентні.]
    public static boolean compareContent(int[] arr_one, int[] arr_two) {
        if(arr_one.length != arr_two.length){
            return false;
        }
        else if(sumElem(arr_one) !=sumElem(arr_two)){
            return false;
        }

        boolean isTrue = true;
        for (int first : arr_one) {
            if(isTrue) {
                isTrue = false;
                for (int second : arr_two) {
                    if(first == second){
                        isTrue = true;
                        break;
                    }
                }
            }
            else{
                return false;
            }
        }
        return true;
    }
    public static int sumElem(int[] array){
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
    //Ex 3 (7) Створіть метод , який може перевернути будь яку число int . Приклад - 357 на вході, метод поверне 753
    public static String turnOver(int value){
        StringBuffer buf = new StringBuffer(Integer.toString(value));
        return buf.reverse().toString();
    }
    //Ex 4 (10) Створіть метод, який дозволяє поєднати між собою будь яку кількість масивів int[] та повернути результуючий масив з методу
    public static int[] concatArrays(int[] ... arrays){
        int length = 0;
        for (int[] arr : arrays) {
            length += arr.length;
        }

        int[] super_array = new int[length];
        int index = 0;

        for(int i = 0; i < arrays.length; i++){
            for(int j = 0; j < arrays[i].length; j++){
                super_array[index] = arrays[i][j];
                index++;
            }
        }
        return super_array;
    }
    //Ex 5 (12) Створіть метод, який сортує будь який масив int[] методом вставок.
    public static int[] insertionSort(int[] array){
        for(int item = 0; item < array.length; item ++){
            int value = array[item];
            int i = item - 1;
            for(; i >= 0; --i){
                if(value < array[i]){
                    array[i + 1] = array[i];
                }
                else{
                    break;
                }
            }
            array[i + 1] = value;
        }
        return array;
    }
/////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Exercise 1 (3)");
        System.out.println("Enter length array:");

        int length = input.nextInt();
        int[] array = new int[length];
        System.out.println("Enter array elements:");
        for(int i = 0; i< array.length; ++i){
            array[i] = input.nextInt();
        }

        System.out.println("Enter the index for the new item:");
        int index = input.nextInt();
        System.out.println("Enter the value for the new item:");
        int value = input.nextByte();

        try {
            int[] new_array = addElem(array, index, value);
            System.out.println("Array after adding an item: ");
            for (int item: new_array) {
                System.out.print(item + " ");
            }
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
        }
        /////////////////////////////////////////////////
        System.out.println("Exercise 2 (6)");
        System.out.println("Enter the length of the first array:");
        int first_length = input.nextInt();

        int[] arr_first = new int[first_length];
        System.out.println("Enter first array:");
        for (int i = 0; i < first_length; i++) {
            arr_first[i] = input.nextInt();
        }

        System.out.println("Enter the length of the second array:");
        int second_length = input.nextInt();

        int[] arr_second = new int[second_length];
        System.out.println("Enter first array:");
        for (int i = 0; i < second_length; i++) {
            arr_second[i] = input.nextInt();
        }

        System.out.print("Arrays are equivalent: ");
        System.out.println(compareContent(arr_first,arr_second));
        ///////////////////////////////////////////////////
        System.out.println("Exercise 3 (7)");
        int rand_val = 10 + (int)(Math.random()* 100000);
        System.out.println("Sours random value: " + rand_val);
        System.out.println("Reverse value: " + turnOver(rand_val));
        ///////////////////////////////////////////////////
        System.out.println("Exercise 4 (10)");
        int[] adds = {1,32,3,4};
        int[] adds2 = {33,12,7};
        int[] adds3 = {8,91};

        int[] super_array = concatArrays(adds,adds2,adds3);

        for (int val : super_array) {
            System.out.print(val + " ");
        }
        ///////////////////////////////////////////////////
        System.out.println("\nExercise 5 (12)");
        int[] after_sort = insertionSort(super_array);
        System.out.println(Arrays.toString(gg));
    }
}
