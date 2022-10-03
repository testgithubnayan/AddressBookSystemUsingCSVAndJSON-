package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {
    static HashMap<String, ArrayList<ContactPerson>> addressBookList = new HashMap<>();
    static ArrayList<ContactPerson> currentAddressBook;
    static String currentAddressBookName;

    static HashMap<String, ArrayList<ContactPerson>> cityContactList = new HashMap<>();
    static HashMap<String, ArrayList<ContactPerson>> stateContactList = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    ContactPerson createContact() {
        System.out.println("Enter first name: ");
        String firstName = sc.next();
        System.out.println("Enter last name: ");
        String lastName = sc.next();
        System.out.println("Enter address: ");
        String address = sc.next();
        System.out.println("Enter city: ");
        String city = sc.next();
        System.out.println("Enter state: ");
        String state = sc.next();
        System.out.println("Enter ZipCode: ");
        int zipCode = sc.nextInt();
        System.out.println("Enter phoneNumber: ");
        long phoneNumber = sc.nextLong();
        System.out.println("Enter Email: ");
        String email = sc.next();

        ContactPerson person = new ContactPerson(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
        System.out.println("created new contact");
        return person;
    }

    void addContact(ContactPerson person) {
        boolean isDuplicate = checkDuplicateContact(person);
        if (isDuplicate) {
            System.out.println("Contact name already exists");
        } else {
            currentAddressBook.add(person);
            System.out.println("contact added to AddressBook " + currentAddressBookName);
            System.out.println(person);
        }
    }

    boolean checkDuplicateContact(ContactPerson newPerson) {
        return currentAddressBook.stream().anyMatch((person) -> person.getFirstName().equals(newPerson.getFirstName()));
    }

    void editContact() {
        System.out.println("Enter name to edit contact");
        String name = sc.next();
        for (ContactPerson person : currentAddressBook) {
            if (person.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("Enter first name: ");
                person.setFirstName(sc.next());
                System.out.println("Enter last name: ");
                person.setLastName(sc.next());
                System.out.println("Enter address: ");
                person.setAddress(sc.next());
                System.out.println("Enter city: ");
                person.setCity(sc.next());
                System.out.println("Enter state: ");
                person.setState(sc.next());
                System.out.println("Enter ZipCode:");
                person.setZipCode(sc.nextInt());
                System.out.println("Enter phoneNumber: ");
                person.setPhoneNumber(sc.nextLong());
                System.out.println("Enter Email: ");
                person.setEmail(sc.next());
                System.out.println("contact updated successfully.");
                System.out.println(person);
                break;
            }
        }
    }

    void deleteContact() {
        boolean isContactFound = false;
        System.out.println("Enter name to delete contact: ");
        String name = sc.next();
        for (ContactPerson contact : currentAddressBook) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("contact found:");
                isContactFound = true;
                System.out.println(contact);
                System.out.println("confirm to delete (y/n)");
                if (sc.next().equalsIgnoreCase("y")) {
                    currentAddressBook.remove(contact);
                    System.out.println("contact deleted");
                }
                break;
            }
        }
        if (!isContactFound) {
            System.out.println("Opps... contact not found");
        }
    }

    void addNewAddressBook() {
        System.out.println("Enter name for AddressBook: ");
        String AddressBookName = sc.next();
        ArrayList<ContactPerson> AddressBook = new ArrayList();
        addressBookList.put(AddressBookName, AddressBook);
        System.out.println("new AddressBook created");
        currentAddressBook = addressBookList.get(AddressBookName);
        currentAddressBookName = AddressBookName;
    }

    void selectAddressBook() {
        System.out.println(addressBookList.keySet());
        System.out.println("Enter name of address book:");
        String addressBookName = sc.next();

        for (String key : addressBookList.keySet()) {
            if (key.equalsIgnoreCase(addressBookName)) {
                currentAddressBook = addressBookList.get(key);
                currentAddressBookName = key;
            }
        }
        System.out.println("current AddressBook is: " + currentAddressBookName);
    }

    void searchContact() {
        System.out.println("1.Search by City \n2.Search by State");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter city :");
                searchByCity(sc.next());
                break;
            case 2:
                System.out.println("Enter State :");
                searchByState(sc.next());
                break;
            default:
                searchContact();
                break;
        }
    }

    void searchByCity(String city) {
        System.out.println("Search Result: ");
        for (String addressBookName : addressBookList.keySet()) {
            addressBookList.get(addressBookName).forEach((person) -> {
                if (person.getCity().equalsIgnoreCase(city))
                    System.out.println(person);
            });
        }
    }

    void searchByState(String state) {
        System.out.println("Search Result: ");
        for (String addressBookName : addressBookList.keySet()) {
            addressBookList.get(addressBookName).forEach((person) -> {
                if (person.getState().equalsIgnoreCase(state))
                    System.out.println(person);
            });
        }
    }

    public void initializeCityAndStateContactList() {
        for (String key : addressBookList.keySet()) {
            for (ContactPerson person : addressBookList.get(key)) {
                String city = person.getCity();
                if (cityContactList.containsKey(city)) {
                    cityContactList.get(city).add(person);
                } else {
                    ArrayList<ContactPerson> list = new ArrayList<>();
                    list.add(person);
                    cityContactList.put(city, list);
                }

                String state = person.getState();
                if (stateContactList.containsKey(state)) {
                    stateContactList.get(state).add(person);
                } else {
                    ArrayList<ContactPerson> list = new ArrayList<>();
                    list.add(person);
                    stateContactList.put(state, list);
                }
            }
        }
    }

    void viewContacts() {
        initializeCityAndStateContactList();
        System.out.println("*****************************\n1.View by City \n2.View by State");
        switch (sc.nextInt()) {
            case 1:
                viewContactByCity();
                break;
            case 2:
                viewContactByState();
                break;
            default:
                viewContacts();
                break;
        }
    }

    void viewContactByCity() {
        System.out.println("Enter City:");
        String city = sc.next();
        for (String key : cityContactList.keySet()) {
            if (key.equalsIgnoreCase(city)) {
                cityContactList.get(key).forEach(person -> System.out.println(person));
            }
        }
    }

    void viewContactByState() {
        System.out.println("Enter State:");
        String state = sc.next();
        for (String key : stateContactList.keySet()) {
            if (key.equalsIgnoreCase(state)) {
                stateContactList.get(state).forEach(person -> System.out.println(person));
            }
        }
    }

    void sortContact() {
        List<ContactPerson> allContacts = getAllContacts();
        List<ContactPerson> sortedContacts;

        System.out.println("1.Sort By Name \n2.Sort By City \n3.Sort By State \n4.Sort By Zipcode \n5.back");
        switch (sc.nextInt()) {
            case 1:
                sortedContacts = allContacts.stream().sorted((x, y) -> x.getFirstName().compareTo(y.getFirstName())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 2:
                sortedContacts = allContacts.stream().sorted((x, y) -> x.getCity().compareTo(y.getCity())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 3:
                sortedContacts = allContacts.stream().sorted((x, y) -> x.getState().compareTo(y.getState())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 4:
                sortedContacts = allContacts.stream().sorted((x, y) -> Integer.compare(x.getZipCode(), y.getZipCode())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 5:
                break;
            default:
                sortContact();
                break;
        }
    }

    List<ContactPerson> getAllContacts() {
        List<ContactPerson> allContacts = new ArrayList<>();
        for (String key : addressBookList.keySet()) {
            allContacts.addAll(addressBookList.get(key));
        }
        return allContacts;
    }

    void writeAddressBook() throws IOException {
        System.out.println("Select option \n1.Write to txt \n2.write to csv \n3.write to json \n4.back");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                for (String key : addressBookList.keySet()) {
                    FileIOServices.writeTxtFile(addressBookList.get(key), key);
                }
                break;
            case 2:
                for (String key : addressBookList.keySet()) {
                    FileIOServices.writeCsvFile(addressBookList.get(key), key);
                }
                break;
            case 3:
                for (String key : addressBookList.keySet()) {
                    FileIOServices.writeJsonFile(addressBookList.get(key), key);
                }
                break;
            case 4:
                break;
            default:
                writeAddressBook();
                break;

        }
    }

    void readAddressBook() throws IOException {
        System.out.println("Select option \n1.read from txt \n2.read from csv \n3.read from json \n4.back");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                FileIOServices.read(new File(FileIOServices.FILE_PATH.concat(".txt")));
                break;
            case 2:
                FileIOServices.read(new File(FileIOServices.FILE_PATH.concat(".csv")));
                break;
            case 3:
                FileIOServices.read(new File(FileIOServices.FILE_PATH.concat(".json")));
                break;
            case 4:
                break;
            default:
                readAddressBook();
        }


    }
}
