#include <iostream>
#include <iomanip>
#include <string>
using namespace std;
struct Otdel {
    string surname;
    string name;
    string otch;
    char pol;
    int year;
    string spec;
    int stag;
};
void Input(Otdel* baza,int N) {
    int prove = 0;
    cin.get();
    for (int i = 0; i < N && prove == 0; i++) {
        cout << "�������: ";
        getline(cin, baza[i].name);
        cout << "���: ";
        getline(cin, baza[i].surname);
        cout << "��������: ";
        getline(cin, baza[i].otch);
        cout << "���: ";
        cin >> baza[i].pol;
        cin.get();
        cout << "�������������: ";
        getline(cin, baza[i].spec);
        cout << "��� ��������: ";
        cin >> baza[i].year;
        cout << "����: ";
        cin >> baza[i].stag;
        cin.get();
        cout << endl;
        if (baza[i].year < 0 || baza[i].stag>2020 - baza[i].year || baza[i].stag < 0)
            prove = 1;
    }
}
void SORTub(Otdel* baza, int N) {
    for (int i = 1; i < N; i++)
        for (int j = i; j > 0 && baza[j - 1].stag < baza[j].stag; j--) {
            swap(baza[j - 1], baza[j]);
        }
}
void Output(Otdel* a,int N) {
    cout << "_____________________________________________________________________________________" << endl;
    cout << "|  �������  |    ���     |    ��������    |���|   �������������   |��� ��������|����|" << endl;
    cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
    for (int i = 0; i < N; i++) {
        cout << "|" << setw(11) << a[i].name << "|" << setw(12) << a[i].surname << "|" << setw(16) << a[i].otch << "|" << setw(3) << a[i].pol << "|" << setw(19) << a[i].spec << "|" << setw(12) << a[i].year << "|" << setw(4) << a[i].stag << "|" << endl;
        cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
    }
}
void OutputPens(Otdel* a, int N) {
    cout << "_____________________________________________________________________________________" << endl;
    cout << "|  �������  |    ���     |    ��������    |���|   �������������   |��� ��������|����|" << endl;
    cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
    for (int i = 0; i < N; i++) {
        if (a[i].year <= 2020 - 55) {
            cout << "|" << setw(11) << a[i].name << "|" << setw(12) << a[i].surname << "|" << setw(16) << a[i].otch << "|" << setw(3) << a[i].pol << "|" << setw(19) << a[i].spec << "|" << setw(12) << a[i].year << "|" << setw(4) << a[i].stag << "|" << endl;
            cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
        }
    }
}
int main(){
    setlocale(LC_ALL, "Russian");
    cout << "������� ���-�� �����������" << endl;
    int N;
    cin >> N;
    Otdel* baza = new Otdel[N];
    Input(baza, N);
    int prove = 0;
    for (int i = 0; i < N && prove == 0; i++)
        if (baza[i].year < 0 || baza[i].stag>2020 - baza[i].year || baza[i].stag < 0)
            prove = 1;
    if (prove == 0) {
        cout << "��������� ������ �����������" << endl;
        Output(baza, N);
        cout << endl;
        SORTub(baza, N);
        cout << "������ ����������� �� �������� �����" << endl;
        Output(baza, N);
        cout << endl;
        cout << "������ ����������� ����������� ��������" << endl;
        OutputPens(baza, N);
        cout << endl;
    }
    else cout << "������" << endl;
    system("pause");
    return 0;
}