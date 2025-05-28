import java.util.*;
import java.io.*;

class Student
{
    private int rollNo;
    private String name;
    private String grade;

    Student(int rollNo,String name,String grade)
    {
        this.rollNo = rollNo;
        this.name = name;
        this.grade = grade;
    }

    int getRollNo() {return rollNo;}
    String getName() {return name;}
    String getGrade() {return grade;}

    void setName(String name){this.name = name;}
    void setGrade(String grade){this.grade = grade;}

    @Override
    public String toString()
    {
        return "Roll no: "+rollNo+", Name: "+name+", Grade: "+grade;
    }
}

class StudentManagementSystems
{
    private List<Student> students = new ArrayList<>();

    void addStudent(Student student)
    {
        students.add(student);
        System.out.println("\nStudent Added successfully\n");
    }

    Student findStudent(int rollNo)
    {
        for(Student s: students)
        {
            if(s.getRollNo() == rollNo)
                return s;
        }
        return null;
    }

    void removeStudent(int rollNo)
    {
        Student s = findStudent(rollNo);

        if(s!=null)
        {
            students.remove(s);
            System.out.println("\nStudent Removed successfully\n");
        }
        else
            System.out.println("\nStudent Not found!\n");
    }

    void displayAll()
    {
        if(students.isEmpty())
        {
            System.out.println("\nNo Students Found\n");
        }
        else
        {
            System.out.println("Student List: \n");
            for (Student s : students)
            {
                System.out.println(s);
            }
        }
    }

    void writeToFile(String fileName) throws Exception
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            for(Student s: students)
            {
                bw.write(s.getRollNo()+","+s.getName()+","+s.getGrade());
                bw.newLine();

            }
        }
    }

    void readFromFile(String fileName) throws Exception
    {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while((line=br.readLine())!=null)
            {
                String[] s = line.split(",");
                students.add(new Student(Integer.parseInt(s[0]),s[1],s[2]));
            }
        }
    }

}

class StudentManagementSystemInterface
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        StudentManagementSystems sms = new StudentManagementSystems();

        while (true)
        {
            System.out.println(" \n 1.Add Student \n 2.Remove Student \n 3.Search Student \n 4.Display All Student \n 5.Save to the File \n 6.Exit\n" );

            System.out.print("Enter Option: ");
            int choice = in.nextInt();

            switch (choice)
            {
                case 1:
                    System.out.print("Enter Student Roll no: ");
                    int rollNo = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = in.nextLine();
                    System.out.print("Enter Student Grade: ");
                    String grade = in.nextLine();

                    if(name.isEmpty() || grade.isEmpty())
                    {
                        System.out.println("Invalid Input!! Name and grade are required");
                        break;
                    }
                    sms.addStudent(new Student(rollNo,name,grade));
                    break;
                case 2:
                    System.out.print("Enter Student Roll no: ");
                    rollNo = in.nextInt();

                    sms.removeStudent(rollNo);
                    break;
                case 3:
                    System.out.print("Enter Roll no to search: ");

                    rollNo = in.nextInt();
                    System.out.println("\n"+sms.findStudent(rollNo)+"\n");
                    break;
                case 4:
                    sms.displayAll();
                    break;
                case 5:
                    try
                    {
                        sms.writeToFile("Student.txt");
                        System.out.println("\nData saved Successfully\n");
                    }
                    catch (Exception e) {
                        System.out.println(" Data Not Saved");
                    }
                case 6:
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
