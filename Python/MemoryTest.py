# MemoryTest.py
#
# Author: Eddie
# Simple script that performs a memory test on the SUT hardware
# This test determines if the program can produce a list with one million items
# Prints SUCCESS if successful, FAIL if not
#
# This program will run on a SUT

import random

from Python.modules import valueList


def generateList(someList):
    count = 0
    while (count<valueList.MEMTEST_MAX):
        someList.append(random.randint(0,9))
        count+=1
    return someList


def main():
    memList = []
    memList = generateList(memList)
    if len(memList) > 0:
        print("SUCCESS")
    else:
        print("FAIL")
        raise Exception