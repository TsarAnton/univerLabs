#include "Header.h"
#include <Windows.h>

//бесконечно проигрывает мелодию
DWORD WINAPI PlayMusic(CONST LPVOID lpParam) {
    while (true) {
        Beep(247, 500);
        Beep(417, 500);
        Beep(417, 500);
        Beep(370, 500);
        Beep(417, 500);
        Beep(329, 500);
        Beep(247, 500);
        Beep(247, 500);
        Beep(247, 500);
        Beep(417, 500);
        Beep(417, 500);
        Beep(370, 500);
        Beep(417, 500);
        Beep(497, 500);
        Sleep(500);
        Beep(497, 500);
        Beep(277, 500);
    }
}