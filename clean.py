#!/usr/bin/python
# coding=utf-8
import zipfile
import shutil
import os
import os.path
import time
import datetime
import sys 


# 主函数的实现
if __name__ == "__main__":

    # build
    dir2 = "app/build"
    flag = os.path.exists(dir2)
    if flag:
        shutil.rmtree(dir2)

    print "clean sucess"
