#include <iostream>
//#include <sys/types.h>
//#include <unistd.h>
#include <string>
#include <fstream>

//#define _POSIX_SOURCE
//#define _XOPEN_SOURCE

using namespace std;

enum instruction {
    q,
    cr,
    I,
    d,
    point,
    n,
    p
};

void print_str(int n, int c_file, int& index, string* contents) {
    for (int i = 0; i <= n; i++) {
        if (index == contents[c_file].length()) {
            break;
        }
        else {
            while (contents[c_file][index] != '\n' && index != contents[c_file].length()) {
                cout << contents[c_file][index];
                index++;
            }
            cout << "\n";
            if (index == contents[c_file].length()) {
                break;
            }
            index += 1;
        }
    }
}

int main() {
    string args[3] = { "file1.txt","file2.txt","file3.txt" };

    int size = sizeof(args) / sizeof(args[0]);
    if (size == 0) {
        cout << "Error: no arguments\n";
    }
    else if (size > 3) {
        cout << "Error: too many arguments\n";
    }
    else {

        //int* files = new int[size];

        //for (int i = 0; i < size; i++) {
        //    //files[i] = open(args[i], O_RDONLY);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //}

        string* contents = new string[size];

        ifstream file1, file2, file3;
        file1.open(args[0]);
        file2.open(args[1]);
        file3.open(args[2]);

        if (file1.is_open())
        {
            contents[0].assign((istreambuf_iterator<char>(file1.rdbuf())), istreambuf_iterator<char>());
            file1.close();
        }

        if (file2.is_open())
        {
            contents[1].assign((istreambuf_iterator<char>(file2.rdbuf())), istreambuf_iterator<char>());
            file2.close();
        }

        if (file3.is_open())
        {
            contents[2].assign((istreambuf_iterator<char>(file3.rdbuf())), istreambuf_iterator<char>());
            file3.close();
        }

        /*cout << "length = " << contents[0].length() << endl;
        for (int i = 0; i < contents[0].length(); i++) {
            cout <<" index ("<<i<<"):"<< contents[0][i];
        }
        cout << endl << "end" << endl;*/

        int c_file = 0;
        int instr = cr;
        int index = 0;

        int begin_index = 0;
        int n = 0;

        while (true) {
            if (instr == cr) {
                begin_index = index;
                n = 22;
                print_str(22, c_file, index, contents);
            }
            else if (instr == q) {
                break;
            }
            else if (instr == I) {
                begin_index = index;
                n = 0;
                print_str(0, c_file, index, contents);
            }
            else if (instr == d) {
                begin_index = index;
                n = 11;
                print_str(11, c_file, index, contents);
            }
            else if (instr == point) {
                if (index == 0) {
                    cout << "Error: this file was not readen\n";
                }
                else {
                    index = begin_index;
                    print_str(n, c_file, index, contents);
                }
            }
            else if (instr == n) {
                c_file++;
                if (c_file == size) {
                    cout << "Error: next file does not exist\n";
                    c_file--;
                }
                else {
                    index = 0;
                    begin_index = 0;
                    n = 0;
                }
            }
            else if (instr == p) {
                c_file--;
                if (c_file == -1) {
                    cout << "Error: previous file does not exist\n";
                    c_file++;
                }
                else {
                    index = 0;
                    begin_index = 0;
                    n = 0;
                }
            }
            bool answer = true;
            while (answer) {
                if (index != contents[c_file].length()) {
                    cout << ":";
                }
                else {
                    cout << "EOF:";
                }
                string next_instr;
                getline(cin, next_instr);
                if (next_instr == "<CR>") {
                    instr = cr;
                    answer = false;
                }
                else if (next_instr == "q") {
                    instr = q;
                    answer = false;
                }
                else if (next_instr == "I") {
                    instr = I;
                    answer = false;
                }
                else if (next_instr == "d") {
                    instr = d;
                    answer = false;
                }
                else if (next_instr == ".") {
                    instr = point;
                    answer = false;
                }
                else if (next_instr == "n") {
                    instr = n;
                    answer = false;
                }
                else if (next_instr == "p") {
                    instr = p;
                    answer = false;
                }
                else {
                    cout << "Error: instruction does not exist\n";
                }
            }
        }
    }
    return 0;
}