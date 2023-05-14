#include <iostream>
#include <string>
#include <fstream>

class test
{
	test(int a)
	{

	}
};

using namespace std;

fstream file;

int main()
{
	try
	{
		file = fstream("C:\\Users\\user\\Desktop\\C++.txt", ios::in, ios::out);
		file << "Python throws errors and exceptions when there is a code gone wrong, which may cause the\
		program to stop abruptly. Python also provides an exception handling method with the help of try-except.\
		Some of the standard exceptions which are most frequent include IndexError, ImportError, IOError, ZeroDivisionError,\
		TypeError, and FileNotFoundError. Here, we will see the Proper way to declare custom exceptions in modern Python. \
		A user can create his own error using the exception class. fstream file;";
	}
	catch (...)
	{

	}
	return 0;
}
