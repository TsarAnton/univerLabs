#include <iostream>
#include <string>
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");
	
	char str[50];
	cout << "¬ведите строку" << endl;
	cin.getline(str, 50);
	string S(str);

	int N;
	cout << "¬ведите N" << endl;
	cin >> N;

	if (N >= 0) {
		if (N < S.size()) {
			S.erase(0, N);
			cout << S << endl;
		}
		else {
			S.insert(0, N - S.size(), '.');
			cout << S << endl;
		}
	}
	else
		cout << "N должно быть неотрицательным" << endl;

	system("pause");
	return 0;
}
