import java.util.Scanner;

public class IpAddress {
    public static void main(String[] args) {
        System.out.print("Введите первый IP: ");
        String address1 = new Scanner(System.in).nextLine(); // "192.168.0.1";

        System.out.print("Введите второй IP: ");
        String address2 = new Scanner(System.in).nextLine(); // "192.168.0.5";

        // Определяем начало диапазона
        String[] array1 = address1.split("\\.");    // Массив из первого IP
        int startIndex = Integer.parseInt(array1[array1.length - 1]); // Песледняя цифра первого IP

        // Определяем начало диапазона
        String[] array2 = address2.split("\\.");    // Массив из второго IP
        int endIndex = Integer.parseInt(array2[array2.length - 1]); // Песледняя цифра второго IP

        // Простая проверка, что окончание диапазона больше его начала
        if (endIndex > startIndex) {

            // Выводим все IP в диапазоне
            System.out.println("\nIP диапазона:");
            for (int i = startIndex + 1; i < endIndex; i++) {
                String temp = String.valueOf(i);    // Преобразуем int в String
                array1[array1.length - 1] = temp;   // Заменяем крайнее значение в массиве
                System.out.println(String.join(".", array1));
            }
        } else {
            System.out.println("Введён неверный диапазон. Второй IP должен быть больше первого. Попробуйте ещё раз");
        }
    }
}
