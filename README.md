# OOAD_project3
# Team Members: Zora Watters and Jake Andrus

How to run: Just run from main!
Language and Environment: We used Java and InjelliJ!
About the Code: Here we used 3 patterns: Factory to make cars and add options, Observer to print the day's info, and Decorator to add options. We have a customer class that holds each type, business, casual, and regular. Each customer is instantiated in main and has their own conditions for renting a car, which is stored in their subclasses. If those condtitions are met, the customer will call the rental function in store, which is a class that keeps track of rental records and the passing of time. Store will then add options to the car that the cusomer picked and create a rental record for that customer. In main, we will call the pass day function that will have the store decrement the time, ncrement the day, and check for returns. We assume no 2 customers will try to rent at the same time.
