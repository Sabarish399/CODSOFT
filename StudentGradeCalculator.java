import java.util.*;

class StudentGradeCalculator
{
    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter total number of subject's: ");
        int totalSubjects = in.nextInt();

        double []marks = new double[totalSubjects];
        double sum=0,average=0;
        char grade;

        for(int i=0;i<totalSubjects;i++)
        {
            System.out.print("\nEnter marks for subject "+(i+1)+" (out of 100) : ");
            marks[i] = in.nextDouble();

            if(marks[i]<0 || marks[i]>100)
            {
                System.out.println("Invalid mark!! please enter a value between 0 to 100: ");
                i--;
                continue;
            }
            sum+=marks[i];
        }

        average = (sum / totalSubjects);


        if(average>=90)
            grade = 'A';
        else if (average>=80)
            grade = 'B';
        else if (average>=70)
            grade = 'C';
        else if (average>=60)
            grade = 'D';
        else if(average>=50)
            grade = 'E';
        else
            grade = 'F';

        System.out.println("\n-------------Student Grade Report-------------\n");
        System.out.printf("Total Marks: "+sum+"\nAverage Percentage: %.2f"+average+"%"+"\nGrade: "+grade);
        System.out.println("\n----------------------------------------------------\n");


    }
}
