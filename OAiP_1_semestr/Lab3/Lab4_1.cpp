#include <iostream> 
using namespace std;

int main()
{
    int i, * mas, * mas1, M, N, tmp, S, sum = 0, j;

    cout << "S= ";
    cin >> S;

    cout << "N= ";
    cin >> N;
    mas1 = new int[N];
    for (i = 0; i < N; i++)
    {
        cout << "H(" << i + 1 << ")= ";
        cin >> mas1[i];
        sum += mas1[i];
    }

    mas = new int[sum + 1];
    mas[0] = 1;
    for (i = 0; i <= sum; i++)
        mas[i] = 0;

    int** mas_plus = new int* [sum + 1];
    for (i = 0; i < sum + 1; i++) {
        mas_plus[i] = new int[(sum+1)*2];
    }

    for (i = 0; i < sum+1; i++) {
        for (j = 0; j < (sum + 1) * 2; j++) {
            mas_plus[i][j] = 0;
        }
    }

    int** mas_minus = new int* [sum + 1];
    for (i = 0; i < sum + 1; i++) {
        mas_minus[i] = new int[(sum + 1)*2];
    }

    for (i = 0; i < sum + 1; i++) {
        for (j = 0; j < (sum + 1) * 2; j++) {
            mas_minus[i][j] = 0;
        }
    }

    bool* check = new bool[sum + 1];
    for (i = 0; i <= sum; i++) {
        check[i] = false;
    }

    for (i = 0; i < N; i++)
    {
        for (j = sum - 1; j >= 0; j--) {
            if (mas[j] == 1 && j + mas1[i] <= sum) {
                mas[j + mas1[i]] = 1;

                mas_plus[j + mas1[i]][j*2] = 1;
                mas_plus[j + mas1[i]][j * 2 + 1] += 1;
                mas_plus[j + mas1[i]][mas1[i]*2] = 1;
                mas_plus[j + mas1[i]][mas1[i] * 2 + 1] += 1;

                check[j + mas1[i]] = true;
            }
            if (check[S] == 1) {
                break;
            }
        }
        mas[mas1[i]] = 1;

        mas_plus[mas1[i]][mas1[i]*2] = 1;
        mas_plus[mas1[i]][mas1[i] * 2 + 1] += 1;

        check[mas1[i]] = true;
        if (check[S] == 1) {
            break;
        }
    }

    cout << "M= ";
    cin >> M;
    for (i = 0; i < M; i++)
    {
        cout << "B(" << i + 1 << ")= ";
        cin >> tmp;
        for (j = 0; j <= sum; j++) {
            if (mas[j] && j - tmp >= 0 && check[j - tmp] == false) {
                mas[j - tmp] = 1;

                mas_minus[j - tmp][tmp * 2] = 1;
                mas_minus[j - tmp][tmp * 2 + 1] += 1;

                for (int i3 = 0; i3 < (sum + 1) * 2; i3++) {
                    if (mas_minus[j][i3] != 0) {
                        if(i3 == tmp*2+1){
                            mas_minus[j - tmp][i3] += 1;
                        }
                        else {
                            mas_minus[j - tmp][i3] = mas_minus[j][i3];
                        }
                    }
                }
                check[j - tmp] = true;
                for (int i3 = 0; i3 < (sum + 1) * 2; i3++) {
                    if (mas_plus[j][i3] != 0)
                        mas_plus[j - tmp][i3] = mas_plus[j][i3];
                }
            }
            if (check[S] == 1) {
                break;
            }
        }
        if (check[S] == 1) {
            break;
        }
    }
    if (S<0 || S>sum)
        cout << "No" << endl;
    else
        if (mas[S] == 0)
            cout << "No" << endl;
        else {
            cout << "Yes" << endl;
            cout << "Money of pokupatel = " << endl;
            for (i = 0; i < (sum+1)*2; i++) {
                if (mas_plus[S][i] == 1) {
                    for (j = 0; j < mas_plus[S][i + 1]; j++) {
                        cout << i/2 << " ";
                    }
                    i++;
                }
            }
            cout << endl;
            cout << "Money of prodovets = " << endl;
            for (i = 0; i < (sum+1)*2; i++) {
                if (mas_minus[S][i] == 1) {
                    for (j = 0; j < mas_minus[S][i + 1]; j++) {
                        cout << i/2 << " ";
                    }
                    i++;
                }
            }
            cout << endl;
        }
    delete[] mas1;
    delete[] mas;
    system("pause");
    return 0;
}