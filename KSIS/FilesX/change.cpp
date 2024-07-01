#include<stdio.h>
#include<stdlib.h>
#include <iostream>
#include <unistd.h>
#include <dcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

int main(int argc, char* argv[]){

    char path[] = argv[1];

    struct stat st;
    stat(path, &st);

    if (st_prove < 0){
        perror("Error: ");
        return 1;
    }

    int prove = truncate(path, 100)
    if (prove < 0){
        perror("Error: ");
        return 1;
    }

    return 0;
}