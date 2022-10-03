package com.bridgelabz;

import java.io.IOException;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Address-book");
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
        addressBook.addNewAddressBook();

        boolean flag1 = true;
        while (flag1){
            System.out.println("*************\n"+AddressBook.addressBookList.keySet());
            System.out.println("current AddressBook Name: "+AddressBook.currentAddressBookName);
            System.out.println("Select option\n1.Add Contact\n2.Edit Contact\n3.view Contacts\n4.Delete Contact\n5.Add New AddressBook\n6.Select AddressBook\n7.Search Contact\n8.Sort Contact\n9.Write Address Book\n10.Read Address Book\n11.Exit");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    ContactPerson contact = addressBook.createContact();
                    addressBook.addContact(contact);
                    break;
                case 2:
                    addressBook.editContact();
                    break;
                case 3:
                    addressBook.viewContacts();
                    break;
                case 4:
                    addressBook.deleteContact();
                    break;
                case 5:
                    addressBook.addNewAddressBook();
                    break;
                case 6:
                    addressBook.selectAddressBook();
                    break;
                case 7:
                    addressBook.searchContact();
                    break;
                case 8:
                    addressBook.sortContact();
                    break;
                case 9:
                    addressBook.writeAddressBook();
                    break;
                case 10:
                    addressBook.readAddressBook();
                    break;
                case 11:
                    flag1=false;
                    break;
                default:
                    System.out.println(option+" is not valid option");
                    break;

            }
        }
    }
}
