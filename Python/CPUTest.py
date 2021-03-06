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
        if(generateNums(modules.valueList.CPUTEST_MAX)==True):
            print ("SUCCESS")
        else:
            # this makes sure that an exception is raised if the CPUTest fails on a SUT
            raise Exception
    except:
        print("ERROR. An unknown error occurred. CPU Terminated.")

def generateNums(UPPERBOUND):
    count = 0
    try:
        while (count<UPPERBOUND):
            random.randint(0,9)
            count+=1
        return True
    except:
        print("Error")
        return False
main()