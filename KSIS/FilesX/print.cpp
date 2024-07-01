#include <iostream>
#include <unistd.h>
#include <dcntl.h>
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/statvfs.h>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

int main() {

    pid_t new_pid = fork();

    if(new_pid < 0) {
        perror("Error");
        return 1;
    }

    pid_t pid = getpid();
    
    if(pid < 0) {
        perror("Error");
        return 1;
    }

    pid_t ppid = getppid();

    if(ppid < 0) {
        perror("Error");
        return 1;
    }

    cout << "PID = " << pid << endl;
    cout << "PPID = " << ppid << endl;

    return 0;
}