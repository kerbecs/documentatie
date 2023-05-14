#include <iostream>
using namespace std;
class celula //fiecare element din structura
{
protected:
    int element; //o valoare
public:
    celula* next; //referinta catre urmatoarea celula

    celula()
    {
        next = NULL;//seteaza urmatorea celula egala cu NULL
        element = 0;
    }
    celula(int element)
    {
        next = NULL;
        this->element = element; //seteaza elementul cu un nr. dat
    }
    void showElement() //afiseaza valoarea elementului
    {
        cout << element << endl;
    }
    celula* getNext() //returneaza o referinta la urmatoarea celula
    {
        return next;
    }
    //clase care vor accesa membrii privati ai clasei celula
    friend class filo;
    friend class fifo;
};

class filo //coada in care ultimul venit e servit
{
protected:
    celula* structura; //structura propriu zisa
    int nrElemente; //numarul de elemente din structura
public:
    filo()
    {
        structura = NULL; //egaleaza structura initial cu NULL
        nrElemente = 0;
    }
    virtual void insert(int valoare)
    {
        static celula* secundar = NULL; //var statica ce va pastra o referinta la celula actuala
        structura = new celula(valoare); //crearea unei noi celule
        ++nrElemente;//creste numarul de elemente cu o unitate
        structura->next = secundar;//inserarea unei noi celule
        secundar = structura;
    }
    void remove(int pozitie)
    {
        int i = 0;
        int operatii = 0;
        celula* structuraNoua = new celula(); //va stoca toate valorile cu exceptia celei sterse
        celula* secunda = structuraNoua;//va pastra o reerinta la prima celula din structura
        while (structura != NULL) //odata ce se ajunge la NULL, s-a ajuns la final
        {
            if (i == pozitie) //cand se ajunge la pozitia dorita
            {
                structura = structura->next;//se trece peste pozitia de sters
                if (structura != NULL)//in caz ca elementul este ultimul
                {

                    structuraNoua->element = structura->element;
                }
            }
            else
            {
                if (structura != NULL) //daca nodul nu e null,exista ceva de inserat
                {
                    structuraNoua->element = structura->element; //se copie valoarea
                    structura = structura->next; //se trece la urmatorul nod
                }
                if (nrElemente-1 == pozitie)//pentru cazul in care dorim sa eliminam ultimul element din structura
                    ++operatii;
                if (structura != NULL && operatii!=(nrElemente-1)) //se mareste noua structura cu un nod daca aceasta nu e nula si daca
                    //nu dorim sa eliminam ultimul element. Daca s-ar elimina ultimul element, atunci ar ramane o celula in plus.
                {
                    structuraNoua->next = new celula();
                    structuraNoua = structuraNoua->next;
                }
            }
            ++i;
         
        }
        structura = new celula();
        structura = secunda;//structura primeste referinta la primul sau nod

    }
    friend void operator<<(ostream& out, filo obj)//supraincarca operatorului <<
    {
        celula* structura2 = obj.structura;
        cout << "---------------------------------------------------" << endl;
        cout << "                 Parcurgere FILO                   " << endl;
        cout << "---------------------------------------------------" << endl;
        while (structura2 != NULL)//se afiseaza elementele pana se ajunge la unul NULL
        {
            structura2->showElement();//arata elementul
            structura2 = structura2->getNext();//se trece la urmatorul nod
        }
    }
    int operator[](int poz)//supraincarca operatorul[]
    {
        celula* secundar = structura;
        for (int i = 0;; i++)
        {
            if (i == poz) //cauta anume pozitia dorita
            {
                return secundar->element;
                break;//odata ce se afiseaza, se iese din bucla
            }
            else
            {
                secundar = secundar->next;//se incearca urmatorul nod
            }
        }
    }
};

class fifo : public filo
{
public:
    fifo()
    {
        structura = NULL;
    }
    //Override
    void insert(int valoare)
    {
        static celula* intermediar; //va seta noile elemente ale noilor celule
        if (structura == NULL) //la prima inserare se executa
        {
            structura = new celula(valoare);//se creaza structura
            intermediar = new celula(); //se creaza un pointer secundar
            intermediar = structura; //pointerul ia valoarea primului nod creat
        }
        else
        {
            intermediar->next = new celula(valoare);//se creaza urmatorul nod
            intermediar = intermediar->next;//se trece la urmatorul nod
        }
    }
    friend void operator<<(ostream& out, fifo obj)//supraincarcarea operatorului <<
    {
        celula* structura2 = obj.structura;
        cout << "---------------------------------------------------" << endl;
        cout << "                 Parcurgere FIFO                   " << endl;
        cout << "---------------------------------------------------" << endl;
        while (structura2 != nullptr)
        {
            structura2->showElement();
            structura2 = structura2->getNext();
        }
    }
};
int main()
{
    filo obj;
    obj.insert(5);
    obj.insert(8);
    obj.insert(1);
    obj.insert(4);
    obj.insert(3);
    obj.insert(6);
    obj.insert(9);
    cout << obj;
    obj.remove(6);
    cout << obj;
    cout << endl;
    cout << "Elementul cu pozitia 1: ";
    cout << obj[1] << endl;

    fifo obj2;
    obj2.insert(5);
    obj2.insert(8);
    obj2.insert(1);
    obj2.insert(4);
    obj2.insert(3);
    obj2.insert(6);
    obj2.insert(9);
    cout << obj2;
    obj2.remove(3);
    cout << obj2;
    cout << endl;
    cout << "Elementul cu pozitia 1: ";
    cout << obj2[1] << endl;
    return 0;
}