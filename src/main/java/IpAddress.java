import java.util.Scanner;

public class IpAddress {

    // Определяем последнее число в IP
    public static int getIndex(String address){
        String[] array = address.split("\\.");    // Массив из IP
        return Integer.parseInt(array[array.length - 1]);
    }

    // Возвращаем новый IP
    public static String getNewIp(String address, int i) {
        String[] array1 = address.split("\\.");    // Массив из IP
        String temp = String.valueOf(i);    // Преобразуем int в String
        array1[array1.length - 1] = temp;   // Заменяем крайнее значение в массиве
        return String.join(".", array1);
    }

    public static void main(String[] args) {
        System.out.print("Введите первый IP: ");
        String address1 = new Scanner(System.in).nextLine(); // "192.168.0.1";

        System.out.print("Введите второй IP: ");
        String address2 = new Scanner(System.in).nextLine(); // "192.168.0.5";

        int startIndex = getIndex(address1); // Начало диапазона
        int endIndex = getIndex(address2); // Конец диапазона

        // Простая проверка, что окончание диапазона больше его начала
        if (endIndex > startIndex) {

            // Выводим все IP в диапазоне
            System.out.println("\nIP диапазона:");
            for (int i = startIndex + 1; i < endIndex; i++) {
                String result = getNewIp(address1, i);
                System.out.println(result);
            }
        } else {
            System.out.println("Введён неверный диапазон. Второй IP должен быть больше первого. Попробуйте ещё раз");
        }
    }
}
