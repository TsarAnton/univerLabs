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
        perror("Error: ");
        return 1;
    }

    struct stat st;
    int st_prove = stat(path, &st);

    if (st_prove < 0){
        perror("Error: ");
        return 1;
    }

    cout<<"File information:"<<endl;
    cout<<"ID of device containing file: "<< st.st_dev << endl;
    cout<<"Inode number: "<<st.st_ino <<endl;
    cout<<"File type and mode: "<<st.st_mode <<endl;
    cout<<"Number of hard links: "<<st.st_nlink <<endl;
    cout<<"User ID of owner: "<<st.st_uid <<endl;
    cout<<"Group ID of owner: "<<st.st_gid <<endl;
    cout<<"Device ID (if special file): "<<st.st_rdev <<endl;
    cout<<"Total size, in bytes: "<<st.st_size <<endl;
    cout<<"Block size for filesystem I/O: "<<st.st_blksize <<endl;
    cout<<"Number of 512B blocks allocated: "<<st.st_blocks <<endl;
    cout<<"Time of last access: "<<ctime(&st.st_ctime) <<endl;
    cout<<"Time of last modification: "<<ctime(&st.st_atime) <<endl;
    cout<<"Time of last status change: "<<ctime(&st.st_mtime) <<endl;

    struct statvfs stvfs;
    int stvfs_prove = statvfs(path, &stvfs);

    if (stvfs_prove < 0){
        perror("Error: ");
        return 1;
    }

    cout<<"File system information:"<<endl;
    cout<<"Filesystem block size: "<<stvfs.f_bsize <<endl;
    cout<<"Fragment size: "<<stvfs.f_frsize <<endl;
    cout<<"Size of fs in f_frsize units: "<<stvfs.f_blocks <<endl;
    cout<<"Number of free blocks: "<<stvfs.f_bfree <<endl;
    cout<<"Number of free blocks for unprivileged users: "<<stvfs.f_bavail <<endl;
    cout<<"Number of inodes: "<<stvfs.f_files <<endl;
    cout<<"Number of free inodes: "<<stvfs.f_ffree <<endl;
    cout<<"Number of free inodes for unprivileged users: "<<stvfs.f_favail <<endl;
    cout<<"Filesystem ID: "<<stvfs.f_fsid <<endl;
    cout<<"Mount flags: "<<stvfs.f_flag <<endl;
    cout<<"Maximum filename length: "<<stvfs.f_namemax <<endl;

    return 0;
}