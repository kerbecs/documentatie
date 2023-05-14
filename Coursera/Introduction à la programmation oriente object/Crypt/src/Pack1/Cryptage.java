package Pack1;
import java.util.HashMap;
import java.util.Random;

import java.util.Random;

public class Cryptage {
    public static void main(String[] args){
        String message = "COURAGEFUYONS";
        String cryptage;
// PARTIES A DECOMMENTER AU FUR ET A MESURE SELON l'ENONCE
/*
// TEST A CLE */
        Code acle1 = new ACle("COURAGEFUYONS", "EQUINOXE");
        System.out.print("Avec le code : " );
        acle1.affiche();
        cryptage = acle1.code(message);
        System.out.print("Codage de " + message + " : ");
        System.out.println(cryptage);
        System.out.print("Decodage de " + cryptage + " : ");
        System.out.println(acle1.decode(cryptage));
        System.out.println("-----------------------------------");
        System.out.println();
// FIN TEST A CLE


// TEST A CLE ALEATOIRE
        Code acle2 = new ACleAleatoire(5);
        System.out.print("Avec le code : " );
        acle2.affiche();
        cryptage = acle2.code(message);
        System.out.print("Codage de " + message + " : ");
        System.out.println(cryptage);
        System.out.print("Decodage de " + cryptage + " : ");
        System.out.println(acle2.decode(cryptage));
        System.out.println("-----------------------------------");
        System.out.println();
// FIN TEST A CLE ALEATOIRE


// TEST CESAR

        Code cesar1 = new Cesar("Cesar", 5);
        System.out.print("Avec le code : " );
        cesar1.affiche();
        cryptage = cesar1.code(message);
        System.out.print("Codage de " + message + " : ");
        System.out.println(cryptage);
        System.out.print("Decodage de " + cryptage + " : ");
        System.out.println(cesar1.decode(cryptage));
        System.out.println("-----------------------------------");
        System.out.println();
// FIN TEST CESAR


// TEST CODAGES


        System.out.println("Test CODAGES: ");
        System.out.println("------------- ");
        System.out.println();
        Code[] tab = { // Decommentez la ligne suivante
// si vous avez fait la classe Cesar
                new Cesar("cesar", 5),
                new ACle("a cle", "EQUINOXE") ,
                new ACleAleatoire(5),
                new ACleAleatoire(10)};
        Codages codes = new Codages(tab);
        codes.test(message);

// FIN TEST CODAGE
    }
}


class Utils {
    // genere un entier entre 1 et max (compris)
    public static int randomInt(int max) {
        Random r = new Random();
        int val = r.nextInt();
        val = Math.abs(val);
        val = val % max;
        val += 1;
        return val;
    }
}

abstract class Code{
    private String nom;
    private String code;
    private HashMap <Character,Integer> table;
    private HashMap <Integer,Character> reverseTable;

    public Code(String nom){
        this.nom = nom;
        table = new HashMap<>();
        reverseTable = new HashMap<>();
        createTable();
    }
    public void createTable() {
        table.put(' ', 0);
        for (char i = 'A'; i <= 'Z'; i++)
            table.put(i, i - 'A' + 1);

        char x = 'A';
        reverseTable.put(0,' ');
        for(int i = 1;x<='Z';i++,x++)
            reverseTable.put(i,x);
    }

    public abstract String code(String s);
    public abstract String decode(String s);
    public abstract void affiche();
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
    public String getNom() {
        return nom;
    }
    public HashMap<Character, Integer> getTable() {
        return table;
    }
    public HashMap<Integer, Character> getReverseTable() {
        return reverseTable;
    }
}
class ACle extends Code{
    private String cle;
    public ACle(String nom,String cle){
        super(nom);
        this.cle = cle;
    }
    public ACle(String nom){
        super(nom);
    }
    public int longueur(){
        return cle.length();
    }

    @Override
    public String code(String s)
    {
        int min = Math.min(s.length(),cle.length());
        int max = Math.max(s.length(),cle.length());
        int[] somme = new int[max];
        String code = "";

        for(int poz1 = 0,poz2 = 0;poz1<max;poz1++,poz2++){
            if(poz2==min)
                poz2 = 0;
            if(s.length()>=cle.length()){
                somme[poz1] = getTable().get(s.charAt(poz1)) + getTable().get(cle.charAt(poz2));
            }

            else{
                somme[poz1] = getTable().get(s.charAt(poz2)) + getTable().get(cle.charAt(poz1));
            }

        }
        for(int i = 0;i<somme.length;i++)
        {
            if(somme[i] > 26)
                somme[i] -= 26;
        }

        for(int i : somme){
            code += getReverseTable().get(i);
        }

        setCode(code);
        return code;

    }
    @Override
    public String decode (String s){

        int poz = 0;
        String decodeur = "";

        int max = Math.max(s.length(),cle.length());
        int min = Math.min(s.length(),cle.length());

        for(int x = 0,y=0;x<max;x++,y++){
            int somme;
            if(y == min)
                y = 0;
            if(s.length()>=cle.length()){
                somme = getTable().get(s.charAt(x)) - getTable().get(cle.charAt(y));
            }
            else{
                somme = getTable().get(s.charAt(y)) - getTable().get(cle.charAt(x));
            }

            if(somme<=0)
                somme += 26;

            decodeur += getReverseTable().get(somme);
        }
        return decodeur;
    }

    @Override
    public void affiche() {
        System.out.println("a cle"+" avec "+cle+" comme cle");
    }

    public void setCle(String cle){
        this.cle = cle;
    }
    public String getCle(){
        return cle;
    }
}
class ACleAleatoire extends ACle{

    private final int longeur;
    private String cle = "";
    public ACleAleatoire(int longeur){
        super("a cle aleatoire");
        this.longeur = longeur;
        genereCle();
    }
    public void genereCle(){
        Random random = new Random();

        for(int i = 0;i<longeur;i++){
            cle += getReverseTable().get(random.nextInt(1,27));
        }
        setCle(cle);
    }
    @Override
    public void affiche() {
        System.out.println("a cle aleatoaire"+" avec "+cle+" comme cle");
    }

}

class Cesar extends Code{
    private int crans;

    public Cesar(String nom, int crans){
        super(nom);
        this.crans = crans;
    }
    public void affiche() {
        System.out.println(getNom()+" a "+crans+" craints");
    }
    public String code(String s)
    {
        String code = "";
        for(int i = 0;i<s.length();i++){
            int value = getTable().get(s.charAt(i)) + crans;
            if(value > getTable().size()){
                value -= getTable().size();
            }
            code += getReverseTable().get(value);
        }
        setCode(code);
        return code;
    }
    public  String decode(String s){
        String text = "";
        for(int i = 0;i<s.length();i++){
            int poz = getTable().get(s.charAt(i)) - 5;
            if(poz <= 0){
                poz = getTable().size() + poz;
            }
            text += getReverseTable().get(poz);
        }
        return text;
    }
}

class Codages{
    private Code[] tab;

    public Codages(Code[] tab){
        this.tab = tab;
    }
    public void test(String message)
    {
        String clePlusLongue = "";
        for (Code code : tab) {
            System.out.print("Avec le code : ");
            code.affiche();
            String cryptage = code.code(message);
            System.out.print("Codage de " + message + " : ");
            System.out.println(cryptage);
            System.out.print("Decodage de " + cryptage + " : ");
            System.out.println(code.decode(cryptage));
            System.out.println("-----------------------------------");
            System.out.println();
        }
        for(Code code : tab){
            if(code.getClass() == ACleAleatoire.class){
                if(((ACleAleatoire) code).getCle().length() > clePlusLongue.length()){
                    clePlusLongue = ((ACleAleatoire) code).getCle();
                }
            }
        }

        System.out.println("Code aleatoire a cle maximale :\n" +
                "a cle aleatoire avec "+ clePlusLongue+ " comme cle");
    }
}

