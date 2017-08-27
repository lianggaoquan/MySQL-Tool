# MySQL Tool

This is a simple tool that can operate the MySQL databases quickly.
Example && user guide:

(1).Query:

Please enter the name of database:
test_jdbc
Enter the localhost and password of the database:
3308
123			# The localhost and password here is not real.

Enter the list you want to edit:
t_user
Enter the operation you want to continue:
enter 1 to query, 2 to delete record, and 3 to insert.
1
Enter the operation field:
userName
Enter the operation symbol
=
Inquiring the dataset...
Enter the inqure condition:
Apple
==========finding the result===========
5---Apple---111



(2).Delete the record in database:

Please enter the name of database:
test_jdbc
Enter the localhost and password of the database:
3308
123

Enter the list you want to edit:
t_user
Enter the operation you want to continue:
enter 1 to query, 2 to delete record, and 3 to insert.
2
Warning ! you are deleting the records....
Enter the operation field:
id
Enter the operation symbol
=
Enter the delete condition:
6
=======Done!=======



(3).Insert record to the database:

Please enter the name of database:
test_jdbc
Enter the localhost and password of the database:
3308
123

Enter the list you want to edit:
t_user
Enter the operation you want to continue:
enter 1 to query, 2 to delete record, and 3 to insert.
3
How many variables do you want to deal with?
3
Now enter the variable names:
id
userName
pwd
Now enter the insert values:
6
google
145
===========Done!=============


