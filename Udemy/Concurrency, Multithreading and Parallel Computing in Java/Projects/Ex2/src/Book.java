import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int id;
    private Lock lock;

    public Book(int id) {
        this.id = id;
        lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException{
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)){
         System.out.println(student+" starts reading "+this);
         Thread.sleep(2000);
         System.out.println(student+" has just finished reading "+this);
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Book " +
                "id=" + id
                ;
    }
}
