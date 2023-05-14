/*-------------------------------------------------------------------------------

This program was made at first in French, but It was translated in English
At the moment, it only works with letters and spaces.

---------------------------------------------------------------------------------*/

package Cryptology;

import java.util.HashMap;
import java.util.Random;

public class Cryptology {
    public static void main(String[] args){
        String message = "A test message";
        String encription;
// Key test
        Code withKey1 = new WithKey("A test message", "SETYHJUI");
        System.out.print("With the code : " );
        withKey1.show();
        encription = withKey1.code(message);
        System.out.print("Encoding of " + message + " : ");
        System.out.println(encription);
        System.out.print("Decoding of " + encription + " : ");
        System.out.println(withKey1.decode(encription));
        System.out.println("-----------------------------------");
        System.out.println();


// Random key test
        Code WithKey2 = new WithRandomKey(5);
        System.out.print("With the code : " );
        WithKey2.show();
        encription = WithKey2.code(message);
        System.out.print("Encoding of " + message + " : ");
        System.out.println(encription);
        System.out.print("Decoding of " + encription + " : ");
        System.out.println(WithKey2.decode(encription));
        System.out.println("-----------------------------------");
        System.out.println();

// TEST CESAR

        Code cesar1 = new Cesar("Cesar", 5);
        System.out.print("With the code : " );
        cesar1.show();
        encription = cesar1.code(message);
        System.out.print("Encoding of " + message + " : ");
        System.out.println(encription);
        System.out.print("Decoding of " + encription + " : ");
        System.out.println(cesar1.decode(encription));
        System.out.println("-----------------------------------");
        System.out.println();
// Codage test
        System.out.println("Test CODAGE: ");
        System.out.println("------------- ");
        System.out.println();
        Code[] tab = {
                new Cesar("cesar", 5),
                new WithKey("With key", "EQUINOXE") ,
                new WithRandomKey(5),
                new WithRandomKey(10)};
        Codages codes = new Codages(tab);
        codes.test(message);
    }
}

abstract class Code{
    private final String message;
    private String code;
    private final HashMap <Character,Integer> table;
    private final HashMap <Integer,Character> reverseTable;

    public Code(String message){
        this.message = message;
        table = new HashMap<>();
        reverseTable = new HashMap<>();
        createTable();
    }
    public void createTable() {
        // This method creates a dictionary which stores the numbers and the corresponding letters
        // and a dictionary which stores the letters and the corresponding numbers

        // dictionary - letter | number
        table.put(' ', 0);
        for (char i = 'A'; i <= 'Z'; i++)
            table.put(i, i - 'A' + 1);
        for (char i = 'a'; i <= 'z'; i++)
            table.put(i, 'Z'-'A' +  1 +i -'a'+1);

        // dictionary - number | letter
        reverseTable.put(0,' ');
        char x = 'A';
        for(int i = 1;x<='Z';i++,x++)
            reverseTable.put(i,x);
        x = 'a';
        for(int i = 'Z'-'A' + 2;x<='z';i++,x++)
            reverseTable.put(i,x);
    }

    public abstract String code(String s);
    public abstract String decode(String s);
    public abstract void show();
    public void setCode(String code){
        // this method set the code

        this.code = code;
    }
    public String getCode(){
        // this method returns the cod which encrypts the message
        return code;
    }
    public String getMessage() {
        // this method returns the message

        return message;
    }
    public HashMap<Character, Integer> getTable() {
        // this method returns the dictionary number | letter

        return table;
    }
    public HashMap<Integer, Character> getReverseTable() {
        // this method returns the dictionary letter | number

        return reverseTable;
    }
}
class WithKey extends Code{
    private String key;
    public WithKey(String message,String key){
        super(message);
        this.key = key;
    }
    public WithKey(String message){
        super(message);
    }
    public int length(){
        // this method returns the key's length

        return key.length();
    }

    @Override
    public String code(String s) {
        // this method encrypts our message and returns it
        // s - is a message to encrypt

        int min = Math.min(s.length(),key.length());
        int max = Math.max(s.length(),key.length());

        // this array stores the value for each character of the message
        int[] amount = new int[max];
        String code = "";

        // the key could be longer than the message, and we need to know which is the longest, the key or the message
        for(int pos1 = 0,pos2 = 0;pos1<max;pos1++,pos2++){
            // pos1 and pos2 is used because the key and the message aren't equal, and we need to
            // know when we reach the end of one of them

            if(pos2==min)
                // if it's the end of the words, we return at 0
                pos2 = 0;
            if(s.length()>=key.length()){
                // if the message is larger than the key
                amount[pos1] = getTable().get(s.charAt(pos1)) + getTable().get(key.charAt(pos2));
            }

            else{
                // if the key is larger than the message
                amount[pos1] = getTable().get(s.charAt(pos2)) + getTable().get(key.charAt(pos1));
            }


        }
        for(int i = 0;i<amount.length;i++)
        {
            if(amount[i] > 52)
                amount[i] -= 52;
        }

        for(int i : amount){
            // here we create our code

            code += getReverseTable().get(i);
        }

        setCode(code);
        return code;

    }
    @Override
    public String decode (String s){
        // this method is to decrypt the code
        // It works similarly to the precedent
        // s - is the code to decrypt

        String decode = "";

        int max = Math.max(s.length(),key.length());
        int min = Math.min(s.length(),key.length());
        for(int x = 0,y=0;x<max;x++,y++){
            int amount;
            if(y == min)
                y = 0;
            if(s.length()>=key.length()){
                amount = getTable().get(s.charAt(x)) - getTable().get(key.charAt(y));
            }
            else{
                amount = getTable().get(s.charAt(y)) - getTable().get(key.charAt(x));
            }

            if(amount<0)
                amount += 52;

            decode += getReverseTable().get(amount);
        }
        return decode;
    }

    @Override
    public void show() {
        // this method shows the key

        System.out.println("With key"+" with "+key+" as key");
    }

    public void setKey(String key){
        // this method set the key
        // key - is the new key to be set
        this.key = key;
    }
    public String getKey(){
        return key;
    }
}
class WithRandomKey extends WithKey{

    private final int length;
    private String key = "";
    public WithRandomKey(int length){
        super("With random key");
        this.length = length;
        generateKey();
    }
    public void generateKey(){
        // this method generates a random key

        Random random = new Random();

        for(int i = 0;i<length;i++){
            key += getReverseTable().get(random.nextInt(1,53));
        }
        setKey(key);
    }
    @Override
    public void show() {
        // this method shows the key

        System.out.println("With random key"+" with "+key+" as key");
    }

}

class Cesar extends Code{
    private final int notches;

    public Cesar(String message, int notches){
        super(message);
        this.notches = notches;
    }
    public void show() {
        // this method shows the notches and the full message

        System.out.println(getMessage()+" with "+notches+" notches");
    }
    public String code(String s) {
        // this method encrypts the message
        // s - the message to be encrypted

        String code = "";
        for(int i = 0;i<s.length();i++){
            int value = getTable().get(s.charAt(i)) + notches;
            if(value > getTable().size()){
                value -= getTable().size();
            }
            code += getReverseTable().get(value);
        }
        setCode(code);
        return code;
    }
    public  String decode(String s){
        // this method decrypts the message
        // s - the message to be decrypted

        String text = "";
        for(int i = 0;i<s.length();i++){
            int pos = getTable().get(s.charAt(i)) - notches;
            if(pos <= 0){
                pos = getTable().size() + pos;
            }
            text += getReverseTable().get(pos)==null?" ":getReverseTable().get(pos);
        }
        return text;
    }
}

class Codages{
    private final Code[] tab;

    public Codages(Code[] tab){
        this.tab = tab;
    }
    public void test(String message) {
        // this method test different types of encrypting
        // message - is the message to be crypted

        String keyPlusLong = "";
        for (Code code : tab) {
            System.out.print("With the code : ");
            code.show();
            String encription = code.code(message);
            System.out.print("Encrypting of " + message + " : ");
            System.out.println(encription);
            System.out.print("Decrypting of " + encription + " : ");
            System.out.println(code.decode(encription));
            System.out.println("-----------------------------------");
            System.out.println();
        }
        for(Code code : tab){
            if(code.getClass() == WithRandomKey.class){
                if(((WithRandomKey) code).getKey().length() > keyPlusLong.length()){
                    keyPlusLong = ((WithRandomKey) code).getKey();
                }
            }
        }

    }
}

