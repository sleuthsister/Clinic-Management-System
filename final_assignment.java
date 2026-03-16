import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import pkg.*;

@SuppressWarnings("unused")


public class final_assignment {
    final static File patientFile = new File("filepath\\assignment\\patientRecords.txt");
    final static File doctorFile = new File("filepath\\assignment\\doctorRecords.txt");
    final static File appointmentsFile = new File("filepath\\assignment\\appointments.txt");
    static Scanner scan = new Scanner(System.in);

    static int generateID(File file)
    {
        int maxID = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("\\|");
                int ID = Integer.parseInt(parts[0]);
                if (ID > maxID) maxID = ID;
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {e.printStackTrace();}
        return maxID + 1;
    }
    
    static void addDetails(File file)
    {
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        System.out.print("Enter age: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter sex: ");
        String sex = scan.nextLine();
        System.out.print("Enter phone number: ");
        long phoneNumber = scan.nextLong();
        scan.nextLine();
        int id = generateID(file);
        try
        {
            FileWriter filewriter = new FileWriter(file, true);
            filewriter.write(id + "|" + name + "|" + age + "|" + sex + "|" + phoneNumber + "\n");
            filewriter.close();
            System.out.println("Details recorded.");
        }
        catch(Exception e) 
        { 
            System.out.println("Exception");
        }
    }

    static void viewDetails(File file)
    {
        try
        {	
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String contentLine = reader.readLine();
            System.out.printf("%-5s | %-10s | %-5s | %-5s | %-10s%n", "ID", "Name", "Age", "Sex", "Phone Number");
            System.out.println("-------------------------------------------------");
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                System.out.printf("%-5s | %-10s | %-5s | %-5s | %-10s%n", content[0], content[1], content[2], content[3], content[4]);
                contentLine = reader.readLine();
            }
            reader.close();
        } 
        catch (IOException e) {e.printStackTrace();} 
    }

    static void deletePatient()
    {
        System.out.print("Enter patient ID to delete: ");
        int id = scan.nextInt();
        scan.nextLine();
        File tempFile = new File("filepath\\assignment\\temp.txt");
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(patientFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String contentLine = reader.readLine();
            String deletePatient = Integer.toString(id);
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                if (!content[0].equals(deletePatient)) 
                {
                    writer.write(content[0] + "|" + content[1] + "|" + content[2] + "|" + content[3] + "|" + content[4] + "\n");
                }
                    contentLine = reader.readLine();
            }
                reader.close();
                writer.close();
                patientFile.delete();
                tempFile.renameTo(patientFile);
            }
        catch (IOException e) {e.printStackTrace();}                
    }

    static void deleteDoctor()
    {
        System.out.print("Enter doctor ID to delete: ");
        int id = scan.nextInt();
        scan.nextLine();
        File tempFile = new File("filepath\\assignment\\temp.txt");
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(doctorFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String contentLine = reader.readLine();
            String deleteDoctor = Integer.toString(id);
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                if (!content[0].equals(deleteDoctor)) 
                {
                    writer.write(content[0] + "|" + content[1] + "|" + content[2] + "|" + content[3] + "|" + content[4] + "\n");
                }
                    contentLine = reader.readLine();
            }
                reader.close();
                writer.close();
                doctorFile.delete();
                tempFile.renameTo(doctorFile);
            }
        catch (IOException e) {e.printStackTrace();}                
    }

    static void searchPatient()
    {
        System.out.print("Enter patient ID or name to search: ");
        String nameORid = scan.nextLine();
        try
        {	
            BufferedReader reader = new BufferedReader(new FileReader(patientFile));
            String contentLine = reader.readLine();
            boolean patientFound = false;
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                if (content[0].equals(nameORid) || content[1].toLowerCase().contains(nameORid.toLowerCase()))
                {
                    System.out.println("Patient ID: " + content[0]);
                    System.out.println("Name: " + content[1]);
                    System.out.println("Sex: " + content[3]);
                    System.out.println("Age: " + content[2]);
                    System.out.println("Contact: " + content[4]);
                    patientFound = true;
                }
                contentLine = reader.readLine();
            }
            if (!patientFound) System.out.println("Patient ID or name not found.");
            reader.close();
        } 
        catch (IOException e) {e.printStackTrace();} 
    }

    static void searchDoctor()
    {
        System.out.print("Enter Doctor ID or name to search: ");
        String nameORid = scan.nextLine();
        try
        {	
            BufferedReader reader = new BufferedReader(new FileReader(doctorFile));
            String contentLine = reader.readLine();
            boolean doctorFound = false;
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                if (content[0].equals(nameORid) || content[1].toLowerCase().contains(nameORid.toLowerCase()))
                {
                    System.out.println("Doctor ID: " + content[0]);
                    System.out.println("Name: " + content[1]);
                    System.out.println("Sex: " + content[3]);
                    System.out.println("Age: " + content[2]);
                    System.out.println("Contact: " + content[4]);
                    doctorFound = true;
                }
                contentLine = reader.readLine();
            }
            if (!doctorFound) System.out.println("Doctor ID or name not found.");
            reader.close();
        } 
        catch (IOException e) {e.printStackTrace();} 
    }

    static void scheduleAppointment()
    {
        System.out.print("Enter Patient ID: ");
        String patientID = scan.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorID = scan.nextLine();
        try
        {	
            BufferedReader reader = new BufferedReader(new FileReader(patientFile));
            String contentLine = reader.readLine();
            boolean patientFound = false;
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                if (content[0].equals(patientID)) patientFound = true;
                contentLine = reader.readLine();
            }
            reader.close();
            if (!patientFound) 
            {
                System.out.println("Patient ID not found.");
                return;
            }
        } 
        catch (IOException e) {e.printStackTrace();} 

        try
        {	
            BufferedReader reader = new BufferedReader(new FileReader(doctorFile));
            String contentLine = reader.readLine();
            boolean doctorFound = false;
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                if (content[0].equals(doctorID)) doctorFound = true;
                contentLine = reader.readLine();
            }
            reader.close();
            if (!doctorFound) 
            {
                System.out.println("Doctor ID not found.");
                return;
            }
        } 
        catch (IOException e) {e.printStackTrace();} 

        System.out.print("Enter Date of Appointment (yyyy-mm-dd): ");
        String[] dateInput = scan.nextLine().split("-");
        int[] date = new int[dateInput.length];
        try { for (int i = 0; i < dateInput.length; i++) date[i] = Integer.parseInt(dateInput[i]); } 
        catch (NumberFormatException e) { System.out.println("Invalid date format. Please use yyyy-mm-dd."); return;}
        LocalDate date2 = LocalDate.of(date[0], date[1], date[2]);
        System.out.print("Enter Time of Appointment (hh:mm): ");
        String[] timeInput = scan.nextLine().split(":");
        int[] time = new int[timeInput.length];
        try { for (int i = 0; i < timeInput.length; i++) time[i]  = Integer.parseInt(timeInput[i]); } 
        catch (NumberFormatException e) { System.out.println("Invalid time format. Please use hh:mm."); return;}
        LocalTime time2 = LocalTime.of(time[0], time[1]);
        
        try
        {
            FileWriter filewriter = new FileWriter(appointmentsFile, true);
            filewriter.write(patientID + "|" +  doctorID + "|" + date2 + "|" + time2 + "\n");
            filewriter.close();
            System.out.println("Appointment scheduled.");
        }
        catch(Exception e) 
        { 
            System.out.println("Exception");
        }
    }

    static void viewAppointmentsbyPatient()
    {
        try
        {	
            BufferedReader reader = new BufferedReader(new FileReader(appointmentsFile));
            System.out.print("Enter Patient ID: ");
            String patientID = scan.nextLine();
            try
            {	
                BufferedReader reader1 = new BufferedReader(new FileReader(patientFile));
                String contentLine1 = reader1.readLine();
                boolean patientFound = false;
                while (contentLine1 != null) 
                {
                    String[] content = contentLine1.split("\\|");
                    if (content[0].equals(patientID)) patientFound = true;
                    contentLine1 = reader1.readLine();
                }
                reader1.close();
                if (!patientFound) 
                {
                    System.out.println("Patient ID not found.");
                    return;
                }
            } 
            catch (IOException e) {e.printStackTrace();}
            String contentLine = reader.readLine();
            System.out.printf("%-10s | %-10s | %-10s | %-5s%n", "Patient ID", "Doctor ID", "Date", "Time");
            System.out.println("-------------------------------------------------");
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                if (content[0].equals(patientID))
                {
                    System.out.printf("%-10s | %-10s | %-10s | %-5s%n", content[0], content[1], content[2], content[3]);
                }
                contentLine = reader.readLine();
            }
            reader.close();
        } 
        catch (IOException e) {e.printStackTrace();} 
    }

    static void viewAppointments()
    {
        try
        {	
            BufferedReader reader = new BufferedReader(new FileReader(appointmentsFile));
            String contentLine = reader.readLine();

            System.out.printf("%-10s | %-10s | %-10s | %-5s%n", "Patient ID", "Doctor ID", "Date", "Time");
            System.out.println("-------------------------------------------------");
            while (contentLine != null) 
            {
                String[] content = contentLine.split("\\|");
                System.out.printf("%-10s | %-10s | %-10s | %-5s%n", content[0], content[1], content[2], content[3]);
                contentLine = reader.readLine();
            }
            reader.close();
        } 
        catch (IOException e) {e.printStackTrace();} 
    }

    public static void main(String[] args)
    {
        try 
        {
            patientFile.createNewFile();
            doctorFile.createNewFile();
            appointmentsFile.createNewFile();
        }
        catch (IOException e) {System.out.println("Exception Occurred.");}
        System.out.println("Welcome to Ruby Clinic");        
        System.out.println("Choose from the menu to perform an action");
        System.out.println("1. Add Patient.");
        System.out.println("2. View Patients.");
        System.out.println("3. Search Patient.");
        System.out.println("4. Delete Patient.");
        System.out.println("5. Add Doctor.");
        System.out.println("6. View Doctors.");
        System.out.println("7. Search Doctor.");
        System.out.println("8. Delete Doctor.");
        System.out.println("9. Schedule Appointment."); 
        System.out.println("10. View Appointments.");
        System.out.println("11. View Appointments for Patient.");
        System.out.println("12. Exit");
        System.out.print("Enter choice: ");
        int choice = scan.nextInt();
        scan.nextLine();
        while (true)
        {    
            switch (choice)
            {
                case 1:
                    addDetails(patientFile);
                    break;
                case 2:
                    viewDetails(patientFile);
                    break;
                case 3:
                    searchPatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    addDetails(doctorFile);
                    break;
                case 6:
                    viewDetails(doctorFile);
                    break;
                case 7:
                    searchDoctor();
                    break;
                case 8:
                    deleteDoctor();
                    break;
                case 9:
                    scheduleAppointment();
                    break;
                case 10:
                    viewAppointments();
                    break;
                case 11:
                    viewAppointmentsbyPatient();
                    break;
                case 12:
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
            System.out.println("Choose from the menu to perform an action");
            System.out.println("1. Add Patient.");
            System.out.println("2. View Patients.");
            System.out.println("3. Search Patient.");
            System.out.println("4. Delete Patient.");
            System.out.println("5. Add Doctor.");
            System.out.println("6. View Doctors.");
            System.out.println("7. Search Doctor.");
            System.out.println("8. Delete Doctor.");
            System.out.println("9. Schedule Appointment."); 
            System.out.println("10. View Appointments.");
            System.out.println("11. View Appointments for Patient.");
            System.out.println("12. Exit");
            System.out.print("Enter choice: ");
            choice = scan.nextInt();
            scan.nextLine();
        }
    }
}
