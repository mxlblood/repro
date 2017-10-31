# VideoTest.py
# Purpose: This aims to test the Video-Graphics ability of the SUT by attempting to take and verify a screenshot.
# Post: On successful generation, console returns with "SUCCESS"; if an issue occurs,"ERROR: " will be printed to the console
import pyscreenshot as ImageGrab
import os

def getAndVerifyIMG(img):
    imgFile = os.stat("screenshotTest.png")
    fileSize = imgFile.st_size
    try:
        if (fileSize > 0):
            return True
        else:
            raise Exception
            return False
    except:
        return False

def main():
    #
    # IMG HANDLER
    #
    img = ImageGrab.grab()
    img.save("screenshotTest.png")

    try:
        if getAndVerifyIMG(img) == True:
            print("SUCCESS")
        else:
            print("ERROR:", Exception)
    except:
        raise Exception