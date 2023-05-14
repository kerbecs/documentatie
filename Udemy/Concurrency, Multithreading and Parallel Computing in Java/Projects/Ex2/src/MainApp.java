import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {

        Student[] students = new Student[Constants.NUMBER_OF_STUDENTS];
        Book[] books = new Book[Constants.NUMBER_OF_BOOKS];
        ExecutorService service = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);

        try{
            for(int i = 0;i<Constants.NUMBER_OF_BOOKS;i++)
                books[i] = new Book(i+1);
            for(int i = 0;i<Constants.NUMBER_OF_STUDENTS;i++) {
                students[i] = new Student(i + 1, books);
                service.execute(students[i]);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            service.shutdown();
        }

    }
}
