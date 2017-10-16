import random

import modules.valueList


# CPUTest.py
# Purpose: This aims to test the CPU of the SuT by attempting to successfully generate 1 million random integers.
# Post: On successful generation, the console returns with "SUCCESS"; If an issue occurs, "ERROR: " will be printed to the console

# guidelines:
    # every section of code should run in a DEFINED main method, just in case specific tests would like to be imported as modules in the future
def main():
    print ("This module will test CPU performance through the generation of", modules.valueList.CPUTEST_MAX, "integers")

    try:
        generateNums(modules.valueList.CPUTEST_MAX)
        print ("SUCCESS")
    except:
        print ("ERROR. An unknown error occurred. CPU Terminated.")

def generateNums(UPPERBOUND):

    for num in UPPERBOUND:
        random.randint(0, 10)
main()