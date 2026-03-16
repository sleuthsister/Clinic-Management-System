package pkg;

public class patient extends person
{
    int patientID;
    String name;
    int age;
    String sex;
    long phoneNumber;
    
    patient() 
    { 
        super();
        this.phoneNumber = 0;
    }

    patient(int patientID, String name, int age, String sex, long phoneNumber)
    {
        super(name, age, sex, phoneNumber);
        this.patientID = patientID;
    }
}