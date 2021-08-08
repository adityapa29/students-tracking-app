# students-tracking-app
A simple Java web application built using Servlets, JSP and JDBC. The web application allow users to perform CRUD operations on student data. 

![student-read](https://user-images.githubusercontent.com/43026075/128635807-71f893a1-3387-49e2-b23c-b7d5ffc7f9a2.PNG)

A new student can be created using the "Add student" button. 

![student-create](https://user-images.githubusercontent.com/43026075/128636035-c6b22c0a-47fc-4e5a-953e-952020545286.PNG)

Once we click on the save button, the new record will be added to the student table.  

![student-create2](https://user-images.githubusercontent.com/43026075/128636182-5d93db11-8826-487a-908b-2038ec968ae0.PNG)

We can update any record in the table with new information for firstname, lastname and email using the update action.
We can delete any record in the table using the delete action. <br>

All the user requests will be redirected to the respective JSPs with the help of StudentControllerServlet. Also to perform any persistance operation we will make use of StudentDbUtil class which will 
make connection to the database. 

## Acknowledgements
JSP, Servlet, JDBC course on Udemy by Chad Darby.
