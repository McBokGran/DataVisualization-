import os
import mysql.connector
import pandas as pd
from os.path import dirname

def read_file_ids():
    mydb = mysql.connector.connect(
        host='studmysql01.fhict.local',
        user='dbi449841',
        password='12345678',
        database='dbi449841'
    )

    print(mydb)

    mycursor = mydb.cursor()

    mycursor.execute("SELECT * FROM file")

    result = mycursor.fetchall()
    traversed_files = []
    for x in result:
        for y in x:
            traversed_files.append(y)

    insert_file_sql = "INSERT INTO file (FileID) VALUES (%s)"

    dirlist = os.listdir("/opt/datafiles")

    for x in dirlist:
        for subdir, dirs, files1 in os.walk("/opt/datafiles/" + x):
            for dir in dirs:
                print("/opt/datafiles/" + x + "/" + dir)
                for subdir, dirs, files in os.walk("/opt/datafiles/" + x + "/" + dir):
                    for folder in dirs:
                        print("/opt/datafiles" + x + "/" + dir + "/" + folder)
                        for subdir, dirs, files in os.walk("/opt/datafiles/" + x + "/" + dir + "/" + folder):
                            newestDate = 0
                            newestTraversedFile = ''
                            for file in files:
                                if file.endswith(".csv") and file in traversed_files:
                                    date = os.path.getmtime(os.path.join(subdir, file))
                                    if date > newestDate:
                                        newestDate = date
                                        newestTraversedFile = file

                                #set all the untraversed files up to be inserted in the database
                                if file.endswith(".csv") and file not in traversed_files:
                                    mycursor.execute(insert_file_sql, (file,))
                                    df = pd.read_csv(os.path.join(subdir, file), sep=';')
                                    print(df.machine_id.to_string(index=False))
                                    df.to_csv("/opt/temp/" + file, compression='gzip')


                            #set the data of the newest file of the traversed files up to be inserted in the database
                            if newestTraversedFile:
                                print(newestTraversedFile)
                                df = pd.read_csv(os.path.join(subdir, newestTraversedFile), sep=';')
                                df['machine_id'] = x
                                df.to_csv("/opt/temp/" + newestTraversedFile, compression='gzip')

    #file_path = dirname(dirname(dirname(__file__)))
    ##print(file_path)
    #for subdir, dirs, files in os.walk("/opt/datafiles"):
    #    for dir in dirs:
    #        print("this is a subdirectory", dir)
#
    #    print(subdir)
    #    for dir in dirs:
    #        print(file_path + dir)
    #        for subdir, dirs, files in os.walk("/opt/datafiles/" + dir):
    #            newestDate = 0
    #            newestTraversedFile = ''
    #            for file in files:
    #                if file.endswith(".csv") and file in traversed_files:
    #                    #check the newest file of the files already scanned previously,
    #                    #because new data might be appended
    #                    date = os.path.getctime(os.path.join(subdir, file))
    #                    if date > newestDate:
    #                        newestDate = date
    #                        newestTraversedFile = file
#
    #                #set all the untraversed files up to be inserted in the database
    #                if file.endswith(".csv") and file not in traversed_files:
    #                    mycursor.execute(insert_file_sql, (file,))
    #                    df = pd.read_csv(os.path.join(subdir, file), sep=';')
    #                    df.to_csv(file_path + "/temp/" + file, compression='gzip')
#
    #            #set the data of the newest file of the traversed files up to be inserted in the database
    #            if newestTraversedFile:
    #                df = pd.read_csv(os.path.join(subdir, newestTraversedFile), sep=';')
    #                df.to_csv("/opt/temp/" + newestTraversedFile, compression='gzip')
                    

    mydb.commit()