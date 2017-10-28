# NetworkingTest.py
# Purpose: This aims to test the Networking ability of the SUT by attempting to successfully connect to and download from a website.
# Post: On successful generation, console returns with "SUCCESS"; if an issue occurs,"ERROR: " will be printed to the console
import urllib.request


def getandVerifyURL(url):
    try:
        reponse = urllib.request.urlopen(url)
        webData = reponse.read()
        text = webData.decode("utf-8")
        if (len(text) > 0):
            return True
        else:
            return Exception
        return True
    except ConnectionError:
        return False


def main():
    urlString = "http://eperez6.create.stedwards.edu/index.html"
    try:
        if getandVerifyURL(urlString) == True:
            print("SUCCESS")
        else:
            print("ERROR", Exception)
    except:
        raise Exception

main()