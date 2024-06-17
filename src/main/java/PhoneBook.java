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
        boolean haveContact = false;    // Есть ли такой контракт

        // Проверяем, есть ли такие ФИО в книге
        for (Map.Entry<String, ArrayList<String>> entry : phoneBook.entrySet()) {

            // Если такой контракт есть в книге
            if (entry.getKey().equals(searchContact)) {
                haveContact = true;

                // Выводим по порядку телефоны наёденного контакта
                for (String phoneNum : entry.getValue()) {
                    System.out.println(num + ". " + phoneNum);
                    num++;
                }
            }
        }

        // Если контакта нет, выводим сообщение
        if (!haveContact) {
            System.out.println("У вас нет такого контакта!");
        }
    }
}
