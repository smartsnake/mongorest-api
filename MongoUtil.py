import pymongo
import sys
from datetime import datetime

#Required
#pip install pymongo

myclient = pymongo.MongoClient("mongodb://heroku_8jn3q3ml:fjvnsqjmiksc58nincmeqs846c@ds245677.mlab.com:45677/heroku_8jn3q3ml")


#Database name
mydb = myclient["heroku_8jn3q3ml"]
#Collection name
users = mydb["Login"]
invoices = mydb["invoice"]

def UserCreateDatabase():
    mylist = [
        { "_id": "1", "username": "admin", "password": "{noop}lamepassword", "role": "ADMIN"},
        { "_id": "2", "username": "Peter", "password": "{noop}lamepassword2", "role": "USER"},
        { "_id": "3", "username": "Amy", "password": "{noop}lamepassword3", "role": "USER"},
        { "_id": "4", "username": "Hannah", "password": "{noop}lamepassword4", "role": "USER"},
        { "_id": "5", "username": "Michael", "password": "{noop}lamepassword5", "role": "USER"},
        { "_id": "6", "username": "Sandy", "password": "{noop}lamepassword6", "role": "USER"},
        { "_id": "7", "username": "Betty", "password": "{noop}lamepassword7", "role": "USER"},
        { "_id": "8", "username": "Richard", "password": "{noop}lamepassword8", "role": "USER"},
        { "_id": "9", "username": "Susan", "password": "{noop}lamepassword9", "role": "USER"},
        { "_id": "10", "username": "Vicky", "password": "{noop}lamepassword10", "role": "USER"},
        { "_id": "11", "username": "Ben", "password": "{noop}lamepassword11", "role": "USER"},
        { "_id": "12", "username": "William", "password": "{noop}lamepassword12", "role": "USER"},
        { "_id": "13", "username": "Chuck", "password": "{noop}lamepassword13", "role": "USER"},
        { "_id": "14", "username": "Viola", "password": "{noop}lamepassword14", "role": "USER"}
        ]
    users.insert_many(mylist)
    print("Populated User Collection.")

def UserDeleteCollection():
    users.drop()
    print("Dropped User Collection")

def InvoiceCreateDatabase():
    mylist = [
	{
	"_id":"1",
	"lookupID":133,
	"customerID":105,
	"date": datetime.now().strftime("%Y"+"-"+"%m"+"-"+"%d"),
	"totalCost":404,
	"totalSale":0,
	"items":[]
	},
	{
	"_id": "2",
	"lookupID": 777,
	"customerID": 105,
	"date": datetime.now().strftime("%Y"+"-"+"%m"+"-"+"%d"),
	"totalCost": 555,
	"totalSale": 200,
	"items": [
		{
		"itemLookupID": 102,
		"quantity": 10,
		"cost": 5,
		"sellPrice": 10
		},
		{
		"itemLookupID": 400,
		"quantity": 10,
		"cost": 5,
		"sellPrice": 10
		}
		]
	},
	{
	"_id": "3",
	"lookupID": 302,
	"customerID": 106,
	"date": datetime.now().strftime("%Y"+"-"+"%m"+"-"+"%d"),
	"totalCost": 30,
	"totalSale": 400,
	"items": [
		{
		"itemLookupID": 311,
		"quantity": 20,
		"cost": 6,
		"sellPrice": 20
		}
		]
	},
	{
	"_id": "4",
	"lookupID": 100,
	"customerID": 107,
	"date": datetime.now().strftime("%Y"+"-"+"%m"+"-"+"%d"),
	"totalCost": 555,
	"totalSale": 1000,
	"items": [
		{
		"itemLookupID": 6,
		"quantity": 10,
		"cost": 5,
		"sellPrice": 100
		},
		{
		"itemLookupID": 14,
		"quantity": 10,
		"cost": 5,
		"sellPrice": 0
		},
		{
		"itemLookupID": 15,
		"quantity": 10,
		"cost": 5,
		"sellPrice": 0
		}
		]
	},
	{
	"_id": "5",
	"lookupID": 101,
	"customerID": 108,
	"date": datetime.now().strftime("%Y"+"-"+"%m"+"-"+"%d"),
	"totalCost": 60,
	"totalSale": 1000,
	"items": [
		{
		"itemLookupID": 6,
		"quantity": 10,
		"cost": 5,
		"sellPrice": 100
		}
		]
	}
	]
    invoices.insert_many(mylist)
    print("Populated Invoice Collection.")
def InvoiceDeleteCollection():
    invoices.drop()
    print("Dropped Invoice Collection")




if(len(sys.argv) > 2):
    if(sys.argv[1] == "invoice"):
        if(sys.argv[2] == "create"):
            InvoiceCreateDatabase()
        elif(sys.argv[2] == "delete"):
            InvoiceDeleteCollection()
    elif(sys.argv[1] == "user"):
        if(sys.argv[2] == "create"):
            UserCreateDatabase()
        elif(sys.argv[2] == "delete"):
            UserDeleteCollection()
    elif(sys.argv[1] == "all"):
        if(sys.argv[2] == "create"):
            UserCreateDatabase()
            InvoiceCreateDatabase()
        elif(sys.argv[2] == "delete"):
            UserDeleteCollection()
            InvoiceDeleteCollection()

    else:
        print("Invalid. HELP: python3 MongoUtil.py [OPTION]")
        print("[OPTION]: create, delete, help")
else:
    print("Invalid. HELP: python3 MongoUtil.py [OPTION]")
    print("[OPTION]: create, delete, help")

