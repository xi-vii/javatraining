import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {

        // Создаём списки телефонов
        ArrayList<String> list1 = new ArrayList<>(List.of("+8 800 2000 500", "+8 800 200 600"));
        ArrayList<String> list2 = new ArrayList<>(List.of("+8 800 2000 700"));
        ArrayList<String> list3 = new ArrayList<>(List.of("+8 800 2000 800", "+8 800 2000 900", "+8 800 2000 000"));

        // Создаём и заполняем телефонную книгу
        Map<String, ArrayList<String>> phoneBook = new HashMap<>();
        phoneBook.put("Иванов И.И.", list1);
        phoneBook.put("Петров П.П.", list2);
        phoneBook.put("Сидоров С.С.", list3);

        // Доп. переменные
        String searchContact = new Scanner(System.in).nextLine();   // Ввод ФИО
        int num = 1;    // Порядковый номер телефона для вывода на экран

        // Проверяем, есть ли такие ФИО в книге
        if (phoneBook.containsKey(searchContact)) {

            // Выводим по порядку телефоны найденного контакта
            for (String phoneNum : phoneBook.get(searchContact)) {
                System.out.print(num + ". " + phoneNum + "\n");
                num++;
            }
        } else {
            System.out.println("У вас нет такого контакта!");
        }
    }
}
