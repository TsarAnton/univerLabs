//статическая загрузка dll
//для этого нужно собрать dll (в visual studio Сборка => Собрать проект)
//после сборки в папке Debug (находиться в самой "первой" папке решения) появляется файл с расширением .lib (в нашем случае Project2.lib)
//копируем его в каталог этого проекта
//создаем файл Functions.h, в котором описываем прототипы функций из dll
#include <iostream>
#include "Functions.h"
//подключаем файл .lib
#pragma comment(lib, "Project2.lib")
int main() {
    hello();
    std::cout << mySum(5, 4) << std::endl;
    system("pause");
    return 0;
}

