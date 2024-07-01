#include <iostream>
#include <unistd.h>
#include <dcntl.h>
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/statvfs.h>
#include<stdio.h>
#include<stdlib.h>

using namespace std;

int main(int argc, char* argv[]){

    char path[] = argv[1];

    int fd = open(path, O_WRONLY, O_APPEND);
    if (fd < 0){
        perror("Error");
        return 1;
    }

    char* arg = {argv[1]};

    pid_t pp = fork();

    if(pp == 0) {
        fexecve("usr/bin/pluma", arg, NULL);
    } else if (pp > 0) {
        //int status;
        //wait(&status);
        sleep(100);
    } else {
        perror("Error");
    }

    return 0;
}