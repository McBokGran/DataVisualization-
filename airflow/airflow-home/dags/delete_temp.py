import os
import pandas as pd

def delete_temp():
    for file in os.scandir("/opt/temp"):
        print("deleting", file.path)
        os.remove("/opt/temp/" + file.name)