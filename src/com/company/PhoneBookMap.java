package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ростислав Ефимов
 * Класс создания HashMap для телефонной книги, с дальнейшей работой!
 */

/**
 * 1)Переделать регулярное выражение что бы нужно бфло вводить Ф И О через пробел, и никак иначе!
 * 2)Реализовать возможность смены значения после поиска по номеру +
 * 3)Создать метод для сохранения коллекции в файл.txt
 * 4)Создать метод для выгрузки сохранненой коллекции из файла, с дальнейшей работой!
 * 5)Подумать как передать прогу под стандарты ООП
 */
public class PhoneBookMap extends FieldForWork {
    /**
     *
     * @throws IOException
     */
    public static void startWork() throws IOException {
        boolean startWorkIsActive = true;
        while (startWorkIsActive){
            System.out.println("ВЫБЕРИТЕ ЗАДАЧУ ИЗ СПИСКА: " +
                    "\n\tСоздать новый контакт - 1 " +
                    "\n\tПоказать весь список контактов - 2 " +
                    "\n\tПоиск контакта - 3 "+
                    "\n\tВыход из программы - 4");
            nextTrip = reader.readLine();
            switch (nextTrip){
                case "1":
                    createNewPhoneBook();
                    break;
                case "2":
                    showAllList();
                    break;
                case "3":
                    search();
                    break;
                case "4":
                    startWorkIsActive = false;
                    break;
                default:
                    System.out.println("Вы ввели не допустимую команду!");
                    continue;
            }
        }
    }

    public static void createNewPhoneBook() throws IOException {
        boolean workIsActive = true;
        while(workIsActive){
            count++;
            boolean numberPhoneCreate = true;
            while (numberPhoneCreate){
                System.out.println("Введите номер телефона: ");
                number = reader.readLine();
                Matcher matcherNumberText = numberTextPattern.matcher(number);
                if(matcherNumberText.find() || number.isEmpty() || (number.length()<12 ||number.length()>12)){
                    System.out.println("Ошибка не правильно введенные данные");
                    continue;
                }
                numberPhoneCreate = false;
            }
            boolean nameUserCreate = true;
            while (nameUserCreate){
                System.out.println("Введите ФИО: ");
                name = reader.readLine();
                Matcher matcherNameText = nameTextPattern.matcher(name);
                if(matcherNameText.find() || name.isEmpty()){
                    System.out.println("Ошибка не правильно введенные данные");
                    continue;
                }
                nameUserCreate = false;
            }
            mapPhoneBook.put(number,name);
            System.out.println("Введите следующую команду: " +
                    "\n\tДля поиска контака - search" +
                    "\n\tДля создания нового контакта - y" +
                    "\n\tДля выхода из программы - exit");
            //mapPhoneBook.put(number,name);
            nextTrip = reader.readLine();
            if (nextTrip.equals("search")) search();
            if (nextTrip.equals("exit")) break;
            showAllList();
        }
        workIsActive = false;
        System.out.println("-------------------------");
        showAllList();
    }

    public static void showAllList() throws IOException {
        if(!mapPhoneBook.isEmpty()){
            System.out.println("Зарегистированно " + count + " users");
            for (Map.Entry<String, String> userTemp : mapPhoneBook.entrySet()){
                System.out.println("\nНомер телефона: " + userTemp.getKey() +
                        "\nФИО: " + userTemp.getValue() + "\n");
          }
        }else {
            System.out.println("Телефонная книга пуста!");
            startWork();
        }
    }


    public static void search() throws IOException {
        if(!mapPhoneBook.isEmpty()){
            System.out.println("Введите номер телефона для поиска: ");
            Scanner sc = new Scanner(System.in);
            String search = sc.nextLine();
            boolean flag = mapPhoneBook.containsKey(search);
            if(flag == true){
                System.out.println("Человек найден: " + "\n\tНомер телефона: " + search +
                        "\n\tФИО: "+ mapPhoneBook.get(search));
                System.out.println("Хотите ли изменить данные контакта: ");
                String newContact = sc.nextLine();
                if (newContact.equals("yes")){
                    boolean nexConractOrder = true;
                    while (nexConractOrder){
                        System.out.println("Введите новые ФИО:");
                        newContact =sc.nextLine();
                        Matcher matcherNameText = nameTextPattern.matcher(newContact);
                        if (matcherNameText.find() || newContact.isEmpty()){
                            System.out.println("Ошибка, введены не допустимые значения!");
                            continue;
                        }
                        nexConractOrder = false;
                    }
                    mapPhoneBook.replace(search, newContact);
                }
            } else {
                System.out.println("Ошибка ввода данных!");
            }
        } else {
            System.out.println("Телефонная книга пуста!");
            startWork();
        }
    }
}
