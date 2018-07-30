# Csv to Arff Converter


# What is ARFF file ?
An ARFF (Attribute-Relation File Format) file is an ASCII text file that describes a list of instances sharing a set of attributes. ARFF files were developed by the Machine Learning Project at the Department of Computer Science of The University of Waikato for use with the Weka machine learning software. 

For source and more information visit: https://www.cs.waikato.ac.nz/ml/weka/arff.html

# How to use converter ?
Set file locations:
```java
String fileLoc = "CSV_FILE_LOCATION";
//At first Arff creating as Txt.
File file = new File("FILE_LOCATION/x.txt");
//At the end of the code, Txt file is renaming as Arff.
File file2 = new File("FILE_LOCATION/converted.arff"); // Converted file location
```
When you run the converter, you'll get 3 options to set your arff file attributes.

(Examples are based on iris.csv file. iris.csv source: https://gist.github.com/netj/8836201)
```
Labels are:
"sepal.length","sepal.width","petal.length","petal.width","variety"
All Nominal : 1 | All Numeric : 2 | Personal Choice : 3
```
*All Nominal* option sets all attributes unique.

*All Numeric* option sets all attributes real.

If you select the third option :
```
Labels are:
"sepal.length","sepal.width","petal.length","petal.width","variety"
All Nominal : 1 | All Numeric : 2 | Personal Choice : 3
3
Enter the number of nominal value
1
Enter the index/es of nominal value/s (First label index is 0):
```

As you can see on the example, enter number of nominal value. After that, you need to enter label index/es. If you want *sepal.length* as nominal value enter 0, for *variety* enter 4. (*Indexes are starting from 0.*)

If you want both as nominal value, enter like that:
```
Enter the number of nominal values
2
Enter the index of nominal values
0
4
```
