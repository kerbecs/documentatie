#include <iostream>
#include <conio.h>
using namespace std;

template<class T> //permite inserarea oricarui tip de valori
class celula
{
private:
	celula* next;//pointer la urmatorul nod
	celula* last;//pointer la nodul curent(ultimul existent la un moment dat)
	T valoare;//valoarea nodului
	unsigned int nrElemente;//nr. de copii al nodului
public:
	celula(T = NULL);
	void add(T);//adaugam un nod
	void parcurgere();//parcurgem arborele

};
template<class T>
celula<T>::celula(T valoare)
{
	next = NULL;
	last = this;
	this->valoare = valoare;
}

template<class T>
void celula<T>::add(T valoare)
{
	if (nrElemente==0)//daca nodul nu are copii, se egaleaza doar valoarea
	{
		last->valoare = valoare;
		++nrElemente;
	}
	else//daca nodul va avea copii, se creeaza unul si ultimul element devine el
	{ 
		last->next = new celula(valoare);
		last = last->next;
}
	}

template<class T>
void celula<T>::parcurgere()
{
	celula* parcurgere = this;//nod secundar de parcurgere
	while (parcurgere != NULL)//daca nodul curent e NULL,s-a ajuns la final
	{
		cout << parcurgere->valoare << "  ";
		parcurgere = parcurgere->next;//se trece la urmatorul nod
	}
}
int main()
{
	unsigned int nr;
	celula<char> a;
	for (char valoare = 'A'; valoare <= 'N'; valoare++)
	{
		a.add(valoare);
	}
	a.parcurgere();
	return 0;
}


