public class Program{
    public static void main(String[] args) {
        Student st1 = new Student("Ivan", 108);
        st1.print();

        method(st1);

        st1.print();

        Integer[] array = {99,1000};

        someMethod(array);

        System.out.println('\n');
        for (int elem : array){
            System.out.printf(elem + " ");
        }
    }

    public static void method(Student student){
        student.setGroup(208);
    }

    public static void someMethod(Integer[] arr){
        Integer[] test = {1,3,4,5,6};
        arr = test;

        for (int elem : arr){
            System.out.printf(elem + " ");
        }
    }

    static class Student{
        private String name;
        private int group;

        public Student(String name, int group){
            this.name = name;
            this.group = group;
        }


        public void setGroup(int group) {
            this.group = group;
        }

        public void print(){
            System.out.println("Name: " + name + "\nGroup:" + group);
        }
    }


}