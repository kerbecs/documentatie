#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ofstream scrie;
	ifstream citeste;

	try
	{
		scrie.open("C:\\Users\\user\\Desktop\\c.txt");
		string text = "Aceasta este prima linie \niar aceasta este a 2 linie.";
		scrie.write(text.c_str(), text.length());
		scrie.close();
	}
	catch (...)
	{
		throw 10;
	}
	return 0;
}