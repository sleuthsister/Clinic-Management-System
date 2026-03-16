package pkg;

public class doctor extends person 
{
    int doctorID;
    String name;
    int age;
    String sex;
    long phoneNumber;

    doctor()
    {
        super();
        this.doctorID = 0;
    }

    doctor(int doctorID, String name, int age, String sex, long phoneNumber)
    {
        super(name, age, sex, phoneNumber);
        this.doctorID = doctorID;
    }
}