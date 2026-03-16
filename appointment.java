package pkg;

import java.time.LocalDate;
import java.time.LocalTime;

public class appointment
{
    patient Patient;
    LocalDate date;
    LocalTime time;

    appointment(patient Patient, LocalDate date, LocalTime time)
    {
        this.Patient = Patient;
        this.date = date;
        this.time = time;
    }
}