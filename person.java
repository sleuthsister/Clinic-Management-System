package pkg;

public class person 
{
    String name;
    int age;
    String sex;
    long phoneNumber;

    person() 
    {
        this.name = "";
        this.age = 0;
        this.sex = "";
        this.phoneNumber = 0;
    }

    person(String name, int age, String sex, long phoneNumber)
    {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }
}