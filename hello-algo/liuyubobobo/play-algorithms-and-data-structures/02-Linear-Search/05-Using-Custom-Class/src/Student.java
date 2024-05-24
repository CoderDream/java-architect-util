public class Student {

    private String name;

    public Student(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object student){

        if(this == student)
            return true;

        if(student == null)
            return false;

        if(this.getClass() != student.getClass())
            return false;

        Student another = (Student)student;
        return this.name.equals(another.name);
    }
}
