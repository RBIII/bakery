							Rocco Basso & Conor Ebbs

ReadME file

This file details the steps that go into running the program along with how to utilize all its functionality

1) Open Command Prompt

2) Navigate to there directory where the files (Bakery.java, Customer.java, Order.java, Item.java) are stored

3) type java Bakery to create an empty Bakery or java Bakery orders.txt bakeryItem.txt to create a Bakery with orders/customers and items initialized

4) This will bring you to the main menu of the bakery which will give 5 options

Customers: Has the following information/options

View All Customers: Shows all customer
	Add a Customer: Allows the user to input the fields required 	to make a new customer
	Update Customer Information: Allows the user update a 	customer with the ID they give. If they don't know the 	appropriate ID it lets them view all customers to find it
	Search: Brings the user to the search menu, details below
	Main Menu: Returns the user to the Main Menu

Orders: Has the following information/options
	View all Order: Shows all current and past orders
	View all unpaid Orders: Show all current, unpaid orders
	Add a new order: Allows the user to input the fields 	required to make a new order
	Update an Order: Allows the user update the order with the 	ID they give. If they don't know the appropriate ID it lets 	them view all orders to find it
	Search: Brings the user to the search menu, details below
	Main Menu: Returns the user to the Main Menu

Inventory: Has the following information/options
	View all Items: Show all available items
	Add Item: Allows the user to input the fields to create an 	item
	Update Item: Allows the user to update the item with the ID 	they give. If they don't know the appropriate ID it lets 	them view all items to find it
	Search: Brings the user to the search menu, details below
	Main Menu: Returns the user to the Main Menu

Search: Has the following search options
	Customer Search
	Search by Customer ID
	View all Customers

	Order Search
	Search by Order ID
	Search Orders containing a specific item
	Search by Order Date
	Search by Pickup Date

Close Program and Write to files: 
	This option is for the end of the day, it will close the 	program and write all information to two text files 	(orders.txt bakeryItem.txt). When the program is re-opened 	the next day it will read from these files again and will 	hold all past data in this manner

