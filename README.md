# Spam Assassin

SpamAssassin is a tool to classify spam messages or not using classifer technique in machine learning. 
You also can add more data to train this machine learning become better to classify spam messages or not by re-evaluate the test data, and save it to train data.

## Getting Started

These application is requirements for Laboratory of Graphics and Artificial Intelligence.

Feature :
- Evaluate and predict the test data
- Open arff file (using Weka Library)
- GUI

## Screenshot
![MainWindow](https://raw.githubusercontent.com/ironlota/spamassassin/master/image/mainwindow.png)
![ChooseFile](https://raw.githubusercontent.com/ironlota/spamassassin/master/image/choosefile.png)
![Tested](https://raw.githubusercontent.com/ironlota/spamassassin/master/image/tested.png)
![Classifier](https://raw.githubusercontent.com/ironlota/spamassassin/master/image/classifier.png)
![Train](https://raw.githubusercontent.com/ironlota/spamassassin/master/image/train.png)

### Prerequisites

What things you need to install the software and how to install them

```
Installed :
- Java Development Kit (version > 7.0)
- Java IDE (Intellij, Netbeans, or Eclipse)
- Gradle

```

What things you need to include in project :

```
Libraries :
- Weka (www.cs.waikato.ac.nz/ml/weka/) - [library for Machine Learning]
```

### Installing

```
wget [https://github.com/ironlota/spamassassin/archive/master.zip](https://github.com/ironlota/spamassassin/archive/master.zip)
unzip master.zip
cd spamassassin-master
```

### How to make arff file for dataset
- Create new file in txt like this [99999 stands for infinite, it will block the edge creation in same node]

    For reduced cost matrix :
    ```
        ReducedCostMatrix

        5 X 5

        99999 20 30 10 11

        15 99999 16 4 2

        3 5 99999 2 4

        19 6 18 99999 3

        16 4 7 16 99999
    ```

    For completed tour :
    ```
        CompletedTour

        4 X 4

        99999 12 10 5

        12 99999 9 8

        10 9 99999 15

        5 8 15 99999
    ```

- Save as txt file in directory "./data/testcase"
- Then, open MainClassForm.java
- Find this code on line 82 [0 for Reduce Cost Matrix and 1 for Completed Tour]
```
 [line 82] typeOfGraph.setSelectedIndex(1); // change 0 or 1
```
- Change this code too if you make new file in directory "./data/testcase"
```
 [line 95]   if (typeOfGraph.getSelectedItem().equals("Reduced Cost Matrix")) {
 [line 96]        selectedFile = new File("./data/testcase/rcm_tc3.txt"); // change the path if you choose 0
 [line 97]        graphParser = new ReducedCostMatrix(selectedFile);
 [line 98]      } else {
 [line 99]        selectedFile = new File("./data/testcase/ct_tc4.txt"); // change the path if you choose 1
 [line 100]       graphParser = new CompletedTour(selectedFile);
 [line 101]  }
```

## Change Log
v.0.1 initial release
- using j48 unpruned tree
- using Indonesian training data set
- using JavaFX for GUI
- using Weka as library

## To do and Bug
- make arff converter from txt file
- show tree in GUI

## Credits
- [Weka 3 - Data Mining Open Source Machine Learning Software](www.cs.waikato.ac.nz/ml/weka/)
- [Machine Learning Recipe with Josh Gordon](https://www.youtube.com/playlist?list=PLOU2XLYxmsIIuiBfYad6rFYQU_jL2ryal)
- [Weka Text Classification for First Time & Beginner Users by Brandon Weinberg](https://www.youtube.com/watch?v=IY29uC4uem8)
- [Text Classification with Weka using a J48 Decision Tree by S0naris](https://www.youtube.com/watch?v=HrixTPMOCD4)
- [JFoenix](www.jfoenix.com)

## Version

0.1 Initial Release

## Authors

* **Ray Andrew** - [Ironlota](https://github.com/ironlota)

## License

This project is licensed under the MIT License - see the [LICENSE.MD](LICENSE.MD) file for details