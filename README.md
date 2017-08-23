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
![MainWindow](https://raw.githubusercontent.com/rayandrews/spamassassin/master/image/mainwindow.png)
![Tested](https://raw.githubusercontent.com/rayandrews/spamassassin/master/image/tested.png)
![Classifier](https://raw.githubusercontent.com/rayandrews/spamassassin/master/image/classifier.png)
![Train](https://raw.githubusercontent.com/rayandrews/spamassassin/master/image/train.png)
![ChooseFile](https://raw.githubusercontent.com/rayandrews/spamassassin/master/image/choosefile.png)

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
- Weka (www.cs.waikato.ac.nz/ml/weka/) -> [library for Machine Learning]
```

### Installing

```
wget [https://github.com/rayandrews/spamassassin/archive/master.zip](https://github.com/rayandrews/spamassassin/archive/master.zip)
unzip master.zip
cd spamassassin-master
```

### Running
```
- gradle run (if you do not want to make a jar)
- gradle jfxJar (if you want to make a jar)

Note :
Due to path issue, you have to make sure that you run the jar file on the project root.
This can be happened because "./dataset" folder remains in root folder of project.
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
- [JavaFX Gradle Plugin](https://github.com/FibreFoX/javafx-gradle-plugin)

## Version

0.1 Initial Release

## Authors

* **Ray Andrew** - [Ray Andrew](https://github.com/rayandrews)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
