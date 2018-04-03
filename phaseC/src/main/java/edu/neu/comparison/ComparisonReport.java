package edu.neu.comparison;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class is used for generating the plagiarism report
 */
public class ComparisonReport implements Serializable{

	/**
	 * This function is used for returning the file name
	 * @return a file name which is a string
	 */
    public String getFilename1() {
        return filename1;
    }

	/**
	 * This function is used to set the name of the file
	 * @param filename1 is a string which determines the name to be
	 *                  used for the file
	 */
	public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

	/**
	 * This function is used for returning the file name
	 * @return a file name which is a string
	 */
    public String getFilename2() {
        return filename2;
    }

	/**
	 * This function is used to set the name of the file
	 * @param filename2 is a string which determines the name to be
	 *                  used for the file
	 */
    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    String filename1;
	String filename2;

	/**
	 * This function is used to return the scores generated from the plagiarism run
	 * @return the score of the plagiarism run
	 */
	public Scores getScores() {
		return scores;
	}

	/**
	 * This function is used to set the scores
	 * @param scores is the value which is to be assigned to the score variable
	 */
	public void setScores(Scores scores) {
		this.scores = scores;
	}

	Scores scores;
	
	public ComparisonReport() {
	}

	/**
	 * This constructor sets the file names and the scores
	 * @param filename1 is the name of the first file
	 * @param filename2 is the name of the second file
	 * @param scores is the value to be assigned as the score
	 */
	
	public ComparisonReport(String filename1, String filename2, Scores scores) {
		this.filename1 = filename1;
		this.filename2 = filename2;
		this.scores = scores;
	}

	/**
	 * This function is used to return all the information as a string
	 * @return the file names and the scores as a string
	 */

	public String toString() {
		return this.filename1+":"+this.filename2+"|"+scores;
	}

	/**
	 * Used for writing the data
	 * @param out is the object of ObjectOutputStream
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(filename1);
		out.writeUTF(filename2);
		out.writeObject(scores);
	}

	/**
	 * Used for reading the data
	 * @param in is the object of the ObjectInputStream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		filename1 = in.readUTF();
		filename2 = in.readUTF();
		scores = (Scores) in.readObject();
	}
	
}
