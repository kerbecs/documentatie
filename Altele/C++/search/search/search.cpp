#include <iostream>
#include <string>
#include <cmath>
using namespace std;

void search(string&, string, string);

int main()
{
	string text;
	string cuvant1, cuvant2;
	cout << "Introduceti textul: ";
	getline(cin, text);
	cout << "\nIntroduceti cuvantul care trebuie inlocuit: ";
	getline(cin,cuvant1);
	cout << "\nIntroduceti cuvantul care il va inlocuit: ";
	getline(cin,cuvant2);
	search(text, cuvant1, cuvant2);
	cout <<"\n"<<text;
}
void search(string& text, string cuvant1, string cuvant2)
{
	int poz1, poz2;
	int pozCuvant=0;
	int j=0;
	bool verificare = true;
	string nou;

	for (int i = 0; i < text.length(); i++)
	{
		if (text[i] == cuvant1[pozCuvant])
		{
			++pozCuvant;
			if (verificare)
			{
				poz1 = i;
				verificare = false;
			}
			if (pozCuvant == cuvant1.length() - 1)
			{
				poz2 = i;
				for (j; j <poz2; j++)
				{
					if (j == poz1)
					{
						nou += cuvant2;
						j = poz2+1;
					}
					else
					{
						nou += text[j];
					}
				}
			}
		}
		else
		{
			verificare = true;
			pozCuvant = 0;
		}
		if (i == text.length() - 1 && j != i)
		{
			for (j; j <= i; j++)
				nou += text[j];
		}
	}
	text = nou;
}
//acasa este bine si este frumos
// este -> fusese
//acasa fusese bine si 
//acasa fusese bine si este frumos