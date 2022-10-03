package com.bridgelabz;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileIOServices {
    static final String FILE_PATH = "C:\\Users\\Nsoni\\IdeaProjects\\RFP186-AddressBookSystemUsingCSVAndJOSN\\src\\main\\resources";


    public enum FileType {
        TXT, CSV
    }

    static void read(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String string;
        while ((string = bufferedReader.readLine()) != null)
            System.out.println(string);
    }

    static void writeTxtFile(ArrayList<ContactPerson> addressBook, String addressBookName) throws IOException {
        File file = new File(FILE_PATH +  addressBookName + ".txt");
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            file.delete();
            file.createNewFile();
        }
        System.out.println("file created");
        FileWriter fileWriter = new FileWriter(file);
        String data = "";
        for (ContactPerson contactPerson : addressBook) {
            data = data.concat(contactPerson.toString()).concat("\n");
        }
        System.out.println(data);
        fileWriter.write(data);
        fileWriter.close();
    }

    public static void writeCsvFile(ArrayList<ContactPerson> addressBook, String addressBookName) throws IOException {
        File file = new File(FILE_PATH + addressBookName + ".csv");
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            file.delete();
            file.createNewFile();
        }
        System.out.println("file created");
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        List<String[]> data = new ArrayList<>();
        for (ContactPerson person : addressBook) {
            String[] contactData = new String[]{person.getFirstName(), person.getLastName(), person.getAddress(), person.getCity(), person.getState(), String.valueOf(person.getZipCode()), String.valueOf(person.getPhoneNumber()), person.getEmail()};
            data.add(contactData);
        }
        csvWriter.writeAll(data);
        fileWriter.close();
    }

    public static void writeJsonFile(ArrayList<ContactPerson> addressBook, String addressBookName) throws IOException {
        File file = new File(FILE_PATH + addressBookName + ".json");
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            file.delete();
            file.createNewFile();
        }
        FileWriter writer =new FileWriter(file);
        Gson gson=new Gson();
        String data="";
        for (ContactPerson person : addressBook) {
            data=data.concat(gson.toJson(person)+"\n");
        }
        writer.write(data);
        writer.close();
    }
}
