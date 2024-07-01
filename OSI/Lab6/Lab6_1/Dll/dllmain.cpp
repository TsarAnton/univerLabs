//это непосредственно сама dll
//основная часть создается автоматически visual studio (создать проект => мастер классических приложений Windows => указываем путь => из выпадающего списка выбираем DLL (!НЕ! СОЗДАВАТЬ ПУСТОЙ ПРОЕКТ))
//мы добавляем лишь 2 файла .h и .cpp (в этом случае Functions.h и Functions.cpp)
#include "framework.h"

BOOL APIENTRY DllMain( HMODULE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
                     )
{
    switch (ul_reason_for_call)
    {
    case DLL_PROCESS_ATTACH:
    case DLL_THREAD_ATTACH:
    case DLL_THREAD_DETACH:
    case DLL_PROCESS_DETACH:
        break;
    }
    return TRUE;
}

