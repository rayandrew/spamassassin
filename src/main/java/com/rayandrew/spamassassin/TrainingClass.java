package com.rayandrew.spamassassin;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.stemmers.IteratedLovinsStemmer;
import weka.core.stopwords.RegExpFromFile;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * Created by ironlota on 6/15/17.
 */
public class TrainingClass {
    public static Instances readDataFile(String filename) throws Exception {
        ConverterUtils.DataSource inputReader = null;
        try {
            inputReader = new ConverterUtils.DataSource(filename);
        } catch (Exception e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }

        if (inputReader == null) {
            return null;
        } else {
            return inputReader.getDataSet();
        }
    }

    public static StringToWordVector defaultStringToWordVector() {
        StringToWordVector stw = new StringToWordVector();
        stw.setIDFTransform(true);
        stw.setTFTransform(true);
        stw.setAttributeIndices("first-last");
        stw.setDebug(false);
        stw.setPeriodicPruning(-1.0);
        stw.setStemmer(new IteratedLovinsStemmer());
        stw.setStopwordsHandler(new RegExpFromFile());
        stw.setTokenizer(new WordTokenizer());
        stw.setWordsToKeep(1000);
        stw.setMinTermFreq(1);
        stw.setOutputWordCounts(true);
        return stw;
    }

    public static J48 defaultJ48() {
        J48 j48 = new J48();
        j48.setUnpruned(true);
        j48.setCollapseTree(true);
        j48.setSubtreeRaising(true);
        j48.setBatchSize("100");
        j48.setUseMDLcorrection(true);
        return j48;
    }

    public static FilteredClassifier trainData(Instances train) throws Exception {
        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(defaultStringToWordVector());
        fc.setClassifier(defaultJ48());
        fc.buildClassifier(train);
        return fc;
    }
}
